package model;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class General {
	/**
	 * C�lculo del peso para la ruta establecida en el par�metro.
	 * @param tourTsp Ruta para calcular el coste.
	 * @return Costo para recorrer la ruta.
	 */
	protected double calcularCosteTourTsp(Vector<Integer> tourTsp){
		double costo= 0;
		
		
		
		return costo;
	}
	
	
	/**
	 * C�lculo de n�mero aleatorio delimitado por el valor de inicio y valor final
	 * @param inicio Valor m�nimo que puede retornar.
	 * @param ultimo Valor m�ximo que puede retornar.
	 * @return Valor aleatorio.
	 */
	protected int obtenerAleatorio(int inicio, int ultimo){
		return ThreadLocalRandom.current().nextInt(inicio, ultimo);
	}
}
