package snippets;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Ejemplo1Guardar {
    public static void main(String[] args) {
		ODB odb = null;
		try
		{
			// Creamos la instancia
			Cliente c = new Cliente(10, "Ferreter�a Tito");
			// Abre/Crea la bd
			odb = ODBFactory.open("clase1db");
			// Guardamos el objeto
			odb.store(c);
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

}
