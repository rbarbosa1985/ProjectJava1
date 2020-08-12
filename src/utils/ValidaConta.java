package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import conta.ContaBase;
import conta.ContaCorrente;
import conta.ContaPoupanca;

public class ValidaConta {
	
	public static ContaBase validaConta(String cpf) throws ArithmeticException, IOException {
		
	    //Lista de Contas
	    List<ContaBase> contas = new ArrayList<>();
	    		
		//Atribuo os valores do arquivo na minha lista.
		contas = Leitora.leitorConta();
		
		ContaBase cc;
		
		int i = 0;
		
		//identificando o usuário
		for (ContaBase conta : contas) 
		{
			
			if (conta.getCpf().equals(cpf)) 
			{
				break;
			}
			i ++;
		}	
		
		if (contas.get(i).getTipoDeConta().equalsIgnoreCase("corrente"))
		{
			cc = new ContaCorrente();
			cc = contas.get(i);
		}
		else
		{
			cc = new ContaPoupanca();
			cc = contas.get(i);
		}
		
		return cc;
	}
}
