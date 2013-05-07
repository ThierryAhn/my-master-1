package HTTPServer;

import java.util.*;

public class HTTPQuery implements IHTTPQuery {

    private String Path;
    private Map<String, String> Params;

    public HTTPQuery(String pth, Map<String, String> prm) {
        Path = pth;
        Params = prm;
    }

    @Override
    public String getPath() {
        return Path;
    }

    @Override
    public Map<String, String> getParams() {
        return Params;
    }
}
