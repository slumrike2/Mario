package databases;

import java.util.ArrayList;


public enum Database {

    INSTANCE;

    private ArchivoSerializable<Usuario> User_Data = new ArchivoSerializable<Usuario>("usuarios");

    public ArrayList<Usuario> getUsuarios() {
        return User_Data.leerArchivo();
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        User_Data.sobrescribirArchivo(usuarios);
    }

    /**
     * 
     * @param usuario a ingresar
     * @return 0: usuario ya existente; 1: usuario agregado
     */
    public int agregarUsuario(Usuario usuario) {
        if (getUsuario(usuario.getNombreUsuario()) == null) {
            User_Data.agregarObjeto(usuario);
            return 1;
        } else {
            return 0;
        }
    }

    public void eliminarUsuario(Usuario usuario) {
        ArrayList<Usuario> usuarios = getUsuarios();
        usuarios.remove(usuario);
        setUsuarios(usuarios);
    }

    public void modificarUsuario(Usuario usuario) {
        ArrayList<Usuario> usuarios = getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(usuario.getNombreUsuario())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        setUsuarios(usuarios);
    }

    public Usuario getUsuario(String nombreUsuario) {
        Usuario usuario = User_Data.buscarObjeto(nombreUsuario);
        return usuario;
    }

    public boolean passwordIsValid(String nombreUsuario, String contrasenia) {
        Usuario usuario = getUsuario(nombreUsuario);
        if (usuario != null) {
            if (usuario.getContrasenia().equals(contrasenia))
                return true;
        }
        return false;
    }

}
