package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import conta.ContaBase;
import conta.ContaCorrente;
import conta.ContaPoupanca;
import enums.ContaEnum;
import enums.UsuarioEnum;
import pessoal.Cliente;
import pessoal.Diretor;
import pessoal.Gerente;
import pessoal.Presidente;
import pessoal.Usuario;



public class Leitora {

	public static List<Usuario> leitor() throws IOException {

		BufferedReader buffRead = new BufferedReader(new FileReader("./usuarios.txt"));
		List<Usuario> lista = new ArrayList<>();
		String linha = "";
		while (true) {
			linha = buffRead.readLine();

			Usuario Usuario = null;
			if (linha != null) {
				String[] split = linha.split(";");

				if (split[3].equalsIgnoreCase(UsuarioEnum.GERENTE.name())) {
					Usuario = new Gerente(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]),(split[5]),Integer.parseInt(split[6]));
					lista.add(Usuario);
				}
				
				else if (split[3].equalsIgnoreCase(UsuarioEnum.DIRETOR.name())) {
					Usuario = new Diretor(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]),(split[5]));
					lista.add(Usuario);
				}
				
				else if (split[3].equalsIgnoreCase(UsuarioEnum.PRESIDENTE.name())) {
					Usuario = new Presidente(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]),(split[5]));
					lista.add(Usuario);
				}

				else if (split[3].equalsIgnoreCase(UsuarioEnum.CLIENTE.name())) {
					Usuario = new Cliente(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]),(split[5]));
					lista.add(Usuario);
				}
			} else
				break;
		}
		buffRead.close();
		return lista;
	}
	
	
	public static List<ContaBase> leitorConta() throws IOException {

		BufferedReader buffRead = new BufferedReader(new FileReader("./contas.txt"));
		List<ContaBase> lista = new ArrayList<>();
		String linha = "";
		while (true) {
			linha = buffRead.readLine();

			ContaBase ContaBase = null;
			if (linha != null) {
				String[] split = linha.split(";");

				if (split[4].equalsIgnoreCase(ContaEnum.CORRENTE.name())) {
					ContaBase = new ContaCorrente(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]), Integer.parseInt(split[3]), split[4],Double.parseDouble(split[5]));
					lista.add(ContaBase);
				}
				
				else if (split[4].equalsIgnoreCase(ContaEnum.POUPANCA.name())) {
					ContaBase = new ContaPoupanca(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]), Integer.parseInt(split[3]), split[4], Double.parseDouble(split[5]));
					lista.add(ContaBase);
				}
				} else
				break;
		}
		buffRead.close();
		return lista;
	}
	

	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = sc.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
		sc.close();
	}
	

