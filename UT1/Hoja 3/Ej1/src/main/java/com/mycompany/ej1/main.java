package com.mycompany.ej1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author DAM217 */
public class main {

    public static void main(String[] args) {
        try {
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce una ruta de archivo txt");
            String ruta = teclado.nextLine();
            System.out.println("Que texto desea añadir");
            String text = teclado.nextLine();
            text = "\t" + text;
            FileOutputStream f = new FileOutputStream(ruta, true);
            byte[] byteText = text.getBytes();
            f.write(byteText);
            f.close();

            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            String finale = "";
            while ((linea = br.readLine()) != null) {
                for (int i = 0; i < linea.length(); i++) {
                    char letra = linea.charAt(i);
                    if (Character.isUpperCase(letra)) {
                        finale = finale + Character.toLowerCase(letra);
                    } else {
                        finale = finale + Character.toUpperCase(letra);
                    }

                }
                System.out.println(finale);
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
