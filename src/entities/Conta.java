package entities;

	//ANDERSON CHAVES DE MORAIS

public  class Conta {

	private int numero;
	private String cpf;
	protected double saldo = 0;
	private boolean ativo;
	
	public Conta (int numero, String cpf) {
		this.numero = numero;
		this.cpf = cpf;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void debito (double valor) {
		if (valor <= saldo) {
			this.saldo -= valor;
		} else {
			System.out.println("Saldo insuficiente para saque");
		}
	}
	
	public void credito (double valor) {
		if (valor > 0) {
			this.saldo += valor;
		} else {
			System.out.println("Valor de deposito inválido");
		}
	}
}