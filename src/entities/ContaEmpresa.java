package entities;

//SANDRO SENNA DOS ANJOS

public class ContaEmpresa extends Conta {

	// limite de empr�itmo 10 mil
	private Double emprestimoEmpresa = 10000d;

	public ContaEmpresa(int numero, String cpf) {
		super(numero, cpf);
	}

	public Double getEmprestimoEmpresa() {
		return emprestimoEmpresa;
	}

	// m�todo soma no saldo e tira do empr�stimo
	public void pedirEmprestimo(double valor) {
		if (valor <= emprestimoEmpresa && valor > 0)
			emprestimoEmpresa -= valor;

		credito(valor);
		System.out.println("Empr�stimo efetivado!");

	}
}