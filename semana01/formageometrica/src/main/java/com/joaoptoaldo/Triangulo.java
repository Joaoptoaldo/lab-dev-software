package com.joaoptoaldo;

public class Triangulo extends FormaGeometrica {
    private double base;
    private double altura;
    

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        area = (base * altura) / 2;
        return area;
    }
}
