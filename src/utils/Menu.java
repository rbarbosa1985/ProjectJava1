package utils;

import java.io.IOException;
import java.util.Scanner;

import conta.ContaBase;
import pessoal.Diretor;
import pessoal.Gerente;
import pessoal.Presidente;
import pessoal.Usuario;


public class Menu {
 	static Scanner sc = new Scanner(System.in);
 	
 	
	public static void cabecalho() 
	{
		//System.out.println(":::::::::::::::::::::::::::::::: BANK PENCET ::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		 System.out.println("######################################################################################################################################");
		 System.out.println("8888888b.  8888888888 888b    888  .d8888b.  8888888888 888b    888 88888888888       888888b.          d8888 888b    888 888    d8P  ");
		 System.out.println("888   Y88b 888        8888b   888 d88P  Y88b 888        8888b   888     888           888   88b        d88888 8888b   888 888   d8P   ");
		 System.out.println("888    888 888        88888b  888 888    888 888        88888b  888     888           888  .88P       d88P888 88888b  888 888  d8P    ");
		 System.out.println("888   d88P 8888888    888Y88b 888 888        8888888    888Y88b 888     888           8888888K.      d88P 888 888Y88b 888 888d88K     ");
		 System.out.println("8888888P   888        888 Y88b888 888        888        888 Y88b888     888           888   Y88b    d88P  888 888 Y88b888 8888888b    ");
		 System.out.println("888        888        888  Y88888 888    888 888        888  Y88888     888           888    888   d88P   888 888  Y88888 888  Y88b   ");
		 System.out.println("888        888        888   Y8888 Y88b  d88P 888        888   Y8888     888           888   d88P  d8888888888 888   Y8888 888   Y88b  ");
		 System.out.println("888        8888888888 888    Y888   Y8888P   8888888888 888    Y888     888           8888888P   d88P     888 888    Y888 888    Y88b ");
		 System.out.println("######################################################################################################################################\n\n");
	}
	
	public static void menuBancario(Usuario user) throws IOException, InterruptedException 
	{
		ContaBase cc;
		ContaBase cc1;
		double valortarifa = 0.0;
		
		String path = "C:\\Projetos\\springtool\\Pencet\\";
		char opcao = '0';
		cc = ValidaConta.validaConta(user.getCpf());
		
		
		while (opcao != '7')
		{
			cabecalho();
			System.out.println(":::::::::::::::::::::::::::::::::::::::::::: MENU DE MOVIMENTA�OES BANCARIAS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Saldo");
			System.out.println("1 - Saque");
			System.out.println("2 - Deposito");
			System.out.println("3 - Tranferencia");
			System.out.println("4 - Relatorio de Tributa��o da Conta Corrente");
			System.out.println("5 - Relat�rio de Rendimento da Conta Poupan�a");
			System.out.println("6 - Desconectar-se");
			System.out.println("7 - Fechar programa");
			System.out.print("\nDigite uma op��o de acordo com o indice: ");
			opcao = sc.next().charAt(0);
			if (opcao == '0')
			{
				System.out.printf("\nSeu Saldo atual �: R$ %.2f\n", cc.getSaldo());
				limpaMenu();
			}
			if (opcao == '1')
			{
				double valor;
				System.out.print("\nDigite o valor que deseja sacar: R$ ");
				valor = sc.nextDouble();
				if (cc.sacar(valor))
				{
					valortarifa += 0.1;
				}
				Leitora.escritorExtrato(path, user, cc);
				limpaMenu();
			}
			if (opcao == '2')
			{
				double valor;
				System.out.print("\nDigite o valor que deseja depositar: R$ ");
				valor = sc.nextDouble();
				cc.depositar(valor);
				valortarifa += 0.1;
				Leitora.escritorExtrato(path, user, cc);
				limpaMenu();
			}
			if (opcao == '3')
			{
				
				double valor;
				int numeroconta = 0;
				
				
				System.out.print("\nDigite o numero da conta destino: ");
				numeroconta = sc.nextInt();
				System.out.print("\nDigite o cpf do Titular da conta destino: ");
				sc.nextLine();
				String cpf = sc.nextLine();
				cc1 = ValidaConta.validaConta(cpf);	
				
				System.out.print("\nDigite o valor que deseja transferir: R$ ");
				valor = sc.nextDouble();
				if ((numeroconta == cc1.getNumeroConta()) && (cpf.equalsIgnoreCase(cc1.getCpf())))
				{
					if (cc.transfere(cc1, valor))
					{
						valortarifa += 0.2;
					}
					Leitora.escritorExtrato(path, user, cc);
					limpaMenu();
				}
				else
				{
					System.out.println("\nDados incorretos!");
					limpaMenu();
				}
				
			}
			if (opcao == '4')
			{
				if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca"))
				{
					System.out.print("\nVoc� n�o possu� uma Conta Corrente.");
					limpaMenu();
				}
				else 
				{
					System.out.printf("\nVoc� gastou em tarifas nesta se��o: R$ %.2f", valortarifa);
					limpaMenu();
				}
				
			}
			if (opcao == '5')
			{
				double valor;
				int dias;
				
				if (cc.getTipoDeConta().equalsIgnoreCase("Corrente"))
				{
					System.out.print("\nVoc� n�o possu� uma Conta Poupan�a.");
					limpaMenu();
				}
				else 
				{
					System.out.print("\nDigite o Valor que pretende simular: ");
					valor = sc.nextDouble();
					System.out.print("\nDigite a quantidade de dias que pretende simular: ");
					dias = sc.nextInt();
					valor = (valor * ((0.10/30)*dias));
					System.out.printf("\nO valor total do rendimento �: R$ %.2f ", valor);
					limpaMenu();
				}
				
				
				
			}
			if (opcao == '6')
			{
				System.out.println("\nEfetuando o Logout!");
				limpaMenu();
				ValidaUsuario.validaUsuario();
			}
			if (opcao == '7')
			{
				System.out.println("\nSaindo do Sistema!");
				limpaMenu();
			}
	}
}
	
