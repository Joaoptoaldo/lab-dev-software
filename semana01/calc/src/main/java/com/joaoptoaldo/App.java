package com.joaoptoaldo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ICalculadora calculadora = new Calculadora();

        System.out.println("Soma: " + calculadora.somar(10, 5));
        System.out.println("Subtração: " + calculadora.subtrair(10, 5));
        System.out.println("Multiplicação: " + calculadora.multiplicar(10, 5));
        System.out.println("Divisão: " + calculadora.dividir(10, 5));
        System.out.println("Raiz quadrada: " + calculadora.raizquadrada(25, 0));
        System.out.println("Potência: " + calculadora.elevarPotencia(2, 3));
        System.out.println("Logaritmo base 10: " + calculadora.logaritmo10(100));
    }
}
