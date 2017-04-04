package snippets;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;

public class Ejemplo4Borrado2 {

    public static void main(String[] args) {
	ODB odb = null;
	try {
	    // Abrimos la bd
	    odb = ODBFactory.open("clase1db");
	  //creo un cliente
	    Cliente c = new Cliente(300000, "Nuevo cliente");
	    //guardo
	    OID idObjeto = odb.store(c); 
	    //mando los cambios a la base.
	    odb.commit(); 
	    //y ahora borro el objeto que ten�a ese id.
	    odb.deleteObjectWithId(idObjeto);
	} finally {
	    if (odb != null)
		odb.close();
	}
    }
}
