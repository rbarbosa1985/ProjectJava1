package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pessoal.Usuario;

public class FindUser {
	public static Usuario find(String cpf) throws IOException {
	    //Lista de Usuarios do Sistema
	    List<Usuario> usuarios = new ArrayList<>();
	    		
		//Atribuo os valores do arquivo na minha lista.
		usuarios = Leitora.leitor();
		
		Usuario user = null;
		
		for (Usuario usuario : usuarios)
		{
			if (usuario.getCpf().equals(cpf))
			{
				user = usuario;
			}
		}
		
		return user;
	}
}
