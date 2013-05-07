package policy;

import java.util.HashSet;

/**
 * Class DenyFirst
 * @author ahounfol
 * @date 20/11/12
 *
 */
public class DenyFirst implements IPolicy{
	
	/**
	 * Contains the differents rules
	 */
	HashSet<String> rules;
	
	/**
	 * Constructor with no parameters.
	 */
	public DenyFirst() {
		rules = new HashSet<String>();
	}

	@Override
	public void allow(String rule) {
		// TODO Auto-generated method stub
		if(!rules.contains(rule))
			rules.add(rule);
	}

	@Override
	public void deny(String rule) {
		// TODO Auto-generated method stub
		if(rules.contains(rule))
			rules.remove(rule);
	}

	@Override
	public boolean isAllowed(String rule) {
		// TODO Auto-generated method stub
		return rules.contains(rule);
	}

}
