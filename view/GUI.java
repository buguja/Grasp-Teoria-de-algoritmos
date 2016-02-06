package view;
/**
 * @author Javier Burón Gutiérrez.
 *
 */

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
	public JTextField textFieldPathFile;
	public JButton btnCalcular;
	public JButton btnExaminar;
	public JTextArea textAreaRecorrido;
	public JTextField textFieldCosto;
	public JLabel lblTipoArchivo;
	public JTextField textFieldNodoInicial;
	
	public GUI(){
		setType(Type.UTILITY);
		setTitle("Grasp y búsqueda local 2-Swap");
		setResizable(false);
		setSize(new Dimension(480, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblJavierBurnGutirrez = new JLabel("Javier Bur\u00F3n Guti\u00E9rrez");
		panel_1.add(lblJavierBurnGutirrez);
		lblJavierBurnGutirrez.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTipoArchivo = new JLabel("archivo");
		lblTipoArchivo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTipoArchivo.setForeground(new Color(60, 179, 113));
		lblTipoArchivo.setBackground(Color.WHITE);
		panel_1.add(lblTipoArchivo);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblArchivo = new JLabel("Archivo:");
		panel_3.add(lblArchivo);
		
		textFieldPathFile = new JTextField();
		textFieldPathFile.setBackground(Color.WHITE);
		textFieldPathFile.setEditable(false);
		panel_3.add(textFieldPathFile);
		textFieldPathFile.setColumns(20);
		
		btnExaminar = new JButton("Examinar");
		panel_3.add(btnExaminar);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		textAreaRecorrido = new JTextArea();
		textAreaRecorrido.setTabSize(4);
		textAreaRecorrido.setWrapStyleWord(true);
		textAreaRecorrido.setLineWrap(true);
		textAreaRecorrido.setEditable(false);
		textAreaRecorrido.setRows(10);
		textAreaRecorrido.setColumns(40);
		panel_4.add(textAreaRecorrido);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		
		JLabel lblCosto = new JLabel("Costo:");
		panel_5.add(lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setBackground(Color.WHITE);
		textFieldCosto.setEditable(false);
		panel_5.add(textFieldCosto);
		textFieldCosto.setColumns(15);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.EAST);
		
		JLabel lblNodoInicial = new JLabel("Nodo inicial:");
		panel_6.add(lblNodoInicial);
		
		textFieldNodoInicial = new JTextField();
		textFieldNodoInicial.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(textFieldNodoInicial);
		textFieldNodoInicial.setColumns(5);
		
		btnCalcular = new JButton("Calcular");
		panel_6.add(btnCalcular);
		
		inicializar();
		setVisible(true);
	}
	
	public void inicializar(){
		btnCalcular.setEnabled(false);
		textAreaRecorrido.setText("");
		textFieldCosto.setText("");
		textFieldPathFile.setText("");
		lblTipoArchivo.setText("");
		textFieldNodoInicial.setText("1");
	}
	
	public String obtenerRutaArchivo(){
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(false);
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			return fc.getSelectedFile().getPath();
		}
		return null;
	}
	
	public void mostrarMensajeDialog(String contenido){
		JOptionPane.showMessageDialog(null, contenido);
	}
}
