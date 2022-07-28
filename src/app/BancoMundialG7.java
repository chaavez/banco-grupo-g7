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

	// SET NUMERO DA CONTA
	private int setNum() {
		System.out.println("Digite o numero da conta: ");
		int numero = scanner.nextInt();

		return numero;
	}

	// SET CPF CONTA
	private String setCpf() {
		System.out.println("Digite seu cpf ou cnpj: ");
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
				contaPoupanca();
				break;

			case 2:
				// contaCorrente();
				break;

			case 3:
				acessarContaEspecial();
				break;

			case 4:
				acessarContaEmpresa();
				break;

			case 5:
				// contaEstudantil();
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

	private void acessarContaEmpresa() {
		if (contaEmpresa == null) {
			int numero = setNum();
			String cpf = setCpf();
			contaEmpresa = new ContaEmpresa(numero, cpf);
		}
		if (!contaEmpresa.isAtivo()) {
			System.out.println("Sua conta está inativa, deseja ativar?");
			System.out.print("S/N: ");
			String ativacao = scanner.next().trim().toLowerCase().substring(0, 1);
			if (ativacao.equals("s")) {
				contaEmpresa.setAtivo(true);
			} else if (ativacao.equals("n")) {
				menu();
			}
		}

		int contador = 1;
		String continuar = "";
		String op = "";

		cabecalho();
		contaEmpresa.mostrarNomeConta();

		do {

			do {
				op = contaEmpresa.mostrarOpcaoDebitoCredito(scanner);

				if (op.equals("d")) {
					contaEmpresa.debitar(op, scanner);

				}
				if (op.equals("c")) {
					contaEmpresa.creditar(op, scanner);

				}
				if (!op.equals("d") && !op.equals("c")) {
					contaEmpresa.exibirErroDigitacao();
				}

			} while (!op.equals("d") && !op.equals("c"));

			do {
				System.out.print("Continuar S/N: ");
				continuar = scanner.next().trim().toLowerCase().substring(0, 1);

				// oferecer empr�stimo ap�s 10 movimentos ou se o usu�rio escolher continuar n�o
				if (contador % 10 == 0 || continuar.equals("n")) {
					do {
						continuar = contaEmpresa.oferecerEmprestimo(continuar, scanner);
					} while (!continuar.equals("s") && !continuar.equals("n"));

					if (continuar.equals("s")) {
						contaEmpresa.setarValorEmprestimo(scanner);
					}

					System.out.print("Continuar S/N: ");
					continuar = scanner.next().trim().toLowerCase().substring(0, 1);
				}

				if (!continuar.equals("s") && !continuar.equals("n")) {
					contaEmpresa.exibirErroDigitacao();
				}
				contador++;

			} while (!continuar.equals("s") && !continuar.equals("n"));

		} while (continuar.equalsIgnoreCase("s"));
		contaEmpresa.mostrarSaldo();
		System.out.println();

		menu();

	}

	public void acessarContaEspecial() {
		
		double limite = contaEspecial.getLimite();
		
		if (contaEspecial == null) {
			int numero = setNum();
			String cpf = setCpf();
			contaEspecial = new ContaEspecial(numero, cpf, limite);
		}
		if (!contaEspecial.isAtivo()) {
			System.out.println("Sua conta est� inativa, deseja ativar?");
			System.out.print("S/N: ");
			String ativacao = scanner.next().trim().toLowerCase().substring(0, 1);
			if (ativacao.equals("s")) {
				contaEspecial.setAtivo(true);
			} else if (ativacao.equals("n")) {
				menu();
			}
		}

		//VALORES INICIAIS CLIENTE
		double saldo = contaEspecial.getSaldo();
		System.out.println("\nSaldo Inicial: " + saldo);
		System.out.println("Limite Inicial: " + limite);
		
		contaEspecial.movimento(scanner, contaEspecial);
		
	}
	
	
	private void contaPoupanca() {
		
		if (contaPoupanca == null) {
			int numero = setNum();
			String cpf = setCpf();
			contaEmpresa = new ContaEmpresa(numero, cpf);
		}
		
		contaPoupanca.tipoConta();
		contaPoupanca.movimento(scanner, contaPoupanca);
		menu();
		
	}
	// DEMAIS CONTAS
}
