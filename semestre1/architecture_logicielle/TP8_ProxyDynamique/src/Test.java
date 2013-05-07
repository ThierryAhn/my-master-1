import policy.DenyFirst;
import policy.IPolicy;
import policy.PolicyProxy;
import employee.Employee;
import employee.IEmployee;

public class Test {
	
	public static String whoAmI(Object object){
		String s = "";
		s+="--------\nNom : " +object.getClass().getName() +"\n";
		for(Class<?> i : object.getClass().getInterfaces()){
			s+= i.getName() + "\n";
		}
		s+="--------\n";
		
		return s;
	}
	
	public static void main(String[] args) {
		// Partie serveur :
		IEmployee e=new Employee("Marcel-Paul Schutzenberger", "Universite de Paris VII", "0235020202", "1729999999999", "Dep. Mathematiques", 1697);
		IPolicy pol=new DenyFirst();
		pol.allow("getName");
		pol.allow("getAddress");

		// IEmployee returned_employee=(IEmployee)PolicyProxy.newInstance(e, pol, new Class[] { IEmployee.class } );
		
		IEmployee returned_employee=(IEmployee)PolicyProxy.newInstance(e, pol);
		
		System.out.println(whoAmI(returned_employee));
		
		// Partie cliente :
		System.out.println("Nom de l'employe : "+returned_employee.getName());
		System.out.println("Adresse de l'employe : "+returned_employee.getAddress());
		System.out.println("Numero de telephone de l'employe : "+returned_employee.getPhoneNumber());
	}
}

