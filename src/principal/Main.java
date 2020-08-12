package principal;

import java.io.IOException;

import utils.Menu;
import utils.ValidaUsuario;

public class Main {


	public static void main(String[] args) throws IOException, InterruptedException {
			
		Menu.cabecalho();
		ValidaUsuario log = new ValidaUsuario();
		
		log.validaUsuario();
		

	}

}
