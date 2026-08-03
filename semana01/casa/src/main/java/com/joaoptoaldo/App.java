package com.joaoptoaldo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Casa c = new Casa();
        
        double valor;

        valor = c.calcularPreco(70);
        System.out.println("Valor 1: " + valor);
        
        valor = c.calcularPreco(70, 3);
        System.out.println("Valor 2: " + valor);
    }
}
