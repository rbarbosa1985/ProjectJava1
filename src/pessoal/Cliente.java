package pessoal;

public class Cliente extends Usuario {
	
	private String numeroConta;
	
	public Cliente() {
	super();
	}
	
	public Cliente(String agencia, String nome, String cpf, String categoria, int senha, String numeroConta) {
		super(agencia, nome, cpf, categoria, senha);
		this.numeroConta = numeroConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	
 
 
}
