package luchadores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TorneoSumo extends LuchadorJapones {
	private LuchadorJapones[] luchadores;

	public TorneoSumo() {

	}

	public void inicializar(String arch) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(arch));
		String[] linea;
		int cant = Integer.parseInt(br.readLine());
		this.luchadores = new LuchadorJapones[cant];
		for (int i = 0; i < cant; i++) {
			linea = br.readLine().split(" ");
			this.luchadores[i] = new LuchadorJapones();
			this.luchadores[i].setAltura(Integer.parseInt(linea[1]));
			this.luchadores[i].setPeso(Integer.parseInt(linea[0]));
		}
		br.close();
	}
	
	public void mostrar() {
		for(LuchadorJapones l : this.luchadores) {
			System.out.println(l.getPeso() + " " + l.getAltura());
		}
	}
	
	public void salida(String arch) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(arch));
		//Calculo de los luchadores
		int cantDom = 0;
		for (int i = 0;i<this.luchadores.length;i++) {
			for (int j = 0;j<this.luchadores.length;j++) {
				if (this.luchadores[i].domina(this.luchadores[j])) {
					cantDom += 1;
				}
			}
			pw.println(cantDom);
			cantDom = 0;
		}
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		TorneoSumo ts = new TorneoSumo();
		ts.inicializar("sumo.txt");
		ts.mostrar();
		ts.salida("out.txt");
	}

}
