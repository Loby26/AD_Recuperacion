package com.mycompany.ej1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author DAM217 */
public class main {

    static File fichero = new File("futbolistas.dat");

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opc;
        int cont = 0;
        int id;
        String alias;
        String cod;
        int puesto;
        float altura;
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
                    List<Integer> ids = leerIds();
                    do {
                        System.out.println("Introduce el id");
                        id = teclado.nextInt();
                        if (ids.get(cont) == id) {
                            System.out.println("Id no valida");
                        }
                    } while (ids.get(cont) == id || cont > ids.size());
                    System.out.println("Introduce el alias");
                    alias = teclado.nextLine();
                    System.out.println("Introduce el codigo de equipo");
                    cod = teclado.nextLine();
                    System.out.println("Introduce el puesto: \n"
                            + "\t1.-Portero\n"
                            + "\t2.-Defensa\n"
                            + "\t3.-Centrocampista\n"
                            + "\t4.-Delantero");
                    puesto = teclado.nextInt();
                    System.out.println("Introduce la altura");
                    altura = teclado.nextFloat();
                    añadirFutbolista(id, alias, cod, puesto, altura);
                    break;
                case 2:
                    listarFutbolistas();
                    break;
                case 3:
                    System.out.println("Introduce el codigo del equipo");
                    cod = teclado.nextLine();
                    listarFutbolistasEquipo(cod);
                    break;
                case 4:
                    System.out.println("Introduce el id del jugador");
                    id = teclado.nextInt();
                    buscarFutbolista(id);
                    break;
                case 5:
                    System.out.println("Introduce el id del jugador");
                    id = teclado.nextInt();
                    System.out.println("Introduce el codigo del nuevo equipo");
                    cod = teclado.nextLine();
                    modificarEquipo(id, cod);
                    break;
                case 6:

                    System.out.println("Introduce el id");
                    id = teclado.nextInt();
                    System.out.println("Introduce el alias");
                    alias = teclado.nextLine();
                    System.out.println("Introduce el codigo de equipo");
                    cod = teclado.nextLine();
                    System.out.println("Introduce el puesto: \n"
                            + "\t1.-Portero\n"
                            + "\t2.-Defensa\n"
                            + "\t3.-Centrocampista\n"
                            + "\t4.-Delantero");
                    puesto = teclado.nextInt();
                    System.out.println("Introduce la altura");
                    altura = teclado.nextFloat();
                    modificarDatos(id, alias, cod, puesto, altura);
                    break;
                case 7:
                    eliminarFutbolista();
                    break;
                case 0:
                    System.out.println("Hasta la proxima");
                    break;
            }
        } while (opc != 0);
    }

    private static List<Integer> leerIds() {
        DataInputStream lector = null;
        List<Integer> ids = new ArrayList<>();
        try {
            lector = new DataInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    ids.add(lector.readInt());
                    lector.skip(4);
                }
            } catch (EOFException e) {

            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ids;
    }

    private static void añadirFutbolista(int id, String alias, String cod, int puesto, float altura) {
        DataOutputStream escritor = null;
        try {
            escritor = new DataOutputStream(new FileOutputStream(fichero));

            escritor.writeInt(id);
            escritor.writeUTF(alias);
            escritor.writeUTF(cod);
            switch (puesto) {
                case 1:
                    escritor.writeUTF("PORTERO");
                    break;
                case 2:
                    escritor.writeUTF("DEFENSA");
                    break;
                case 3:
                    escritor.writeUTF("CENTROCAMPISTA");
                    break;
                case 4:
                    escritor.writeUTF("DELANTERO");
                    break;
                default:
                    break;
            }
            escritor.writeFloat(altura);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritor.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void listarFutbolistas() {
        DataInputStream lector = null;
        try {
            lector = new DataInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    System.out.print("Id: " + lector.readInt() + "Alias: " + lector.readUTF()
                            + "Equipo: " + lector.readUTF() + "Puesto " + lector.readUTF()
                            + "Altura: " + lector.readFloat() + "\n");
                }
            } catch (EOFException e) {

            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void listarFutbolistasEquipo(String equipo) {
        DataInputStream lector = null;
        int id;
        String jugador, cod, puesto;
        float altura;
        boolean encontrado = false;
        System.out.println("Jugadores del equipo " + equipo);
        try {
            lector = new DataInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    id = lector.readInt();
                    jugador = lector.readUTF();
                    cod = lector.readUTF();
                    puesto = lector.readUTF();
                    altura = lector.readFloat();
                    if (cod.equalsIgnoreCase(equipo)) {
                        System.out.println("Id: " + id + " Jugador: " + jugador
                                + " Equipo: " + cod + " Puesto: " + puesto
                                + " Altura: " + altura + "\n");
                        encontrado = true;
                    }
                }
            } catch (EOFException e) {
                if (!encontrado) {
                    System.out.println("El equipo no tiene jugadores");
                }
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void buscarFutbolista(int id) {
        DataInputStream lector = null;
        try {
            lector = new DataInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    if (lector.readInt() == id) {
                        System.out.println("Id: " + id + "Alias: " + lector.readUTF()
                                + "Equipo: " + lector.readUTF() + "Puesto " + lector.readUTF()
                                + "Altura: " + lector.readFloat() + "\n");
                    } else {
                        lector.readUTF();
                        lector.readUTF();
                        lector.readUTF();
                        lector.readFloat();
                    }
                }
            } catch (EOFException e) {

            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void modificarEquipo(int id, String cod) {
        DataInputStream lector = null;
        DataOutputStream escritor = null;
        String jugador = "", equipo = "", puesto = "";
        float altura = 0;
        boolean encontrado = false;
        try {
            lector = new DataInputStream(new FileInputStream(fichero));
            File ficheroAux = new File("futbolistasAux.dat");
            escritor = new DataOutputStream(new FileOutputStream(ficheroAux));
            try {
                while (true) {
                    if (lector.readInt() == id) {
                        jugador = lector.readUTF();
                        equipo = lector.readUTF();
                        puesto = lector.readUTF();
                        altura = lector.readFloat();
                        encontrado = true;
                    } else {
                        lector.readUTF();
                        lector.readUTF();
                        lector.readUTF();
                        lector.readFloat();
                    }
                }
            } catch (EOFException e) {
                if (encontrado) {
                    System.out.println("Id: " + id + " Jugador: " + jugador
                            + " Equipo: " + equipo + " Puesto: " + puesto
                            + " Altura: " + altura + "\n");
                }
                fichero.delete();
                ficheroAux.renameTo(fichero);
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritor.close();
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void modificarDatos(int id, String alias, String cod, int puesto, float altura) {
        DataInputStream lector = null;
        DataOutputStream escritor = null;
        int idAux;
        try {
            lector = new DataInputStream(new FileInputStream(fichero));
            File ficheroAux = new File("futbolistasAux.dat");
            escritor = new DataOutputStream(new FileOutputStream(ficheroAux));
            try {
                while (true) {
                    idAux = lector.readInt();
                    if (idAux == id) {
                        
                        System.out.println("");
                        escritor.writeInt(id);
                        escritor.writeUTF(lector.readUTF());
                        escritor.writeUTF(cod);
                        lector.readUTF();
                        escritor.writeUTF(lector.readUTF());
                        escritor.writeFloat(lector.readFloat());
                    } else {
                        lector.readUTF();
                        lector.readUTF();
                        lector.readUTF();
                        lector.readFloat();
                    }
                }
            } catch (EOFException e) {
                fichero.delete();
                ficheroAux.renameTo(fichero);
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritor.close();
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void eliminarFutbolista() {

    }
}
