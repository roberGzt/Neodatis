package daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import org.neodatis.odb.Objects;

import daos.DAO;
import properties.Parametros;

public class DAONeodatis<T> implements DAO<T>
{
	private ODB odb;

	public void guardar(T t)
	{
		odb = null;
		try
		{
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
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

	public void borrar(T t)
	{
		odb = null;
		try
		{
			// Abrimos la bd
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			Objects<T> objects = odb.getObjects(t.getClass());

			objects.forEach(o -> {
				if (t.equals((T) o))
					odb.delete(o);
			});

			// Guardamos los cambios
			odb.commit();
		}
		finally
		{
			if (odb != null)
				odb.close();
		}
	}

}
