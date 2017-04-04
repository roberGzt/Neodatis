package snippets;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Ejemplo2Leer {
    public static void main(String[] args) {
	ODB odb = null;
	try {
	    odb = ODBFactory.open("clase1db");
	    // Recuperamos todos los clientes
	    Objects<Cliente> clientes = odb.getObjects(Cliente.class);
	    // y los mostramos
	    clientes.forEach(cliente -> System.out.println(cliente.getRazonSocial()));
	} finally {
	    // cerramos la bd
	    if (odb != null)
		odb.close();
	}
    }
}
