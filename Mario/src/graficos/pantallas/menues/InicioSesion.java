package graficos.pantallas.menues;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constantes.constantes.Pantallas_Menues;
import databases.Database;
import databases.Sesion;
import databases.Usuario;
import graficos.Gui;

public class InicioSesion extends JPanel {

    // #region variables

    // #region botones
    public JButton bttIniciarSesion = new JButton("INICIAR SESION");
    public JButton bttVolver = new JButton("VOLVER");
    // #endregion botones

    // #region labels
    JLabel lbHeader = new JLabel("Ingrese los datos para iniciar sesion");
    JLabel lbIngresarNombreUsuario = new JLabel("Ingrese su nombre de usuario");
    JLabel lbIngresarContrasenia = new JLabel("Ingrese su contraseña");
    // #endregion labels

    // #region textFields
    JTextField txtNumbreUsuario = new JTextField(16);
    JTextField txtContrasenia = new JTextField(16);
    // #endregion textFields

    // #endregion variables

    public InicioSesion() {

        setLayout(null);

        iniciarBotones();
        add(bttIniciarSesion);
        add(bttVolver);

        iniciarLabels();
        add(lbHeader);
        add(lbIngresarNombreUsuario);
        add(lbIngresarContrasenia);

        iniciarTextFields();
        add(txtNumbreUsuario);
        add(txtContrasenia);
        iniciarPosiciones();

    }

    private void accion_btt_IniciarSesion() {
        iniciarSesion();
    }

    private void accion_btt_Volver() {
        Gui.switchPantallaMenu(Pantallas_Menues.MENU_PRINCIPAL);
    }

    // #region inicializaciones
    private void iniciarBotones() {
        bttIniciarSesion.addActionListener(e -> accion_btt_IniciarSesion());
        bttVolver.addActionListener(e -> accion_btt_Volver());
    }

    private void iniciarLabels() {
        lbHeader.setBounds(100, 50, 300, 30);
        lbIngresarNombreUsuario.setBounds(100, 100, 300, 30);
        lbIngresarContrasenia.setBounds(100, 150, 300, 30);
    }

    private void iniciarTextFields() {
        txtNumbreUsuario.setBounds(100, 120, 300, 30);
        txtContrasenia.setBounds(100, 170, 300, 30);
    }

    private void iniciarPosiciones() {
        bttIniciarSesion.setBounds(100, 200, 300, 30);
        bttVolver.setBounds(100, 230, 300, 30);
    }

    // #endregion inicializaciones

    private void iniciarSesion() {
        String nombreUsuario = txtNumbreUsuario.getText();
        String contrasenia = txtContrasenia.getText();

        if (nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (Database.INSTANCE.passwordIsValid(nombreUsuario, contrasenia)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                // *Se crea la sesion con el usuario actual */
                Usuario usuario = Database.INSTANCE.getUsuario(nombreUsuario);
                Sesion.INSTANCE.crearSession(usuario);
                Gui.switchPantallaMenu(Pantallas_Menues.MENU_PRINCIPAL);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contrasenia incorrectos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
