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
	public double usarLimite(double valor) {
		
		//OBS.: Saldo esta pedindo para alterar na conta para protected
		double novoLimite = limite - (valor - this.saldo);
		System.out.println("Voce esta utilizando seu limite | Saldo atual: " + valor + " | Limite atual: " + novoLimite);
		System.out.println();
		System.out.println("Debito concluido!");

		System.out.println("Saldo final: 0 "  + " | Limite final: " + novoLimite);
		//OBS.: Saldo esta pedindo para alterar na conta para protected
		this.saldo = 0;
		limite = novoLimite;
		
		return novoLimite;

	}
}