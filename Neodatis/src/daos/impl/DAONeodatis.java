package daos.impl;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import daos.DAO;
import properties.Parametros;

public class DAONeodatis<T> implements DAO<T>
{
	private ODB odb;

	@Override
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

	@Override
	public void borrar(T t)
	{
		odb = null;
		try
		{
			// Abrimos la bd
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			Objects<T> objects = odb.getObjects(t.getClass());

			objects.forEach(o -> {
				if (t.equals(o))
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
	
	public  Objects<T> consultar(IQuery query){
		ODB odb = null;
		Objects<T> resultadoQuery = null;
		try
		{
			// Abrimos la bd
			odb = ODBFactory.open(Parametros.getProperties().getProperty(Parametros.dbPath));
			resultadoQuery = odb.getObjects(query);			
		}
		finally
		{
			if (odb != null)
				odb.close();
		}
		
		return resultadoQuery;
		
	}

}
