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
	
	@Override
	public String toString() {
		return "Domicilio [" + calle + " " + numero + "," + depto + "," + codPostal
				+ "," + localidad + "," + provincia + "]";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((codPostal == null) ? 0 : codPostal.hashCode());
		result = prime * result + ((depto == null) ? 0 : depto.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domicilio other = (Domicilio) obj;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (codPostal == null) {
			if (other.codPostal != null)
				return false;
		} else if (!codPostal.equals(other.codPostal))
			return false;
		if (depto == null) {
			if (other.depto != null)
				return false;
		} else if (!depto.equals(other.depto))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}
	
	
	

}
