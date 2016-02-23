package controller;
/**
 * @author Javier Burón Gutiérrez.
 *
 */

import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import model.Construccion;
import model.DosSwap;
import model.General;
import model.MatrizAdyacencia;
import view.GUI;

public class Controller implements ActionListener{
	private GUI gui= null;
	private MatrizAdyacencia matrizAdyacencia;
	
	public Controller(){
		gui= new GUI();
		gui.btnExaminar.addActionListener(this);
		gui.btnCalcular.addActionListener(this);
	}
	
	/**
	 * Obtener el contenido del archivo de texto.
	 * @param Ruta Ruta del archivo en el disco duro
	 * @return Contenido del archivo
	 * @throws IOException En caso de tener problemas con el archivo, lanza excepción
	 */
	private String obtenerContenidoArchivo(String ruta) throws IOException{
		String content= "";
		FileReader in= new FileReader(ruta);
		BufferedReader bfR= new BufferedReader(in);
		
		String line= null;
		while((line= bfR.readLine()) != null){
			content+= line + "\n";
		}
		
		bfR.close();
		return content;
	}
	
	/**
	 * Obtener contenido de archivo, obtener par de coordenadas y calcular matriza de adyacecia. Llamada a métodos.
	 * @throws IOException Selanza esta excepción cuando se llama al método <b>obtenerContenidoArchivo()</b>
	 */
	private void cargarContenidoEnMemoria() throws IOException{
		String name;
		String ruta= gui.obtenerRutaArchivo();
		if(ruta == null){
			throw new IOException("Archivo no encontrado");
		}
		String contenidoFile= "";

		gui.textFieldPathFile.setText(ruta);
		contenidoFile= obtenerContenidoArchivo(ruta);
		if(contenidoFile != ""){
			String[] splitContentFile= contenidoFile.split("\n");
			name= splitContentFile[0].replaceAll("NAME", "");
			name= name.replace(':', ' ');
			name= name.trim();
			gui.lblTipoArchivo.setText(name.toUpperCase());
			matrizAdyacencia= new MatrizAdyacencia(splitContentFile);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(gui.btnExaminar)){
			gui.inicializar();
			try {
				cargarContenidoEnMemoria();
				gui.btnCalcular.setEnabled(true);
			} catch (IOException e1) {
				gui.mostrarMensajeDialog(e1.getMessage());
			}
		}else if(e.getSource().equals(gui.btnCalcular)){
			Construccion construccion= new Construccion(matrizAdyacencia.matrizAdyacencia, 0.8f);
			DosSwap dosSwap= new DosSwap(matrizAdyacencia.matrizAdyacencia);
			construccion.resolver(Integer.parseInt(gui.textFieldNodoInicial.getText()));
			dosSwap.generarSVn(construccion.getTourTsp(), construccion.getCosto());
			for(int i=0; i<1000; i++){
				construccion.resolver(General.obtenerAleatorio(2, 40));
				dosSwap.generarSVn(construccion.getTourTsp(), construccion.getCosto());
			}
			
			gui.textFieldCosto.setText(String.valueOf(dosSwap.getCosto()));
			gui.textAreaRecorrido.setText(dosSwap.getTourTsp().toString());
		}
	}
}
