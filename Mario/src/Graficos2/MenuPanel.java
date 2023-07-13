package Graficos2;

import javax.swing.JButton;
import javax.swing.JPanel;

import ClasesPadre.Jugador.Personaje;
import Game.Gui;
import Inputs.InptutMouse;
import Inputs.InputTeclado;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.awt.Dimension;

public class MenuPanel extends JPanel {

    JButton boton = new JButton("Iniciar");

    static int contador = 0;
    public BufferedImage aux;

    public MenuPanel() {
        setPreferredSize(new Dimension(1280, 800));
        // ? se encargan de agregar los inputs

        boton.setBounds(100, 100, 100, 100);
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gui.switchState();
            }

        });
        add(boton);

        setBackground(java.awt.Color.BLACK);
        setFocusable(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // *se encarga de dibujar los frames del peronsaje
    }

    public void FrameUpdate() {

    }

}
