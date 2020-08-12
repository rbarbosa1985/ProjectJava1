package conta;

public class ContaCorrente extends ContaBase {
    private double limite;
    static int qtdContas;
	
	public ContaCorrente() {
		super();
		qtdContas ++;
	};	  

	public ContaCorrente(int agencia, String cpf, double saldo, int numeroConta, String tipoDeConta, double limite) {
		super(agencia, cpf, saldo, numeroConta, tipoDeConta);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public static int getQtdContas() {
		return qtdContas;
	}

	@Override
	public boolean sacar(double valor) throws InterruptedException {
		if ( super.getSaldo() < valor + 0.10) {
			System.out.println("\nSaldo insuficiente para efetuar o saque!");
			return false;
		} else {
			double novoSaldo = super.getSaldo() - valor - 0.10;
			super.setSaldo(novoSaldo);
			System.out.println("\nSaque realizado com sucesso!");
			return true;
		}
	}
	@Override
	public boolean transfere(ContaBase destino, double valor) throws InterruptedException {
		boolean retirou = this.sacar(valor + 0.10);
		
		if (retirou == false) {
			System.out.println("\nSaldo insuficiente para efetuar a transferencia!");
			return false;
		}
		else {
			destino.depositar(valor);
			System.out.println("\nTransferencia realizada com sucesso!");
			return true;
		}
	}
	@Override
	public void depositar(double valor) {
		super.setSaldo(super.getSaldo() + valor - 0.10);
		
	}
	@Override
	public void depositarDeTransferencia(double valor) {
		super.setSaldo(super.getSaldo() + valor);
		
	}
}
