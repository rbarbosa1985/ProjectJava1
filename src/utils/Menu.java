package utils;

import java.io.IOException;
import java.util.Scanner;

import java.util.InputMismatchException;
import Exceptions.MyExceptions;
import conta.ContaBase;
import pessoal.Diretor;
import pessoal.Gerente;
import pessoal.Presidente;
import pessoal.Usuario;

public class Menu {

	public static void cabecalho() {
		System.out.println(
				"######################################################################################################################################");
		System.out.println(
				"8888888b.  8888888888 888b    888  .d8888b.  8888888888 888b    888 88888888888       888888b.          d8888 888b    888 888    d8P  ");
		System.out.println(
				"888   Y88b 888        8888b   888 d88P  Y88b 888        8888b   888     888           888   88b        d88888 8888b   888 888   d8P   ");
		System.out.println(
				"888    888 888        88888b  888 888    888 888        88888b  888     888           888  .88P       d88P888 88888b  888 888  d8P    ");
		System.out.println(
				"888   d88P 8888888    888Y88b 888 888        8888888    888Y88b 888     888           8888888K.      d88P 888 888Y88b 888 888d88K     ");
		System.out.println(
				"8888888P   888        888 Y88b888 888        888        888 Y88b888     888           888   Y88b    d88P  888 888 Y88b888 8888888b    ");
		System.out.println(
				"888        888        888  Y88888 888    888 888        888  Y88888     888           888    888   d88P   888 888  Y88888 888  Y88b   ");
		System.out.println(
				"888        888        888   Y8888 Y88b  d88P 888        888   Y8888     888           888   d88P  d8888888888 888   Y8888 888   Y88b  ");
		System.out.println(
				"888        8888888888 888    Y888   Y8888P   8888888888 888    Y888     888           8888888P   d88P     888 888    Y888 888    Y88b ");
		System.out.println(
				"######################################################################################################################################\n\n");
	}

