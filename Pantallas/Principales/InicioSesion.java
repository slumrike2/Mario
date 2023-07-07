package gui.Pantallas.Principales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;   

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import databases.Database;
import databases.Sesion;
import databases.Usuario;
import gui.controladores.Gui;
import gui.controladores.Pantalla;
import gui.controladores.Gui.PantallasPosibles;

public class InicioSesion extends Pantalla {
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
        super();
        panel.setLayout(null);
        iniciarBotones();
        panel.add(bttIniciarSesion);
        panel.add(bttVolver);

        iniciarLabels();
        panel.add(lbHeader);
        panel.add(lbIngresarNombreUsuario);
        panel.add(lbIngresarContrasenia);

        iniciarTextFields();
        panel.add(txtNumbreUsuario);
        panel.add(txtContrasenia);
        iniciarPosiciones();

    }

    // #region inicializaciones

    private void accion_btt_IniciarSesion() {
        iniciarSesion();
    }

    private void accion_btt_Volver() {
        Gui.INSTANCE.goTo(PantallasPosibles.MENU_PRINCIPAL);

    }

    private void iniciarPosiciones() {
        bttIniciarSesion.setBounds(0, 550, 400, 50);
        bttVolver.setBounds(400, 550, 400, 50);

        lbHeader.setBounds(200, 10, 700, 50);
        lbIngresarNombreUsuario.setBounds(0, 200, 300, 50);
        lbIngresarContrasenia.setBounds(0, 300, 300, 50);

        txtNumbreUsuario.setBounds(300, 200, 300, 50);
        txtContrasenia.setBounds(300, 300, 300, 50);

    }

    private void iniciarTextFields() {
        txtNumbreUsuario.setToolTipText("Ingrese su nombre de usuario");
        txtContrasenia.setToolTipText("Ingrese su contraseña");

    }

    private void iniciarLabels() {
        lbHeader.setFont(baseFont);
        lbIngresarNombreUsuario.setFont(baseFont);
        lbIngresarContrasenia.setFont(baseFont);

    }

    protected void iniciarBotones() {

        // Funcionalidades
        bttIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_btt_IniciarSesion();
            }
        });

        bttVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_btt_Volver();
            }
        });

        // Tamaños y posiciones

    }

    // #endregion inicializaciones

    // #region metodos

    private void iniciarSesion() {
        String nombreUsuario = txtNumbreUsuario.getText();
        String contrasenia = txtContrasenia.getText();

        if (nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (Database.INSTANCE.passwordIsValid(nombreUsuario, contrasenia)) {
                JOptionPane.showMessageDialog(panel, "Inicio de sesion exitoso", "Exito",
                        JOptionPane.INFORMATION_MESSAGE);
                Usuario usuario = Database.INSTANCE.getUsuario(nombreUsuario);
                Sesion.INSTANCE.crearSession(usuario);
                Gui.INSTANCE.goTo(PantallasPosibles.MENU_PRINCIPAL);
            } else {
                JOptionPane.showMessageDialog(panel, "Usuario o contrasenia incorrectos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
