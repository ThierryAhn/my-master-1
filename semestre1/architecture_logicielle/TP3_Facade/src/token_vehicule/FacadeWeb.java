package token_vehicule;

/**
 * Interface FacadeAdmin
 * @author ahounfol
 * @date 16/10/12
 *
 */
public interface FacadeWeb {
	public String findVehicule(String name);
	public String findVehiculeByPrice(int lo, int hi);
	public String orderVehiculeDoc(String name, String address);
}

/**
 * Implementation of the interface FacadeWeb
 * @author ahounfol
 * @date 16/10/12
 *
 */
class FacadeWebImpl implements FacadeWeb{

	/**
	 * Return a web description of the vehicule if find.
	 * @param name name of the vehicule
	 */
	public String findVehicule(String name) {
		if(DatabaseImpl.getContext().getVehicule(name) != null){
			return "<html><body>\n" + 
			DatabaseImpl.getContext().getVehicule(name).getWebDescription() +"\n" +
			"</body></html>";
		}
		
		return "<html><body>\n" + "Véhicule " +name + " non trouvé\n" + "</body></html>\n";
	}

	
	public String findVehiculeByPrice(int lo, int hi) {
		String s = "<html><body>\n";
		for(Vehicule vehicule : DatabaseImpl.getContext().getVehiculesByPrice(lo, hi)){
			s += vehicule.getName() + "\n";
		}
		s += "</body></html>\n";
		
		return s;
	}

	@Override
	public String orderVehiculeDoc(String name, String address) {
		
		if (DatabaseImpl.getContext().getVehicule(name) == null) {
			return "<html><body>Véhicule " +name + " non trouvé\n</body></html>";
		}
		
		String print = DatabaseImpl.getContext().getVehicule(name).getPrintableDocument();
		int printId = PrinterImpl.getDefaultPrinter().print(print);
		
		Mailing mail = new MailingImpl();
		
		String webPage = "<html><body>\n" + mail.send(printId, address) + 
		"\n</body></html>";
		
		return webPage;
	}


	
}