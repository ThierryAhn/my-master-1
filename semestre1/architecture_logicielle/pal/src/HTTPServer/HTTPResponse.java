package HTTPServer;

import java.io.*;

public class HTTPResponse implements IHTTPResponse {

    private OutputStream os;
    private String mime = "text/plain";

    public HTTPResponse() {
        os = new ByteArrayOutputStream();
    }

    @Override
    public void setMIME(String mime) {
        this.mime = mime;
    }

    public String getMIME() {
        return mime;
    }

    @Override
    public OutputStream getOutputStream() {
        return os;
    }
}
