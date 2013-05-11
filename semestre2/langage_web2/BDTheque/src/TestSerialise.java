import model.DataBinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Bds;
import util.Bds.Bd;


public class TestSerialise {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Bds bds = new Bds();
		Bds.Bd bd = new Bds.Bd();
        
        // infos bd
        Bds.Bd.Informations informations = new Bds.Bd.Informations();
        informations.setTitre("titre");
       
        bd.setInformations(informations);
        
        // image bd
        Bds.Bd.Image imageBd = new Bds.Bd.Image();
        imageBd.setValue("image");
        bd.setImage(imageBd);
        bds.getBd().add(bd);
        System.out.println(DataBinding.serialisetoString(bd));
	}

}
