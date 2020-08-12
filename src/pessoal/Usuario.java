package pessoal;

public abstract class Usuario {

	private String agencia;
	private String nome;
	private String cpf;
	private String categoria;
	private int senha;

	public Usuario() {
	};
	

	public Usuario(String agencia, String nome, String cpf, String categoria, int senha) {
		super();
		this.agencia = agencia;
		this.nome = nome;
		this.cpf = cpf;
		this.categoria = categoria;
		this.senha = senha;
	}


	public String getAgencia() {
		return agencia;
	}


	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public int getSenha() {
		return senha;
	}


	public void setSenha(int senha) {
		this.senha = senha;
	}


	@Override
	public String toString() {
		return "Usuario [agencia=" + agencia + ", nome=" + nome + ", cpf=" + cpf + ", categoria=" + categoria
				+ ", senha=" + senha + "]";
	}


}