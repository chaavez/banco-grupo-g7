package entities;

import java.util.Scanner;

public class ContaEspecial extends Conta {

	/* (+) usarLimite (soma no saldo e tira do limite) */
	private static double limite = 1000.00;

	Scanner sc = new Scanner(System.in);
	
	// CONSTRUTOR CONTA ESPECIAL
	public ContaEspecial(int numero, String cpf, double limite) {
		super(numero, cpf);
	}

	// GETTERS AND SETTERS
	public static double getLimite() {
		return limite;
	}

	// METHOD MOVIMENTO
	public void movimento(Scanner sc, ContaEspecial ctEspecial) {
		String continuar = "";
		int contador = 1;
		String op = "";

		do {
			
			do {
				System.out.print("\n" + contador + "- Movimento \'D' débito ou \'C' crédito: ");
				op = sc.next().trim().toLowerCase().substring(0, 1);

				// DEBITO
				if (op.equals("d")) {
					double debito = ctEspecial.checarValorDigitado();
										
					if (debito < getSaldo()) {
						ctEspecial.debito(debito);
						System.out.println("Saldo: " + getSaldo() + " | Limite mensal: " + getLimite());

					} else if (debito - getSaldo() <= getLimite()) {
						
						//VERIFICACAO DE SALDO APOS 10 MOVIMENTOS
						if (contador == 10 && getSaldo() - debito < 0) {
							System.out.println("Apos seus 10 movimentos, seu saldo nao pode ficar negativo!");
							break;
						} else {
							double novoLimite = ctEspecial.usarLimite(debito, ctEspecial);
							limite = novoLimite;
						}
						
					} else if (debito - getSaldo() > getLimite()) {
						System.out.println("Movimentação negada. Seu limite esta em " + getLimite()
								+ "reais e seu saldo esta em: " + getSaldo() + "reais");
						break;
					} 

				// CREDITO
				} else if (op.equals("c")) {
					double credito = ctEspecial.checarValorDigitado();
					
					//VERIFICACAO DE SALDO APOS 10 MOVIMENTOS
					if (contador == 10 && getSaldo() + credito < 0) {
						System.out.println("Apos seus 10 movimentos, seu saldo nao pode ficar negativo!");
						break;
					}
					
					ctEspecial.credito(credito);
					System.out.println("Saldo atual: " + getSaldo() + " | Limite mensal: " + getLimite());

				} else if (op != "d" && op != "c") {
					System.out.print("\nErro! Informe \'D'ou \'C'!");
				} 
				
				contador ++;

			} while (!op.equals("d") && !op.equals("c"));

			do {
				System.out.print("\nContinuar S/N: ");
				continuar = sc.next().trim().toLowerCase().substring(0, 1);

				if (!continuar.equals("s") && !continuar.equals("n")) {
					System.out.print("\nErro! Informe \'S' ou \'N'!");
				}

			} while (!continuar.equals("s") && !continuar.equals("n"));

			if (contador > 10) {
				System.out.println("Voce atingiu seus 10 movimentos! Sistema encerrado");
				break;
			}

		} while (continuar.equalsIgnoreCase("s"));
	}

	// METHOD USAR LIMITE
	public double usarLimite(double valor, ContaEspecial ctEspecial) {

		double novoLimite = getLimite() - (valor - getSaldo());
		ctEspecial.debito(valor);

		System.out
				.println("Voce esta utilizando seu limite | Saldo atual: " + valor + " | Limite mensal: " + novoLimite);
		System.out.println("\nDebito concluido!");
		System.out.println("Saldo final: " + getSaldo() + " | Limite mensal: " + novoLimite);

		return novoLimite;
	}
	
	//METHOD CHECAR VALOR 
	public Double checarValorDigitado() {
		Boolean erro;
		Double valor = 0.0;

		do { 
			System.out.print("Valor a movimentar: R$");
			try {
				valor = Double.parseDouble(sc.next().replace(",", "."));
				erro = false; 
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero real positivo");
				erro = true;
			}
		} while (erro);

		return valor;

	}
}