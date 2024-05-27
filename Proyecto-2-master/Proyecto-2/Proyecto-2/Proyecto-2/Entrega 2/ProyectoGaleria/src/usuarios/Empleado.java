package usuarios;


public class Empleado extends Usuario{
	
	private static final String tipo = "Empleado";
		
	public Empleado(String login, String password, int telefono, String nombre) {
		super(login, password,  nombre, telefono);
	}
	

	public String getTipo() {
		return this.tipo;
	}
}