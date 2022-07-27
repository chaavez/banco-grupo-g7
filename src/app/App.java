package app;

import java.io.IOException;
import java.util.Scanner;

import entities.ContaEspecial;
import entities.ContaCorrente;
import entities.ContaEmpresa;
import entities.ContaPoupanca;

public class App {
	public static void main(String[] args) throws IOException {
		menu();
	}

	private static void menu() throws IOException {
		Scanner sc = new Scanner(System.in);
		int opcao;

		do {
			cabecalho();
			System.out.println("MENU DE OPÇÕES");
			System.out.println("[1] - CONTA POUPANÇA");
			System.out.println("[2] - CONTA CORRENTE");
			System.out.println("[3] - CONTA ESPECIAL");
			System.out.println("[4] - CONTA EMPRESA");
			System.out.println("[5] - CONTA ESTUDANTIL");
			System.out.println("[6] - SAIR");
			System.out.print("Escolha a opção: ");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				;
				break;

			case 2:
				contaCorrente(sc);
				break;

			case 3:
				contaEspecial(sc);
				;
				break;

			case 4:
				contaEmpresa(sc);
				break;

			case 5:
				;
				break;

			default:
				if (opcao != 6) {
					System.out.println("\nOpção inválida!");
					System.out.println("Escolha uma opção na lista!");
					System.in.read();
				}
			}
		} while (opcao != 6);
		System.out.println("\nAplicação Encerrada");
		System.out.println("Obrigado por usar o nosso Banco! \nEstamos aqui para Simplificar sua vida!");
		System.in.read();
		sc.close();
	}

	private static void cabecalho() {
		System.out.println();
		System.out.println("BANCO MUNDIAL G7");
		System.out.println("Simplificando sua vida\n\n");
	}

	// SET NUMERO CONTA
	public static int setNum(Scanner sc) {
		System.out.println("Digite seu numero: ");
		int num = sc.nextInt();

		return num;
	}

	// SET CPF CONTA
	public static String setCpf(Scanner sc) {
		System.out.println("Digite seu cpf: ");
		String cpf = sc.next();

		return cpf;
	}

	private static void contaEmpresa(Scanner sc) throws IOException {

		ContaEmpresa movimento = new ContaEmpresa(2828, "98765432112");

		int contador = 1;
		String continuar;

		cabecalho();
		System.out.println();
		System.out.println("CONTA EMPRESA");
		System.out.println("Saldo Atual:" + movimento.getSaldo());

		do {
			System.out.print("Movimento 'D' débito ou 'C' crédito: ");
			String op = sc.next().trim().toLowerCase().substring(0, 1);

			if (op.equals("d")) {
				System.out.print("Valor movimento: R$");
				double valor = sc.nextDouble();
				movimento.debito(valor);

			} else if (op.equals("c")) {
				System.out.print("Valor movimento: R$");
				double valor2 = sc.nextDouble();
				movimento.credito(valor2);

			} else if (op != "d" && op != "c") {
				System.out.print("Digite 'C' ou 'D': ");
			}

			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10 || continuar.equalsIgnoreCase("N")) {
				System.out.print("Vc topa um emprestimo? Vc tem R$" + movimento.getEmprestimoEmpresa()
						+ " liberado!!! Vai pegar quanto? S/N?");
				continuar = sc.next().trim().substring(0, 1);
				contador = 1;

				if (continuar.equalsIgnoreCase("S")) {
					System.out.print("Valor do Emprestimo: ");
					double valorEmprestimo = sc.nextDouble();
					movimento.pedirEmprestimo(valorEmprestimo);
				}

				System.out.print("Continuar S/N: ");
				continuar = sc.next().trim().toLowerCase().substring(0, 1);

			}

		} while (continuar.equalsIgnoreCase("s"));
		System.out.println("Saldo Atual:" + movimento.getSaldo());

		menu();

	}

	public static void contaEspecial(Scanner sc) {

		// INPUT VALORES CLIENTE
		int num = setNum(sc);
		String cpf = setCpf(sc);
		double limite = ContaEspecial.getLimite();

		// CRIA NOVO CLIENTE
		ContaEspecial ctEspecial = new ContaEspecial(num, cpf, limite);
		double saldo = ctEspecial.getSaldo();
		System.out.println("\nSaldo Inicial: " + saldo);
		System.out.println("Limite Inicial: " + limite);

		ctEspecial.movimento(sc, ctEspecial);

	}

	private static void contaCorrente(Scanner sc) {
		ContaCorrente cc = new ContaCorrente(0, null);

		int contador = 1;
		String continuar;

		System.out.println("BANCO MUNDIAL G7 \n" + "Simplificando sua vida \n" + "\n" + "CONTA CORRENTE" + "\n"
				+ "Saldo: " + cc.getSaldo());

		// INSERÇÃO DE DADOS REFERENTE A DÉBITO E CRÉDITO
		do {
			System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
			String op = sc.next().trim().toLowerCase().substring(0, 1);

			if (op.equals("d")) {
				System.out.print("Valor movimento: R$");
				double valor = sc.nextDouble();
				cc.debito(valor);

			} else if (op.equals("c")) {
				System.out.print("Valor movimento: R$");
				double valor2 = sc.nextDouble();
				cc.credito(valor2);

			} else if (op != "d" && op != "c") {
				System.out.print("Digite \'C'\" ou \'D'\": ");
			}

			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10) {
				break;
			}

		} while (continuar.equalsIgnoreCase("s"));

		// ESCOLHA NA OPÇÃO DE QUERER OU NÃO OS CHEQUES
		System.out.print("\n Você possui até 3 cheques disponíveis liberados! Deseja solicitar? (S/N)");
		continuar = sc.next().trim().substring(0, 1);

		// INPUT PARA A CONDIÇÃO DO MÉTODO
		if (continuar.equalsIgnoreCase("s")) {
			System.out.print("\n Quantidade de cheques: ");
			cc.contadorTalao = sc.nextInt();
			cc.liberaCheque();
		}
		;

		// RETORNO COM O CÁLCULO DOS INPUTS DÉBITO E CRÉDITO COM A DIFERENÇA DA ESCOLHA
		System.out.println("Saldo:" + cc.getSaldo());

	}

	private static void contaPoupanca(Scanner sc) {

		int num = setNum(sc);
		String cpf = setCpf(sc);

		System.out.println("Digite o dia de criacao de conta: ");
		int diaAniversario = sc.nextInt();

		ContaPoupanca ctPoupanca = new ContaPoupanca(num, cpf, diaAniversario);

		ContaPoupanca ctPoupanca = new ContaPoupanca(num, cpf, diaAniversario, true);

		ctPoupanca.tipoConta();

		ctPoupanca.movimento(sc, ctPoupanca);

	}

}
