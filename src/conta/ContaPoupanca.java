package conta;

public class ContaPoupanca extends ContaBase {
	
	private double rendimento;
    static int qtdContas;

	// implementar metodo rendimento 
	
	
	public double getRendimento() 
	{
		return rendimento;
	}
	
	public void setRendimento(double rendimento) 
	{
		this.rendimento = rendimento;
	}
		
	public ContaPoupanca() 
	{
		super();
		qtdContas ++;
	}
	
	public ContaPoupanca(int agencia, String cpf, double saldo, int numeroConta, String tipoDeConta, double rendimento) {
		super(agencia, cpf, saldo, numeroConta, tipoDeConta);
		this.rendimento = rendimento;
	}

	@Override
	public String toString() {
		return "ContaPoupanca [rendimento=" + rendimento + ", getRendimento()=" + getRendimento() + ", getCpf()="
				+ getCpf() + ", getSaldo()=" + getSaldo() + ", getNumeroConta()=" + getNumeroConta() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}