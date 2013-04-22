package token_vehicule;

/**
 * Interface FacadeAdmin
 * @author ahounfol
 * @date 16/10/12
 *
 */
public interface FacadeAdmin {
	public void addVehicule(String name, int price, String wdoc, String pdoc);
}

/**
 * Implementation of the interface FacadeAdmin
 * @author ahounfol
 * @date 16/10/12
 *
 */
class FacadeAdminImpl implements FacadeAdmin{

	/**
	 * Add a vehicule in the database
	 * @param name the name of the vehicule
	 * @param price the price of the vehicule
	 * @param wdoc the web description of the vehicule
	 * @parm pdoc the text description for printing
	 */
	public void addVehicule(String name, int price, String wdoc, String pdoc) {
		if(DatabaseImpl.getContext().getVehicule(name) == null){
			Vehicule vehicule = new VehiculeImpl(name, price, wdoc, pdoc);
			DatabaseImpl.getContext().addVehicule(vehicule);
		}
		
	}
	
}