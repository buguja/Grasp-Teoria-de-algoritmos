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
	 * C�clulo del costo mayor en aristas. A partir de nodo actual hacia nodo disponible.
	 * @return Mayor costo.
	 */
	private double calcularBeta(){
		int nodoDisponible= nodosDisponibles.get(0);
		double mayor= matrizAdyacencia[nodoActual][nodoDisponible];
		
		for(int i=1; i<nodosDisponibles.size(); i++){
			nodoDisponible= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoDisponible] > mayor){
				mayor= matrizAdyacencia[nodoActual][nodoDisponible];
			}
		}
		
		return mayor;
	}
	
	/**
	 * C�clulo del costo menor en aristas. A partir de nodo actual hacia nodo disponible.
	 * @return Menor costo.
	 */
	private double calcularGama(){
		int nodoDisponible= nodosDisponibles.get(0);
		double menor= matrizAdyacencia[nodoActual][nodoDisponible];
		
		for(int i=1; i<nodosDisponibles.size(); i++){
			nodoDisponible= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoDisponible] < menor){
				menor= matrizAdyacencia[nodoActual][nodoDisponible];
			}
		}
		
		return menor;
	}
	
	/**
	 * C�lculo de la formula para LRC. <b>"(beta-(alfa*(beta-gama)))</b>" 
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
			if(matrizAdyacencia[nodoActual][nodoDisponible] <= resultadoEcuacion){
				lrc.add(nodoDisponible);
			}
		}
		
		return lrc;
	}
	
	/**
	 * Resolver el proceso de construcci�n para un nodo elegido
	 */
	public void resolver(int nodoInicial){
		Vector<Integer> lrc= null;
		Integer randomLRC;
		
		inicializarTourTsp();
		setNodoActual(nodoInicial);
		inicializarNodosDisponibles();
		
		tourTsp.addElement(nodosDisponibles.get(nodoActual));
		nodosDisponibles.removeElementAt(nodoActual);
		
		do{
			lrc= calcularLRC();
			randomLRC= lrc.get(obtenerAleatorio(0, lrc.size()));
			tourTsp.addElement(randomLRC);
			nodosDisponibles.removeElement(randomLRC);
			nodoActual= randomLRC;
		}while(!nodosDisponibles.isEmpty());
		
		tourTsp.addElement(tourTsp.get(0));
		calcularCosteTourTsp();
	}
}