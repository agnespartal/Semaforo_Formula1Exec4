package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCorrida;

public class Principal {

	public static void main(String[] args) {
		
		int permissao2 = 5;
		Semaphore semaphore1 = new Semaphore(1);
		Semaphore semaphore3 = new Semaphore(1);
		Semaphore semaphore4 = new Semaphore(1);
		Semaphore semaphore5 = new Semaphore(1);
		Semaphore semaphore6 = new Semaphore(1);
		Semaphore semaphore7 = new Semaphore(1);
		Semaphore semaphore8 = new Semaphore(1);
		Semaphore semaphore2 = new Semaphore(permissao2);
		
		 
		double [] vetor = new double [15];
		
		for (int idCarro = 1; idCarro < 15; idCarro++) {
			Thread threadCorrida = new ThreadCorrida(idCarro,semaphore2,semaphore1,semaphore3,semaphore4,semaphore5,semaphore6,semaphore7,semaphore8 , vetor);
			threadCorrida.start();
		}
		
		
		
		
	}

}
