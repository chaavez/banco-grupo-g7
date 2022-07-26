package app;

import java.util.Scanner;

import entities.ContaEspecial;
import entities.ContaCorrente;
import entities.ContaEmpresa;
import entities.ContaPoupanca;

public class App {

	public static void main(String[] args) {
		String continuar;
		int opcao;
		Scanner sc = new Scanner(System.in);

		do {
			opcao = menu(sc);
			switch (opcao) {

			case 1:
				contaPoupanca(sc);
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
					System.out.println("Opção inválida!");
				}
			}
		} while (opcao != 6);
		System.out.println("Aplicação Encerrada");
		sc.close();
	}
	
	private static void cabecalho() {
		System.out.println("BANCO MUNDIAL G7");
		System.out.println("Simplificando sua vida");
		System.out.println();
	}

	private static int menu(Scanner sc) {
		cabecalho();
		System.out.println("MENU DE OPÇÕES");
		System.out.println("[1] - CONTA POUPANÇA");
		System.out.println("[2] - CONTA CORRENTE");
		System.out.println("[3] - CONTA ESPECIAL");
		System.out.println("[4] - CONTA EMPRESA");
		System.out.println("[5] - CONTA ESTUDANTIL");
		System.out.println("[6] - SAIR");
		System.out.print("Escolha a opção: ");
		int opcao = sc.nextInt();
		return opcao;
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
		String cpf = sc.nextLine();

		return cpf;
	}

	private static void contaEmpresa(Scanner sc) {

		ContaEmpresa movimento = new ContaEmpresa();

		int contador = 1;
		String continuar;

		System.out.println("BANCO MUNDIAL G7");
		System.out.println("Simplificando sua vida");
		System.out.println();
		System.out.println("CONTA EMPRESA");
		System.out.println("Saldo:" + movimento.getSaldo());

		do {
			System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
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
				System.out.print("Digite \'C'\" ou \'D'\": ");
			}

			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10) {
				break;
			}

		} while (continuar.equalsIgnoreCase("s"));

		System.out.print("Vc topa um emprestimo? Vc tem R$" + movimento.getEmprestimoEmpresa()
				+ " liberado!!! Vai pegar quanto? S/N?");
		continuar = sc.next().trim().substring(0, 1);

		if (continuar.equalsIgnoreCase("S")) {
			System.out.print("Valor do Emprestimo: ");
			double valorEmprestimo = sc.nextDouble();
			movimento.pedirEmprestimo(valorEmprestimo);
		}

		System.out.println("Saldo:" + movimento.getSaldo());

	}

	public static void contaEspecial(Scanner sc) {

		int contador = 1;
		String continuar;

		// CABECALHO
		System.out.println();
		System.out.println("--------------");
		System.out.println();
		System.out.println("BANCO MUNDIAL G7");
		System.out.println("Simplificando sua vida");
		System.out.println();
		System.out.println("CONTA ESPECIAL");

		// INPUT VALORES CLIENTE
		System.out.println("Digite seu numero: ");
		int num = sc.nextInt();
		System.out.println("Digite seu cpf: ");
		String cpf = sc.next();

		// CRIA NOVO CLIENTE
		ContaEspecial movimento = new ContaEspecial(num, cpf);
		System.out.println("Saldo: " + movimento.getSaldo());
		System.out.println("Limite atual: " + ContaEspecial.getLimite());
		System.out.println();
		System.out.println("--------------");
		System.out.println();
		
		double saldo = movimento.getSaldo();
		System.out.println("Saldo: " + saldo);
		double limite = ContaEspecial.getLimite();
		System.out.println("Limite: " + limite);
		
		// MOVIMENTACOES
		do {
			
			System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
			String op = sc.next().trim().toLowerCase().substring(0, 1);
			
			// DEBITO
			if (op.equals("d")) {
				System.out.println("2 - DEBITO");
				System.out.println("Digite o valor a debitar");
				int valor = sc.nextInt();

				if (valor < saldo) {
					double novoSaldo =  movimento.debito(valor);
					saldo = novoSaldo;
					System.out.println("Saldo: " + novoSaldo + " | Limite: " + limite);
				} else if (valor - saldo > limite) {
					System.out.println("Movimentação negada. Seu limite é de apenas 1000 reais");
				} else if (valor - saldo <= limite) {
//					System.out.println("----Limite: " + limite);
//					System.out.println("----Saldo: " + saldo);
					double novoLimite = movimento.usarLimite(valor);
					saldo = 0;
					limite = novoLimite;
				}

			// CREDITO
			} else if (op.equals("c")) {
				System.out.println("Saldo: " + saldo + " | Limite: " + limite);
				System.out.println("1 - CREDITO");
				System.out.println("Digite o valor a creditar");
				int valor = sc.nextInt();
				double saldoNovo = movimento.credito(valor);
				System.out.println("Saldo atual: " + saldoNovo + " | Limite atual: " + limite);
				saldo = saldoNovo;

			} else if (op != "d" && op != "c") {
				System.out.print("Digite \'C'\" ou \'D'\": ");
			}

			// CONTINUAR OU SAIR
			System.out.println();
			System.out.println("--------------");
			System.out.println();
			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10) {
				break;
			}

		} while (continuar.equalsIgnoreCase("s"));

	}

	private static void contaCorrente(Scanner sc) {
		ContaCorrente cc = new ContaCorrente(0, null);
		
		int contador = 1;
		String continuar;
		
		System.out.println("BANCO MUNDIAL G7 \n"
				+ "Simplificando sua vida \n"
					+ "\n"
					+ "CONTA CORRENTE"
					+ "\n"
					+ "Saldo: " + cc.getSaldo()
				);
		
		//INSERÇÃO DE DADOS REFERENTE A DÉBITO E CRÉDITO
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
		
		//ESCOLHA NA OPÇÃO DE QUERER OU NÃO OS CHEQUES
		System.out.print("\n Você possui até 3 cheques disponíveis liberados! Deseja solicitar? (S/N)");
		continuar = sc.next().trim().substring(0, 1);
		
		//INPUT PARA A CONDIÇÃO DO MÉTODO
			if (continuar.equalsIgnoreCase("s")) {
				System.out.print("\n Quantidade de cheques: ");
				cc.contadorTalao = sc.nextInt();
				cc.liberaCheque();
				};
		
		//RETORNO COM O CÁLCULO DOS INPUTS DÉBITO E CRÉDITO COM A DIFERENÇA DA ESCOLHA
		System.out.println("Saldo:" + cc.getSaldo());
	
	}
	
	

	
	private static void contaPoupanca(Scanner sc) {
		
		int num = setNum(sc);
		String cpf = setCpf(sc);
		
		System.out.println("Digite o dia de criacao de conta: ");
		int diaAniversario = sc.nextInt();
		
		ContaPoupanca ctPoupanca = new ContaPoupanca(num, cpf, diaAniversario);
		
		
		
		
		
		
		
	}
	
	
	
}

