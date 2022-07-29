package entities;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.*;

// AMANDA LUIZA ILG

public class ContaPoupanca extends Conta{
	
	int diaAniversarioPoupanca;
	DecimalFormat formato = new DecimalFormat("0.00");
	
	///// GETTERS E SETTERS /////
	public int getDiaAniversarioPoupanca() {
		return diaAniversarioPoupanca;
	}

	public void setDiaAniversarioPoupanca(int diaAniversarioPoupanca) {	
			this.diaAniversarioPoupanca = diaAniversarioPoupanca;
	}

	///// TIPO CONTA /////
	public void mostrarTipoConta() {
		System.out.println();
		System.out.println("CONTA POUPANÇA");
		System.out.println();
		System.out.println("Saldo: R$ " + formato.format(getSaldo()) + "\n");
		System.out.println();
	}
	
	///// CONSTRUTOR CONTA POUPANCA /////
	public ContaPoupanca(int numero, String cpf) {
		super(numero, cpf);
		//this.setAtivo(true);
		this.setDiaAniversarioPoupanca(LocalDate.now().getDayOfMonth());
	}	
	
	
	// METDO CORRECAO DE ANIVERSARIO DE CONTA
	public double correcao() {
		return getSaldo() * 0.005;
	}
	
		
	public void verificaDia(Scanner sc) {
		System.out.println("Digite o dia de hoje: ");
		int dia = sc.nextInt();
		
		if(dia == this.diaAniversarioPoupanca) {
			credito(correcao()); // RETORNA O VALOR DA CORRECAO DE ANIVERSARIO E CREDITA NA CONTA
			System.out.print("Parabéns! Sua conta está fazendo aniversário! Você teve uma correção de 0,05% no seu saldo!\n");
			System.out.println("R$ " + formato.format(getSaldo()) + "\n");
		}
	}	
	
	///// METODO PARA REGISTRO DOS MOVIMENTOS /////
 	public void movimentar(Scanner sc, ContaPoupanca ctPoupanca) {
		String continuar;
		int contador = 1;
		String confirma;
		
		//// VERIFICA SE A CONTA ESTÁ ATIVA PARA REALIZAR MOVIMENTOS
		if(ctPoupanca.isAtivo() == true) {
			do {
				ctPoupanca.verificaDia(sc);
				
				System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
				String op = sc.next().trim().toLowerCase().substring(0, 1);
		
				///// DEBITO
				if (op.equals("d")) {
					System.out.print("Valor movimento: R$");
					double debito = sc.nextDouble();
					System.out.println("Tem certeza que quer realizar o débito de R$ " + debito + "? S/N");
					confirma = sc.next().trim().toLowerCase().substring(0, 1);
					if(confirma.equals("s") && debito >= 0) {
						ctPoupanca.debito(debito);
						System.out.println("Operação realizada!");
					} else {
						System.out.println("Operação cancelada!");
					}
					ctPoupanca.mostrarTipoConta();
		
				///// CREDITO
				} else if (op.equals("c")) {
					System.out.print("Valor movimento: R$");
					double credito = sc.nextDouble();
					System.out.println("Tem certeza que quer realizar o credito de R$ " + credito + "? S/N");
					confirma = sc.next().trim().toLowerCase().substring(0, 1);
					if(confirma.equals("s")) {
						ctPoupanca.credito(credito);
						System.out.println("Operação realizada!");
					} else {
						System.out.println("Operação cancelada!");
					}
					ctPoupanca.mostrarTipoConta();
	
		
				} else if (!op.equals("d") && !op.equals("c")) {
					System.out.print("Digite \'C'\" ou \'D'\": ");
				}
				
			    ///// CONTINUAR REALIZANDO MOVIMENTOS NA CONTA
				System.out.print("Continuar S/N: ");
				continuar = sc.next().trim().toLowerCase().substring(0, 1);
		
				contador++;
				
				//// REALIZACAO DOS 10 MOVIMENTOS
				if (contador >= 10) {
					System.out.println("A conta realizou o limite de movimentos!");
					System.out.println("Sua conta será encerrada!");
					System.out.println("O saldo final da conta é de: R$ " + formato.format(getSaldo()));
					System.out.println();
					setAtivo(false);
					break;
				}
				
			} while (continuar.equalsIgnoreCase("s"));

		//// VERIFICA SE QUER ATIVAR A CONTA
		} else {
			ctPoupanca.setAtivo(ativarConta(sc));
		}
		
		
	}
 	
 	
 	/// PERGUNTA AO USUARIO SE QUER OU NÃO ATIVAR A CONTA
 	public static boolean ativarConta(Scanner sc) {
 		System.out.println("Deseja ativar sua conta? S/N");
		String ativar = sc.next().trim().toLowerCase().substring(0, 1);
		if(ativar.equals("s")) {
			return true;
		} else {
			return false;
		}

 	}
		
		

}
