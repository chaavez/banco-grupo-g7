package entities;

import java.util.Scanner;

//SANDRO SENNA DOS ANJOS

public class ContaEmpresa extends Conta {

	// limite de empr�stimo 10 mil
	private Double emprestimoEmpresa = 10000d;

	Scanner sc = new Scanner(System.in);

	public ContaEmpresa(int numero, String cpf) {
		super(numero, cpf);
	}

	public Double getEmprestimoEmpresa() {
		return emprestimoEmpresa;
	}

	@Override
	public void debito(double valor) {		

		if (valor <= getSaldo()) {
			super.debito(valor);			
		} else {
			System.out.println("Opera��o Inv�lida!");
		}
	}	

	// m�todo soma no saldo e tira do empr�stimo
	public void pedirEmprestimo(Double valor) {

		if (valor > 0 && valor <= emprestimoEmpresa) {
			emprestimoEmpresa -= valor;
			credito(valor);
			System.out.println("Empr�stimo efetivado!");
			mostrarSaldo();
		} else {
			System.out.println("Empr�stimo n�o realizado!");
		}

	}

	public void mostrarNomeConta() {
		System.out.println("CONTA EMPRESA");
		mostrarSaldo();
	}

	public void mostrarSaldo() {
		System.out.printf("Saldo Atual: %.2f%n", getSaldo());
	}

	public String mostrarOpcaoDebitoCredito(Scanner sc) {
		System.out.print("Movimento 'D' d�bito ou 'C' cr�dito: ");
		String op = sc.next().trim().toLowerCase().substring(0, 1);
		return op;
	}

	public void exibirErroDigitacao() {
		System.out.println("Erro! Informe um valor v�lido!");
	}

	public void debitar(String op, Scanner sc) {
		System.out.print("Valor movimento: R$");
		double valor = sc.nextDouble();
		debito(valor);
		mostrarSaldo();
	}

	public void creditar(String op, Scanner sc) {
		System.out.print("Valor movimento: R$");
		double valor = sc.nextDouble();
		credito(valor);
		mostrarSaldo();
	}

	public String oferecerEmprestimo(String continuar, Scanner sc) {

		System.out.printf("Voc� tem R$%.2f %s%n", getEmprestimoEmpresa(), "liberado para empr�stimo!!!");
		System.out.print("Vai pegar quanto? S/N? ");
		continuar = sc.next().trim().toLowerCase().substring(0, 1);
		return continuar;
	}

	public void setarValorEmprestimo(Scanner sc) {
		System.out.print("Valor do Emprestimo: ");
		double valorEmprestimo = sc.nextDouble();
		pedirEmprestimo(valorEmprestimo);
	}

}
