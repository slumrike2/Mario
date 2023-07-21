package graficos.pantallas;

import databases.Database;
import databases.Usuario;
import graficos.Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constantes.*;

public class Registro extends JPanel {

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

    BufferedImage imagenFondo;

    JButton bttRegistrar = new JButton("Registrarse");
    JButton bttVolver = new JButton("Volver");

    public Registro() {
        setPreferredSize(new Dimension(1080, 720));
        setLayout(null);
        loadImages();

        inicializarBotones();
        inicializarPosiciones();

        iniciarLabels();
        add(lbHeader);
        add(lbIngresarNombre);
        add(lbIngresarNombreUsuario);
        add(lbIngresarContrasenia);
        add(lbConfirmarContrasenia);

        iniciarTextFields();
        add(txtNombre);
        add(txtNumbreUsuario);
        add(txtContrasenia);
        add(txtConfirmarContrasenia);

        add(bttRegistrar);
        add(bttVolver);

        setVisible(true);
    }

    private void loadImages() {
        InputStream is = MenuPrincipal.class.getResourceAsStream("res/menuBackground.png");

        try {
            imagenFondo = ImageIO.read(is);

        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, null);
    }

    // #region botonesAcciones

    private void botonRegistrarseActionPerformed() {
        registrarUsuario();
    }

    private void botonVolverActionPerformed() {
        Gui.switchPantallaMenu(Constantes.Pantallas_Menues.MENU_PRINCIPAL);
    }

    private void inicializarBotones() {
        // !Para utilizar cuando se estilice el boton/botones. Probablemente se haga una

        bttRegistrar.addActionListener(e -> botonRegistrarseActionPerformed());
        bttVolver.addActionListener(e -> botonVolverActionPerformed());
    }
    // #endregion

    // #region Inicializadores

    private void iniciarTextFields() {
        txtNombre.setToolTipText("Ingrese su nombre");
        txtNumbreUsuario.setToolTipText("Ingrese su nombre de usuario");
        txtContrasenia.setToolTipText("Ingrese su contraseña");
        txtConfirmarContrasenia.setToolTipText("Confirme su contraseña");

    }

    private void iniciarLabels() {
        // TODO asignar fuente

    }

    private void inicializarPosiciones() {
        bttRegistrar.setBounds(0, 530, 490, 50);
        bttVolver.setBounds(490, 530, 490, 50);

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

    // #endregion

    private boolean validarEntradas() {
        if (txtNombre.getText().isEmpty() || txtNumbreUsuario.getText().isEmpty() || txtContrasenia.getText().isEmpty()
                || txtConfirmarContrasenia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan datos", "Dialog", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!txtContrasenia.getText().equals(txtConfirmarContrasenia.getText())) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Dialog",
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
                JOptionPane.showMessageDialog(this, "Usuario registrado", "Dialog",
                        JOptionPane.INFORMATION_MESSAGE);
                Gui.switchPantallaMenu(Constantes.Pantallas_Menues.MENU_PRINCIPAL);
                limpiarLabels();
            } else
                JOptionPane.showMessageDialog(this, "El usuario ya existe", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
        }

    }

    private void limpiarLabels() {
        txtNombre.setText("");
        txtNumbreUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
    }
}
