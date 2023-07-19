package databases;

import java.io.*;
import java.util.ArrayList;

import interfaces.Identificable;

public class ArchivoSerializable<T> {
    private static String globalPath = "src\\userData\\";

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private File file;

    // #region
    public ArchivoSerializable(String nombreArchivo) {

        file = new File(globalPath + nombreArchivo + ".dat");
        crearArchivo(nombreArchivo);
    }
    // #endregion

    // #region methods

    // #region escritura

    static public void crearArchivo(String nombreArchivo) {
        try {
            File file = new File(globalPath + nombreArchivo + ".dat");
            if (!file.exists()) {
                file.createNewFile();
                // Se realiza la cabecera del archivo
                OutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file, true));
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }

    public void sobrescribirArchivo(ArrayList<T> Arreglo) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file, false));
            for (T objeto : Arreglo) {
                outputStream.writeObject(objeto);
            }
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }

    public void agregarColeccion(ArrayList<T> peliculas) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file, true));
            for (T objeto : peliculas) {
                outputStream.writeObject(objeto);
            }
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }

    public void agregarObjeto(T objeto) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file, true)) {
                @Override
                protected void writeStreamHeader() throws IOException {
                };
            };
            outputStream.writeObject(objeto);
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }

    public void eliminarArchivo() {
        file.delete();
    }

    // #endregion

    // #region Lectura
    public boolean estaVacio() {
        return file.length() == 0;
    }

    @SuppressWarnings("unchecked")
    public void imprimirArchivo() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                T pelicula = (T) inputStream.readObject();
                System.out.println(pelicula.toString());
            }

        } catch (EOFException e) {
            return;
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            return;
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> leerArchivo() {
        ArrayList<T> objetos = new ArrayList<T>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                T pelicula = (T) inputStream.readObject();
                objetos.add(pelicula);
            }

        } catch (EOFException e) {
            return objetos;
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            return objetos;
        }
    }

    @SuppressWarnings("unchecked")
    public T buscarObjeto(String identificador) {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Identificable objeto = (Identificable) inputStream.readObject();
                if (objeto.identificar(identificador)) {
                    return (T) objeto;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    // #endregion

    public static String getGlobalPath() {
        return globalPath;
    }

    public static void setGlobalPath(String globalPath) {
        ArchivoSerializable.globalPath = globalPath;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    // #endregion
}