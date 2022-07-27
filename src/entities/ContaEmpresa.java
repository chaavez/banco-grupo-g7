package entities;

//SANDRO SENNA DOS ANJOS

public class ContaEmpresa extends Conta {

	// limite de empréitmo 10 mil
	private Double emprestimoEmpresa = 10000d;

	public ContaEmpresa(int numero, String cpf) {
		super(numero, cpf);
	}

	public Double getEmprestimoEmpresa() {
		return emprestimoEmpresa;
	}

	// método soma no saldo e tira do empréstimo
	public void pedirEmprestimo(double valor) {
		if (valor <= emprestimoEmpresa && valor > 0)
			emprestimoEmpresa -= valor;

		credito(valor);
		System.out.println("Empréstimo efetivado!");

	}
}