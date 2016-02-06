package model;

import java.io.FileReader;
import java.util.Vector;

public class Construccion {
	private Vector<Integer> tour;
	private Vector<Integer> nodosDisponibles;
	public double costo;
	private double[][] matrizAdyacencia; 
	private int nodoActual;
	private int cantidadNodos;
	private float alfa;
	
	public Construccion(double[][] matrizAdyacencia, float alfa, int nodoActual){
		this.matrizAdyacencia= matrizAdyacencia;
		this.cantidadNodos= matrizAdyacencia.length;
		this.alfa= alfa;
		setNodoActual(nodoActual);
		tour= new Vector<Integer>();
		
		inicializarNodosDisponibles();
		resolver();
	}
	
	public void setNodoActual(int nodoActual){
		this.nodoActual= nodoActual - 1;
	}
	
	public void resolver(){
		Vector<Integer> lcr= null; 
		
		nodosDisponibles.removeElementAt(nodoActual);
		tour.add(nodoActual);
		
		lcr= calcularLCR();
	}
	
	private void inicializarNodosDisponibles(){
		nodosDisponibles= new Vector<Integer>();
		for(int i=0; i<cantidadNodos; i++){
			nodosDisponibles.add(i+1);
		}
	}
	
	/*private boolean isInTour(int nodo){
		for(int i=0; i<tour.size(); i++){
			if(tour.get(i).equals(nodo)){
				return true;
			}
		}
		return false; 
	}*/
	
	private double obtenerBeta(){
		double mayor;
		int nodoCandidato= nodosDisponibles.get(0);
		mayor= matrizAdyacencia[nodoActual][nodoCandidato];
		for(int i=1; i<nodosDisponibles.size(); i++){
			nodoCandidato= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoCandidato] > mayor){
				mayor= matrizAdyacencia[nodoActual][nodoCandidato];
			}
		}
		return mayor;
	}
	
	private double obtenerGama(){ //No me acuerdo si revisa  en la matriz n,n
		double menor;
		int nodoCandidato= nodosDisponibles.get(0);
		menor= matrizAdyacencia[nodoActual][nodoCandidato];
		for(int i=1; i<nodosDisponibles.size(); i++){
			nodoCandidato= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoCandidato] < menor){
				menor= matrizAdyacencia[nodoActual][nodoCandidato];
			}
		}
		return menor;
	}
	
	private double ejecutarEcuacion(){
		double beta= obtenerBeta();
		double gama= obtenerGama();
		return (beta - (alfa * (beta - gama))); //aplicada formula correctamente?
	}
	
	private Vector<Integer> calcularLCR(){
		double resultadoEcuacion= ejecutarEcuacion();
		int nodoCandidato= 0;
		Vector<Integer> lcr= new Vector<Integer>();
		for(int i=0; i<nodosDisponibles.size(); i++){ //No me acuerdo si revisa  en la matriz n,n
			nodoCandidato= nodosDisponibles.get(i);
			if(matrizAdyacencia[nodoActual][nodoCandidato] < resultadoEcuacion){
				lcr.add(nodoCandidato);
			}
		}
		return lcr;
	}
}