package com.pucrs.orgarq;

public class Cache {
	
	private Linha[] linhas;
	int tamTag;	
	int tamPalavra;
	int tamLinha;
	
	private class Linha {
		private String tag;
		private String[] dados;
		
		public Linha(){
			
			this.tag = "666";
			this.dados = new String[(int)Math.pow(2,tamPalavra)];
		}
		
		public boolean compararTag(String tag){
			return this.tag.equals(tag);			
		}
		
		public void writeData(String tag, String linha){
			
			this.tag = tag;
			
			String palavra = "";
			
			for(int i=0;i<dados.length;i++){
				
				palavra = Integer.toBinaryString(i);
				
				while(palavra.length()<tamPalavra)
		        	palavra = "0" + palavra;
		        	
				dados[i] = tag + linha + palavra;				
				System.out.println("Gravei " + dados[i] + " T " + tag + " L " + linha + " P " + palavra);
			}			
		}
		
	}
	
	public Cache(int tamTag, int tamLinha, int tamPalavra){
		
		this.tamTag = tamTag;
		this.tamLinha = tamLinha; 
		this.tamPalavra = tamPalavra;
		
		
		this.linhas = new Linha[(int)Math.pow(2,tamLinha)];
		System.out.println("Criei cache com " + linhas.length + " linhas");
		
		for(int i=0;i<linhas.length;i++){
			System.out.println("Criei linha " + i);
			linhas[i] = new Linha();
		}
				
	}
	
	
	public boolean buscaTag(String tag, String linha){
		
		int pos = Integer.parseInt(linha,2);
		
		if(linhas[pos].compararTag(tag))
			return true;
		
		return false;
	}

	public void write(String tag, String linha){
		
		this.linhas[Integer.parseInt(linha,2)].writeData(tag, linha);
	}
	
	public String getData() {
		StringBuilder sb = new StringBuilder();

		for(int i=0;i<linhas.length;i++){
			Linha aux = linhas[i];
			sb.append("\nLinha " + i + "\t");
			
			for(int j=0;j<aux.dados.length;j++){				
				sb.append(aux.dados[j] + "\t");
			}
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
