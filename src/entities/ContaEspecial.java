package entities;

public class ContaEspecial extends Conta {
	/* (+) usarLimite (soma no saldo e tira do limite) */
	private static double limite = 1000.00;

	public ContaEspecial(int numero, String cpf) {
		super(numero, cpf);
	}

	// GETTERS AND SETTERS
	public static double getLimite() {
		return limite;
	}

	public static void setLimite(double limite) {
		ContaEspecial.limite = limite;
	}

	// METHOD
<<<<<<< HEAD
	public static double usarLimite(double valor, double saldo) {
		double saldoComLimite = valor;
		double novoLimite = limite - (valor - saldo);
		System.out.println(
				"Voc� est� utilizando seu limite | Saldo atual: " + saldoComLimite + " | Limite atual: " + novoLimite);
=======
	public double usarLimite(double valor) {
		
		double novoLimite = limite - (valor - this.saldo);
		System.out.println("Voce esta utilizando seu limite | Saldo atual: " + valor + " | Limite atual: " + novoLimite);
>>>>>>> 7ae8c678676dc3a77bbea2fc450b65ac931ca90d
		System.out.println();
		System.out.println("Debito concluido!");

		System.out.println("Saldo final: 0 "  + " | Limite final: " + novoLimite);
		
		this.saldo = 0;
		limite = novoLimite;
		
		return novoLimite;

	}
}