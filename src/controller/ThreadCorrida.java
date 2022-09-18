package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorrida extends Thread {

	private int idCarro;
	private Semaphore escuderia1;
	private Semaphore escuderia2;
	private Semaphore escuderia3;
	private Semaphore escuderia4;
	private Semaphore escuderia5;
	private Semaphore escuderia6;
	private Semaphore escuderia7;
	private Semaphore semaforo;
	private double melhorVoltaPiloto = 100;
	private static double melhorVolta = 100;
	private static double[] posicaoPilotoTempo;
	private static int contador = 0;
	private static int grid = 0;

	public ThreadCorrida(int idCarro, Semaphore semaforo, Semaphore escuderia1, Semaphore escuderia2,
			Semaphore escuderia3, Semaphore escuderia4, Semaphore escuderia5, Semaphore escuderia6,
			Semaphore escuderia7, double[] posicaoPilotoTempo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
		this.escuderia1 = escuderia1;
		this.escuderia2 = escuderia2;
		this.escuderia3 = escuderia3;
		this.escuderia4 = escuderia4;
		this.escuderia5 = escuderia5;
		this.escuderia6 = escuderia6;
		this.escuderia7 = escuderia7;
		ThreadCorrida.posicaoPilotoTempo = posicaoPilotoTempo;
	}

	@Override
	public void run() {
		escuderias();
		gridLargada();
	}

	private void escuderias() {
		if (idCarro == 1 || idCarro == 2) {
			try {
				escuderia1.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia1.release();
			}
		}
		if (idCarro == 3 || idCarro == 4) {
			try {
				escuderia2.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia2.release();
			}
		}
		if (idCarro == 5 || idCarro == 6) {
			try {
				escuderia3.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia3.release();
			}
		}
		if (idCarro == 7 || idCarro == 8) {
			try {
				escuderia4.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia4.release();
			}
		}
		if (idCarro == 9 || idCarro == 10) {
			try {
				escuderia5.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia5.release();
			}
		}
		if (idCarro == 11 || idCarro == 12) {
			try {
				escuderia6.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia6.release();
			}
		}
		if (idCarro == 13 || idCarro == 14) {
			try {
				escuderia7.acquire();
				entradaPista();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				escuderia7.release();
			}
		}
	}

	private void entradaPista() {
		try {
			semaforo.acquire();
			System.out.println("#" + idCarro + " entrou na pista");
			corrida();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void corrida() {
		int pista = 1000;
		int deslocamento = (int) ((Math.random() * 101) + 100);
		int tempo = 100;
		int percorrido = 0;
		
		for (int i = 1; i < 4; i++) {
			double tInicial = System.nanoTime();
			while (percorrido < pista) {
				percorrido += deslocamento;
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			percorrido = 0;
			double tFinal = System.nanoTime();
			double tTotal = tFinal - tInicial;
			tTotal = tTotal / Math.pow(10, 9);
			System.out.println("#" + idCarro + "==> Volta " + i + " com o tempo de: " + tTotal);
			
			if (tTotal < melhorVoltaPiloto) {
				melhorVoltaPiloto = tTotal;
			}
		}
		
		if (melhorVoltaPiloto < melhorVolta) {
			melhorVolta = melhorVoltaPiloto;
		}
		posicaoPilotoTempo[idCarro] = melhorVoltaPiloto;

		System.err.println("#" + idCarro + " tempo da melhor volta: " + melhorVoltaPiloto);
		contador++;
	}

	private void gridLargada() {
		while (contador < 14) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (grid != 1) {
			System.out.println("Melhor volta da corrida:" + melhorVolta);

			System.out.println("Ordem Grid da Largada\n");
			int tamanho = posicaoPilotoTempo.length;
			for (int i = 0; i <= tamanho; i++) {
				for (int j = 0; j < tamanho - 1; j++) {
					if (posicaoPilotoTempo[j] > posicaoPilotoTempo[j + 1]) {
						double aux = posicaoPilotoTempo[j];
						posicaoPilotoTempo[j] = posicaoPilotoTempo[j + 1];
						posicaoPilotoTempo[j + 1] = aux;
					}
				}
			}

			for (int i = 1; i < 15; i++) {
				System.out.println(i + "o. com o tempo de: " + posicaoPilotoTempo[i]);
			}
			grid++;
		}
	}
}
