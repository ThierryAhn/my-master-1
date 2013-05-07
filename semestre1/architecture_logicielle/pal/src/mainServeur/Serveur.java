package mainServeur;


import HTTPServer.HTTPServer;
import HTTPServer.IHTTPListener;
import HTTPServer.IHTTPQuery;
import HTTPServer.IHTTPResponse;
import device.Device;
import interfaceHtml.HtmlConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;

import patronProxy.IConfig;
import patronProxy.ProxyConfig;
import patronVisiteur.IVisiteurConfig;
import patronVisiteur.VisiteurConfig;


class MyDeviceListener implements IHTTPListener {

	//attributs
	
    private Device device;
    private IConfig config;
    private IVisiteurConfig visiteurConfig;
    private HtmlConfig pageHtml;
    
    //constructeur
    
    public MyDeviceListener() {
        device = new Device();
        visiteurConfig = new VisiteurConfig(device);
        config = new ProxyConfig();
        pageHtml = new HtmlConfig(visiteurConfig, config);
    }

    //fonction
    
    public static String getMimeType(String fileUrl)
            throws java.io.IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(fileUrl);
    }

    //methode
    
    @SuppressWarnings("unchecked")
	public void listen(IHTTPQuery q, IHTTPResponse r) {
        String path = q.getPath();
        String[] bits = path.split("[.]");
        OutputStream os = r.getOutputStream();

        if ((bits.length > 1)) {
            try {
                r.setMIME(getMimeType(bits[1]));

                InputStream is = new FileInputStream(
                        getClass().getClassLoader().getResource(
                        "." + path).getFile());
                final int bufSize = 1024;
                byte[] buf = new byte[bufSize];

                int read;
                do {
                    read = is.read(buf);
                    os.write(buf);
                } while (read == bufSize);
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            r.setMIME("text/html");
            if (path.matches("/setvalue") && q.getParams() != null) {
                	for (Map.Entry map : q.getParams().entrySet()) {
    	                if (device.getSettingValue((String) map.getKey()) != null) {
    	                	pageHtml.AffichageModification(os);
    	                    device.setSettingValue((String) map.getKey(), (String) map.getValue());
    	                    
    	                }
                	} 
            } else {
            	try {
                    path = java.net.URLDecoder.decode(path, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    ex.getMessage();
                }
                pageHtml.AffichageInitial(os, path);
        }   
        }
    }
}

public class Serveur {
    public static void main(String args[]) throws IOException {
        HTTPServer server = HTTPServer.getServer(4444, true);
        server.run(new MyDeviceListener());
    }
}
