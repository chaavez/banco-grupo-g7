package app;

import java.util.Scanner;

import entities.ContaCorrente;
import entities.ContaEmpresa;
import entities.ContaEspecial;
import entities.ContaEstudantil;
import entities.ContaPoupanca;

public class BancoMundialG7 {
	private static Scanner scanner = new Scanner(System.in);
	private ContaPoupanca contaPoupanca;
	private ContaCorrente contaCorrente;
	private ContaEmpresa contaEmpresa;
	private ContaEspecial contaEspecial;
	private ContaEstudantil contaEstudantil;

	public void criarContas() {
		int numero = setNum();
		String cpf = setCpf();
		//contaPoupanca = new ContaPoupanca(numero, cpf, 1, false);
		contaCorrente = new ContaCorrente(numero, cpf);
		contaEmpresa = new ContaEmpresa(numero, cpf);
		contaEspecial = new ContaEspecial(numero, cpf, 1);
		contaEstudantil = new ContaEstudantil(numero, cpf);
	}

	// SET NUMERO DA CONTA
	private int setNum() {
		System.out.println("Digite seu numero: ");
		int numero = scanner.nextInt();

		return numero;
	}

	// SET CPF CONTA
	private String setCpf() {
		System.out.println("Digite seu cpf: ");
		String cpf = scanner.next();

		return cpf;
	}

	public void menu() {
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
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				//contaPoupanca();
				break;

			case 2:
				//contaCorrente();
				break;

			case 3:
				//contaEspecial();
				break;

			case 4:
				contaEmpresa();
				break;

			case 5:
				//contaEstudantil();
				break;

			default:
				if (opcao != 6) {
					System.out.println("\nOpção inválida!");
					System.out.println("Escolha uma opção na lista!");
				}
			}
		} while (opcao != 6);
		System.out.println("\nAplicação Encerrada");
		System.out.println("Obrigado por usar o nosso Banco! \nEstamos aqui para Simplificar sua vida!");
		scanner.close();
	}

	private void cabecalho() {
		System.out.println();
		System.out.println("BANCO MUNDIAL G7");
		System.out.println("Simplificando sua vida\n\n");
	}

	private void contaEmpresa() {

		int contador = 1;
		String continuar;

		cabecalho();
		System.out.println();
		System.out.println("CONTA EMPRESA");
		System.out.println("Saldo Atual:" + contaEmpresa.getSaldo());

		do {
			System.out.print("Movimento 'D' débito ou 'C' crédito: ");
			String op = scanner.next().trim().toLowerCase().substring(0, 1);

			if (op.equals("d")) {
				System.out.print("Valor movimento: R$");
				double valor = scanner.nextDouble();
				contaEmpresa.debito(valor);

			} else if (op.equals("c")) {
				System.out.print("Valor movimento: R$");
				double valor2 = scanner.nextDouble();
				contaEmpresa.credito(valor2);

			} else if (op != "d" && op != "c") {
				System.out.print("Digite 'C' ou 'D': ");
			}

			System.out.print("Continuar S/N: ");
			continuar = scanner.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10 || continuar.equalsIgnoreCase("N")) {
				System.out.print("Vc topa um emprestimo? Vc tem R$" + contaEmpresa.getEmprestimoEmpresa()
						+ " liberado!!! Vai pegar quanto? S/N?");
				continuar = scanner.next().trim().substring(0, 1);
				contador = 1;

				if (continuar.equalsIgnoreCase("S")) {
					System.out.print("Valor do Emprestimo: ");
					double valorEmprestimo = scanner.nextDouble();
					contaEmpresa.pedirEmprestimo(valorEmprestimo);
				}

				System.out.print("Continuar S/N: ");
				continuar = scanner.next().trim().toLowerCase().substring(0, 1);

			}

		} while (continuar.equalsIgnoreCase("s"));
		System.out.println("Saldo Atual:" + contaEmpresa.getSaldo());

		menu();

	}
	
	// DEMAIS CONTAS
}

