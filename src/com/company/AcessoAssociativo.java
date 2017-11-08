package com.company;

import java.util.ArrayList;

import static com.company.Utils.hexToBin;

public class AcessoAssociativo {

    //Mapeamento associativo
    //CacheAssociativa(num de bits da tag, num de bits da linha, num de bits da palavra). Tag + palavra deve ser 5
    private final CacheAssociativa cacheAssociativa;
    //MemoriaAssociativa(num de bits da linha). Deve ser igual ao num de bits da linha da CacheAssoc
    private final MemoriaAssociativa memoriaAssociativa;
    private ArrayList<String> acessos;
    private float miss = 0;
    private float hit = 0;

    public AcessoAssociativo(CacheAssociativa cacheAssociativa,
                             MemoriaAssociativa memoriaAssociativa,
                             ArrayList<String> acessos) {
        this.cacheAssociativa = cacheAssociativa;
        this.memoriaAssociativa = memoriaAssociativa;
        this.acessos = acessos;
    }

    public StringBuilder associativo(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nSimulação de cache com acesso associativo");
        stringBuilder.append("\n " + cacheAssociativa + "\n");

        String aux = "";

        for(int i=0; i<acessos.size() && !(acessos.get(i)==null);i++){

            aux = hexToBin(acessos.get(i));
            String tag = aux.substring(0, cacheAssociativa.tamTag);

            int leitura = memoriaAssociativa.buscarTag(tag);

            if(leitura!=-1){
                stringBuilder.append("\n " + acessos.get(i) + " MISS T " + tag);
                cacheAssociativa.write(leitura);
                miss++;
            }

            else{
                stringBuilder.append("\n " + acessos.get(i) + " HIT T " + tag);
                hit++;
            }
        }
        return stringBuilder;
    }

    public String resultado(){
        StringBuilder stringBuilder = associativo();

        //Imprime status da cache e contagem de hit/miss
        stringBuilder.append("\nConte￺do da mem￳ria associativa e da cache");

        for(int i=0;i<memoriaAssociativa.getTamanho();i++){
            stringBuilder.append("\n " +"Linha " + i);
            stringBuilder.append("\n " +"Memoria associativa - " + memoriaAssociativa.getData(i));
            stringBuilder.append("\n " +" Cache - " + cacheAssociativa.getData(i));
        }

        //Contabiliza resultado
        float ratio = hit / (miss + hit);

        stringBuilder.append("\n\nTotal miss " + miss
                + "\nTotal hit " + hit
                + "\nHit ratio " + ratio * 100 + "%");

        return stringBuilder.toString();
    }

}
