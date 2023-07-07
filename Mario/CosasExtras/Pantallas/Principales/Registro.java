package gui.Pantallas.Principales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import databases.Database;
import databases.Usuario;
import gui.controladores.Gui;
import gui.controladores.Pantalla;
import gui.controladores.Gui.PantallasPosibles;

public class Registro extends Pantalla {
    // #region variables

    // #region botones
    public JButton bttRegistrar = new JButton("REGISTRAR");
    public JButton bttVolver = new JButton("VOLVER");
    // #endregion botones

    // #region labels
    JLabel lbHeader = new JLabel("Ingrese los datos para registrar un usuario");
    JLabel lbIngresarNombre = new JLabel("Ingrese su nombre");
    JLabel lbIngresarNombreUsuario = new JLabel("Ingrese su nombre de usuario");
    JLabel lbIngresarContrasenia = new JLabel("Ingrese su contraseña");
    JLabel lbConfirmarContrasenia = new JLabel("Confirme su contraseña");
    // #endregion labels

    // #region textFields
    JTextField txtNombre = new JTextField(16);
    JTextField txtNumbreUsuario = new JTextField(16);
    JTextField txtContrasenia = new JTextField(16);
    JTextField txtConfirmarContrasenia = new JTextField(16);

    // #endregion textFields

    // #endregion variables

    public Registro() {
        super();
        panel.setLayout(null);
        iniciarBotones();
        panel.add(bttRegistrar);
        panel.add(bttVolver);

        iniciarLabels();
        panel.add(lbHeader);
        panel.add(lbIngresarNombre);
        panel.add(lbIngresarNombreUsuario);
        panel.add(lbIngresarContrasenia);
        panel.add(lbConfirmarContrasenia);

        iniciarTextFields();
        panel.add(txtNombre);
        panel.add(txtNumbreUsuario);
        panel.add(txtContrasenia);
        panel.add(txtConfirmarContrasenia);
        iniciarPosiciones();
    }

    // #region inicializaciones

    private void accion_btt_Registrar() {
        registrarUsuario();
    }

    private void accion_btt_Volver() {
        Gui.INSTANCE.goTo(PantallasPosibles.MENU_PRINCIPAL);

    }

    private void iniciarPosiciones() {
        bttRegistrar.setBounds(0, 550, 400, 50);
        bttVolver.setBounds(400, 550, 400, 50);

        lbHeader.setBounds(200, 10, 700, 50);
        lbIngresarNombre.setBounds(0, 100, 300, 50);
        lbIngresarNombreUsuario.setBounds(0, 200, 300, 50);
        lbIngresarContrasenia.setBounds(0, 300, 300, 50);
        lbConfirmarContrasenia.setBounds(0, 400, 300, 50);

        txtNombre.setBounds(300, 100, 300, 50);
        txtNumbreUsuario.setBounds(300, 200, 300, 50);
        txtContrasenia.setBounds(300, 300, 300, 50);
        txtConfirmarContrasenia.setBounds(300, 400, 300, 50);

    }

    private void iniciarTextFields() {
        txtNombre.setToolTipText("Ingrese su nombre");
        txtNumbreUsuario.setToolTipText("Ingrese su nombre de usuario");
        txtContrasenia.setToolTipText("Ingrese su contraseña");
        txtConfirmarContrasenia.setToolTipText("Confirme su contraseña");

    }

    private void iniciarLabels() {
        lbHeader.setFont(baseFont);
        lbIngresarNombre.setFont(baseFont);
        lbIngresarNombreUsuario.setFont(baseFont);
        lbIngresarContrasenia.setFont(baseFont);
        lbConfirmarContrasenia.setFont(baseFont);

    }

    protected void iniciarBotones() {

        // Funcionalidades
        bttRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_btt_Registrar();
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

    // #region Funcionalidades

    private boolean validarEntradas() {
        if (txtNombre.getText().isEmpty() || txtNumbreUsuario.getText().isEmpty() || txtContrasenia.getText().isEmpty()
                || txtConfirmarContrasenia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Faltan datos", "Dialog", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!txtContrasenia.getText().equals(txtConfirmarContrasenia.getText())) {
            JOptionPane.showMessageDialog(panel, "Las contraseñas no coinciden", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void registrarUsuario() {
        if (validarEntradas()) {
            // Intentar ingresar usuario
            Usuario usuario = new Usuario(txtNombre.getText(), txtNumbreUsuario.getText(), txtContrasenia.getText());
            if (Database.INSTANCE.agregarUsuario(usuario) == 1) {
                JOptionPane.showMessageDialog(Gui.INSTANCE.getFrame(), "Usuario registrado", "Dialog",
                        JOptionPane.INFORMATION_MESSAGE);
                Gui.INSTANCE.goTo(PantallasPosibles.MENU_PRINCIPAL);
            } else
                JOptionPane.showMessageDialog(Gui.INSTANCE.getFrame(), "El usuario ya existe", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
        }

    }
    // #endregion Funcionalidades

}
