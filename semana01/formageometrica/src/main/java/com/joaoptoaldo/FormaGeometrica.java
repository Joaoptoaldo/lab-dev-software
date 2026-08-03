package com.joaoptoaldo;

public class FormaGeometrica {
    private double lado;
    protected double area;

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    /**
     * método que calcula a área da forma geométrica
     * @return área da forma geométrica
     */
    public double calcularArea() {
        area = lado * lado;
        return area;
    }

}
