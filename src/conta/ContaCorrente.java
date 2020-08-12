package conta;

public class ContaCorrente extends ContaBase {
    private double limite;
    static int qtdContas;
	
	public ContaCorrente() {
		super();
		qtdContas ++;
	};
		
	public ContaCorrente(String cpf, double saldo, int numeroConta, String tipoDeConta, double limite) {
		super(cpf, saldo, numeroConta, tipoDeConta);
		qtdContas ++;
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
	public boolean sacar(double valor) {
		if ( super.getSaldo() < valor + 0.10) {
			System.out.println("Saldo insuficiente para efetuar o saque!");
			return false;
		} else {
			double novoSaldo = super.getSaldo() - valor - 0.10;
			super.setSaldo(novoSaldo);
			System.out.println("Transferencia realizada com sucesso!");
			return true;
		}
	}
	@Override
	public boolean transfere(ContaBase destino, double valor) {
		boolean retirou = this.sacar(valor + 0.20);
		
		if (retirou == false) {
			System.out.println("Saldo insuficiente para efetuar a transferencia!");
			return false;
		}
		else {
			destino.depositar(valor);
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
	
	public void relatorioTarifa() {
		
	}
}
