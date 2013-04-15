package discovery;

import java.util.List;

public interface RepositoryMBean {
	public List<String> listServicesAvailable();
	public boolean addRepository(String url);
	public boolean removeRepository(String url);
	public String getExposedURL();
}
