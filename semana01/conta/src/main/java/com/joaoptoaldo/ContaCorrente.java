package com.joaoptoaldo;

public class ContaCorrente {
    private float saldo;
        
    /**
     * método que retorna o saldo da conta
     * @return saldo da conta
     */
    public float getSaldo() {
        return this.saldo;
    }

    
    /**
     * método que define o saldo inicial da conta
     * @param valor valor inicial da conta
     */
    public void definirSaldoInicial(float valor){
        this.saldo = valor;
    }
    
    /**
     * método que realiza um depósito na conta
     * @param valor valor a ser depositado
     */
    public void depositar(float valor){
        this.saldo = saldo + valor;
    }
    

    /**
     * método que realiza um saque na conta
     * @param valor valor a ser sacado
     * @return true se o saque foi realizado com sucesso, false caso contrário
     */
    public boolean sacar(float valor){
        if(valor <= saldo){
            saldo = saldo - valor;
            return true;
        }
        
        else{
            return false;
        }

    }
    
}
