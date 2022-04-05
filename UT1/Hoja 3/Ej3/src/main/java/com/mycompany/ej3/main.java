package com.mycompany.ej3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author DAM217 */
public class main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in, "ISO-8859-1");
        int opc;
        String nombre, apellido;
        do {
            System.out.println("Introduce una opcion:\n"
                    + "1. Añadir persona\n"
                    + "2. Buscar persona\n"
                    + "3. Buscar nombre\n"
                    + "4. Apellidos comienzan por\n"
                    + "5. Eliminar persona\n"
                    + "0. Salir");
            opc = teclado.nextInt();
            teclado.nextLine();
            switch (opc) {
                case 1:
                    System.out.println("Introduzca el nombre: ");
                    nombre = teclado.nextLine();
                    System.out.println("Introduczca el apellido: ");
                    apellido = teclado.nextLine();
                    añadirPersona(nombre, apellido);
                    break;
                case 2:
                    System.out.println("Introduzca el nombre: ");
                    nombre = teclado.nextLine();
                    System.out.println("Introduczca el apellido: ");
                    apellido = teclado.nextLine();
                    buscarPersona(nombre, apellido);
                    break;
                case 3:
                    System.out.println("Introduzca el nombre: ");
                    nombre = teclado.nextLine();
                    List<String> listaNombres = listarNombres(nombre);
                    for (int i = 0; i < listaNombres.size(); i++) {
                        System.out.println("Persona " + i + ": " + listaNombres.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Introduzca los primeros caracteres del apellido: ");
                    apellido = teclado.nextLine();
                    List<String> listaApellidos = listarApellidos(apellido);
                    for (int i = 0; i < listaApellidos.size(); i++) {
                        System.out.println("Persona " + i + ": " + listaApellidos.get(i));
                    }
                    break;
                case 5:
                    System.out.println("Introduzca el nombre: ");
                    nombre = teclado.nextLine();
                    System.out.println("Introduczca el apellido: ");
                    apellido = teclado.nextLine();
                    eliminarPersona(nombre, apellido);
                    break;
                case 0:
                    System.out.println("Hasta la proxima");
                    break;
            }
        } while (opc != 0);

    }

    private static void añadirPersona(String nombre, String apellido) {
        try {
            String text = "\n" + apellido.toUpperCase() + ", " + nombre.toUpperCase();
            FileOutputStream f = new FileOutputStream("personas.txt", true);
            byte[] byteText = text.getBytes();
            f.write(byteText);
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void buscarPersona(String nombre, String apellido) {
        BufferedReader br = null;
        String text = "\n" + apellido.toUpperCase() + ", " + nombre.toUpperCase();
        try {
            br = new BufferedReader(new FileReader("personas.txt"));
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(text)) {
                    encontrado = true;
                }
            }
            if (encontrado) {
                System.out.println("Persona encontrada");
            } else {
                System.out.println("La persona no esta en la lista");
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

    private static List<String> listarNombres(String nombre) {
        List<String> lista = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("personas.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split(",");
                if (nombre == separado[1]) {
                    lista.add(linea);
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
        return lista;
    }

    private static List<String> listarApellidos(String apellido) {
        List<String> lista = new ArrayList<>();
        BufferedReader br = null;
        String reducido = "";
        try {
            br = new BufferedReader(new FileReader("personas.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split(",");
                for (int i = 0; i < apellido.length(); i++) {
                    reducido = reducido + separado[0].charAt(i);
                }
                if (reducido.equals(apellido)) {
                    lista.add(linea);
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
        return lista;
    }

    private static void eliminarPersona(String nombre, String apellido) {
        BufferedReader br = null;
        try {
            File f1 = new File("personas.txt");
            File fAux = new File("personasAux.txt");
            FileOutputStream f = new FileOutputStream("personasAux.txt", true);
            String text = apellido.toUpperCase() + ", " + nombre.toUpperCase();
            br = new BufferedReader(new FileReader("personas.txt"));
            String linea, lineaAux;
            while ((linea = br.readLine()) != null) {
                if (!linea.equals(text)) {
                    lineaAux = "\n" + linea;
                    byte[] byteText = lineaAux.getBytes();
                    f.write(byteText);
                }
            }
            f.close();
            br.close();
            f1.delete();
            fAux.renameTo(f1);

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
