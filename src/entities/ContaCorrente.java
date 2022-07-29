package entities;

import java.util.Scanner;

//DEBORA LETICIA RUBIM DE PAIVA

public class ContaCorrente extends Conta {
	Scanner sc = new Scanner(System.in);
	
	//CONSTRUTOR
	public ContaCorrente(int numero, String cpf) {
		super(numero, cpf);
	}

	public int contadorTalao = 1;
	
	// GETTERS AND SETTERS
	public int getContadorTalao() {
		return contadorTalao;
	}
	public void setContadorTalao(int contadorTalao) {
		this.contadorTalao = contadorTalao;
	}
	
	//MOSTRAR RESULTADO

	
	//MÉTODO PARA PREENCHIMENTO DE INFO
	
	public void preenchimento() {
		double saldo = this.getSaldo();
		
		int contador = 1;
		String continuar;
		
		System.out.println("CONTA CORRENTE" + "\n"
				+ "Saldo Atual: " + saldo);

		// INSERÇÃO DE DADOS REFERENTE A DÉBITO E CRÉDITO
		do {
			System.out.print("Movimento \'D'\" débito ou \'C'\" crédito: ");
			String op = sc.next().trim().toLowerCase().substring(0, 1);

			if (op.equals("d")) {
				System.out.print("Valor movimento - débito: R$");
				double valor = sc.nextDouble();
				
					if (valor <= saldo) {
						super.debito(valor);			
					} else {
						System.out.println("Operação Inválida!");
					}

			} else if (op.equals("c")) {
				System.out.print("Valor movimento - crédito: R$");
				double valor2 = sc.nextDouble();
				this.credito(valor2);

			} else if (op != "d" && op != "c") {
				System.out.print("Digite \'C'\" ou \'D'\": ");
				preenchimento();
			}

			System.out.print("Continuar S/N: ");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			contador++;
			if (contador > 10) {
				break;
			}

		} while (continuar.equalsIgnoreCase("s"));
		
		//CONDIÇÃO PARA SER LIBERADO OS CHEQUES
		
		if (this.getSaldo() >= 30) {
			
			// OPÇÃO DE QUERER OU NÃO OS CHEQUES
			
			System.out.print("\n Você possui até 3 cheques disponíveis liberados! \n Deseja solicitar? (S/N)");
			continuar = sc.next().trim().toLowerCase().substring(0, 1);

			if (continuar.equalsIgnoreCase("s")) {
				
				liberaCheque();
			}
		}
	}
	
	//METODO PARA DESCONTO DO TALAO
	public double desconto() {
		return getSaldo() - (contadorTalao*30);
		
	}
	
	//MÉTODO PARA LIBERAR OS CHEQUES 
	public double liberaCheque() {
		
		
		System.out.print("\n Quantidade de cheques: ");
		contadorTalao = sc.nextInt();
		
		//LAÇO PARA OPÇÕES		
		
		if (contadorTalao <= 3) {
				desconto();
				String continuar;
				
					if(contadorTalao == 1 && this.getSaldo() < 60 ) {
						System.out.println("Você solicitou " + contadorTalao + " cheque.");
						System.out.println("\n Você atingiu o valor máximo da sua conta para o cheque.");
												
					} else if (contadorTalao == 2 && this.getSaldo() < 90) {
						System.out.println("Você solicitou " + contadorTalao + " cheques.");
						System.out.println("\n Você atingiu o valor máximo da sua conta para o cheque.");
												
					} else if(contadorTalao == 3 && this.getSaldo() >= 90) {
						System.out.println("Você solicitou " + contadorTalao + " cheques.");
						System.out.println("\n Você atingiu a quantidade máxima de solicitação de cheques.");
												
					} else if (contadorTalao == 1 && this.getSaldo() > 90) {
						System.out.println("\n Você pode solicitar mais 2 cheques! \n Você deseja?");
						continuar = sc.next().trim().toLowerCase().substring(0, 1);
						
					} else if (contadorTalao == 2 && this.getSaldo() > 90) {
						System.out.println("\n Você pode solicitar mais 1 cheque! \n Você deseja?  (S/N)");
						continuar = sc.next().trim().toLowerCase().substring(0, 1);
						
					}else {
						System.out.println("Valores inferiores a  disponibilidade de cheques." +
					"\n Deseja continuar? (S/N)");
						continuar = sc.next().trim().toLowerCase().substring(0, 1);
							if (continuar.equalsIgnoreCase("s")) {
							liberaCheque(); 
							}
					
					}
					
			} else {
				System.out.println("\n Esta quantidade não é válida! Tente novamente. ");
				return liberaCheque(); 
			}
		
		return desconto();
			
		}

	//RESULTADO
	public void resultado(){
		System.out.println("Saldo Atual:" + desconto() + " teste: "+ getSaldo());
	}
	
	}