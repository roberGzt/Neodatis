package entidades;

import java.util.Date;

public class Persona
{
	private String nombre;
	private String apellido;
	private Date fechaDeNacimiento;
	private Domicilio domicilio;
	
	public Persona(){
		this.nombre = "nombre";
		this.apellido = "apellido";
		this.fechaDeNacimiento = new Date();
		this.domicilio = new Domicilio();
	}
	
	public Persona(int i){
		this.nombre = "nombre "+ i;
		this.apellido = "apellido"+ i;
		this.fechaDeNacimiento = new Date();
		this.domicilio = new Domicilio(i);
		
	}

	public Persona(String nombre, String apellido, Date fechaDeNacimiento, Domicilio domicilio)
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

	public Date getFechaDeNacimiento()
	{
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento)
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
	

}
