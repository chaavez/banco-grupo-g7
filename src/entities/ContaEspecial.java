package entities;

public class ContaEspecial extends Conta {
	/* (+) usarLimite (soma no saldo e tira do limite) */
	private static double limite = 1000.00;
	private static double saldo = 0;

	public ContaEspecial(int numero, String cpf, double saldo, double limite) {
		super(numero, cpf, saldo);
	}

	// GETTERS AND SETTERS
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		ContaEspecial.saldo = saldo;
	}

	public static double getLimite() {
		return limite;
	}

	public static void setLimite(double limite) {
		ContaEspecial.limite = limite;
	}

	// METHOD
	public static double usarLimite(double valor, double saldo) {
		double saldoComLimite = valor;
		double novoLimite = limite - (valor - saldo);
		System.out.println(
				"Você està utilizando seu limite | Saldo atual: " + saldoComLimite + " | Limite atual: " + novoLimite);
		System.out.println();
		System.out.println("Debito concluido!");

		double novoSaldo = valor - saldoComLimite;
		System.out.println("Saldo: " + novoSaldo + " | Limite: " + novoLimite);

		return novoSaldo;
		

	}
}