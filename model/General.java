package model;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public abstract class General {
	protected double[][] matrizAdyacencia;
	protected Vector<Integer> tourTsp;
	protected double costo; 
	
	public General(double[][] matrizAdyacencia){
		this.matrizAdyacencia= matrizAdyacencia;
	}
	
	/**
	 * @return Ruta calculada, Tour TSP.
	 */
	public Vector<Integer> getTourTsp(){
		return tourTsp;
	}
	
	/**
	 * @return Costo para recorrer el tourTSP.
	 */
	public double getCosto(){
		return costo;
	}
	
	/**
	 * Inicilizar Tour tsp. Limpia Vector
	 */
	protected void inicializarTourTsp(){
		tourTsp= null;
		tourTsp= new Vector<Integer>();
	}
	
	/**
	 * Cálculo del peso para la ruta establecida en el parámetro.
	 * @param tourTsp Ruta para calcular el coste.
	 * @return Costo para recorrer la ruta.
	 */
	protected double calcularCosteTourTsp(){
		costo= 0;
				
		for(int i=0; i<tourTsp.size()-1; i++){
			costo+= matrizAdyacencia[tourTsp.get(i)-1][tourTsp.get(i+1)-1]; 
		}
		
		return costo;
	}
	
	/**
	 * Cálculo de número aleatorio delimitado por el valor de inicio y valor final
	 * @param inicio Valor mínimo que puede retornar.
	 * @param ultimo Valor máximo que puede retornar.
	 * @return Valor aleatorio.
	 */
	public final static int obtenerAleatorio(int inicio, int ultimo){
		if(inicio != ultimo){
			return ThreadLocalRandom.current().nextInt(inicio, ultimo);
		}
		return 0;
	}
}