	public boolean menuBancario(Usuario user) throws ArithmeticException, IOException, InterruptedException {
		ContaBase cc;
		ContaBase cc1 = null;
		double valortarifa = 0.0;
		boolean retorno = true;
		Scanner input = new Scanner(System.in);

		String path = "./";
		char opcao = '0';
		cc = ValidaConta.validaConta(user.getCpf());

		while (opcao != '7') {
			limpaMenu();
			cabecalho();
			System.out.println(
					":::::::::::::::::::::::::::::::::::::::::::: MENU DE MOVIMENTAÇOES BANCARIAS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Saldo");
			System.out.println("1 - Saque");
			System.out.println("2 - Deposito");
			System.out.println("3 - Transferencia");
			System.out.println("4 - Relatorio de Tributação da Conta Corrente");
			System.out.println("5 - Relatório de Rendimento da Conta Poupança");
			System.out.println("6 - Desconectar-se");
			System.out.println("7 - Fechar programa");
			System.out.print("\nDigite uma opção de acordo com o indice: ");

			opcao = input.next().charAt(0);
			if (opcao == '0') {
				System.out.printf("\nSeu Saldo atual é: R$ %.2f\n", cc.getSaldo());
				Leitora.escritorSaldo(path, user, cc);
				Thread.sleep(4000);
				limpaMenu();
			} else if (opcao == '1') {
				double valor = 0;
				boolean continua = true;
				do {
					try {
						System.out.print("\nDigite o valor que deseja sacar: R$ ");
						valor = input.nextDouble();
						if (valor <= 0) {
							throw new MyExceptions("O valor precisa ser maior que 0.");
						} else {
							if (cc.sacar(valor)) {
								valortarifa += 0.1;
								if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca")) {
									Leitora.escritorSaque(path, user, cc, valor, 0);
								} else {
									Leitora.escritorSaque(path, user, cc, valor, 0.10);
								}
								System.out.println("\nContando as cedulas...");
								Thread.sleep(2000);
								System.out.println("\nSaque realizado com sucesso!");

							}
						}
						continua = false;
					} catch (MyExceptions e) {
						System.out.println("\nO Valor não pode ser menor ou igual a 0.");
					}
				} while (continua);
				Thread.sleep(4000);
				limpaMenu();
			} else if (opcao == '2') {
				double valor = 0;
				boolean continua = true;
				do {
					try {
						System.out.print("\nDigite o valor que deseja depositar: R$ ");
						valor = input.nextDouble();
						if (valor <= 0) {
							throw new MyExceptions("O valor precisa ser maior que 0.");
						} else {
							cc.depositar(valor);
							valortarifa += 0.1;
							if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca")) {
								Leitora.escritorDeposito(path, user, cc, valor, 0);
							} else {
								Leitora.escritorDeposito(path, user, cc, valor, 0.10);
							}
							System.out.println("\nO Valor foi depositado com sucesso.");
						}
						continua = false;
					} catch (MyExceptions e) {
						System.out.println("\nO Valor não pode ser menor ou igual a 0.");
					}
				} while (continua);
				Thread.sleep(2000);
				limpaMenu();
			} else if (opcao == '3') {

				double valor = 0;
				int numeroconta = 0;
				String cpf = "";
				Usuario user1 = null;
				try 
				{
					System.out.print("\nDigite o numero da conta destino: ");
					numeroconta = input.nextInt();
					System.out.print("\nDigite o cpf do Titular da conta destino: ");
					input.nextLine();
					cpf = input.nextLine();
					user1 = FindUser.find(cpf);
					if (user1 == null) {
						throw new MyExceptions("\nDados incorretos!");
					} else {
						cc1 = ValidaConta.validaConta(cpf);
						if ((numeroconta == cc1.getNumeroConta()) && (cpf.equalsIgnoreCase(cc1.getCpf()))) {
							System.out.print("\nDigite o valor que deseja transferir: R$ ");
							valor = input.nextDouble();
							if (valor <= 0) {
								throw new MyExceptions("O valor precisa ser maior que 0.");
							} else {
								System.out.println("\nDados do Favorecido:");
								System.out.println("Nome: " + user1.getNome() + " CPF: " + user1.getCpf()
										+ " Numero da Conta: " + cc1.getNumeroConta());
								System.out.print("\nConfirma os dados da conta destino (s/n)? ");
								char confirma = input.next().charAt(0);
								if (confirma == 's') {
									if (cc.transfere(cc1, valor)) {
										valortarifa += 0.2;
									}
									if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca")) {
										Leitora.escritorTransferencia(path, user, cc, cc1, user1.getNome(),
												numeroconta, valor, 0);
									} else {
										Leitora.escritorTransferencia(path, user, cc, cc1, user1.getNome(),
												numeroconta, valor, 0.20);
									}
									Thread.sleep(4000);
									limpaMenu();
								} else {
									System.out.println("\nCancelado pelo usuário!");
									limpaMenu();
								}
							}
						} else {
							System.out.println("\nDados incorretos!");
							limpaMenu();
						}
					}	
				} catch (MyExceptions e) {
					System.out.println(e.getMessage());
				}
			}

			else if (opcao == '4') {
				if (cc.getTipoDeConta().equalsIgnoreCase("Poupanca")) {
					System.out.print("\nVocê não possuí uma Conta Corrente.");
					Thread.sleep(2000);
					limpaMenu();
				} else {
					System.out.printf("\nVocê gastou em tarifas nesta seção: R$ %.2f", valortarifa);
					Thread.sleep(4000);
					limpaMenu();
				}

			} else if (opcao == '5') {
				double valor;
				int dias;

				if (cc.getTipoDeConta().equalsIgnoreCase("Corrente")) {
					System.out.print("\nVocê não possuí uma Conta Poupança.");
					Thread.sleep(2000);
					limpaMenu();
				} else {
					System.out.print("\nDigite o Valor que pretende simular: ");
					valor = input.nextDouble();
					System.out.print("\nDigite a quantidade de dias que pretende simular: ");
					dias = input.nextInt();
					valor = (valor * ((0.10 / 30) * dias));
					valor = Math.round(valor * 100);
					valor = valor / 100;
					System.out.printf("\nO valor total do rendimento é: R$ %.2f ", valor);
					Thread.sleep(4000);
					limpaMenu();
				}

			} else if (opcao == '6') {
				System.out.println("\nEfetuando o Logout!");
				limpaMenu();
				opcao = '7';
				retorno = true;
			} else if (opcao == '7') {
				System.out.println("\nSaindo do Sistema!");
				limpaMenu();
				retorno = false;
			} else {
				System.out.println("\nOpção incorreta!");
			}
		}
		return retorno;
	}

	public boolean menuGerente(Usuario user) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		char opcao = '9';
		boolean retorno = true;
		while (opcao != '2') {
			cabecalho();
			System.out.println(
					":::::::::::::::::::::::::::::::::::::::::::::::::::: MENU GERENTE ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Imprimir o relatorio Numero de Contas da Agencia: ");
			System.out.println("1 - Desconectar-se");
			System.out.println("2 - Fechar Programa");
			System.out.print("\nDigite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);

			if (opcao == '0') {
				System.out.println("\nQuantidade de contas na sua Agencia é: " + ((Gerente) user).qtdContas());
				System.out.println("\nDigite o Enter para continuar... ");
				sc.nextLine();
				sc.nextLine();
				limpaMenu();
			} else if (opcao == '1') {
				System.out.print("\nEfetuando Logout");
				limpaMenu();
				opcao = '2';
				retorno = true;
			} else if (opcao == '2') {
				System.out.println("\nSaindo do Sistema!");
				limpaMenu();
				retorno = false;
			} else {
				System.out.println("\nOpção Incorreta!");
				limpaMenu();
			}
		}
		return retorno;
	}

	public boolean menuDiretor(Usuario user) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		char opcao = '9';
		boolean retorno = true;
		while (opcao != '2') {
			cabecalho();
			System.out.println(
					":::::::::::::::::::::::::::::::::::::::::::::::::::: MENU DIRETOR :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Imprimir o relatorio Nome, CPF e Agencia de todos os Cliente: ");
			System.out.println("1 - Desconectar-se");
			System.out.println("2 - Fechar Programa");
			System.out.print("\nDigite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);

			if (opcao == '0') {
				System.out.println("\nSegue abaixo o Relatorio com os clientes: ");
				((Diretor) user).consultaContas();
				System.out.println("\nDigite o Enter para continuar... ");
				sc.nextLine();
				sc.nextLine();
				limpaMenu();
			}

			else if (opcao == '1') {
				System.out.print("\nEfetuando Logout");
				limpaMenu();
				opcao = '2';
				retorno = true;
			} else if (opcao == '2') {
				System.out.println("\nSaindo do Sistema!");
				limpaMenu();
				retorno = false;
			} else {
				System.out.println("\nOpção Incorreta!");
				limpaMenu();
			}
		}
		return retorno;
	}

	public boolean menuPresidente(Usuario user) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		char opcao = '9';
		boolean retorno = true;
		while (opcao != '2') {
			cabecalho();
			System.out.println(
					"::::::::::::::::::::::::::::::::::::::::::::::::::: MENU PRESIDENTE :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n\n");
			System.out.println("0 - Imprimir o relatório Valor Total Capital no Banco: ");
			System.out.println("1 - Desconectar-se");
			System.out.println("2 - Fechar Programa");
			System.out.print("\nDigite uma opção de acordo com o indice: ");
			opcao = sc.next().charAt(0);

			if (opcao == '0') {
				System.out.printf("\nO valor total de capital do Banco Pencent é: R$ %.2f ",
						((Presidente) user).capital());
				System.out.println("\n\nDigite o Enter para continuar... ");
				sc.nextLine();
				sc.nextLine();
				limpaMenu();
			} else if (opcao == '1') {
				System.out.print("\nEfetuando Logout");
				limpaMenu();
				opcao = '2';
				retorno = true;
			} else if (opcao == '2') {
				System.out.println("\nSaindo do Sistema!");
				limpaMenu();
				retorno = false;
			} else {
				System.out.println("\nOpção Incorreta!");
				limpaMenu();
			}
		}
		return retorno;
	}

	public static void limpaMenu() throws IOException, InterruptedException {
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		if (System.getProperty("os.name").contains("Windows"))
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		else
			Runtime.getRuntime().exec("clear");
	}

	public int menuNivelAcesso(Usuario user) throws IOException, InterruptedException, InputMismatchException {
		Scanner sc = new Scanner(System.in);
		int opcao = '9';
		boolean continua = true;
		do {
			try {
				limpaMenu();
				cabecalho();
				System.out.println("::::::::::::::::::::::::::::::::::::::::::: MENU DE NIVEL DE ACESSO - "
						+ user.getCategoria().toUpperCase() + " :::::::::::::::::::::::::::::::::::: \n\n");
				System.out.println("0 - Acesso Conta Pessoal");
				System.out.println("1 - Acesso Sistema Banco Pencent");
				System.out.println("2 - Desconectar-se");
				System.out.println("3 - Fechar Programa");
				System.out.print("\nDigite uma opção de acordo com o indice: ");
				opcao = sc.nextInt();
				if (opcao >= 4) {
					throw new MyExceptions("\nSelecione a opção correta!\n");
				}
				limpaMenu();
				continua = false;
			} catch (InputMismatchException e) {
				System.out.println("\nDigite apenas numeros de acordo com o indice!\n");
				Thread.sleep(2000);
				sc.nextLine();
			}
		} while (continua);
		return opcao;
	}

}
