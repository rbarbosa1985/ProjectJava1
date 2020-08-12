package pessoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import conta.ContaBase;
import utils.Leitora;
import utils.Menu;

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
 
	//Quantidade de contas na agencia
	public int qtdContas() throws IOException 
	{
		int count = 0;
		
	    //Lista de Contas
	    List<ContaBase> contas = new ArrayList<>();
	    		
		//Atribuo os valores do arquivo na minha lista.
		contas = Leitora.leitorConta();
		
		for (ContaBase conta : contas) {
			
			if (this.getAgenciaGestao() == conta.getAgencia())
			{
				count++;
			}
		}	
		
		return count;
	}
}
