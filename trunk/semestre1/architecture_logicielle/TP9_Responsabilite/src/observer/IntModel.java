package observer;

class IntModel implements IIntModel {
	private int value=33;
	
	public void inc() { ++value; }
	public void dec() { --value; }
	public void random() { value=(int)(100000*Math.random()); }
	public int getValue() { return value; }
}

