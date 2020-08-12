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
			System.out.println(":::::::::::::::::::::::::::::::::::::::::::: MENU DE MOVIMENTAÇOES BANCARIAS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Saldo");
			System.out.println("1 - Saque");
			System.out.println("2 - Deposito");
			System.out.println("3 - Tranferencia");
			System.out.println("4 - Relatorio de Tributação da Conta Corrente");
			System.out.println("5 - Relatório de Rendimento da Conta Poupança");
			System.out.println("6 - Desconectar-se");
			System.out.println("7 - Fechar programa");
			System.out.print("\nDigite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);
			if (opcao == '0')
			{
				System.out.printf("\nSeu Saldo atual é: R$ %.2f\n", cc.getSaldo());
				Leitora.escritorSaldo(path, user, cc);
				limpaMenu();
			}
			else if (opcao == '1')
			{
				double valor;
				System.out.print("\nDigite o valor que deseja sacar: R$ ");
				valor = sc.nextDouble();				
				if (cc.sacar(valor))
				{
					valortarifa += 0.1;
					if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca"))
					{
						Leitora.escritorSaque(path, user, cc, valor, 0);
					}
					else
					{
						Leitora.escritorSaque(path, user, cc, valor, 0.1);
					}
					System.out.println("\nSaque realizado com sucesso!");
				}
				limpaMenu();
			}
			else if (opcao == '2')
			{
				double valor;
				System.out.print("\nDigite o valor que deseja depositar: R$ ");
				valor = sc.nextDouble();
				cc.depositar(valor);
				valortarifa += 0.1;
				if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca"))
				{
					Leitora.escritorDeposito(path, user, cc, valor, 0);
				}
				else
				{
					Leitora.escritorDeposito(path, user, cc, valor, 0.1);
				}
				limpaMenu();
			}
			else if (opcao == '3')
			{
				
				double valor;
				int numeroconta = 0;
				
				
				System.out.print("\nDigite o numero da conta destino: ");
				numeroconta = sc.nextInt();
				System.out.print("\nDigite o cpf do Titular da conta destino: ");
				sc.nextLine();
				String cpf = sc.nextLine();
				cc1 = ValidaConta.validaConta(cpf);	
				Usuario user1 = FindUser.find(cpf);
				System.out.print("\nDigite o valor que deseja transferir: R$ ");
				valor = sc.nextDouble();
				
				if ((numeroconta == cc1.getNumeroConta()) && (cpf.equalsIgnoreCase(cc1.getCpf())))
				{
					System.out.println("\nDados do Favorecido:");
					System.out.println("Nome: " + user1.getNome() + " CPF: " + user1.getCpf() + " Numero da Conta: " + cc1.getNumeroConta());
					System.out.print("\nConfirma os dados da conta destino (s/n)? ");
					char confirma = sc.next().charAt(0);
					if (confirma == 's')
					{
						if (cc.transfere(cc1, valor))
						{
							valortarifa += 0.2;
						}
						if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca"))
						{
							Leitora.escritorTransferencia(path, user, cc, cc1, user1.getNome(), numeroconta, valor, 0);
						}
						else
						{
							Leitora.escritorTransferencia(path, user, cc, cc1, user1.getNome(), numeroconta, valor, 0.2);
						}
						limpaMenu();
					}
					else
					{
						System.out.println("\nDados incorretos!");
						limpaMenu();
					}
				}
				else
				{
					System.out.println("\nDados incorretos!");
					limpaMenu();
				}
				
			}
			else if (opcao == '4')
			{
				if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca"))
				{
					System.out.print("\nVocê não possuí uma Conta Corrente.");
					limpaMenu();
				}
				else 
				{
					System.out.printf("\nVocê gastou em tarifas nesta seção: R$ %.2f", valortarifa);
					limpaMenu();
				}
				
			}
			else if (opcao == '5')
			{
				double valor;
				int dias;
				
				if (cc.getTipoDeConta().equalsIgnoreCase("Corrente"))
				{
					System.out.print("\nVocê não possuí uma Conta Poupança.");
					limpaMenu();
				}
				else 
				{
					System.out.print("\nDigite o Valor que pretende simular: ");
					valor = sc.nextDouble();
					System.out.print("\nDigite a quantidade de dias que pretende simular: ");
					dias = sc.nextInt();
					valor = (valor * ((0.10/30)*dias));
					System.out.printf("\nO valor total do rendimento é: R$ %.2f ", valor);
					limpaMenu();
				}
				
				
				
			}
			else if (opcao == '6')
			{
				System.out.println("\nEfetuando o Logout!");
				limpaMenu();
				ValidaUsuario.validaUsuario();
			}
			else if (opcao == '7')
			{
				System.out.println("\nSaindo do Sistema!");
				limpaMenu();
			}
			else
			{
				System.out.println("Opção incorreta!");
			}
	}
}
	
	public static void menuGerente(Usuario user) throws IOException, InterruptedException
	{	
		char opcao = '9';
		while(opcao != '2')
		{
			cabecalho();
			System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::: MENU GERENTE ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");			
			System.out.println("0 - Imprimir o relatorio Numero de Contas da Agencia: ");
			System.out.println("1 - Desconectar-se");
			System.out.println("2 - Fechar Programa");
			System.out.print("Digite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);
				
			if (opcao == '0')
			{			
				System.out.println("Quantidade de contas na sua Agencia é: " + ((Gerente)user).qtdContas());
				System.out.println("\nDigite o Enter para continuar... ");
				sc.nextLine();
				sc.nextLine();
				limpaMenu();
			}
			else if (opcao == '1')
			{
				System.out.print("\nEfetuando Logout");
				limpaMenu();
				ValidaUsuario.validaUsuario();
			}
			else if (opcao == '2')
			{
				Thread.sleep(1000);
			}
			else
			{
				System.out.println("Opção Incorreta!");
				limpaMenu();
			}
		}
	}
	
	public static void menuDiretor(Usuario user) throws IOException, InterruptedException
	{
		char opcao = '9';
		while (opcao != '2')
		{
			cabecalho();
			System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::: MENU DIRETOR :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Imprimir o relatorio Nome, CPF e Agencia de todos os Cliente: ");
			System.out.println("1 - Desconectar-se");
			System.out.println("2 - Fechar Programa");
			System.out.print("Digite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);
			
			if (opcao == '0')
			{			
				System.out.println("Segue abaixo o Relatorio com os clientes:");
				((Diretor)user).consultaContas();
				System.out.println("\nDigite o Enter para continuar... ");
				String pausa = sc.nextLine();
				limpaMenu();
			}
			
			else if (opcao == '1')
			{
				System.out.print("\nEfetuando Logout");
				limpaMenu();
				ValidaUsuario.validaUsuario();
			}
			else if (opcao == '2')
			{
				Thread.sleep(1000);
				limpaMenu();
			}
			else
			{
				System.out.println("Opção Incorreta!");
				limpaMenu();
			}
		}
	}
	
	public static void menuPresidente(Usuario user) throws IOException, InterruptedException
	{
		char opcao = '9';
		while (opcao != '2')
		{
			cabecalho();
			System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::: MENU PRESIDENTE :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Imprimir o relatório Valor Total Capital no Banco: ");
			System.out.println("1 - Desconectar-se");
			System.out.println("2 - Fechar Programa");
			System.out.print("Digite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);
			
			if (opcao == '0')
			{
				System.out.println("Quantidade de contas na sua Agencia é: " + ((Presidente)user).capital());
				System.out.println("\nDigite o Enter para continuar... ");
				String pausa = sc.nextLine();
				limpaMenu();
			}
			else if (opcao == '1')
			{
				System.out.print("\nEfetuando Logout");
				limpaMenu();
				ValidaUsuario.validaUsuario();
			}
			else if (opcao == '2')
			{
				Thread.sleep(1000);
			}
			else
			{
				System.out.println("Opção Incorreta!");
				limpaMenu();
			}
		}
	}
	
	public static void limpaMenu() throws IOException, InterruptedException {
		Thread.sleep(2000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
	}
	
	public static void menuNivelAcesso(Usuario user) throws IOException, InterruptedException
	{
		char opcao = '9';
		boolean teste = true;
		while (teste)
		{
			cabecalho();
			System.out.println("::::::::::::::::::::::::::::::::::::::::::: MENU DE NIVEL DE ACESSO - " + user.getCategoria().toUpperCase() + " :::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Acesso Conta Pessoal");
			System.out.println("1 - Acesso Sistema Banco Pencet");
			System.out.println("2 - Desconectar-se");
			System.out.println("3 - Fechar Programa");
			System.out.print("Digite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);
			if (opcao == '0')
			{
				menuBancario(user);
				teste = false;
				limpaMenu();
			}
			else if (opcao == '1')
			{
				if (user.getCategoria().equals("Gerente"))
				{
					menuGerente(user);
					teste = false;
					limpaMenu();
				}
				if (user.getCategoria().equals("Diretor"))
				{
					menuDiretor(user);
					teste = false;
					limpaMenu();
				}
				if (user.getCategoria().equals("Presidente"))
				{
					menuPresidente(user);
					teste = false;
					limpaMenu();
				}
			}
			else if (opcao == '2')
			{
				System.out.print("\nEfetuando Logout");
				ValidaUsuario.validaUsuario();
				limpaMenu();
				teste = false;
			}
			else if (opcao == '3')
			{
				System.out.print("\nSaindo do Sistema.");
				limpaMenu();
				teste = false;
			}
			else
			{
				System.out.print("\nOpção digitada incorreta");
				limpaMenu();
			}
		}
	}
	
}





