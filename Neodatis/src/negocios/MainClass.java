package negocios;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.impl.PersonaDAONeodatis;
import entidades.Persona;
import properties.Parametros;

public class MainClass
{

	public static void main(String[] args)
	{
		Parametros.SetearParametros();	
		
//		a.- Todas las personas cuyo nombre sea exactamente �Juan�.
		PersonaDAONeodatis daoP = new PersonaDAONeodatis();
		for (int i =0; i<10; i++){
			Persona p= new Persona (i);
			p.setNombre("Juan");
			daoP.guardar(p);
		}
		for (int i =0; i<10; i++){
			Persona p= new Persona (i);
			p.setNombre("Pepe");
			daoP.guardar(p);
		}
		
			
		ODB odb = null;
		try {
		    // Abrimos la bd
		    odb = ODBFactory.open(Parametros.getProperties().getProperty("dbPath"));
		    IQuery query = new CriteriaQuery(Persona.class, Where.equal("nombre", "Juan"));
		    Objects<Persona> personas = odb.getObjects(query);
		    personas.forEach(p -> System.out.println(p.getNombre() +" " + p.getApellido()) );
		} finally {
		    if (odb != null)
			odb.close();
		}
		
//		b.- Todas las personas cuyo nombre comience con �J�, ordenados por apellido y luego por nombre.
		
		
		
//		c.- Todas las personas que tengan entre 25 y 30 a�os.
		
		
		
//		d.- Todas las personas que vivan en Buenos Aires.
//		e.- La cantidad de personas que no tengan domicilio cargado.
		
		
		
	}

}
