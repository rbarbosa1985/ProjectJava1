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
	
public static void validaUsuario() throws ArithmeticException, IOException, InterruptedException {
	
	 	Scanner sc = new Scanner(System.in);
	 	Usuario login;
	 	
	    //Lista de Usuarios do Sistema
	    List<Usuario> usuarios = new ArrayList<>();
	    		
		//Atribuo os valores do arquivo na minha lista.
		usuarios = Leitora.leitor();
		
		int i =0;
		
		boolean teste = true;
		
		while (teste)
		{
			Menu.cabecalho();
			
			System.out.println("Por favor digite suas credencias para acessar o sistema! ");
			
			System.out.print("\nDigite CPF do usuário: ");
			String cpf = sc.nextLine();
			
			System.out.print("Digite sua senha: ");
			String senha = sc.nextLine();
			
			Menu.limpaMenu();
			
			//identificando o usuário
			for (Usuario usuario : usuarios) {
				
				if ((usuario.getCpf().equals(cpf)) && (usuario.getSenha() == Integer.parseInt(senha)))
				{
					if (usuario.getCategoria().equals("Cliente")){
						Menu.menuBancario(usuario);
						teste = false;
					}
					if (usuario.getCategoria().equals("Gerente")){
						login = new Gerente();
						login = usuario;
						Menu.menuNivelAcesso(usuario);
						teste = false;
					}
					if (usuario.getCategoria().equals("Diretor")){
						login = new Diretor();
						login = usuario;
						Menu.menuNivelAcesso(usuario);
						teste = false;
					}
					if (usuario.getCategoria().equals("Presidente")){
						login = new Presidente();
						login = usuario;
						Menu.menuNivelAcesso(usuario);
						teste = false;
					}
				}
			}
			if (teste)
			{
				System.out.println("Usuario ou senha incorretos... \n\n");
				i++;
				if (i>2)
				{
					System.out.println("Numero de tentativas excedidas... \n\n");
					break;
				}
			}		
		}
	}
}

