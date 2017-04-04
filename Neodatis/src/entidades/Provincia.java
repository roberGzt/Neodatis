package entidades;

public class Provincia
{
	private String nombre;
	
	public Provincia(){
		this.nombre = "Nombre provincia";
	}
	
	public Provincia(int i){
		this.nombre = "Nombre provincia" + i;
	}

	public Provincia(String nombre)
	{
		super();
		this.nombre = nombre;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	

}
