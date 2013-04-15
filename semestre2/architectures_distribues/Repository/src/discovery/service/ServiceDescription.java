package discovery.service;

import javax.xml.namespace.QName;

/**
 * Décrit les informations nécessaire pour publier et se connecter à un service
 */
public class ServiceDescription {

	/**
	 * Url où est publié le service
	 */
	private String url;

	/**
	 * ServiceName du service
	 */
	private QName serviceName;
	
	/**
	 * PortName du service
	 */
	private QName portName;
	
	public ServiceDescription(){}
	
	public QName getServiceName() {
		return serviceName;
	}

	public void setServiceName(QName serviceName) {
		this.serviceName = serviceName;
	}

	public QName getPortName() {
		return portName;
	}

	public void setPortName(QName portName) {
		this.portName = portName;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
