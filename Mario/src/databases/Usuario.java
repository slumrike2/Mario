package databases;

import java.io.Serializable;

import interfaces.Identificable;

public class Usuario implements Comparable<Usuario>, Serializable, Identificable {
    // #region variables de instancia
    private String nombre;
    private String nombreUsuario;
    private String estadisticas_fileName;
    private String contrasenia;
    // #endregion

    // #region variables de usuario (estadisticas)
    private int cantidadPartidasJugadas;
    private int cantidadPartidasGanadas;
    private int cantidadPartidasPerdidas;
    private int cantidadPartidasAbandonadas;
    // #endregion

    public Usuario(String nombre, String nombreUsuario, String contrasenia) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;

        // Se crea el archivo correspondiente al usuario.
        estadisticas_fileName = "stats_" + nombreUsuario;
        ArchivoSerializable.crearArchivo(estadisticas_fileName);
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

    // #region getters y setters
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

    public String getEstadisticas_fileName() {
        return estadisticas_fileName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEstadisticas_fileName(String estadisticas_fileName) {
        this.estadisticas_fileName = estadisticas_fileName;
    }

    public int getCantidadPartidasJugadas() {
        return cantidadPartidasJugadas;
    }

    public void setCantidadPartidasJugadas(int cantidadPartidasJugadas) {
        this.cantidadPartidasJugadas = cantidadPartidasJugadas;
    }

    public int getCantidadPartidasGanadas() {
        return cantidadPartidasGanadas;
    }

    public void setCantidadPartidasGanadas(int cantidadPartidasGanadas) {
        this.cantidadPartidasGanadas = cantidadPartidasGanadas;
    }

    public int getCantidadPartidasPerdidas() {
        return cantidadPartidasPerdidas;
    }

    public void setCantidadPartidasPerdidas(int cantidadPartidasPerdidas) {
        this.cantidadPartidasPerdidas = cantidadPartidasPerdidas;
    }

    public int getCantidadPartidasAbandonadas() {
        return cantidadPartidasAbandonadas;
    }

    public void setCantidadPartidasAbandonadas(int cantidadPartidasAbandonadas) {
        this.cantidadPartidasAbandonadas = cantidadPartidasAbandonadas;
    }

    // #endregion

}
