package gui.Pantallas.ManejoPeliculas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

import databases.*;
import gui.controladores.*;
import gui.controladores.Gui.PantallasPosibles;
import peliculas.Pelicula;

public class MenuGestionarColeccion extends Pantalla {

    protected static class MyTableModel extends AbstractTableModel {

        String[] headers = { "ID", "Nombre", "Actor principal", "Genero", "Duracion" };
        Object[][] tableData = { { " ", " ", " ", " ", " " } };

        // ajustar las columnas de la tabla al tamaño del contenido de la celda mas
        // grande de cada columna
        public void ajustarColumnas() {
            for (int column = 0; column < tblPeliculas.getColumnCount(); column++) {
                TableColumn tableColumn = tblPeliculas.getColumnModel().getColumn(column);
                int preferredWidth = tableColumn.getMinWidth();
                int maxWidth = tableColumn.getMaxWidth();

                for (int row = 0; row < tblPeliculas.getRowCount(); row++) {
                    TableCellRenderer cellRenderer = tblPeliculas.getCellRenderer(row, column);
                    Component c = tblPeliculas.prepareRenderer(cellRenderer, row, column);
                    int width = c.getPreferredSize().width + tblPeliculas.getIntercellSpacing().width;
                    preferredWidth = Math.max(preferredWidth, width);

                    // We've exceeded the maximum width, no need to check other rows

                    if (preferredWidth >= maxWidth) {
                        preferredWidth = maxWidth;
                        break;
                    }
                }

                tableColumn.setPreferredWidth(preferredWidth);
            }
        }

        @Override
        public String getColumnName(int column) {
            return headers[column];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public int getRowCount() {
            return tableData.length;
        }

        @Override
        public int getColumnCount() {
            return headers.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return tableData[rowIndex][columnIndex];
        }
    }

    public JButton bttConsultarPelicula = new JButton("CONSULTAR PELICULA");
    public JButton bttIngresarPelicula = new JButton("INGRESAR PELICULA");
    public JButton bttEliminarPelicula = new JButton("ELIMINAR PELICULA");
    public JButton bttEliminarConjuntoPeliculas = new JButton("ELIMINAR CONJUNTO DE PELICULAS");
    public JButton bttOrdenarPeliculas = new JButton("ORDENAR PELICULAS");

    public JButton bttGuardar = new JButton("GUARDAR");
    public JButton bttVolver = new JButton("VOLVER");
    public static MyTableModel modeloTabla = new MyTableModel();
    public static JTable tblPeliculas = new JTable(modeloTabla);
    public static JScrollPane scrollPane = new JScrollPane(tblPeliculas);

    public MenuGestionarColeccion() {
        super();
        panel.add(scrollPane);
        panel.add(bttConsultarPelicula);
        panel.add(bttIngresarPelicula);
        panel.add(bttEliminarPelicula);
        panel.add(bttEliminarConjuntoPeliculas);
        panel.add(bttOrdenarPeliculas);
        panel.add(bttVolver);
        panel.add(bttGuardar);
        tblPeliculas.setEnabled(false);

        iniciarBotones();
        iniciarPosiciones();
    }

    protected void accion_bttVolver() {
        Gui.INSTANCE.goTo(PantallasPosibles.MENU_PRINCIPAL);
    }

    protected void accion_bttGuardar() {
        Sesion.INSTANCE.guardarCambios();
    }

    private void accion_bttConsultarPelicula() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(panel, "Ingrese el id de la pelicula que desea consultar",
                "Consultar pelicula", JOptionPane.QUESTION_MESSAGE));

