package entidades;

public class Domicilio
{
	private String calle;
	private Integer numero;
	private String depto;
	private String codPostal;
	private String localidad;
	private Provincia provincia;
	
	public Domicilio(){
		this.calle = "calle";
		this.numero = 123;
		this.depto = "depto";
		this.codPostal = "ABC123";
		this.localidad = "Localidad";
		this.provincia = new Provincia();
		
		
	}
	
	public Domicilio(int i){
		this.calle = "calle " + i;
		this.numero = 1;
		this.depto = "depto " +i;
		this.codPostal = "ABC"+i;
		this.localidad = "Localidad "+i;
		this.provincia = new Provincia(i);
		
		
	}

	public Domicilio(String calle, Integer numero, String depto, String codPostal, String localidad, Provincia provincia)
	{
		super();
		this.calle = calle;
		this.numero = numero;
		this.depto = depto;
		this.codPostal = codPostal;
		this.localidad = localidad;
		this.provincia = provincia;
	}

	public String getCalle()
	{
		return calle;
	}

	public void setCalle(String calle)
	{
		this.calle = calle;
	}

	public Integer getNumero()
	{
		return numero;
	}

	public void setNumero(Integer numero)
	{
		this.numero = numero;
	}

	public String getDepto()
	{
		return depto;
	}

	public void setDepto(String depto)
	{
		this.depto = depto;
	}

	public String getCodPostal()
	{
		return codPostal;
	}

	public void setCodPostal(String codPostal)
	{
		this.codPostal = codPostal;
	}

	public String getLocalidad()
	{
		return localidad;
	}

	public void setLocalidad(String localidad)
	{
		this.localidad = localidad;
	}

	public Provincia getProvincia()
	{
		return provincia;
	}

	public void setProvincia(Provincia provincia)
	{
		this.provincia = provincia;
	}
	
	
	

}
