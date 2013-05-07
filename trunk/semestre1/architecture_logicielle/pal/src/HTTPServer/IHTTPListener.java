package HTTPServer;

public interface IHTTPListener {
	public void listen(IHTTPQuery q, IHTTPResponse r);
}
