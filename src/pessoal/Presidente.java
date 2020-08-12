package pessoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import conta.ContaBase;
import utils.Leitora;

public class Presidente extends Funcionario {
	
	public Presidente(String agencia, String nome, String cpf, String categoria, int senha, String matricula) 
	{
		super(agencia, nome, cpf, categoria, senha, matricula);
	}

	public Presidente () 
	{
		super();
	}
	
	public double capital() throws IOException {
		double valor = 0.0;
		
	    //Lista de Contas
	    List<ContaBase> contas = new ArrayList<>();
	    		
		//Atribuo os valores do arquivo na minha lista.
		contas = Leitora.leitorConta();
		
		for (ContaBase conta : contas) {
			
			valor += conta.getSaldo();
		}	
		
		return valor;
	}
	
}
// relatorio capital total no banco