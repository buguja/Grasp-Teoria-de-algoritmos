package model;

import java.util.Vector;

public class Construccion extends General{
	private Vector<Integer> nodosDisponibles;
	private int nodoActual;
	private float alfa;
	
	public Construccion(double[][] matrizAdyacencia, float alfa){
		super(matrizAdyacencia);
		this.alfa= alfa;
	}
	
	/**
	 * Establece el nodo actual. Resta 1 esto es para que trabaje con arreglos
	 * @param nodoActual
	 */
	public void setNodoActual(int nodoActual){
		this.nodoActual= nodoActual - 1;
	}
	
	/**
	 * Crear un vector con todos los nodos necesarios, ayuda para recorrer nodos
	 */
	private void inicializarNodosDisponibles(){
		nodosDisponibles= null;
		nodosDisponibles= new Vector<Integer>();
		for(int i=0; i<matrizAdyacencia.length; i++){
			nodosDisponibles.add(i+1);
		}
	}
	
	/**
	 * Cáclulo del costo mayor en aristas. A partir de nodo actual hacia nodo disponible.
	 * @return Mayor costo.
	 */
	private double calcularBeta(){
		int nodoDisponible= nodosDisponibles.get(0);
		double mayor= matrizAdyacencia[nodoActual][nodoDisponible-1];
		
		for(int i=1; i<nodosDisponibles.size(); i++){
			nodoDisponible= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoDisponible-1] > mayor){
				mayor= matrizAdyacencia[nodoActual][nodoDisponible-1];
			}
		}
		
		return mayor;
	}
	
	/**
	 * Cáclulo del costo menor en aristas. A partir de nodo actual hacia nodo disponible.
	 * @return Menor costo.
	 */
	private double calcularGama(){
		int nodoDisponible= nodosDisponibles.get(0);
		double menor= matrizAdyacencia[nodoActual][nodoDisponible-1];
		
		for(int i=1; i<nodosDisponibles.size(); i++){
			nodoDisponible= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoDisponible-1] < menor){
				menor= matrizAdyacencia[nodoActual][nodoDisponible-1];
			}
		}
		
		return menor;
	}
	
	/**
	 * Cálculo de la formula para LRC. <b>"(beta-(alfa*(beta-gama)))</b>" 
	 * @return Valor para comprobar pesos aceptados en LRC.
	 */
	private double calcularFormula(){
		double beta= calcularBeta();
		double gama= calcularGama();
		return (beta - (alfa * (beta - gama)));
	}
	
	/**
	 * Generar lista restringida de candidatos. 
	 * @return LRC
	 */
	private Vector<Integer> calcularLRC(){
		Vector<Integer> lrc= new Vector<Integer>();
		double resultadoEcuacion= calcularFormula();
		int nodoDisponible= 0;
		
		for(int i=0; i<nodosDisponibles.size(); i++){
			nodoDisponible= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoDisponible-1] <= resultadoEcuacion){
				lrc.add(nodoDisponible);
			}
		}
		
		return lrc;
	}
	
	/**
	 * Resolver el proceso de construcción para un nodo elegido
	 */
	public void resolver(int nodoInicial){
		Vector<Integer> lrc= null;
		int nodoLRC;
		
		inicializarTourTsp();
		setNodoActual(nodoInicial);
		inicializarNodosDisponibles();
		
		tourTsp.addElement(nodosDisponibles.get(nodoActual));
		nodosDisponibles.removeElementAt(nodoActual);
	
		while(!nodosDisponibles.isEmpty()){
			lrc= calcularLRC();
			nodoLRC= lrc.get(obtenerAleatorio(0, lrc.size()));
			nodosDisponibles.removeElement(nodoLRC);
			tourTsp.addElement(nodoLRC);
			setNodoActual(nodoLRC);
		}
		
		tourTsp.addElement(tourTsp.get(0));
		calcularCosteTourTsp();
	}
}