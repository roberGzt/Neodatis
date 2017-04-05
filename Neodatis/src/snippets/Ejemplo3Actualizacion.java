package snippets;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Ejemplo3Actualizacion {

    public static void main(String[] args) {
	ODB odb = null;
	try {
	    // Abrimos la bd
	    odb = ODBFactory.open("clase1db");
	    IQuery query = new CriteriaQuery(Cliente.class, Where.like("razonSocial", "Ferreter�a Tito"));
	    Objects<Cliente> clientes = odb.getObjects(query);

	    // Recuperamos el objeto a modificar
	    Cliente tito = clientes.getFirst();

	    // Cambiamos el cuit
	    tito.setCuit(48754821);

	    // Actualizamos
	    odb.store(tito);

	    // Guardamos los cambios
	    odb.commit();
	} finally {
	    if (odb != null)
		odb.close();
	}
    }
}
