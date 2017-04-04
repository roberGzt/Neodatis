package negocios;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import properties.Parametros;

public class QueriesTest {
	private final String dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);

	@Before
	public void setUp() throws Exception {
		File f = new File(dbFilePath);
		if (f.exists()) f.delete();		
		agregarDatosDePrueba();
	}

	private void agregarDatosDePrueba() {
		// TODO Auto-generated method stub
		
	}

	@After
	public void tearDown() throws Exception {
		File f = new File(dbFilePath);
		if (f.exists()) f.delete();		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
