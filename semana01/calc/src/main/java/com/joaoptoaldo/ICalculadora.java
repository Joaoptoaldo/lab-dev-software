package com.joaoptoaldo;

/**
 * interface para a calculadora, que define os métodos que devem ser implementados pelas classes
 * ICalculadora
 */
public interface ICalculadora {
    double somar(double n1, double n2);
    double subtrair(double n1, double n2);
    double multiplicar(double n1, double n2);
    double dividir(double n1, double n2);
    double raizquadrada(double n1, double n2);
    double elevarPotencia(double n1, double n2);
    double logaritmo10(double n1);
}
