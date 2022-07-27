package entities;

//HERICK SIMON NUNES BANDEIRA

public class ContaEstudantil extends Conta {
	
	private double emprestimoDisponivel = 5000;
	private boolean statusConta = false;

	public ContaEstudantil(int numero, String cpf) {
		super(numero, cpf);
	}
	
	public double getEmprestimoDisponivel() {
		return emprestimoDisponivel;
	}

	public void setEmprestimoDisponivel(double valor) {
		this.emprestimoDisponivel += valor;
	}

	public boolean isStatusConta() {
		return statusConta;
	}

	public void setStatusConta(boolean statusConta) {
		this.statusConta = statusConta;
	}

	private void SolicitarEmprestimoEstudantil(double valor) {
		if (valor <= emprestimoDisponivel) {
			emprestimoDisponivel -= valor;
			credito(valor);
		}
	}
	
}
