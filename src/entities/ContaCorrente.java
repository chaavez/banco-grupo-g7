package entities;

import java.util.Scanner;

//DEBORA LETICIA RUBIM DE PAIVA

public class ContaCorrente extends Conta {
	Scanner sc = new Scanner(System.in);

	// CONSTRUTOR
	public ContaCorrente(int numero, String cpf) {
		super(numero, cpf);
	}

	public int contadorTalao = 3;

	// GETTERS AND SETTERS
	public int getContadorTalao() {
		return contadorTalao;
	}

	// MÉTODO PARA PREENCHIMENTO DE INFO

	public void preencherDados() {
		double saldo = this.getSaldo();

		int contador = 1;
		String continuar;

		System.out.println("CONTA CORRENTE" + "\n" + "Saldo Atual: " + saldo);

		// INSERÇÃO DE DADOS REFERENTE A DÉBITO E CRÉDITO
		do {
			System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
			String op = sc.next().trim().toLowerCase().substring(0, 1);

			if (op.equals("d")) {
				System.out.print("Valor movimento - débito: R$");
				double valor = sc.nextDouble();

				if (valor <= saldo) {
					super.debito(valor);
				} else {
					System.out.println("Operação Inválida!");
				}

			} else if (op.equals("c")) {
				System.out.print("Valor movimento - crédito: R$");
				double valor2 = sc.nextDouble();
				this.credito(valor2);

			} else if (op != "d" && op != "c") {
				System.out.print("Digite \'C'\" ou \'D'\": ");
				preencherDados();
			}

			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10) {
				break;
			}

		} while (continuar.equalsIgnoreCase("s"));

		// CONDICIONAL PARA LIBERAÇÃO DE CÓDIGO

		while (contadorTalao >= 1) {
			if (getSaldo() > 90) {
				int qtCheque;
				System.out.println("\n Você possui " + contadorTalao
						+ " cheques disponíveis liberados! \n Deseja solicitar? (S/N)");
				continuar = sc.next().trim().toLowerCase().substring(0, 1);
				if (continuar.equalsIgnoreCase("s")) {
					System.out.println("Você deseja quantos talões?");
					qtCheque = sc.nextInt();
					liberarCheque(qtCheque);
					System.out.println("Talão resgatado!");
					return;
				} else {
					return;
				}
			} else if (getSaldo() > 60) {
				int qtCheque;
				System.out.println("\n Você possui " + contadorTalao
						+ " cheques disponíveis liberados! \n Deseja solicitar? (S/N)");
				continuar = sc.next().trim().toLowerCase().substring(0, 1);
				if (continuar.equalsIgnoreCase("s")) {
					System.out.println("Você deseja quantos talões?");
					qtCheque = sc.nextInt();
					liberarCheque(qtCheque);
					System.out.println("Talão resgatado!");
					return;
				} else {
					return;
				}
			} else if (getSaldo() > 30) {
				int qtCheque;
				System.out.println("\n Você possui " + contadorTalao
						+ " cheques disponíveis liberados! \n Deseja solicitar? (S/N)");
				continuar = sc.next().trim().toLowerCase().substring(0, 1);
				if (continuar.equalsIgnoreCase("s")) {
					System.out.println("Você deseja quantos talões?");
					qtCheque = sc.nextInt();
					liberarCheque(qtCheque);
					//System.out.println("Talão resgatado!");
					return;
				} else {
					return;
				}
			}
		}

	}

	// METODO PARA DESCONTO DO TALAO
	public void liberarCheque(int ctTalao) {
		contadorTalao -= ctTalao;
		debito(30 * ctTalao);
	}

	// METODO PARA RESULTADO
	public void mostrarResultado() {
		System.out.println("Saldo Atual:" + getSaldo());
	}
	@Override
	public void debito(double valor) {		

		if (valor <= getSaldo()) {
			super.debito(valor);			
		} else {
			System.out.println("Saldo insuficiente");
		}
	}	

}