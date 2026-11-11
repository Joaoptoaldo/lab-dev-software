/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minhathread;

/**
 *
 * @author laboratorio
 */
public class ExemploThreads {
    public static void main(String[] args) {
        MinhaThread thread1 = new MinhaThread("Thread 1 - menagem a cada 1 segundo", 1000);
        MinhaThread thread2 = new MinhaThread("Thread 2 - menagem a cada 2 segundos", 2000);
        
        thread1.start();
        thread2.start();
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread principal - iteração" + i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         }
        
        thread1.interrupt();
        thread2.interrupt();
    }
}
