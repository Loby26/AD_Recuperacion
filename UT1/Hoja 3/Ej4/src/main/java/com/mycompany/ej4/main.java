package com.mycompany.ej4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author DAM217 */
public class main {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("alumnos.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
               String[] alumnos = linea.split("-");
               int edad = Integer.parseInt(alumnos[1]);
                if (edad >= 20) {
                    System.out.println(alumnos[0]+" tiene " + alumnos[1] + " años");
                }
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
