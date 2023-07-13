package databases;

public enum Sesion {
    INSTANCE;

    private Usuario usuario = null;
    private boolean sesionIniciada = false;

    // #region constructor
    public void crearSession(Usuario usuario) {
        this.usuario = usuario;
        sesionIniciada = true;
    }

    public void cerrarSession() {
        usuario = null;
        sesionIniciada = false;
    }
    // #endregion

    // #region getters y setters
    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // #endregion

}