	public static void menuGerente(Usuario user) throws IOException, InterruptedException
	{	
		System.out.println(":::::::::::::::::::::::::::::::: MENU GERENTE ::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Imprimir o relatorio Numero de Contas da Agencia: ");
		System.out.println("1 - Desconectar-se");
		System.out.println("2 - Fechar Programa");
		System.out.print("Digite uma op��o de acordo com o indice: ");
		char opcao = sc.next().charAt(0);
			
		if (opcao == '0')
		{
			System.out.println("Quantidade de contas na sua Agencia �: " + ((Gerente)user).qtdContas());
			limpaMenu();
		}
		if (opcao == '1')
		{
			System.out.print("\nEfetuando Logout");
			limpaMenu();
		}
		if (opcao == '2')
		{
			System.out.print("\nSaindo do Sistema.");
			limpaMenu();
		}
	}
	
	public static void menuDiretor(Usuario user) throws IOException, InterruptedException
	{
		System.out.println(":::::::::::::::::::::::::::::::: MENU DIRETOR :::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Imprimir o relatorio Nome, CPF e Agencia de todos os Cliente: ");
		System.out.println("1 - Desconectar-se");
		System.out.println("2 - Fechar Programa");
		System.out.print("Digite uma op��o de acordo com o indice: ");
		char opcao = sc.next().charAt(0);
		
		if (opcao == '0')
		{
			System.out.println("Segue abaixo o Relatorio com os clientes:");
			((Diretor)user).consultaContas();
			limpaMenu();
		}
		
		if (opcao == '1')
		{
			System.out.print("\nEfetuando Logout");
			limpaMenu();
		}
		if (opcao == '2')
		{
			System.out.print("\nSaindo do Sistema.");
			limpaMenu();
		}
	}
	
	public static void menuPresidente(Usuario user) throws IOException, InterruptedException
	{
		System.out.println(":::::::::::::::::::::::::::::: MENU PRESIDENTE ::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Imprimir o relat�rio Valor Total Capital no Banco: ");
		System.out.println("1 - Desconectar-se");
		System.out.println("2 - Fechar Programa");
		System.out.print("Digite uma op��o de acordo com o indice: ");
		char opcao = sc.next().charAt(0);
		
		if (opcao == '0')
		{
			System.out.println("Quantidade de contas na sua Agencia �: " + ((Presidente)user).capital());
			limpaMenu();
		}
		if (opcao == '1')
		{
			limpaMenu();
		}
		if (opcao == '2')
		{
			limpaMenu();
		}
	}
	
	public static void limpaMenu() throws IOException, InterruptedException {
		//System.out.printf("\nPressione enter para conitnuar...");
		//System.in.read();
		Thread.sleep(2000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public static void menuNivelAcesso(Usuario user) throws IOException, InterruptedException
	{
		System.out.println("::::::::::::::::::::::::::: MENU DE NIVEL DE ACESSO - " + user.getCategoria().toUpperCase() + " :::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Acesso Conta Pessoal");
		System.out.println("1 - Acesso Sistema Banco Pencet");
		System.out.println("2 - Desconectar-se");
		System.out.println("3 - Fechar Programa");
		System.out.print("Digite uma op��o de acordo com o indice: ");
		char opcao = sc.next().charAt(0);
		if (opcao == '0')
		{
			menuBancario(user);
		}
		else if (opcao == '1')
		{
			if (user.getCategoria().equals("Gerente"))
			{
				menuGerente(user);
			}
			if (user.getCategoria().equals("Diretor"))
			{
				menuDiretor(user);
			}
			if (user.getCategoria().equals("Presidente"))
			{
				menuPresidente(user);
			}
		}
		if (opcao == '2')
		{
			System.out.print("\nEfetuando Logout");
			limpaMenu();
		}
		else if (opcao == '3')
		{
			System.out.print("\nSaindo do Sistema.");
			limpaMenu();
		}
		else
		{
			System.out.print("\nOp��o digitada incorreta, Saindo do Sistema.");
			limpaMenu();
		}
	}
	
}





