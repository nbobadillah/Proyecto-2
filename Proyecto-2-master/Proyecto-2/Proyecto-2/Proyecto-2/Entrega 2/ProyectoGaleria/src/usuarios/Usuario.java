package usuarios;

	abstract public class Usuario {
	private String login;
	private String password;
	private String nombre;
	private int telefono;

	
	
	public Usuario(String login, String password, String nombre, int telefono) {
		
		this.login = login;
		
		this.password = password;
		
		this.nombre = nombre;
		
		this.telefono = telefono;

	}



	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTelefono() {
		return this.telefono;
	} 

	public abstract String getTipo();

	}


