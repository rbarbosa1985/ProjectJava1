package pessoal;

public abstract class Funcionario extends Usuario {
	
	private String matricula;

	public Funcionario() {
		super();
	}
		
	public Funcionario(String agencia, String nome, String cpf, String categoria, int senha, String matricula) {
		super(agencia, nome, cpf, categoria, senha);
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


}




