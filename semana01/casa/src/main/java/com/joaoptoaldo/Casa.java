package com.joaoptoaldo;
  
/**
 * Classe que representa uma casa
 */
public class Casa {
    private double valor;

    public double getValor() {
      return valor;
    }


    public void setValor(double valor) {
      this.valor = valor;
    }


    /**
     * método que calcula o preço da casa com base no tamanho em metros quadrados
     * @param tamanho tamanho da casa em metros quadrados
     * @return preço da casa
     */
    public double calcularPreco(int tamanho) {
        valor = 4300 * tamanho;
        return valor;
    }

    /**
     * método que calcula o preço da casa com base no tamanho em metros quadrados e no número de quartos
     * @param tamanho tamanho da casa em metros quadrados
     * @param quartos número de quartos
     * @return preço da casa
     */
    public double calcularPreco(int tamanho, int quartos) {
        valor = (4300*tamanho) + (2500*quartos);
        return valor;
    }

}
