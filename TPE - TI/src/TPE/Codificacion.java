package TPE;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Codificacion {

    private final List<Integer> cotizaciones;
    private final HashMap<Integer, Double> distProb = new HashMap<>();
    private ArbolHuffman raiz = new ArbolHuffman(0, 0, null, null);
    private HashMap<Integer, String> codigosPorSimbolo = new HashMap<>();
    private String codificacionHSE = "";
    private String codificacionRLC = "";
    private final LinkedHashMap<Integer, Integer> RLC = new LinkedHashMap<>();

    public Codificacion(Scanner input){
        this.cotizaciones = getCotizaciones(input);
    }
    public Codificacion(List<Integer> input){
        this.cotizaciones = input;
    }


    // ---------------------- Ejericio 2.a) ----------------------
    //
    private List<Integer> getCotizaciones(Scanner input) {
        List<Integer> arrayValues = new ArrayList<>();
        while (input.hasNext()) {
            int valorActual = Integer.parseInt(input.nextLine());
            arrayValues.add(valorActual);
        }
        return arrayValues;
    }

    public void calcularDistProb() {
        int cantValores = cotizaciones.size();
        HashMap<Integer, Integer> exitos = new HashMap<Integer, Integer>();

        for (int valor : cotizaciones) {
            if (!exitos.containsKey(valor)) {
                exitos.put(valor, 1);
            } else {
                int cantExitos = exitos.get(valor);
                exitos.replace(valor, cantExitos + 1);
            }
        }
        for (Map.Entry<Integer, Integer> par : exitos.entrySet()) {
            distProb.put(par.getKey(), par.getValue() / (double) cantValores);
        }
    }

    // ---------------------- Ejericio 2.b) ----------------------

    public void codificarSimbolos() {
        raiz = crearArbol();
        codigosPorSimbolo = raiz.getCodigos();
    }

    public ArbolHuffman crearArbol() {
        List<ArbolHuffman> simbolosOrdPorProba = new ArrayList<ArbolHuffman>();
        for (Map.Entry<Integer, Double> par : distProb.entrySet()) {
            ArbolHuffman nodo = new ArbolHuffman(par.getKey(), par.getValue(), null, null);
            simbolosOrdPorProba.add(nodo);
        }
        while (simbolosOrdPorProba.size() > 1) {
            Collections.sort(simbolosOrdPorProba);
            ArbolHuffman hijoIzq = simbolosOrdPorProba.get(0);
            ArbolHuffman hijoDer = simbolosOrdPorProba.get(1);
            ArbolHuffman padre = new ArbolHuffman(0, hijoIzq.getProbabilidad() + hijoDer.getProbabilidad(), hijoIzq, hijoDer);
            simbolosOrdPorProba.remove(hijoIzq);
            simbolosOrdPorProba.remove(hijoDer);
            simbolosOrdPorProba.add(padre);
        }
        return simbolosOrdPorProba.get(0);
    }


    public void codificarFuente() {
        raiz = crearArbol();
        codigosPorSimbolo = raiz.getCodigos();
        int i = 0;
        for (int cotizacion : cotizaciones) {
            String str = codigosPorSimbolo.get(cotizacion);
            codificacionHSE += str;
        }
    }


    // ---------------------- Ejericio 2.c) ----------------------
    public void calcularRLC(){
        int n = cotizaciones.size();
        for (int i = 0; i < n; i++) {
            int count = 1;
            while (i < n - 1 && cotizaciones.get(i) == cotizaciones.get(i + 1)) {
                count++;
                i++;
            }
            RLC.put(cotizaciones.get(i), count);
        }
    }

    public void printRLCBinario(){
        calcularRLC();
        for (Map.Entry<Integer, Integer> par : RLC.entrySet()) {
            String simbolo = "";
            String simboloBin = Integer.toBinaryString(par.getKey());
            simbolo = completarByteConCeros(simboloBin.length());
            simbolo = simbolo + simboloBin;

            String repeticiones = "";
            String repeticionesBin = Integer.toBinaryString(par.getValue());
            repeticiones = completarByteConCeros(repeticionesBin.length());
            repeticiones = repeticiones + repeticionesBin;
            codificacionRLC = codificacionRLC + simbolo + repeticiones;
            System.out.println("simbolo binario " + simbolo + "\n");
            System.out.println("repeticiones binario " + repeticiones + "\n");
        }
    }

    private String completarByteConCeros(int n) {
        String str = "";
        for (int i = 0; i < 8-n%8; i++){
            str = '0' + str;
        }
        return str;
    }


    // Codificacion en Bits
    public void codificarBits(){

    }

    // ---------------------- Prints ----------------------

    public void printDistProb() {
        this.calcularDistProb();
        for (Map.Entry<Integer, Double> par : distProb.entrySet()) {
            System.out.print("Valor: " + par.getKey() + " Probabilidad: " + par.getValue() + "\n");
            System.out.print("\n");
        }
    }

    public void printArbol(){
        if(raiz != null){
            raiz.preOrden();
        }
    }

    public void printCodigos(){
        this.codificarSimbolos();
        for (Map.Entry<Integer, String> par : codigosPorSimbolo.entrySet()) {
            System.out.print("Valor: " + par.getKey() + " Codigo: "+ par.getValue() + "\n");
            System.out.print("\n");
        }
    }

    public void printCodificacionHSE() {
        this.codificarFuente();
        System.out.print("Tamañno de Codificacion: " + codificacionHSE.length() + "\n");
        System.out.print("Codigo completo: " + codificacionHSE + "\n");
        System.out.print("\n");
    }

    public void printCodificacionRLC() {
        this.calcularRLC();
        for (Map.Entry<Integer, Integer> par : RLC.entrySet()) {
            System.out.print("Valor: " + par.getKey() + " Repeticiones: " + par.getValue() + "\n");
            System.out.print("\n");
        }
    }

    public HashMap<Integer, String> getCodigosPorSimbolo() {
        return codigosPorSimbolo;
    }

    public String getCodificacionHSE() {
        return codificacionHSE;
    }

    public String getCodificacionRLC() {
        return codificacionRLC;
    }
}
