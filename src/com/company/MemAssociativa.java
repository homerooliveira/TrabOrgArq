package com.company;

import java.util.ArrayList;

public class MemAssociativa {
	
	private String[] tags;
	private ArrayList<Integer> LRU; 
		
	public MemAssociativa(int tamTags){
		
		this.tags = new String[(int)Math.pow(2,tamTags)];	
		this.LRU = new ArrayList<Integer>();
		
		for(int i=0;i<tags.length;i++){
			LRU.add(i);
		}		
	}
	
	public int getTamanho(){
		return tags.length;
	}

	public int buscarTag(String tag){
		
		for(int i=0;i<tags.length;i++){
			if(tag.equals(tags[i])){
				atualizaLRU(i);
				System.out.println("Encontrei " + tag + " em linha " + Integer.toBinaryString(i));
				return -1;
			}
		}
		
		return write(tag);
	}

	private void atualizaLRU(int i) {
		
		System.out.print("\nLRU � ");
		for(int n : LRU)
			System.out.print(n);
		System.out.println("\nLRU removeu " + i);
		LRU.remove(LRU.indexOf(i));
		LRU.add(i);
		System.out.println("LRU adicionei " + LRU.get(LRU.size()-1));
		
		System.out.print("\nLRU � ");
		for(int n : LRU)
			System.out.print(n);
		System.out.println("\n");
	}

	private int write(String tag) {
		
		int linha = LRU.get(0); 
		tags[linha] = tag;
		atualizaLRU(linha);
		
		System.out.println("Gravei " + tag + " em linha " + linha);
		
		return linha;
	}
	
	public String getData(int linha) {		
		return tags[linha];		
	}
	
	
	
	
			
		
		
	
	
	
	
}
