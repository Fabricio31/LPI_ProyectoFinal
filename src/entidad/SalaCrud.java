package entidad;

public class SalaCrud 
{
	private int sala;
	private String numero;
	private int piso;
	private String capacidad;
	private String recursos;
	private byte estado;
	public int getSala() {
		return sala;
	}
	public void setSala(int sala) {
		this.sala = sala;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public String getRecursos() {
		return recursos;
	}
	public void setRecursos(String recursos) {
		this.recursos = recursos;
	}
	public Byte getEstado() {
		return estado;
	}
	public void setEstado(Byte estado) {
		this.estado = estado;
	}
	

}
