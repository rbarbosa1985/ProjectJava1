package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pessoal.Cliente;
import pessoal.Diretor;
import pessoal.Gerente;
import pessoal.Presidente;
import pessoal.Usuario;	
import utils.Menu;

public class ValidaUsuario {
	
public Usuario validaUsuario() throws ArithmeticException, IOException, InterruptedException {
	
	 	Scanner sc = new Scanner(System.in);
	 	
	    //Lista de Usuarios do Sistema
	    List<Usuario> usuarios = new ArrayList<>();
	    		
		//Atribuo os valores do arquivo na minha lista.
		usuarios = Leitora.leitor();
		
		Usuario login = null;
		
		int i =0;
		
		boolean teste = true;
		
		while (teste)
		{
			Menu.limpaMenu();
			Menu.cabecalho();
			
			System.out.println("Por favor digite suas credencias para acessar o sistema! ");
			
			System.out.print("\nDigite CPF do usuário: ");
			String cpf = sc.nextLine();
			
			System.out.print("Digite sua senha: ");
			String senha = sc.nextLine();
			
			
			
			//identificando o usuário
			for (Usuario usuario : usuarios) {
				
				if ((usuario.getCpf().equals(cpf)) && (usuario.getSenha() == Integer.parseInt(senha)))
				{
					if (usuario.getCategoria().equals("Cliente")){
						login = new Cliente();
						login = usuario;
						teste = false;
					}
					if (usuario.getCategoria().equals("Gerente")){
						login = new Gerente();
						login = usuario;
						teste = false;
					}
					if (usuario.getCategoria().equals("Diretor")){
						login = new Diretor();
						login = usuario;
						teste = false;
					}
					if (usuario.getCategoria().equals("Presidente")){
						login = new Presidente();
						login = usuario;
						teste = false;
					}
				}
			}
			if (teste)
			{
				System.out.println("\nUsuario ou senha incorretos... \n\n");
				System.out.println("\nVocê tem " + (2-i) + " tentativas. \n\n");
				Thread.sleep(2000);
				i++;
				if (i>2)
				{
					System.out.println("Numero de tentativas excedidas... \n\n");
					Thread.sleep(2000);
					break;
				}
			}		
		}
		
		return login;
		
	}
}

