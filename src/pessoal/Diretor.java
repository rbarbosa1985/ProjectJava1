package pessoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.Leitora;

public class Diretor extends Funcionario {

	public Diretor() {
		super();
		}

	public Diretor(String agencia, String nome, String cpf, String categoria, int senha, String matricula) {
		super(agencia, nome, cpf, categoria, senha, matricula);
		
	
	}
	
	public void consultaContas() throws IOException {	
	    //Criando a Lista de Usuarios
		List<Usuario> usuarios = new ArrayList<>();
	    
		//Atribuo os valores do arquivo na minha lista.
		usuarios = Leitora.leitor();
	
		Collections.sort(usuarios);
		
		for (Usuario user : usuarios)
		{
			System.out.println("Nome do Cliente: " + user.getNome() + " CPF: " + user.getCpf() + " Numero da Conta: " + user.getAgencia());
		}
	}
	
}
	


