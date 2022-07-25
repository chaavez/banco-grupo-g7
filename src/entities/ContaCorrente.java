package entities;

//DEBORA LETICIA RUBIM DE PAIVA

public class ContaCorrente extends Conta {

	public ContaCorrente(int numero, String cpf) {
		super(numero, cpf);
	}

	public int contadorTalao = 1;
		
	public int getContadorTalao() {
		return contadorTalao;
	}
	public void setContadorTalao(int contadorTalao) {
		this.contadorTalao = contadorTalao;
	}
	
	//MÉTODO PARA LIBERAR OS CHEQUES 
	public void liberaCheque() {
		if (contadorTalao <= 3) {
				//OBS.: Saldo está pedindo para alterar na conta para protected
				this.saldo = this.saldo - (contadorTalao * 30);
				System.out.println("\n Liberado(s) " + contadorTalao + " cheque(s) no valor de R$ " + this.saldo);
			} else {
				//OBS.: Fazer com que a opção retorne para quantidade de cheques
				System.out.println("\n Esta quantidade não é válida! Tente novamente. ");
			}
			
		}

}
