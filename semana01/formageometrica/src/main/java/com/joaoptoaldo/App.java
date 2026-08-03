package com.joaoptoaldo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args ) {
        FormaGeometrica quadrado = new FormaGeometrica();
        quadrado.setLado(50);
        
        double area;
        
        area = quadrado.calcularArea();
        System.out.println("area do quadrado: " + area);


        Triangulo triangulo = new Triangulo(); 
        triangulo.setAltura(10);             
        triangulo.setBase(5);
        
        area = triangulo.calcularArea();
        System.out.println("area do triangulo: " + area);
    
    }
}
