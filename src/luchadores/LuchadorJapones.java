package luchadores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LuchadorJapones {
	private int peso;
	private int altura;
	
	public LuchadorJapones() {
		this.peso = 0;
		this.altura = 0;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public boolean domina(LuchadorJapones obj)
	{
		int domPeso = this.peso - obj.peso;
		int domAlt = this.altura - obj.altura;
		if ((domPeso > 0 && domAlt > 0) || (domPeso == 0 && domAlt > 0) || (domPeso > 0 && domAlt == 0) ) {
			return true;
		}
		else if ((domPeso < 0 && domAlt < 0) || (domPeso == 0 && domAlt < 0) || (domPeso < 0 && domAlt == 0)) {
			return false;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		//Lectura archivo in
		BufferedReader br = new BufferedReader(new FileReader("sumo.txt"));
		int cant = Integer.parseInt(br.readLine());
		LuchadorJapones[] arr_lj = new LuchadorJapones[10];
		String[] linea;
		for(int i = 0;i<cant;i++) {
			linea = br.readLine().split(" ");
			arr_lj[i] = new LuchadorJapones();
			arr_lj[i].altura = Integer.parseInt(linea[1]);
			arr_lj[i].peso = Integer.parseInt(linea[0]);
		}
		
		//Recorro para verificar el vector con un foreach
		for(LuchadorJapones l : arr_lj) {
			System.out.println(l.peso + " " + l.altura);
		}
		
		//Creo archivo out
		PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
		
		//Calculo de los luchadores
		int cantDom = 0;
		for (int i = 0;i<cant;i++) {
			for (int j = 0;j<cant;j++) {
				if (arr_lj[i].domina(arr_lj[j])) {
					cantDom += 1;
				}
			}
			pw.println(cantDom);
			cantDom = 0;
		}
		
		//Cierro el archivo
		br.close();
		pw.close();
	}
}