        Pelicula pelicula = Sesion.INSTANCE.getPeliculas().consultarPelicula(id);
        if (pelicula != null) {
            JOptionPane.showMessageDialog(panel, pelicula.toString(), "Pelicula", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel, "No se encontro la pelicula con el id ingresado", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void accion_bttIngresarPelicula() {
        JOptionPane.showMessageDialog(panel, "Ingrese los datos de la pelicula a continuacion", "Ingresar pelicula",
                JOptionPane.INFORMATION_MESSAGE);
        int id = Integer
                .parseInt(JOptionPane.showInputDialog(panel, "Ingrese el id de la pelicula", "Ingresar pelicula",
                        JOptionPane.QUESTION_MESSAGE));

        String nombre = JOptionPane.showInputDialog(panel, "Ingrese el nombre de la pelicula", "Ingresar pelicula",
                JOptionPane.QUESTION_MESSAGE);

        String actorPrincipal = JOptionPane.showInputDialog(panel, "Ingrese el nombre del actor principal",
                "Ingresar pelicula", JOptionPane.QUESTION_MESSAGE);

        String genero = JOptionPane.showInputDialog(panel, "Ingrese el genero de la pelicula", "Ingresar pelicula",
                JOptionPane.QUESTION_MESSAGE);

        int duracion = Integer
                .parseInt(JOptionPane.showInputDialog(panel, "Ingrese la duracion de la pelicula en minutor",
                        "Ingresar pelicula", JOptionPane.QUESTION_MESSAGE));

        Pelicula pelicula = new Pelicula(id, nombre, actorPrincipal, genero, duracion);

        if (Sesion.INSTANCE.getPeliculas().insertarPelicula(pelicula))
            JOptionPane.showMessageDialog(panel, "Pelicula ingresada con exito", "Ingresar pelicula",
                    JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(panel, "Ya existe una pelicula con ese ID", "Error",
                    JOptionPane.ERROR_MESSAGE);

        actualizarTabla();

    }

    
    private void accion_bttEliminarPelicula() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(panel, "Ingrese el id de la pelicula que desea eliminar",
                "Eliminar pelicula", JOptionPane.QUESTION_MESSAGE));

        if (Sesion.INSTANCE.getPeliculas().eliminarPelicula(id))
            JOptionPane.showMessageDialog(panel, "Pelicula eliminada con exito", "Eliminar pelicula",
                    JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(panel, "No se encontro la pelicula con el id ingresado", "Error",
                    JOptionPane.ERROR_MESSAGE);

        actualizarTabla();
    }

    private void accion_bttEliminarConjuntoPeliculas() {
        // Eliminar conjunto de peliculas dado por otra lista de peliculas de otro
        // archivo / Usuario

        // Guardar en un arreglo todos los usuarios
        ArrayList<Usuario> usuarios = Database.INSTANCE.getUsuarios();
        usuarios.remove(Sesion.INSTANCE.getUsuario());

        // Pedirle al usuario que seleccione un usuario de la lista usuarios
        String[] usuariosString = new String[usuarios.size()];
        for (int i = 0; i < usuarios.size(); i++) {
            usuariosString[i] = usuarios.get(i).getNombre();
        }

        String usuarioSeleccionado = (String) JOptionPane.showInputDialog(panel,
                "Seleccione el usuario que desea eliminar peliculas", "Eliminar conjunto de peliculas",
                JOptionPane.QUESTION_MESSAGE, null, usuariosString, usuariosString[0]);

        ArrayList<Pelicula> peliculas = Database.INSTANCE.getUsuario(usuarioSeleccionado).getPeliculas();

        // Informarle al usuario las peliculas del usuario seleccionado

        String[] peliculasString = new String[peliculas.size()];
        for (int i = 0; i < peliculas.size(); i++) {
            peliculasString[i] = peliculas.get(i).getId() + " - " + peliculas.get(i).getNombre();
        }

        // Mostrar en un dialog informativo las peliculas del usuario seleccionado
        JOptionPane.showMessageDialog(panel, peliculasString, "Peliculas que se van a eliminar",
                JOptionPane.WARNING_MESSAGE);

        // Pedirle al usuario que confirme la eliminacion de las peliculas
        int confirmacion = JOptionPane.showConfirmDialog(panel,
                "Esta seguro que desea eliminar las peliculas del usuario " + usuarioSeleccionado + "?",
                "Eliminar conjunto de peliculas", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar las peliculas del usuario seleccionado
            Sesion.INSTANCE.getPeliculas().eliminarConjuntoPeliculas(peliculas);
            JOptionPane.showMessageDialog(panel, "Peliculas eliminadas con exito", "Eliminar conjunto de peliculas",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel, "No se eliminaron las peliculas", "Eliminar conjunto de peliculas",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        actualizarTabla();
    }

    private void accion_bttOrdenarPeliculas() {
        // Preguntar el tipo de ordenamiento, Por nombre o por duracion inversa
        String[] opciones = { "Nombre", "Duracion inverso" };
        String opcion = (String) JOptionPane.showInputDialog(panel, "Seleccione el tipo de ordenamiento",
                "Ordenar peliculas", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        // Ordenar las peliculas

        if (opcion.equals("Nombre")) {
            Sesion.INSTANCE.getPeliculas().ordenNatural();
        } else {
            Sesion.INSTANCE.getPeliculas().ordenDuracionInverso();
        }

        actualizarTabla();
    }

    public void iniciarPosiciones() {
        bttVolver.setBounds(400, 550, 400, 50);
        bttGuardar.setBounds(0, 550, 400, 50);
        tblPeliculas.setBounds(350, 25, 400, 500);
        scrollPane.setBounds(300, 25, 475, 500);

        bttConsultarPelicula.setBounds(25, 75, 250, 30);
        bttIngresarPelicula.setBounds(25, 175, 250, 30);
        bttEliminarPelicula.setBounds(25, 275, 250, 30);
        bttEliminarConjuntoPeliculas.setBounds(25, 375, 250, 30);
        bttOrdenarPeliculas.setBounds(25, 475, 250, 30);

    }

    public static void actualizarTabla() {
        modeloTabla.tableData = Sesion.INSTANCE.getPeliculas().getTablaPeliculas();
        modeloTabla.fireTableStructureChanged();
        modeloTabla.ajustarColumnas();
    }

    protected void iniciarBotones() {
        bttVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accion_bttVolver();
            }
        });

        bttGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accion_bttGuardar();
            }
        });

        bttIngresarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttIngresarPelicula();
            }
        });

        bttEliminarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttEliminarPelicula();
            }
        });

        bttConsultarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttConsultarPelicula();
            }
        });

        bttEliminarConjuntoPeliculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttEliminarConjuntoPeliculas();
            }
        });

        bttOrdenarPeliculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttOrdenarPeliculas();
            }
        });

        bttVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttVolver();
            }
        });

        bttGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                accion_bttGuardar();
            }
        });
    }

}
