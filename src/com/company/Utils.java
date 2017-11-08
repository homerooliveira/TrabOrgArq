package com.company;

import java.math.BigInteger;

public class Utils {
    //C￳digo obtido em https://stackoverflow.com/a/41707271
    // Converte string em hexadecimal para string em bin￡rio
    public static String hexToBin(String hex) {

        int tamanho = hex.length() * 4;
        String binario = new BigInteger(hex, 16).toString(2);

        if(binario.length() < tamanho)
            binario = completar(binario, tamanho);

        return binario;
    }

    //Completa o binario com 0s ￠ esquerda se necess￡rio
    private static String completar(String binario, int tamanho){

        int dif = tamanho - binario.length();
        String pad = "";
        for(int i = 0; i < dif; ++i){
            pad = pad.concat("0");
        }
        binario = pad.concat(binario);

        return binario;
    }
}
