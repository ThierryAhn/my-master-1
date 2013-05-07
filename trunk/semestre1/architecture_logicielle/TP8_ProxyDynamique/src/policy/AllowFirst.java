package policy;

import java.util.HashSet;

/**
 * Class AllowFirst
 * @author ahounfol
 * @date 20/11/12
 *
 */
public class AllowFirst implements IPolicy{
	
	/**
	 * Contains the differents rules
	 */
	HashSet<String> rules;
	
	/**
	 * Constructor with no parameters.
	 */
	public AllowFirst(){
		rules = new HashSet<String>();
	}
	
	@Override
	public void allow(String rule) {
		// TODO Auto-generated method stub
		if(rules.contains(rule))
			rules.remove(rule);
	}

	@Override
	public void deny(String rule) {
		// TODO Auto-generated method stub
		if(!rules.contains(rule))
			rules.add(rule);
		
	}

	@Override
	public boolean isAllowed(String rule) {
		// TODO Auto-generated method stub
		return !rules.contains(rule);
	}

}
