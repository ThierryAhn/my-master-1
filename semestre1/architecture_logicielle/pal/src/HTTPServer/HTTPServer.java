package HTTPServer;

import java.util.*;
import java.net.*;
import java.io.*;

public class HTTPServer {

    private ServerSocket serverSocket;
    private IHTTPListener listener;
    private boolean verbose;

    private HTTPServer(ServerSocket ss, boolean verbose) {
        serverSocket = ss;
        this.verbose = verbose;
    }

    public static HTTPServer getServer(int port, boolean verbose) throws IOException {
        ServerSocket ss;
        try {
            if (verbose) {
                System.out.println("HTTPServer : starting server on port: " + port);
            }
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("HTTPServer : Could not listen on port: " + port);
            throw e;
        }
        return new HTTPServer(ss, verbose);
    }

    private void processRequest() throws IOException {
        Socket clientSocket = null;
        try {
            if (verbose) {
                System.out.println("HTTPServer : Waiting for request...");
            }
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("HTTPServer : Accept failed.");
            throw e;
        }

        // Read the query :
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine = in.readLine();

        if (verbose) {
            System.out.println("HTTPServer : Query : " + inputLine);
        }

        if (inputLine != null) {
            // Split the first line :
            String[] bits = inputLine.split(" ");
            /*		System.out.println(inputLine);
             for (String s : bits) {
             System.out.println("\t"+s);					
             }
             */
            HTTPResponse r = new HTTPResponse();
            if (bits[0].equals("GET")) {
                // Split the path from the parameters :
                bits = bits[1].split("[?]");

                String paramStr = (bits.length > 1) ? bits[1] : null;
                Map<String, String> params = null;

                if (paramStr != null) {
                    params = new HashMap<String, String>();

                    String[] paramPairs = paramStr.split("[&]");
                    for (String aPair : paramPairs) {
                        String[] pair = aPair.split("[=]");
                        params.put(
                                java.net.URLDecoder.decode(pair[0], "UTF-8"),
                                java.net.URLDecoder.decode(pair[1], "UTF-8"));
                    }
                }

                HTTPQuery q = new HTTPQuery(bits[0], params);
                System.out.println(inputLine);
                for (String s : bits) {
                    System.out.println("\t" + s);
                }
                listener.listen(q, r);
            } else {
                PrintWriter out = new PrintWriter(r.getOutputStream());
                r.setMIME("text/html");
                out.println("<html><body>Erreur ! Requête invalide :<br>"
                        + inputLine + "</body></html>");
                if (verbose) {
                    System.out.println("HTTPServer : Method : " + bits[0] + " not supported. An error page was sent to the user agent.");
                }
            }
            // Reading the rest ?
	/*			while ( !(inputLine = in.readLine()).equals("") ) {
             String[] bits=inputLine.split(" ");
             System.out.println(inputLine);
             for (String s : bits) {
             System.out.println("\t"+s);					
             }
             }
             */
            OutputStream os = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);
            out.println("HTTP/1.1 200 OK");
            out.println("connection: close");
            out.println("Content-Type: " + r.getMIME() + "; charset=UTF-8");
            out.println();
            out.flush();

            // Output the response :
            ((ByteArrayOutputStream) r.getOutputStream()).writeTo(os);
            // I know, it is bad !

            os.flush();

            System.out.println("Closing stream ...");
            out.close();
        } // if (inputLine != null)

        System.out.println("Closing socket ...");
        clientSocket.close();

        if (verbose) {
            System.out.println("HTTPServer : response sent.\n\n");
        }
    }

    public void run(IHTTPListener l) throws IOException {
        assert (l != null);
        listener = l;
        while (true) {
            processRequest();
        }
    }
}
