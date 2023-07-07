package gui.controladores;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class Pantalla {
    protected JPanel panel = new JPanel();
    protected Font baseFont = new Font("Comic Sans MS", Font.PLAIN, 20);


    public Pantalla() {
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);
    }

    public void add(JPanel panel) {
        this.panel.add(panel);
    }

    public void remove(JPanel panel) {
        this.panel.remove(panel);
    }


    public JPanel getPanel() {
        return panel;
    }


}
