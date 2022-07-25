package entities;

	//SANDRO SENNA DOS ANJOS

	public class ContaEmpresa extends Conta {
		
		private Double saldo = 0d;
		private Double emprestimoEmpresa = 10000d;	
					

		public Double getSaldo() {
			return saldo;
		}

		public void setSaldo(Double saldo) {
			this.saldo = saldo;
		}

		public Double getEmprestimoEmpresa() {
			return emprestimoEmpresa;
		}

		public void setEmprestimoEmpresa(Double emprestimoEmpresa) {
			this.emprestimoEmpresa = emprestimoEmpresa;
		}	
		
		public Double debito(Double valor) { // o saldo não pode ficar negativo
			
			Double novoSaldo = saldo - valor;
			
			if(novoSaldo < 0) {			
				return saldo;
			}	
			
			saldo = novoSaldo;
			return novoSaldo;
		}

		public Double credito(Double valor) {
			return saldo += valor;
		}		
				
		//método soma no saldo e tira do emprestimo
		public double pedirEmprestimo(double valor) {
		if(valor <= emprestimoEmpresa && valor > 0)
		emprestimoEmpresa -= valor;	
		
		return saldo += valor;
			
		}
	}