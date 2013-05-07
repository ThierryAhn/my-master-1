package HTTPServer;
import java.io.*;

public interface IHTTPResponse {
	public void setMIME(String mime);
	public OutputStream getOutputStream();
}
