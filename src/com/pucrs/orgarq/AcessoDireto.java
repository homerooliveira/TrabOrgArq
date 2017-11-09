package com.pucrs.orgarq;

import java.util.ArrayList;

import static com.pucrs.orgarq.Utils.hexToBin;

public class AcessoDireto {

    //Acesso direto
    //Cache(num de bits da tag, num de bits da linha, num de bits da palavra). Total deve ser 8
    private Cache cache;
    private ArrayList<String> acessos;
    private float miss = 0;
    private float hit = 0;

    public AcessoDireto(Cache cache, ArrayList<String> acessos) {
        this.cache = cache;
        this.acessos = acessos;
    }

    public StringBuilder direto(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nSimulação de cache com acesso direto");
        stringBuilder.append("\n " + cache + "\n");

        String aux = "";

        for(int i=0; i<acessos.size() && !(acessos.get(i)==null);i++){

            aux = hexToBin(acessos.get(i));

            String tag = aux.substring(0, cache.tamTag);


            String linha = aux.substring(cache.tamTag, cache.tamLinha+cache.tamTag);

            if(!cache.buscaTag(tag, linha)){
                stringBuilder.append("\n "+ acessos.get(i) + " MISS T " + tag + " L " + linha);
                cache.write(tag, linha);
                miss++;
            }
            else{
                stringBuilder.append("\n "+ acessos.get(i) + " HIT T " + tag + " L " + linha);
                hit++;
            }
        }
        return stringBuilder;
    }

    public String resultado(){
        StringBuilder stringBuilder = direto();

        //Imprime status da cache e contagem de hit/miss
        stringBuilder.append("\nConte￺do da cache com acesso direto");
        stringBuilder.append("\n"+ cache.getData());

        //Contabiliza resultado
        float ratio = hit / (miss + hit);

        stringBuilder.append("\nTotal miss " + miss
                + "\nTotal hit " + hit
                + "\nHit ratio " + ratio * 100 + "%");

        return stringBuilder.toString();
    }
}
