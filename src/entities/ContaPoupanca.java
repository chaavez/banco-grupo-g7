package entities;
import java.util.Scanner;

// AMANDA LUIZA ILG

public class ContaPoupanca extends Conta{
	
	int diaAniversarioPoupanca;
	
	///// GETTERS E SETTERS /////
	public int getDiaAniversarioPoupanca() {
		return diaAniversarioPoupanca;
	}

	public void setDiaAniversarioPoupanca(int diaAniversarioPoupanca) {
		this.diaAniversarioPoupanca = diaAniversarioPoupanca;
	}

	///// TIPO CONTA /////
	public void tipoConta() {
		System.out.println();
		System.out.println("CONTA POUPANÇA");
		System.out.println();
		System.out.println("Saldo: R$ " + getSaldo());
	}
	
	///// Construtor Conta Poupanca /////
	public ContaPoupanca(int numero, String cpf, int diaAniversarioPoupanca, boolean ativo) {
		super(numero, cpf);
		this.setAtivo(ativo);
		this.saldo = 0;
		this.setDiaAniversarioPoupanca(diaAniversarioPoupanca);
	}
	
	///// Metodo para Registro dos Movimentos /////
	public void movimento(Scanner sc, ContaPoupanca ctPoupanca) {
		String continuar;
		int contador = 1;
		
		do {
			System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
			String op = sc.next().trim().toLowerCase().substring(0, 1);
	
			if (op.equals("d")) {
				System.out.print("Valor movimento: R$");
				double debito = sc.nextDouble();
				ctPoupanca.debito(debito);
				ctPoupanca.tipoConta();
	
			} else if (op.equals("c")) {
				System.out.print("Valor movimento: R$");
				double credito = sc.nextDouble();
				ctPoupanca.credito(credito);
				ctPoupanca.tipoConta();

	
			} else if (op != "d" && op != "c") {
				System.out.print("Digite \'C'\" ou \'D'\": ");
			}
	
			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);
	
			contador++;
			
			if (contador >= 10) {
				break;
			}
			
		} while (continuar.equalsIgnoreCase("s"));
		
	}	
	
	
	// Metodo Correcao no Dia do Aniversario da Conta
	public double correcao(double saldo) {
		saldo = saldo * 0.005;
		System.out.print("Parabéns! Sua conta está fazendo aniversário! Você teve uma correção de 0,5% no seu saldo!");
		System.out.print("R$ " + saldo);
		return saldo;
	}

}
