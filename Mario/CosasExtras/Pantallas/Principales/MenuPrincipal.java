package gui.Pantallas.Principales;

import javax.swing.*;

import databases.Sesion;
import gui.controladores.Gui;
import gui.controladores.Pantalla;
import gui.controladores.Gui.PantallasPosibles;

import java.awt.event.*;

public class MenuPrincipal extends Pantalla {
    public JButton bttRegistro = new JButton("REGISTRAR");
    public JButton bttInicioSesion = new JButton("INICIAR SESION");
    public JButton bttGestionarColeccion = new JButton("GESTIONAR COLECCION");
    public JButton bttSalir = new JButton("SALIR");

    private void accion_bttRegistro() {
        Gui.INSTANCE.goTo(PantallasPosibles.REGISTRO);
    }

    private void accion_bttInicioSesion() {
        Gui.INSTANCE.goTo(PantallasPosibles.INICIO_SESION);
    }

    private void accion_bttGestionarColeccion() {
        if(Sesion.INSTANCE.isSesionIniciada())
            Gui.INSTANCE.goTo(PantallasPosibles.MENU_GESTIONAR_COLECCION);
        else
        JOptionPane.showMessageDialog(panel, "Inicie sesion para acceder a su coleccion", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void accion_bttSalir() {
        Gui.INSTANCE.finalizar();
    }

    protected void iniciarBotones() {

        // Funcionalidades
        bttRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttRegistro();
            }
        });

        bttInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttInicioSesion();
            }
        });

        bttGestionarColeccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttGestionarColeccion();
            }
        });

        bttSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttSalir();
            }
        });

        // Tamaños y posiciones

        bttGestionarColeccion.setBounds(100, 50, 300, 50);
        bttRegistro.setBounds(100, 150, 300, 50);
        bttInicioSesion.setBounds(100, 250, 300, 50);
        bttSalir.setBounds(100, 350, 300, 50);



    }

    public MenuPrincipal() {
        super();

        panel.setLayout(null);

        iniciarBotones();

        panel.add(bttRegistro);
        panel.add(bttInicioSesion);
        panel.add(bttGestionarColeccion);
        panel.add(bttSalir);

    }

}
