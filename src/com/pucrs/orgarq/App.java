package com.pucrs.orgarq;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	//Sequ￪ncia de acessos ￠ memor￡ria
	public static ArrayList<String> acessos = new ArrayList<String>();
	
	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(Paths.get("enderecos.csv"))) {
			scanner.useDelimiter("\\r\\n");
			while (scanner.hasNext()) {
				final String next = scanner.next();
				System.out.println(next);
				acessos.add(next);
			}

			System.out.println(acessos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		salvarResultados();
	}

	private static void salvarResultados() {
		try (PrintWriter pw = new PrintWriter("resultados.txt")){

			try (Scanner scanner = new Scanner(Paths.get("config.csv"))) {
				while (scanner.hasNext()){
					Scanner parser = new Scanner(scanner.next());
					parser.useDelimiter(";");

					String tipoDeMemoria = parser.next();

					if (tipoDeMemoria.equals("d")) {
						int tamanhoTag = parser.nextInt();
						int tamanhoLinha = parser.nextInt();
						int tamanhoPalavra = parser.nextInt();

						Cache cache = new Cache(tamanhoTag, tamanhoLinha, tamanhoPalavra);
						AcessoDireto acessoDireto = new AcessoDireto(cache, acessos);

						pw.println();
						pw.println(acessoDireto.resultado());
					} else if (tipoDeMemoria.equals("a")){
						int tamanhoTag = parser.nextInt();
						int tamanhoLinha = parser.nextInt();
						int tamanhoPalavra = parser.nextInt();

						 CacheAssociativa cacheAssociativa = new CacheAssociativa(tamanhoTag, tamanhoLinha, tamanhoPalavra);
						 MemoriaAssociativa memoriaAssociativa = new MemoriaAssociativa(tamanhoLinha);
						 AcessoAssociativo acessoAssociativo = new AcessoAssociativo(cacheAssociativa, memoriaAssociativa, acessos);

						 pw.println();
						 pw.println(acessoAssociativo.resultado());
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
