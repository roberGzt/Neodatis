package daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import daos.DAO;
import properties.Parametros;

public class DAONeodatis<T> implements DAO<T>{

	
	public void guardar(T t){
		ODB odb = null;
		try
		{
			odb = ODBFactory.open(Parametros.getProperties().getProperty("dbPath"));
			odb.store(t);
		}
		finally
		{
			if (odb != null)
			{
				// Cerramos la bd
				odb.close();
			}
		}

	}
	public void borrar (T t){
		ODB odb = null;
		try {
		    // Abrimos la bd
		    odb = ODBFactory.open(Parametros.getProperties().getProperty("dbPath"));

		    odb.deleteObjectWithId(odb.getObjectId(t));
		    
		    // Guardamos los cambios
		    odb.commit();
		} finally {
		    if (odb != null)
			odb.close();
		}
	}

	
}
