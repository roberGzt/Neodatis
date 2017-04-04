package daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import daos.DAO;

public class DAONeodatis<T> implements DAO<T>{

	
	public void guardar(T t){
		ODB odb = null;
		try
		{
			odb = ODBFactory.open("ejercicio2db");
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
		    odb = ODBFactory.open("ejercicio2db");

		    odb.deleteObjectWithId(odb.getObjectId(t));
		    
		    // Guardamos los cambios
		    odb.commit();
		} finally {
		    if (odb != null)
			odb.close();
		}
	}

	
}
