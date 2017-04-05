package daos;

public interface DAO<T>
{
	public void guardar(T t);

	public void borrar(T t);
	
}
