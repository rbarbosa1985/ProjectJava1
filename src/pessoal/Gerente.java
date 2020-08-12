package pessoal;

public class Gerente extends Funcionario {
	private int agenciaGestao;
	
	public Gerente() {
	super();
	}
		
	public Gerente(String agencia, String nome, String cpf, String categoria, int senha, String matricula, int agenciaGestao) {
		super(agencia, nome, cpf, categoria, senha, matricula);
		this.setAgenciaGestao(agenciaGestao);
	}


	public int numeroContas(int numeroContas) {
		return numeroContas;
	}

	public int getAgenciaGestao() {
		return agenciaGestao;
	}

	public void setAgenciaGestao(int agenciaGestao) {
		this.agenciaGestao = agenciaGestao;
	}
 

}
