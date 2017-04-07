package daos.impl;

import java.util.Collection;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import daos.PersonaDAO;
import entidades.Persona;

public class PersonaDAONeodatis extends DAONeodatis<Persona> implements PersonaDAO {

	@Override
	public Collection<Persona> getPersonas() {
		IQuery query = new CriteriaQuery(Persona.class);
		Objects<Persona> personas = consultar(query);
		return personas;
	}

	@Override
	public Collection<Persona> getPersonasConNombre(String nombre) {
		IQuery query = new CriteriaQuery(Persona.class, Where.equal("nombre", nombre));
		Objects<Persona> personas = consultar(query);
		return personas;
	}

	@Override
	public Collection<Persona> getPersonasConNombreEmpezadoEn(char c) {
		IQuery query = new CriteriaQuery(Persona.class, Where.ilike("nombre", c + "%")).orderByAsc("apellido,nombre");
		Objects<Persona> personas = consultar(query);
		return personas;
	}

	@Override
	public Collection<Persona> getPersonasConEdadEntre(Integer limiteInferior, Integer limiteSuperior) {
		IQuery query = new CriteriaQuery(Persona.class,
				Where.and().add(Where.ge("edad", limiteInferior)).add(Where.le("edad", limiteSuperior)));
		Objects<Persona> personas = consultar(query);
		return personas;
	}

	@Override
	public Collection<Persona> getPersonasQueVivenEnProvincia(String provincia) {
		@SuppressWarnings("serial")
		IQuery query = new SimpleNativeQuery() {

			@SuppressWarnings("unused")
			public boolean match(Persona p) {
				return p.getDomicilio().getProvincia().getNombre().toLowerCase().equals(provincia.toLowerCase());
			}
		};
		Objects<Persona> personas = consultar(query);
		return personas;
	}

	@Override
	public Collection<Persona> getPersonasSinDomicilio() {
		IQuery query = new CriteriaQuery(Persona.class, Where.isNull("domicilio"));
		Objects<Persona> personas = consultar(query);
		return personas;
	}

}
