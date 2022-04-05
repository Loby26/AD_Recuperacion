package com.mycompany.ej2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author DAM217 */
public class main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        BufferedReader br = null;
        try {
            System.out.println("Introduce una ruta de archivo txt");
            String ruta = teclado.nextLine();
            System.out.println("Que caracter desea buscar");
            String car = teclado.nextLine();
            br = new BufferedReader(new FileReader(ruta));
            String linea;
            int cont = 0;
            while ((linea = br.readLine()) != null) {
                linea = linea.toLowerCase();
                for (int i = 0; i < linea.length(); i++) {
                    char letra = linea.charAt(i);
                    if (letra == car.charAt(0)) {
                        cont++;
                        System.out.println("Posicion: " + i);
                    }
                }
                System.out.println("Total de apariciones: " + cont);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
