package utils;

import java.io.IOException;
import java.util.Scanner;

import conta.ContaBase;
import pessoal.Usuario;


public class Menu {
 	static Scanner sc = new Scanner(System.in);
 	
 	
	public static void cabecalho() 
	{
		System.out.println(":::::::::::::::::::::::::::::::: BANK PENCET ::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
	}
	
	public static void menuBancario(Usuario user) throws IOException 
	{
		ContaBase cc;
		ContaBase cc1;
		double valortarifa = 0.0;
		
		String path = "C:\\Projetos\\springtool\\Pencet\\";
		char opcao = '0';
		cc = ValidaConta.validaConta(user.getCpf());
		
		
		while (opcao != '6')
		{
			System.out.println("\n\n:::::::::::::::::::::::: MENU DE MOVIMENTAÇOES BANCARIAS :::::::::::::::::::::::::::::::::::: \n\n");
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
			}
			if (opcao == '1')
			{
				double valor;
				System.out.print("Digite o valor que deseja sacar: ");
				valor = sc.nextDouble();
				if (cc.sacar(valor))
				{
					valortarifa += 0.1;
				}
				
				Leitora.escritorExtrato(path, user, cc);
			}
			if (opcao == '2')
			{
				double valor;
				System.out.print("Digite o valor que deseja depositar: ");
				valor = sc.nextDouble();
				cc.depositar(valor);
				valortarifa += 0.1;
				Leitora.escritorExtrato(path, user, cc);
			}
			if (opcao == '3')
			{
				
				double valor;
				int numeroconta = 0;
				
				
				System.out.print("Digite o numero da conta destino: ");
				numeroconta = sc.nextInt();
				System.out.print("Digite o cpf do Titular da conta destino: ");
				sc.nextLine();
				String cpf = sc.nextLine();
				cc1 = ValidaConta.validaConta(cpf);			
				System.out.print("Digite o valor que deseja transferir: ");
				valor = sc.nextDouble();
				if ((numeroconta == cc1.getNumeroConta()) && (cpf.equalsIgnoreCase(cc1.getCpf())))
				{
					if (cc.transfere(cc1, valor))
					{
						valortarifa += 0.2;
					}
					Leitora.escritorExtrato(path, user, cc);
				}
				else
					System.out.println("Dados incorretos!");
				
			}
			if (opcao == '4')
			{
				if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca"))
				{
					System.out.print("Você não possuí uma Conta Corrente.");
				}
				else 
				{
					System.out.printf("Você gastou em tarifas nesta seção: R$ %.2f", valortarifa);
				}
				
			}
			if (opcao == '5')
			{
				double valor;
				int dias;
				
				if (cc.getTipoDeConta().equalsIgnoreCase("Corrente"))
				{
					System.out.print("Você não possuí uma Conta Poupança.");
				}
				else 
				{
					System.out.print("Digite o Valor que pretende simular: ");
					valor = sc.nextDouble();
					System.out.print("Digite a quantidade de dias que pretende simular: ");
					dias = sc.nextInt();
					valor = (valor * ((0.10/30)*dias));
					System.out.printf("O valor total do rendimento é: R$ %.2f ", valor);
					
				}
				
				
				
			}
			if (opcao == '6')
			{
				System.out.println("Efetuando o Logout!");
				ValidaUsuario.validaUsuario();
			}
			if (opcao == '7')
			{
				System.out.println("Saindo do Sistema!");
			}
	}
}
	
	public static void menuGerente()
	{
		System.out.println(":::::::::::::::::::::::::::::::: MENU GERENTE ::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Imprimir o relatorio Numero de Contas da Agencia: ");
		System.out.println("1 - Desconectar-se");
		System.out.println("2 - Fechar Programa");
		System.out.print("Digite uma opção de acordo com o indice: ");
		char opcao = sc.next().charAt(0);

		if (opcao == '1')
		{
			System.out.print("Efetuando Logout");
		}
		if (opcao == '2')
		{
			System.out.print("Saindo do Sistema.");
		}
	}
	
	public static void menuDiretor()
	{
		System.out.println(":::::::::::::::::::::::::::::::: MENU DIRETOR :::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Imprimir o relatorio Nome, CPF e Agencia de todos os Cliente: ");
		System.out.println("1 - Desconectar-se");
		System.out.println("2 - Fechar Programa");
		System.out.print("Digite uma opção de acordo com o indice: ");
		char opcao = sc.next().charAt(0);

		if (opcao == '1')
		{
			System.out.print("Efetuando Logout");
		}
		if (opcao == '2')
		{
			System.out.print("Saindo do Sistema.");
		}
	}
	
	public static void menuPresidente()
	{
		System.out.println(":::::::::::::::::::::::::::::: MENU PRESIDENTE ::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Imprimir o relatório Valor Total Capital no Banco: ");
		System.out.println("1 - Desconectar-se");
		System.out.println("2 - Fechar Programa");
		System.out.print("Digite uma opção de acordo com o indice: ");
		char opcao = sc.next().charAt(0);

		if (opcao == '1')
		{
			System.out.print("Efetuando Logout");
		}
		if (opcao == '2')
		{
			System.out.print("Saindo do Sistema.");
		}
	}
	
	public static void menuNivelAcesso(Usuario user) throws IOException
	{
		System.out.println("::::::::::::::::::::::::::: MENU DE NIVEL DE ACESSO - " + user.getCategoria().toUpperCase() + " :::::::::::::::::::::::::::::::::::: \n\n");
		System.out.println("0 - Acesso Conta Pessoal");
		System.out.println("1 - Acesso Sistema Banco Pencet");
		System.out.println("2 - Desconectar-se");
		System.out.println("3 - Fechar Programa");
		System.out.print("Digite uma opção de acordo com o indice: ");
		char opcao = sc.next().charAt(0);
		if (opcao == '0')
		{
			menuBancario(user);
		}
		else if (opcao == '1')
		{
			if (user.getCategoria().equals("Gerente"))
			{
				menuGerente();
			}
			if (user.getCategoria().equals("Diretor"))
			{
				menuDiretor();
			}
			if (user.getCategoria().equals("Presidente"))
			{
				menuPresidente();
			}
		}
		if (opcao == '2')
		{
			System.out.print("Efetuando Logout");
		}
		else if (opcao == '3')
		{
			System.out.print("Saindo do Sistema.");
		}
		else
		{
			System.out.print("Opção digitada incorreta, Saindo do Sistema.");
		}
	}
	
}





