package entities;

import java.util.Scanner;

public class ContaEspecial extends Conta {

	/* (+) usarLimite (soma no saldo e tira do limite) */
	private static double limite = 1000.00;

	// CONSTRUTOR CONTA ESPECIAL
	public ContaEspecial(int numero, String cpf, double limite) {
		super(numero, cpf);
	}

	// GETTERS AND SETTERS
	public static double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		ContaEspecial.limite = limite;
	}

////	@Override
//	public void debito (double valor) {
//			this.setSaldo(this.getSaldo() - valor);
//	}

	// METHOD MOVIMENTO
	public void movimento(Scanner sc, ContaEspecial ctEspecial) {
		String continuar;
		int contador = 1;

		do {
			System.out.print("\n" + contador + "- Movimento \'D'\" débito ou \'C'\" crédito: ");

			String op = sc.next().trim().toLowerCase().substring(0, 1);

			// DEBITO
			if (op.equals("d")) {
				System.out.println("Digite o valor a debitar: ");
				double debito = sc.nextInt();

				if (debito < getSaldo()) {
					ctEspecial.debito(debito);
					System.out.println("Saldo: " + getSaldo() + " | Limite mensal: " + getLimite());

				} else if (debito - getSaldo() > getLimite()) {
					System.out.println("Movimentação negada. Seu limite esta em " + getLimite()
							+ "reais e seu saldo esta em: " + getSaldo() + "reais");

				} else if (debito - getSaldo() <= getLimite()) {
					double novoLimite = ctEspecial.usarLimite(debito, ctEspecial);
					limite = novoLimite;
				}

			// CREDITO
			} else if (op.equals("c")) {
				System.out.println("Digite o valor a creditar: ");
				int credito = sc.nextInt();
				ctEspecial.credito(credito);
				System.out.println("Saldo atual: " + getSaldo() + " | Limite mensal: " + getLimite());

			} else if (op != "d" && op != "c") {
				System.out.print("Digite \'C'\" ou \'D'\": ");
			}

			System.out.print("\nContinuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;

			if (contador >= 10 && getSaldo()<0 ){
				System.out.println("Apos 10 movimentacoes o saldo nao pode ser negativo!");
				System.out.println("Saldo atual: " + getSaldo() + " | Limite mensal: " + getLimite());
			}

		} while (continuar.equalsIgnoreCase("s"));
	}

	// METHOD USAR LIMITE
	public double usarLimite(double valor, ContaEspecial ctEspecial) {

		double novoLimite = getLimite() - (valor - getSaldo());
		ctEspecial.debito(getSaldo());

		System.out.println("Voce esta utilizando seu limite | Saldo atual: " + valor + " | Limite mensal: " + novoLimite);
		System.out.println("\nDebito concluido!");
		System.out.println("Saldo final: " + getSaldo() + " | Limite mensal: " + novoLimite);

		return novoLimite;
	}
}