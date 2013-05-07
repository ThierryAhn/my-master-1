package HTTPServer;
import java.util.Map;

public interface IHTTPQuery {
	public String getPath();
	public Map<String, String> getParams();
}
