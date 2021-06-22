package TPE;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        InputStream inBTC = Main.class.getResourceAsStream("/BTC");
        InputStream inETH = Main.class.getResourceAsStream("/ETH");

        Scanner input_ETH = new Scanner(inETH);
        Scanner input_BTC = new Scanner(inBTC);
        */

        Scanner input_BTC = new Scanner(new File("C:/Users/Usuario/Desktop/TI TPE/BTC"));
        Scanner input_ETH = new Scanner(new File("C:/Users/Usuario/Desktop/TI TPE/ETH"));
/*

        // ---------- EJERCICIO 1 ----------

        Moneda moneda_ETH = new Moneda(input_ETH);
        Moneda moneda_BTC = new Moneda(input_BTC);

        System.out.println("Matriz de pasaje BTC:");
        moneda_BTC.printMatrizdePasaje();

        System.out.println("Matriz de pasaje ETH:");
        moneda_ETH.printMatrizdePasaje();

        moneda_BTC.printAutocorrelacion();
        moneda_ETH.printAutocorrelacion();

        moneda_BTC.printCorrelacionCruzada(moneda_ETH);

*/

        // ---------- EJERCICIO 2 Resultados ----------
/*
        Scanner input_BTC_2 = new Scanner(new File("C:/Users/Usuario/Desktop/TI TPE/BTC"));
        Scanner input_ETH_2 = new Scanner(new File("C:/Users/Usuario/Desktop/TI TPE/ETH"));

        Codificacion codificacion_BTC = new Codificacion(input_BTC_2);
        Codificacion codificacion_ETH = new Codificacion(input_ETH_2);

        System.out.println("Distribucion de probabilidad BTC:");
        codificacion_BTC.printDistProb();

        System.out.println("Distribucion de probabilidad ETH:");
        codificacion_ETH.printDistProb();


        // Codigos por simbolo
        System.out.println("Codigos BTC:");
        codificacion_BTC.printCodigos();
        System.out.println("Codigos ETH:");
        codificacion_ETH.printCodigos();


        System.out.println("Codificacion Huffman BTC:");
        codificacion_BTC.printCodificacionHSE();

        System.out.println("Codificacion Huffman ETH:");
        codificacion_ETH.printCodificacionHSE();

        System.out.println("Codificacion RLC BTC:");
        codificacion_BTC.printRLC();

        System.out.println("Codificacion RLC ETH:");
        codificacion_ETH.printRLC();


        CodificacionBits cb = new CodificacionBits();
        cb.writeOutputFile(codificacion_ETH.getCodificacionHSE(), codificacion_ETH.getCodigosPorSimbolo() , "ETH HSE");
*/


        // Huffman Semi-Estatico

        Codificacion codificacion_BTC = new Codificacion(input_BTC);
        codificacion_BTC.printDistProb();
        codificacion_BTC.printCodificacionHSE();
        CodificacionBits cb_BTC_HSE = new CodificacionBits();
        cb_BTC_HSE.writeOutputFile(codificacion_BTC.getCodificacionHSE(), "BTC Huffman Semi-Estatico");
        //Tamaño original: 6,83 KB (6.998 bytes)
        //Tamaño comprimido: 1,17 KB (1.199 bytes)

        Codificacion codificacion_ETH = new Codificacion(input_ETH);
        codificacion_ETH.printDistProb();
        codificacion_ETH.printCodificacionHSE();
        CodificacionBits cb_ETH_HSE = new CodificacionBits();
        cb_ETH_HSE.writeOutputFile(codificacion_ETH.getCodificacionHSE(), "ETH Huffman Semi-Estatico");
        //Tamaño original: 5,39 KB (5.520 bytes)
        //Tamaño comprimido: 838 bytes (838 bytes)

        // RLC

        codificacion_BTC.printRLCBinario();
        CodificacionBits cb_BTC_RLC = new CodificacionBits();
        cb_BTC_RLC.writeOutputFile(codificacion_BTC.getCodificacionRLC(), " BTC RLC");
        //Tamaño comprimido: 2,84 KB (2.916 bytes)

        codificacion_ETH.printRLCBinario();
        CodificacionBits cb_ETH_RLC = new CodificacionBits();
        cb_ETH_RLC.writeOutputFile(codificacion_ETH.getCodificacionRLC(), " ETH RLC");
        //Tamaño comprimido: 444 bytes (444 bytes)

        //ETH se comprime mucho mas con RLC


/*
        //

        // TP 4 - Ej 1 - Huffman Semi-Estatico

        List<Integer> ejemploHSE = new ArrayList<>();
        Pruebas p = new Pruebas();
        ejemploHSE = p.EjHSE();
        Codificacion codHSE = new Codificacion(ejemploHSE);

        System.out.println("Distribucion de Probabilidad: ");
        codHSE.printDistProb();

        System.out.println("Codigos: ");
        codHSE.printCodigos();

        System.out.println("Codificacion Arbol de Huffman: ");
        codHSE.printArbol();

        System.out.println("Codificacion Huffman Semi-Estatico:");
        codHSE.printCodificacionHSE();

        // Codificacion Bits

        CodificacionBits cbEj = new CodificacionBits();
        cbEj.writeOutputFile(codHSE.getCodificacionHSE(), codHSE.getCodigosPorSimbolo() , "Ejemplo");


        // RLC - Ej Filmina

        Pruebas pRLC = new Pruebas();
        List<Integer> ejemploRLC;
        ejemploRLC = pRLC.EjRLC();
        Codificacion codRLC = new Codificacion(ejemploRLC);

        System.out.println("Codificacion RLC :");
        codRLC.printCodificacionRLC();
        codRLC.printRLCBinario();

*/

    }
}