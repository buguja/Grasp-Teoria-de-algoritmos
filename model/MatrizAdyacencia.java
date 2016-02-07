package model;

import java.util.Vector;

public class MatrizAdyacencia {
	public double[][] matrizAdyacencia;
	public int size;
	
	public MatrizAdyacencia(String[] splitContentFile){
		size= splitContentFile.length-7;
		inicializarMatrizAdyacencia();
		calcularMatrizAdyacencia(obtenerParCoordenadas(splitContentFile));
	}
	
	/*public double obtenerPeso(int nodoInicial, int nodoFinal){
		return matrizAdyacencia[nodoInicial][nodoFinal];
	}*/
	
	/**
	 * Inicializar matriz de adyacencia, cada elemento en un número negativo
	 * @param size Tamaño para la matriz de adyacencia
	 */
	private void inicializarMatrizAdyacencia(){
		matrizAdyacencia= new double[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				matrizAdyacencia[i][j]= -1991;
			}
		}
	}

	/**
	 * Calcula la matriza de adyacencia utilizando los datos obtenidos del archivo
	 * @param parCoordenadas Par de coordenadas, obtenidas del archivo.
	 */
	private void calcularMatrizAdyacencia(Vector<String> parCoordenadas) {
		for(int i=0; i<parCoordenadas.size() - 1; i++){
			for(int j=i+1; j<parCoordenadas.size(); j++){
				matrizAdyacencia[j][i] = 
						matrizAdyacencia[i][j]= calcularPeso(parCoordenadas.get(i), parCoordenadas.get(j));
			}
		}
	}
	
	/**
	 * Obtner en Double las coordenadas de las coordenadas en String 
	 * @param Coordenadas Coordenadas en String
	 * @return Coordenadas en double
	 */
	private Double[] calcularCoordenadasNodo(String coordenadas){
		String[] splitCoordA= coordenadas.split(" ");
		Double[] par={
			Double.parseDouble(splitCoordA[0]),
			Double.parseDouble(splitCoordA[1])
		};
		
		return par;
	}
	
	/**
	 * Calcular peso entre nodo A y B
	 * @param coordA Par de coordenadas para el nodo A
	 * @param coordB Par de coordenadas para el nodo B
	 * @return Peso entre el nodo A y B
	 */
	private double calcularPeso(String coordA, String coordB) {
		double peso= 0;
		Double[] coordenadasA= calcularCoordenadasNodo(coordA);
		Double[] coordenadasB= calcularCoordenadasNodo(coordB);
		
		peso= Math.pow((coordenadasB[0] - coordenadasA[0]), 2) + Math.pow((coordenadasB[1] - coordenadasA[1]), 2);
		peso= Math.sqrt(peso);
		
		return peso;
	}
	
	/**
	 * Obtener vector con par de coordenadas y establece el nombre del archivo analizado a la GUI.
	 * @param contenidoFile Contenido del archivo de texto
	 * @return Vector con par de coordenadas.
	 */
	private Vector<String> obtenerParCoordenadas(String[] splitContentFile) {
		Vector<String> parCoordenadas= new Vector<String>();
		
		String[] splitParCoord= null; 
		for(int i=6; i<splitContentFile.length-1; i++){
			splitParCoord= splitContentFile[i].split(" ");
			parCoordenadas.add(splitParCoord[1] + " " + splitParCoord[2]);
		}
		
		return parCoordenadas;
	}
}
