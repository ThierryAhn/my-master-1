package utils;

public class LabelledData {
	private int cls;
	private byte[] glyph;
	
	public LabelledData(int cls, byte[] glyph) { this.cls=cls; this.glyph = glyph; }
	public int getCls() { return cls; }
	public byte[] getGlyph() {return glyph; }
}
