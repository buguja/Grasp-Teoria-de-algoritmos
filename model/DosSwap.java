package model;

import java.util.Vector;

public class DosSwap extends General{
	
	public DosSwap(double[][] matrizAdyacencia) {
		super(matrizAdyacencia);
	}

	public void generarSVn(Vector<Integer> s, double menorCosto){
		Integer tmp, tmp2;
		double newCoste;
		tourTsp= s;
		costo= menorCosto;
		
		for(int i=1; i<s.size(); i++){
			tmp= s.get(0);
			for(int j=1; j<s.size(); j++){
				s.set(j-1, tmp);
				tmp= s.get(j);
			}
			tourTsp= s;
			newCoste= calcularCosteTourTsp();
			if(newCoste < costo){
				costo= newCoste;
			}
		}
	}
}