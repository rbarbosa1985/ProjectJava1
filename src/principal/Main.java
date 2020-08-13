package principal;

import java.io.IOException;

import Exceptions.Exceptions;
import pessoal.Usuario;
import utils.Menu;
import utils.ValidaUsuario;

public class Main {


	public static void main(String[] args) throws ArithmeticException, IOException, InterruptedException 
	{	
		boolean teste = true;
		
			while (teste)
			{
				Usuario login;
				ValidaUsuario valida = new ValidaUsuario();
				Menu bank = new Menu();
				
				login = valida.validaUsuario();
				
				if (login != null)
				{
					if (login.getCategoria().equals("Cliente"))
					{
						teste = bank.menuBancario(login);
					}
					if (login.getCategoria().equals("Gerente"))
					{
						int opcao = bank.menuNivelAcesso(login);
						if (opcao == 0)
						{
							teste = bank.menuBancario(login);
						}
						else if (opcao == 1)
						{
							teste = bank.menuGerente(login);
						}
						else if (opcao == 2)
						{
							teste = true;
						}
						else if (opcao == 3)
						{
							teste = false;
						}
					}
					if (login.getCategoria().equals("Diretor"))
					{
						int opcao = bank.menuNivelAcesso(login);
						if (opcao == 0)
						{
							teste = bank.menuBancario(login);
						}
						else if (opcao == 1)
						{
							teste = bank.menuDiretor(login);
						}
						else if (opcao == 2)
						{
							teste = true;
						}
						else if (opcao == 3)
						{
							teste = false;
						}
					}
					if (login.getCategoria().equals("Presidente"))
					{
						int opcao = bank.menuNivelAcesso(login);
						if (opcao == 0)
						{
							teste = bank.menuBancario(login);
						}
						else if (opcao == 1)
						{
							teste = bank.menuPresidente(login);
						}
						else if (opcao == 2)
						{
							teste = true;
						}
						else if (opcao == 3)
						{
							teste = false;
						}
					}	
				}
				else
					break;
			}
			

	}

}
