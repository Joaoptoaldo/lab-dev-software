package com.joaoptoaldo;

public class PIXPagamento extends MetodoPagamento {
    public PIXPagamento() {
        super("PIX");
    }

    @Override
    public void processaPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + " processado via " + getNomeMetodo());
    }

    @Override
    public void mostraDetalhesPagamento() {
        System.out.println("Método: " + getNomeMetodo());
        System.out.println("ID do Pagamento: " + getIdPagamento());
    }
}
