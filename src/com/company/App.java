package com.company;

import java.math.BigInteger;
import java.util.ArrayList;

public class App {
	
	//Sequ?ncia de acessos ? memor?ria
	public static ArrayList<String> acessos = new ArrayList<String>();
			
	//Contadores
	public static float miss = 0;
	public static float hit = 0;
	public static float ratio;
	
	//Acesso direto
	//Cache(num de bits da tag, num de bits da linha, num de bits da palavra). Total deve ser 8
	public static Cache cache = new Cache(3,2,3);
	
	//Mapeamento associativo
	//CacheAssociativa(num de bits da tag, num de bits da linha, num de bits da palavra). Tag + palavra deve ser 5
	public static CacheAssociativa cacheAssoc = new CacheAssociativa(5,2,3);
	//MemAssociativa(num de bits da linha). Deve ser igual ao num de bits da linha da CacheAssoc 
	public static MemAssociativa memAssoc = new MemAssociativa(2);	
	
	public static void main(String[] args) {
		
		//Inserir a leitura do arquivo aqui
		acessos.add("3A");
		acessos.add("3B");
		acessos.add("C1");
		acessos.add("B6");
		acessos.add("3C");
		acessos.add("41");
		acessos.add("11");
		acessos.add("A3");
		acessos.add("D2");
		
		direto();
		reset();
		associativo();				
	}
	
	public static void direto(){
		
		System.out.println("\nSimula??o de cache com acesso direto");
		System.out.println(cache + "\n");
		
		String aux = "";
				
		for(int i=0; i<acessos.size() && !(acessos.get(i)==null);i++){
			
			aux = hexToBin(acessos.get(i));
			
			String tag = aux.substring(0, cache.tamTag);
			String linha = aux.substring(cache.tamTag, cache.tamLinha+cache.tamTag);
			
			if(!cache.buscaTag(tag, linha)){
				System.out.println(acessos.get(i) + " MISS T " + tag + " L " + linha);
				cache.write(tag, linha);				
				miss++;
			}
			else{
				System.out.println(acessos.get(i) + " HIT T " + tag + " L " + linha);
				hit++;
			}
		}
		
		//Imprime status da cache e contagem de hit/miss
		System.out.println("\nConte?do da cache com acesso direto");
		System.out.println(cache.getData());
		resultado();
		
	}
	
	public static void associativo(){
		
		System.out.println("\nSimula??o de cache com acesso associativo");
		System.out.println(cacheAssoc + "\n");
		
		String aux = "";
		
		for(int i=0; i<acessos.size() && !(acessos.get(i)==null);i++){
			
			aux = hexToBin(acessos.get(i));
			String tag = aux.substring(0, cacheAssoc.tamTag);
			
			int leitura = memAssoc.buscarTag(tag);
				
			if(leitura!=-1){
				System.out.println(acessos.get(i) + " MISS T " + tag);
				cacheAssoc.write(leitura);
				miss++;				
			}
			
			else{
				System.out.println(acessos.get(i) + " HIT T " + tag);
				hit++;				
			}
		}
		
		//Imprime status da cache e contagem de hit/miss
		System.out.println("\nConte?do da mem?ria associativa e da cache");
				
		for(int i=0;i<memAssoc.getTamanho();i++){
			System.out.println("Linha " + i);
			System.out.print("Mem?ria associativa - " + memAssoc.getData(i));
			System.out.println(" Cache - " + cacheAssoc.getData(i));
		}
		
		resultado();
	}
	
	public static void resultado(){
		
		//Contabiliza resultado
		ratio = hit/(miss+hit);
		
		System.out.println("\nTotal miss " + miss
				+ "\nTotal hit " + hit
				+ "\nHit ratio " + ratio*100 + "%");
	}
	
	public static void reset(){
		miss = 0;
		hit = 0;
		System.out.println("\nContadores resetados. MISS: " + miss + " HIT: " + hit + "\n");
		
	}
	
	//C?digo obtido em https://stackoverflow.com/a/41707271
	// Converte string em hexadecimal para string em bin?rio	
	public static String hexToBin(String hex) {
	    
		int tamanho = hex.length() * 4;
	    String binario = new BigInteger(hex, 16).toString(2);

	    if(binario.length() < tamanho)
	    	binario = completar(binario, tamanho);
	    
	    return binario;
	}
	
	//Completa o binario com 0s ? esquerda se necess?rio
	public static String completar(String binario, int tamanho){
	    
	        int dif = tamanho - binario.length();
	        String pad = "";
	        for(int i = 0; i < dif; ++i){
	            pad = pad.concat("0");
	        }
	        binario = pad.concat(binario);
	    
	    return binario;
	}

}