public static void escritorSaldo(String path, Usuario p, ContaBase c) throws IOException {
	   
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
Date data = new Date();

path = path + simpleDateFormat.format(data) + p.getNome() + ".txt";

    BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    String linha = "**** COMPROVANTE DE TRANSAÇÃO BANCARIA - SALDO ****";
    buffWrite.append(linha + "\n\n");

    linha = "DATA HH: " + simpleDateFormat2.format(data);
   
    if(p.getCategoria().equalsIgnoreCase(UsuarioEnum.CLIENTE.name())){
    linha = "Cliente: "+((Cliente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Cliente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo da sua conta é: R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
    }
   
    if(p.getCategoria().equals(UsuarioEnum.GERENTE.name())){
    linha = "Cliente: "+((Gerente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Gerente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo da sua conta é: R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
    }
   
    linha = "================ Fim do Comprovante =================";
    buffWrite.append(linha + "\n");
    linha = "======= OBRIGADO POR UTILIZAR NOSSOS SERVIÇOS========";
    buffWrite.append(linha + "\n");
    linha = "======== Pencent Bank Copyright © Copyright ® =======";
    buffWrite.append(linha + "\n");
   
    buffWrite.close();
}

public static void escritorSaque(String path, Usuario p, ContaBase c, double valor, double valortarifa) throws IOException {
   
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
Date data = new Date();

path = path + simpleDateFormat.format(data) + p.getNome() + ".txt";

    BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
   
    String linha = "**** COMPROVANTE DE TRANSAÇÃO BANCARIA - SAQUE ****";
    buffWrite.append(linha + "\n\n");

    linha = "DATA HH: " + simpleDateFormat2.format(data);
   
    if(p.getCategoria().equalsIgnoreCase(UsuarioEnum.CLIENTE.name())){
    linha = "Cliente: "+((Cliente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Cliente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n");
        linha = "Valor de saque realizado: R$ "+ valor;
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo Atual da sua conta é: R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Tarifa desta operação foi R$: " + valortarifa;
        buffWrite.append(linha + "\n\n");
    }
    if(p.getCategoria().equals(UsuarioEnum.GERENTE.name())){
    linha = "Cliente: "+((Gerente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Gerente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Valor de saque realizado: R$ "+ valor;
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo Atual da sua conta é: R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Tarifa desta operação foi R$: " + valortarifa;
        buffWrite.append(linha + "\n\n");
    }
   
    linha = "================ Fim do Comprovante =================";
    buffWrite.append(linha + "\n");
    linha = "======= OBRIGADO POR UTILIZAR NOSSOS SERVIÇOS========";
    buffWrite.append(linha + "\n");
    linha = "======== Pencent Bank Copyright © Copyright ® =======";
    buffWrite.append(linha + "\n");
   
    buffWrite.close();
}

public static void escritorDeposito(String path, Usuario p, ContaBase c, double valor,double valortarifa) throws IOException {
   
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
Date data = new Date();

path = path + simpleDateFormat.format(data) + p.getNome() + ".txt";

    BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
   
    String linha = "** COMPROVANTE DE TRANSAÇÃO BANCARIA - DEPOSITO **";
    buffWrite.append(linha + "\n\n");

    linha = "DATA HH: " + simpleDateFormat2.format(data);
   
    if(p.getCategoria().equalsIgnoreCase(UsuarioEnum.CLIENTE.name())){
    linha = "Cliente: "+((Cliente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Cliente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Valor depositado foi de: R$ "+ valor;
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo Atual da sua conta é: R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Tarifa desta operação foi R$: " + valortarifa;
        buffWrite.append(linha + "\n\n");
    }
    if(p.getCategoria().equals(UsuarioEnum.GERENTE.name())){
    linha = "Cliente: "+((Gerente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Gerente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Valor depositado foi de: R$ "+ valor;
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo Atual da sua conta é: R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Tarifa desta operação foi R$: " + valortarifa;
        buffWrite.append(linha + "\n\n");
    }
   
    linha = "================ Fim do Comprovante =================";
    buffWrite.append(linha + "\n");
    linha = "======= OBRIGADO POR UTILIZAR NOSSOS SERVIÇOS========";
    buffWrite.append(linha + "\n");
    linha = "======== Pencent Bank Copyright © Copyright ® =======";
    buffWrite.append(linha + "\n");
   
    buffWrite.close();
}

public static void escritorTransferencia(String path, Usuario p, ContaBase c, ContaBase cb1, String nomedestino, int numeroconta ,double valor, double valortarifa) throws IOException {
   
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
Date data = new Date();

path = path + simpleDateFormat.format(data) + p.getNome() + ".txt";

    BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
   
    String linha = "** COMPROVANTE DE TRANSAÇÃO BANCARIA - TRANSFERENCIA **";
    buffWrite.append(linha + "\n\n");

    linha = "DATA HH: " + simpleDateFormat2.format(data);
   
    if(p.getCategoria().equalsIgnoreCase(UsuarioEnum.CLIENTE.name())){
    linha = "Cliente: "+((Cliente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Cliente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n");
        linha="================Tranferido para:====================";
        buffWrite.append(linha + "\n\n");
        linha = "Valor Total Transferido R$: "+ valor;
        buffWrite.append(linha + "\n");
        linha = "Conta Destino: "+ numeroconta;
        buffWrite.append(linha + "\n");
        linha = "Cliente: " + nomedestino + " CPF: " + ((ContaBase)cb1).getCpf();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo atual da sua conta R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Tarifa desta operação foi R$: " + valortarifa;
        buffWrite.append(linha + "\n\n");
    }
    if(p.getCategoria().equals(UsuarioEnum.GERENTE.name())){
    linha = "Cliente: "+((Gerente)p).getNome();
    buffWrite.append(linha + "\n");
    linha = "Agencia: "+((Gerente)p).getAgencia() + " - Numero da Conta :" + ((ContaBase)c).getNumeroConta() +" Conta: " +((ContaBase)c).getTipoDeConta();
        buffWrite.append(linha + "\n");
        linha="====================================================";
        buffWrite.append(linha + "\n");
        linha="================Tranferido para:====================";
        buffWrite.append(linha + "\n\n");
        linha = "Valor Total Transferido R$: "+ valor;
        buffWrite.append(linha + "\n");
        linha = "Conta Destino: "+ numeroconta;
        buffWrite.append(linha + "\n");
        linha = "Cliente: " + nomedestino + " CPF: " + ((ContaBase)cb1).getCpf();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "O Saldo atual da sua conta R$ "+((ContaBase)c).getSaldo();
        buffWrite.append(linha + "\n\n");
        linha="====================================================";
        buffWrite.append(linha + "\n\n");
        linha = "Tarifa desta operação foi R$: " + valortarifa;
        buffWrite.append(linha + "\n\n");
    }
   
    linha = "================ Fim do Comprovante =================";
    buffWrite.append(linha + "\n");
    linha = "======= OBRIGADO POR UTILIZAR NOSSOS SERVIÇOS========";
    buffWrite.append(linha + "\n");
    linha = "======== Pencent Bank Copyright © Copyright ® =======";
    buffWrite.append(linha + "\n");
   
    buffWrite.close();
}
}




	
	
	
	
	

