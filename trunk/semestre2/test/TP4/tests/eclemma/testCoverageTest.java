package eclemma;

import org.junit.Test;


public class testCoverageTest {
	
	@Test
	public void testMethodIf(){
		CoverageTest.methodIf(true ,true);
		CoverageTest.methodIf(false ,true);
		CoverageTest.methodIf(false ,false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMethodException(){
		CoverageTest.methodException();
	}
}
