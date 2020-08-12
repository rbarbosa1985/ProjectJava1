package pessoal;

public class Presidente extends Funcionario {
	
	public Presidente(String agencia, String nome, String cpf, String categoria, int senha, String matricula) 
	{
		super(agencia, nome, cpf, categoria, senha, matricula);
	}

	public Presidente () 
	{
		super();
	}
}
// relatorio capital total no banco