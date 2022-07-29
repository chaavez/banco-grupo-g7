package entities;

import java.util.Scanner;

//SANDRO SENNA DOS ANJOS

public class ContaEmpresa extends Conta {

	// limite de empréstimo 10 mil
	private Double emprestimoEmpresa = 10000.00;

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
			System.out.println("Operação Inválida!");
		}
	}	

	// método soma no saldo e tira do empréstimo
	public void pedirEmprestimo(Double valor) {
		
		
		if (valor > 0 && valor <= emprestimoEmpresa) {
			emprestimoEmpresa -= valor;
			credito(valor);
			System.out.println("Empréstimo efetivado!");
			mostrarSaldo();
		} else {
			System.out.println("Empréstimo não realizado!");
		}		

	}		

	public void mostrarNomeConta() {
		System.out.println("CONTA EMPRESA");
		mostrarSaldo();
	}

	public void mostrarSaldo() {
		System.out.printf("Saldo atual: %.2f%n", getSaldo());
	}

	public String mostrarOpcaoDebitoCredito() {
		System.out.print("Movimento: 'D' para débito ou 'C' para crédito: ");
		String op = sc.next().trim().toLowerCase().substring(0, 1);
		return op;
	}

	public void exibirErroDigitacaoDebitoCredito() {
		System.out.println("Erro! Digite 'D' ou 'C'");
	}

	public void exibirErroDigitacaoSimNao() {
		System.out.println("Erro! Digite 'S' para continuar ou 'N' para sair!");
	}
	
	public void debitar() {
		System.out.print("Valor do débito: R$");
		double valor = sc.nextDouble();
		debito(valor);
		mostrarSaldo();
	}

	public void creditar() {
		System.out.print("Valor do crédito: R$");
		double valor = sc.nextDouble();
		credito(valor);
		mostrarSaldo();
	}

	public String oferecerEmprestimo() {

		System.out.printf("Você tem R$%.2f %s%n", getEmprestimoEmpresa(), "liberado para empréstimo!!!");
		System.out.print("Vai pegar agora? S/N? ");
		String continuar = sc.next().trim().toLowerCase().substring(0, 1);
		return continuar;
	}

	public void setarValorEmprestimo() {		
				
		System.out.print("Valor do empréstimo: ");		
		double valorEmprestimo = sc.nextDouble();
		pedirEmprestimo(valorEmprestimo);
		
	}
	
}
