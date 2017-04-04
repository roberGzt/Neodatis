package negocios;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.PersonaDAO;
import daos.impl.PersonaDAONeodatis;
import entidades.Domicilio;
import entidades.Persona;
import entidades.Provincia;
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
//		a.- Todas las personas cuyo nombre sea exactamente ‘Juan’.
//		b.- Todas las personas cuyo nombre comience con ‘J’, ordenados por apellido y luego por nombre.
//		c.- Todas las personas que tengan entre 25 y 30 años.
//		d.- Todas las personas que vivan en Buenos Aires.
//		e.- La cantidad de personas que no tengan domicilio cargado
		
		ArrayList<Persona> personas = instanciaPersonas();
		
		PersonaDAO pDAO = new PersonaDAONeodatis();
		
		personas.forEach(p -> pDAO.guardar(p));
		
		
	}

	private ArrayList<Persona> instanciaPersonas() {
		Provincia provBuenosAires = new Provincia("Buenos Aires");
		Provincia provSalta = new Provincia("Salta");
		Provincia provJujuy = new Provincia("Jujuy");
		Domicilio buenosAires = new Domicilio("Siempreviva", 123, "", "1232", "Una Localidad de bs as", provBuenosAires);
		Domicilio salta = new Domicilio("Saltando", 123, "", "1342", "Una Localidad salta", provSalta);
		Domicilio jujuy = new Domicilio("Jujüe", 123, "", "1342", "Una Localidad jujuy", provJujuy);
		LocalDate edadEntre25y30_1 = LocalDate.of(1992,1,1);
		LocalDate edadEntre25y30_2 = LocalDate.of(1987,1,1);
		LocalDate edadEntre25y30_3 = LocalDate.of(1990,1,1);
		LocalDate edadMenor25 = LocalDate.of(1997,1,1);
		LocalDate edadMayor30 = LocalDate.of(1997,1,1);
		
		ArrayList<Persona> personas = new ArrayList<>();
		personas.add(new Persona("Juan","Alvarez",edadEntre25y30_1,buenosAires ));		
		personas.add(new Persona("Juan","Perez",edadEntre25y30_2,buenosAires));
		personas.add(new Persona("Juana","Belladona",edadEntre25y30_3,salta));
		personas.add(new Persona("Jose","Cortez",edadMenor25,jujuy));
		personas.add(new Persona("Fernando","Pizarro",edadMayor30,null));
		return personas;
	}

	//@After
	public void tearDown() throws Exception {
		File f = new File(dbFilePath);
		if (f.exists()) f.delete();		
	}

	@Test
	public void testBorrarPersonas() {	
		
		Objects<Persona> personas = getPersonasDB();
		assertEquals(instanciaPersonas().size(), personas.size());	
		PersonaDAO pDAO= new PersonaDAONeodatis();
	    personas.forEach(p -> pDAO.borrar(p));	    
	    personas = getPersonasDB();
	    assertEquals(0, personas.size());
	    
	
		
	}

	private Objects<Persona> getPersonasDB() {
		ODB odb = null;
		Objects<Persona> personas = null;
		try {
		    //Abro la db y borro los elementos.
		    odb = ODBFactory.open(dbFilePath);
		    personas = odb.getObjects(Persona.class);	    		    
		} finally {
		    if (odb != null)
			odb.close();
		}
		return personas;
	}

}
