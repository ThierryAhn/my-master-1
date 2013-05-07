package hamcrest;

public enum Day{
	DIMANCHE(1),
	LUNDI(2),
	MARDI(3),
	MERCREDI(4),
	JEUDI(5),
	VENDREDI(6),
	SAMEDI(7);
	
	/* SAMEDI(1),
	DIMANCHE(2),
	LUNDI(3),
	MARDI(4),
	MERCREDI(5),
	JEUDI(6),
	VENDREDI(7);*/
	
	private final int index;
	
	private Day(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
}
