package daos;

import java.util.Collection;

import entidades.Persona;

public interface PersonaDAO extends DAO<Persona>
{
	public Collection<Persona> getPersonas();	
	public Collection<Persona> getPersonasConNombre(String nombre);
	public Collection<Persona> getPersonasConNombreEmpezadoEn(char c);
	public Collection<Persona> getPersonasConEdadEntre(Integer limiteInferior, Integer limiteSuperior);
	public Collection<Persona> getPersonasQueVivenEnProvincia(String provinvcia);
	public Collection<Persona> getPersonasSinDomicilio();
}
