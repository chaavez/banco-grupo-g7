package entities;

import java.util.Scanner;

//HERICK SIMON NUNES BANDEIRA

public class ContaEstudantil extends Conta {
	
	Scanner sc = new Scanner(System.in);
	int contador = 0;
	String op;
	double valorDevido;
	
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
	
	public void mostrarNomeConta() {
		System.out.println("CONTA ESTUDANTIL");
		mostrarSaldo();
	}

	public void mostrarSaldo() {
		System.out.printf("Saldo Atual: R$%.2f%n", getSaldo());
	}
	
	@Override
	public void debito(double valor) {		
		if (valor <= getSaldo()) {
			super.debito(valor);			
		} else {
			System.out.println("Saldo Insuficiente!");
		}
	}
	
	public void debitar(Scanner sc) {
		System.out.print("Valor do pagamento: R$");
		double valor = sc.nextDouble();
		debito(valor);
		contador++;
		mostrarSaldo();
	}

	public void creditar(Scanner sc) {
		System.out.print("Valor do depósito: R$");
		double valor = sc.nextDouble();
		credito(valor);
		contador++;
		mostrarSaldo();
	}

	private void SolicitarEmprestimoEstudantil(double valor) {
		if (valor <= emprestimoDisponivel) {
			emprestimoDisponivel -= valor;
			credito(valor);
			contador++;
			System.out.println("O valor solicitado já foi depositado na sua conta\n!");
			mostrarSaldo();
		} else {
			System.out.println("Saldo para empréstimo insuficiente!");
			setarValorEmprestimo(sc);
		}
	}

	public void oferecerEmprestimo(Scanner sc) {
		System.out.printf("Valor pré-aprovado para empréstimo: ");
		System.out.printf("R$%.2f", emprestimoDisponivel);
		do {
			System.out.print("\nGostaria de solicitar um empréstimo? \nS(SIM) ou N(NÃO): ");
			op = sc.next().trim().toLowerCase().substring(0, 1);
	
			if (op.equals("s")) {
				System.out.print("\nValor do Emprestimo: ");
				double valorEmprestimo = sc.nextDouble();
				SolicitarEmprestimoEstudantil(valorEmprestimo);
			} else if (op.equals("n")) {
				break;
			} else {
				System.out.println("Opção inválida!");
			}
		}while(op != "n");
		
	}

	public void setarValorEmprestimo(Scanner sc) {
		System.out.printf("Valor pré-aprovado para empréstimo: ");
		System.out.printf("R$%.2f", emprestimoDisponivel);
		System.out.print("\nValor do Emprestimo: R$");
		double valorEmprestimo = sc.nextDouble();
		SolicitarEmprestimoEstudantil(valorEmprestimo);
	}
	
	public void verificaContador() {
		if (contador == 10) {
			oferecerEmprestimo(sc);
			contador = 0;
		}
	}
	
	public void pagarEmprestimo() {
		valorDevido = ((5000 - emprestimoDisponivel) + ((5000 - emprestimoDisponivel)*0.0169));
		System.out.printf("Valor para a quitação do empréstimo: ");
		System.out.printf("R$%.2f", valorDevido );
		do {
			System.out.print("\nGostaria de quitar o empréstimo? \nS(SIM) ou N(NÃO): ");
			op = sc.next().trim().toLowerCase().substring(0, 1);
	
			if (op.equals("s")) {
				debito(valorDevido);
				setEmprestimoDisponivel(5000 - emprestimoDisponivel);
				break;
			} else if (op.equals("n")) {
				break;
			} else {
				System.out.println("Opção inválida!");
			}
		}while(op != "n");
	}
	
	public void desativarConta() {
		if (emprestimoDisponivel == 5000) {
			if (getSaldo() == 0) {
				setStatusConta(!isStatusConta());
			} else{
				System.out.println("A conta não pode ser desativada com saldo!\n\n");
			}
		} else{
			System.out.println("Existe empréstimo ativo nessa conta!\n\n");
		}
	}
}
