package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Persona
{
	private String nombre;
	private String apellido;
	private LocalDate fechaDeNacimiento;
	private Domicilio domicilio;
	
	public Persona(){
		this.nombre = "nombre";
		this.apellido = "apellido";
		this.fechaDeNacimiento = LocalDate.now();
		this.domicilio = new Domicilio();
	}
	
	public Persona(int i){
		this.nombre = "nombre "+ i;
		this.apellido = "apellido"+ i;
		this.fechaDeNacimiento = LocalDate.now();
		this.domicilio = new Domicilio(i);
		
	}

	public Persona(String nombre, String apellido, LocalDate fechaDeNacimiento, Domicilio domicilio)
	{
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.domicilio = domicilio;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getApellido()
	{
		return apellido;
	}

	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}

	public LocalDate getFechaDeNacimiento()
	{
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento)
	{
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Domicilio getDomicilio()
	{
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio)
	{
		this.domicilio = domicilio;
	}
	
	public int getEdad(){		
		return LocalDate.now().getYear() - fechaDeNacimiento.getYear();
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + " " + fechaDeNacimiento.format(DateTimeFormatter.ISO_LOCAL_DATE)
				+ " " + domicilio + " Edad:" + getEdad();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((fechaDeNacimiento == null) ? 0 : fechaDeNacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Persona other = (Persona) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (fechaDeNacimiento == null) {
			if (other.fechaDeNacimiento != null)
				return false;
		} else if (!fechaDeNacimiento.equals(other.fechaDeNacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
