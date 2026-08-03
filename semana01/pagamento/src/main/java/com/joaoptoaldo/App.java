package com.joaoptoaldo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        MetodoPagamento cartao = new CartaoCreditoPagamento();
        MetodoPagamento paypal = new PayPalPagamento();
        MetodoPagamento pix = new PIXPagamento();

        cartao.processaPagamento(250.00);
        cartao.mostraDetalhesPagamento();

        System.out.println();

        paypal.processaPagamento(180.50);
        paypal.mostraDetalhesPagamento();

        System.out.println();

        pix.processaPagamento(95.90);
        pix.mostraDetalhesPagamento();
        
    }
}
