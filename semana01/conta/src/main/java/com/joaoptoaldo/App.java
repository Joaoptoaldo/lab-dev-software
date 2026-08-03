package com.joaoptoaldo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ContaCorrente novaConta = new ContaCorrente();
        
        novaConta.definirSaldoInicial(1000);
                
        System.out.println("Saldo inicial: " + novaConta.getSaldo());
        
        novaConta.sacar(500);
        
        novaConta.depositar(50);
        
        System.out.println("Saldo apos depósito: " + novaConta.getSaldo());
    
    }
}
