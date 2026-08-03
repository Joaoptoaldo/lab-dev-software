package com.joaoptoaldo;

import java.util.Random;

public abstract class MetodoPagamento {
    private String nomeMetodo;
    private int idPagamento;

    public MetodoPagamento(String nomeMetodo) {
        this.nomeMetodo = nomeMetodo;
        this.idPagamento = new Random().nextInt(100000);// gera um ID aleatório para o pagamento
    }

    public String getNomeMetodo() {
        return nomeMetodo;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    /**
     * método abstrato que processa o pagamento
     * @param valor valor a ser pago
     */
    public abstract void processaPagamento(double valor);

    /**
     * método abstrato que mostra os detalhes do pagamento
     */
    public abstract void mostraDetalhesPagamento();

}