package com.company;

public class CacheAssociativa {
	
	private Linha[] linhas;
	int tamTag;
	int tamPalavra;
	int tamLinha;
	
	private class Linha {
		private String[] dados;
		private boolean preenchida;
		
		public Linha(){
			this.dados = new String[(int)Math.pow(2,tamPalavra)];
			this.preenchida = false;
		}
		
		public void writeData(){
			
			if(!preenchida){
				String palavra = "";
				
				for(int i=0;i<dados.length;i++){
					
					palavra = Integer.toBinaryString(i);
					
					while(palavra.length()<tamPalavra)
			        	palavra = "0" + palavra;
			        	
					dados[i] = palavra;
				}
				
				preenchida = true;
			}
		}
	}
	
	public CacheAssociativa(int tamTag, int tamLinha, int tamPalavra){
		
		this.tamTag = tamTag;
		this.tamLinha = tamLinha; 
		this.tamPalavra = tamPalavra;		
		
		this.linhas = new Linha[(int)Math.pow(2,tamLinha)];
		System.out.println("Criei cache associativa com " + linhas.length + " linhas");
		
		for(int i=0;i<linhas.length;i++){
			System.out.println("Criei linha " + i);
			linhas[i] = new Linha();
		}
				
	}
	
	public void write(int linha){
		
		linhas[linha].writeData();
	}
	
	public String getData(int linha) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<linhas[linha].dados.length;i++){				
			sb.append(linhas[linha].dados[i] + "\t");
		}
		
		return sb.toString();		
	}
	
	@Override
	public String toString(){
		return "\nTamanho da tag " + tamTag + " bits" 
				+ "\nTamanho da palavra " + tamPalavra + " bits"
				+ "\nQuantidade de linhas " + linhas.length + " bits";		
	}
	
	
	
}
