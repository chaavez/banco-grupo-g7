package entities;

	//ANDERSON CHAVES DE MORAIS

public abstract class Conta {

	private int numero;
	private String cpf;
	private double saldo = 0;
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

	public double debito (double valor) {
		Double novoSaldo = saldo - valor;
		
		if(novoSaldo < 0) {			
			return saldo;
		}	
		
		saldo = novoSaldo;
		return novoSaldo;
	}
	public double credito (double valor) {
		return saldo += valor;
	}
}