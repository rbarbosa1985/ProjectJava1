package conta;

public abstract class ContaBase {
		
	static final int agencia = 0401;
	private String cpf;
	private double saldo;
	private int numeroConta;
	private String tipoDeConta;

	public ContaBase() {}
	
	public ContaBase(String cpf, double saldo, int numeroConta, String tipoDeConta) {
		this.cpf = cpf;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.tipoDeConta = tipoDeConta;
	}

	
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(String tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}

	public boolean sacar(double valor) 
	{
		if (this.saldo < valor) {
			System.out.println("Saldo insuficiente para efetuar o saque!");
			return false;
		} else {
			double novoSaldo = this.saldo - valor;
			this.saldo = novoSaldo;
			System.out.println("Transferencia realizada com sucesso!");
			return true;
		}
	}
	public boolean transfere(ContaBase destino, double valor) 
	{
		boolean retirou = this.sacar(valor);
		
		if (retirou == false) {
			System.out.println("Saldo insuficiente para efetuar a transferencia!");
			return false;
		}
		else {
			destino.depositar(valor);
			return true;
		}
	}

	public void depositar(double valor) 
	{
		this.saldo = this.saldo + valor;
		
	}

	public void depositarDeTransferencia(double valor) 
	{
		this.saldo = this.saldo + valor;
	}
}

	