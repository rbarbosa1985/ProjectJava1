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
			System.out.println("\nNome do Cliente: " + user.getNome() + "   \t\t\t| CPF: " + user.getCpf() + " \t\t\t| Numero da Agencia: " + user.getAgencia());
		}
	}
	
}
	


