package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.impl.PersonaDAONeodatis;
import entidades.Domicilio;
import entidades.Persona;
import entidades.Provincia;
import properties.Parametros;

public class QueriesTest
{
	private static String dbFilePath;
	private static PersonaDAONeodatis pDAO;

	@BeforeClass
	public static void setUpClass(){
		pDAO = new PersonaDAONeodatis();
		dbFilePath = Parametros.getProperties().getProperty(Parametros.dbPath);
	}
	
	@Before
	public void setUp() throws Exception
	{
		
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
		agregarDatosDePrueba();
	}

	// @After
	public void tearDown() throws Exception
	{
		File f = new File(dbFilePath);
		if (f.exists())
			f.delete();
	}

	@Test
	public void testBorrarPersonas()
	{
		IQuery query = new CriteriaQuery(Persona.class);
		Objects<Persona> personas = pDAO.consultar(query);
		assertEquals(instanciaPersonas().size(), personas.size());
		personas.forEach(p -> pDAO.borrar(p));
		personas = pDAO.consultar(new CriteriaQuery(Persona.class));
		assertEquals(0, personas.size());
	}

	// a.- Todas las personas cuyo nombre sea exactamente ‘Juan’.
	@Test
	public void testPersonasConNombreJuan()
	{
		IQuery query = new CriteriaQuery(Persona.class, Where.equal("nombre", "Juan"));
		Objects<Persona> personas = pDAO.consultar(query);
		
		assertEquals(2, personas.size());
	}

	// b.- Todas las personas cuyo nombre comience con ‘J’, ordenados por
	// apellido y luego por nombre.
	@Test
	public void testPersonasConNombreEmpezadoEnJ()
	{
		IQuery query = new CriteriaQuery(Persona.class, Where.like("nombre", "J%")).orderByAsc("apellido,nombre");
		Objects<Persona> resultadoQuery = pDAO.consultar(query);

		assertEquals(4, resultadoQuery.size());
		ArrayList<String> personas = new ArrayList<>();
		resultadoQuery.forEach(p -> personas.add(p.getApellido()));
		assertEquals("Alvarez", personas.get(0));
		assertEquals("Belladona", personas.get(1));
		assertEquals("Cortez", personas.get(2));
		assertEquals("Perez", personas.get(3));

	}

	// c.- Todas las personas que tengan entre 25 y 30 años.
	@Test
	public void testPersonasConEdadEntre25y30()
	{
		IQuery query = new CriteriaQuery(Persona.class, Where.and().add(Where.ge("edad", 25)).add(Where.le("edad", 30)));
		Objects<Persona> resultadoQuery = pDAO.consultar(query);

		assertEquals(3, resultadoQuery.size());
		ArrayList<String> personas = new ArrayList<>();
		resultadoQuery.forEach(p -> personas.add(p.getApellido()));
		assertTrue(personas.contains("Alvarez"));
		assertTrue(personas.contains("Perez"));
		assertTrue(personas.contains("Belladona"));

	}

	// d.- Todas las personas que vivan en Buenos Aires.
	@SuppressWarnings("serial")
	@Test
	public void testPersonasQueVivenEnBsAs()
	{
		IQuery query = new SimpleNativeQuery(){
			@SuppressWarnings("unused")
			public boolean match(Persona p){
				return p.getDomicilio().getProvincia().getNombre().toLowerCase().startsWith("buenos aires");
			}
		};
		Objects<Persona> resultadoQuery = pDAO.consultar(query);

		assertEquals(2, resultadoQuery.size());
		ArrayList<String> personas = new ArrayList<>();
		resultadoQuery.forEach(p -> personas.add(p.getApellido()));
		assertTrue(personas.contains("Alvarez"));
		assertTrue(personas.contains("Perez"));		

	}

	// e.- La cantidad de personas que no tengan domicilio cargado
	@Test
	public void testPersonasSinDomicilio()
	{
		IQuery query = new CriteriaQuery(Persona.class, Where.isNull("domicilio"));
		Objects<Persona> resultadoQuery = pDAO.consultar(query);

		assertEquals(1, resultadoQuery.size());
		ArrayList<String> personas = new ArrayList<>();
		resultadoQuery.forEach(p -> personas.add(p.getApellido()));
		assertTrue(personas.contains("Pizarro"));
				

	}

	private void agregarDatosDePrueba()
	{
		// a.- Todas las personas cuyo nombre sea exactamente ‘Juan’.
		// b.- Todas las personas cuyo nombre comience con ‘J’, ordenados por
		// apellido y luego por nombre.
		// c.- Todas las personas que tengan entre 25 y 30 años.
		// d.- Todas las personas que vivan en Buenos Aires.
		// e.- La cantidad de personas que no tengan domicilio cargado

		ArrayList<Persona> personas = instanciaPersonas();

		personas.forEach(p -> pDAO.guardar(p));

	}

	private ArrayList<Persona> instanciaPersonas()
	{
		Provincia provBuenosAires = new Provincia("Buenos Aires");
		Provincia provSalta = new Provincia("Salta");
		Provincia provJujuy = new Provincia("Jujuy");
		Domicilio buenosAires = new Domicilio("Siempreviva", 123, "", "1232", "Una Localidad de bs as", provBuenosAires);
		Domicilio salta = new Domicilio("Saltando", 123, "", "1342", "Una Localidad salta", provSalta);
		Domicilio jujuy = new Domicilio("Jujüe", 123, "", "1342", "Una Localidad jujuy", provJujuy);
		LocalDate edadEntre25y30_1 = LocalDate.of(1992, 1, 1);
		LocalDate edadEntre25y30_2 = LocalDate.of(1987, 1, 1);
		LocalDate edadEntre25y30_3 = LocalDate.of(1990, 1, 1);
		LocalDate edadMenor25 = LocalDate.of(1997, 1, 1);
		LocalDate edadMayor30 = LocalDate.of(1997, 1, 1);

		ArrayList<Persona> personas = new ArrayList<>();
		personas.add(new Persona("Juan", "Alvarez", edadEntre25y30_1, buenosAires));
		personas.add(new Persona("Juan", "Perez", edadEntre25y30_2, buenosAires));
		personas.add(new Persona("Juana", "Belladona", edadEntre25y30_3, salta));
		personas.add(new Persona("Jose", "Cortez", edadMenor25, jujuy));
		personas.add(new Persona("Fernando", "Pizarro", edadMayor30, null));
		return personas;
	}
}
