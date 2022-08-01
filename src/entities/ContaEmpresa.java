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

		if (valor > 0 && valor <= getSaldo()) {
			super.debito(valor);
		} else {
			System.out.println("Operação Inválida!");
		}
	}

	// método da regra de negócio que soma no saldo e tira do empréstimo
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

	public String exibirMenu() {
		String opcao;
		System.out.println();
		System.out.println("MOVIMENTAÇÃO");
		System.out.println("[C] - CRÉDITO");
		System.out.println("[D] - DÉBITO");
		System.out.println("[E] - EMPRÉSTIMO");
		System.out.println("[S] - SAIR");
		System.out.print("Escolha a opção: ");
		opcao = sc.next().trim().toLowerCase().substring(0, 1);

		return opcao;

	}

	public void acessarCredito(String tipo) {

		exibirNomeMovimento(tipo);
		Double valor = checarValorDigitado();
		creditar(valor);

	}

	public void acessarDebito(String tipo) {

		exibirNomeMovimento(tipo);
		Double valor = checarValorDigitado();
		debitar(valor);
	}

	public void acessarEmprestimo(String tipo) {
		String aux;

		if (checarValorDisponívelEmprestimo()) {
			System.out.println("Você já utilizou todo limite disponível!");
		} else {

			aux = oferecerEmprestimo();

			while (!aux.equals("s") && !aux.equals("n")) {
				exibirErroDigitacaoSimNao();
				aux = continuarMovimentacao(tipo);
			}

			exibirNomeMovimento(tipo);
			Double valor = checarValorDigitado();
			pedirEmprestimo(valor);
		}
	}

	public String continuarMovimentacao(String continuar) {
		System.out.print("Continuar movimentação? S/N: ");
		continuar = sc.next().trim().toLowerCase().substring(0, 1);
		return continuar;
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

	public void exibirNomeMovimento(String tipo) {

		if (tipo.equalsIgnoreCase("d")) {
			System.out.print("Débito ");
		}

		if (tipo.equalsIgnoreCase("c")) {
			System.out.print("Crédito ");
		}

		if (tipo.equalsIgnoreCase("e")) {
			System.out.print("Empréstimo ");

		}

	}

	public Double checarValorDigitado() {
		Boolean erro;
		Double valor = 0.0;

		do { // enquanto não digitar um número válido, continua no while
			System.out.print("Valor: R$");
			try {
				valor = Double.parseDouble(sc.next().replace(",", "."));
				erro = false; // se chegou aqui é porque o número é válido
			} catch (NumberFormatException e) {
				// numero inválido
				System.out.println("Digite um numero real positivo");
				erro = true;
			}
		} while (erro);

		return valor;

	}

	public void debitar(Double valor) {

		debito(valor);
		mostrarSaldo();
	}

	public void creditar(Double valor) {
		credito(valor);
		mostrarSaldo();
	}

	public Boolean checarValorDisponívelEmprestimo() {

		if (getEmprestimoEmpresa() == 0) {
			return true;
		}
		return false;
	}

	public String oferecerEmprestimo() {

		System.out.printf("Você tem R$%.2f %s%n", getEmprestimoEmpresa(), "liberado para empréstimo!!!");
		System.out.print("Vai pegar agora? S/N? ");
		String continuar = sc.next().trim().toLowerCase().substring(0, 1);
		return continuar;
	}

}