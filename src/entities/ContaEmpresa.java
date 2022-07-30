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

	// método da ega de negócio que soma no saldo e tira do empréstimo
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

	public Double checarValorDigitado() {
		Boolean erro;
		Double valor = 0.0;

		do { // enquanto não digitar um número válido, continua no while
			System.out.print("Valor do crédito: R$");
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

	public String oferecerEmprestimo() {

		System.out.printf("Você tem R$%.2f %s%n", getEmprestimoEmpresa(), "liberado para empréstimo!!!");
		System.out.print("Vai pegar agora? S/N? ");
		String continuar = sc.next().trim().toLowerCase().substring(0, 1);
		return continuar;
	}

}