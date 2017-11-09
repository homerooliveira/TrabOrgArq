package com.pucrs.orgarq;

import java.math.BigInteger;

public class Utils {
    //C￳digo obtido em https://stackoverflow.com/a/41707271
    // Converte string em hexadecimal para string em bin￡rio
    public static String hexToBin(String hex) {

        String preBin = new BigInteger(hex, 16).toString(2);
        Integer length = preBin.length();
        if (length < 8) {
            for (int i = 0; i < 8 - length; i++) {
                preBin = "0" + preBin;
            }
        }
        return preBin;
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
