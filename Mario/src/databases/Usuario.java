package databases;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Identificable;

public class Usuario implements Comparable<Usuario>, Serializable, Identificable {
    private String nombre;
    private String nombreUsuario;
    private String contrasenia;
    private String Estadisticas_fileName;

    public Usuario(String nombre, String nombreUsuario, String contrasenia) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;

        // Se crea el archivo correspondiente al usuario.
        coleccion_fileName = "coleccion_" + nombreUsuario;
        ArchivoSerializable.crearArchivo(coleccion_fileName);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getColeccion_fileName() {
        return coleccion_fileName;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return new ArchivoSerializable<Pelicula>(coleccion_fileName).leerArchivo();
    }

    public void setColeccion_fileName(String coleccion_fileName) {
        this.coleccion_fileName = coleccion_fileName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean validar(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
    }

    @Override
    public int compareTo(Usuario u) {
        return this.nombre.compareTo(u.getNombre());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }

    public boolean identificar(String nombreUsuario) {
        return this.nombreUsuario.equals(nombreUsuario);
    }

}
