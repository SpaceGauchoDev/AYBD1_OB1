package mda.obligatorio;

import javax.swing.*;

public class MySort {
    private static char[] mAlfabeto = {'_', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                       'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                                       'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                                       'x', 'y', 'z'};

    private static Enums.Dir mDir;

    private static int obtenerPosicionEnAlfabeto (char letra){
        int cont = 0;
        boolean encontre = false;
        while (!encontre && cont < mAlfabeto.length){
            if(letra == mAlfabeto[cont]){
                encontre = true;
            }else{
                cont++;
            }
        }
        if(!encontre){
            cont = -1;
        }
        return cont;
    }

    private static int compararLetras(char primerLetra, char segundaLetra){
        int result = 0;
        int numPrimerLetra = obtenerPosicionEnAlfabeto(primerLetra);
        int numSegundaLetra = obtenerPosicionEnAlfabeto(segundaLetra);

        if(numPrimerLetra < numSegundaLetra){
            if(Enums.Dir.Ascendente == mDir){
                result = 1;
            }else{
                result = -1;
            }
        }
        if(numPrimerLetra > numSegundaLetra){
            if(Enums.Dir.Ascendente == mDir){
                result = -1;
            }else{
                result = 1;
            }
        }

        return  result;
    }


    private static int compararInts(int primerNumero, int segundoNumero, Enums.Dir pDir){
        int result = 0;
        if(primerNumero < segundoNumero){
            if(pDir == Enums.Dir.Ascendente){
                result = 1;
            }else{
                result = -1;
            }
        }

        if(primerNumero > segundoNumero){
            if(pDir == Enums.Dir.Ascendente){
                result = -1;
            }else{
                result = 1;
            }
        }

        return result;
    }

    public static int compararStrings(String primerPalabra, String segundaPalabra, Enums.Dir pDir){
        mDir = pDir;

        primerPalabra = primerPalabra.replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
        int largoPrimera = primerPalabra.length();
        int cont = 0;

        segundaPalabra = segundaPalabra.replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
        int largoSegunda = segundaPalabra.length();

        int resultado = 0;

        while (cont < largoPrimera && cont < largoSegunda && resultado == 0){
            int comparacion = compararLetras(primerPalabra.charAt(cont), segundaPalabra.charAt(cont));
            if(comparacion == 0){
                cont++;
            }
            else{
                resultado = comparacion;
            }
        }

        // si no hay resultados conclusivos todas las letras evaluadas hasta ahora son iguales
        if(resultado == 0){
            // si tienen el mismo largo y todas las letras son iguales
            if(largoPrimera == largoSegunda){
                return resultado;
            }

            // si la primer palabra es mas larga que la segunda, revisamos el resto de los caracteres
            // de la primera contra el primero de la segunda
            if(largoPrimera > largoSegunda){
                while (cont < largoPrimera && resultado == 0){
                    int comparacion = compararLetras(primerPalabra.charAt(cont), segundaPalabra.charAt(0));
                    if(comparacion == 0){
                        cont++;
                    }
                    else{
                        resultado = comparacion;
                    }
                }

                // todos los caracteres son iguales, la palabra mas corta va primero
                if (resultado == 0){
                    resultado = -1;
                }
            }

            // si la segunda palabra es mas larga que la primera, revisamos el resto de los caracteres
            // de la segunda contra el primero de la primera
            if(largoSegunda > largoPrimera){
                while (cont < largoSegunda && resultado == 0){
                    int comparacion = compararLetras(primerPalabra.charAt(0), segundaPalabra.charAt(cont));
                    if(comparacion == 0){
                        cont++;
                    }
                    else{
                        resultado = comparacion;
                    }
                }

                // todos los caracteres son iguales, la palabra mas corta va primero
                if (resultado == 0){
                    resultado = 1;
                }
            }
        }
        return resultado;
    }

    // basado en bubble sort
    public static void ordenarListaDeDatoString(Lista pLista, Enums.Dir pDir){
        // no ordenamos listas chicas o de tipos mixtos
        if(pLista.getLargo() == 0 || pLista.getLargo() == 1 || pLista.getTipo() != Enums.TipoDato.STRING){
            return;
        }
        int tope = pLista.getLargo() - 1;
        for(int i = 0; i < tope; i++){
            for(int j = 0; j < tope; j++){

                String s1 = pLista.getNodoEnPos(j).getDatoString();
                String s2 = pLista.getNodoEnPos(j+1).getDatoString();
                if( compararStrings(s1,s2,pDir) < 0)
                {
                    intercambiarNodosEnPos(j,j+1,pLista);
                }
            }
        }
    }

    public static void ordenarListaDeDatoInt(Lista pLista, Enums.Dir pDir){
        // no ordenamos listas chicas o de tipos mixtos
        if(pLista.getLargo() == 0 || pLista.getLargo() == 1 || pLista.getTipo() != Enums.TipoDato.INT){
            return;
        }
        int tope = pLista.getLargo() - 1;
        for(int i = 0; i < tope; i++){
            for(int j = 0; j < tope; j++){

                int i1 = pLista.getNodoEnPos(j).getDatoInt();
                int i2 = pLista.getNodoEnPos(j+1).getDatoInt();
                if( compararInts(i1,i2,pDir) < 0)
                {
                    intercambiarNodosEnPos(j,j+1,pLista);
                }
            }
        }
    }

    public static void ordenarListaDeDatoListaPorNombre(Lista pLista, Enums.Dir pDir){
        // no ordenamos listas chicas o de tipos mixtos
        if(pLista.getLargo() == 0 || pLista.getLargo() == 1 || pLista.getTipo() != Enums.TipoDato.LIST){
            return;
        }
        int tope = pLista.getLargo() - 1;
        for(int i = 0; i < tope; i++){
            for(int j = 0; j < tope; j++){

                String s1 = pLista.getNodoEnPos(j).getDatoLista().getNombre();
                String s2 = pLista.getNodoEnPos(j+1).getDatoLista().getNombre();
                if( compararStrings(s1,s2,pDir) < 0)
                {
                    intercambiarNodosEnPos(j,j+1,pLista);
                }
            }
        }
    }

    public static void intercambiarNodosEnPos(int pos_1, int pos_2, Lista pLista){
        Nodo pN1 = pLista.getNodoEnPos(pos_1);
        Nodo pN2 = pLista.getNodoEnPos(pos_2);

        if(pN1.getTipoDato() == pN2.getTipoDato() && pN1 != pN2 ){

            // Si es una lista de solo dos elementos
            if(pLista.getLargo() == 2 ){
                pLista.eliminarUltimoElemento();
                pLista.eliminarUltimoElemento();
                if(pos_2 > pos_1 ){
                    pN2.setEsInicial();
                    pLista.insertarNodoAlFinal(pN2);
                    pN1.setEsFinal();
                    pLista.insertarNodoAlFinal(pN1);
                }else{
                    pN1.setEsInicial();
                    pLista.insertarNodoAlFinal(pN1);
                    pN2.setEsFinal();
                    pLista.insertarNodoAlFinal(pN2);
                }
                return;
            }

            // caso A) n1 del medio, n2 del medio
            if(pN1.getEsDelMedio() && pN2.getEsDelMedio()){
                int dif = pos_2 - pos_1;

                // no son contiguos
                if(dif != 1 && dif != -1 ){
                    // estado original
                    Nodo ant_N1 = pN1.getAnterior();
                    Nodo sig_N1 = pN1.getSiguiente();

                    Nodo ant_N2 = pN2.getAnterior();
                    Nodo sig_N2 = pN2.getSiguiente();

                    // linkeo los vecinos
                    ant_N2.setSiguiente(pN1);
                    sig_N2.setAnterior(pN2);

                    ant_N1.setSiguiente(pN2);
                    sig_N1.setAnterior(pN1);

                    // linkeo los nodos dados
                    pN1.setAnterior(ant_N2);
                    pN1.setSiguiente(sig_N2);

                    pN2.setAnterior(ant_N1);
                    pN2.setSiguiente(sig_N1);
                }

                // contiguos pos_2 mayor que pos_1
                if(dif == 1 ){
                    // estado original
                    Nodo sig_N2 = pN2.getSiguiente();
                    Nodo ant_N1 = pN1.getAnterior();

                    // linkeo los vecinos
                    sig_N2.setAnterior(pN1);
                    ant_N1.setSiguiente(pN2);

                    // linkeo los nodos dados
                    pN2.setAnterior(ant_N1);
                    pN2.setSiguiente(pN1);

                    pN1.setSiguiente(sig_N2);
                    pN1.setAnterior(pN2);
                }

                // contiguos pos_1 mayor que pos_2
                if(dif == -1 ){
                    // estado original
                    Nodo sig_N1 = pN1.getSiguiente();
                    Nodo ant_N2 = pN2.getAnterior();

                    // linkeo los vecinos
                    sig_N1.setAnterior(pN2);
                    ant_N2.setSiguiente(pN1);

                    // linkeo los nodos dados
                    pN2.setSiguiente(sig_N1);
                    pN2.setAnterior(pN1);

                    pN1.setAnterior(ant_N2);
                    pN1.setSiguiente(pN2);
                }

            }

            // caso B) n1 inicial,   n2 final
            else if(pN1.getEsInicial() && pN2.getEsFinal()){
                pLista.eliminarPrimerElemento();
                pLista.eliminarUltimoElemento();

                pN2.setEsInicial();
                pLista.insertarNodoAlInicio(pN2);

                pN1.setEsFinal();
                pLista.insertarNodoAlFinal(pN1);
            }

            // caso C) n1 final,     n2 inicial
            else if(pN1.getEsFinal() && pN2.getEsInicial()){
                pLista.eliminarPrimerElemento();
                pLista.eliminarUltimoElemento();

                pN2.setEsFinal();
                pLista.insertarNodoAlFinal(pN2);

                pN1.setEsInicial();
                pLista.insertarNodoAlInicio(pN1);
            }

            // caso D) n1 inicial,   n2 del medio
            else if(pN1.getEsInicial() && pN2.getEsDelMedio()){
                pLista.eliminarEnPosicion(pos_2);
                pLista.eliminarPrimerElemento();

                pN2.setEsInicial();
                pLista.insertarNodoAlInicio(pN2);

                pLista.insertarNodoEnPosicion(pos_2,pN1);
            }

            // caso E) n1 final,     n2 del medio
            else if(pN1.getEsFinal() && pN2.getEsDelMedio()){
                pLista.eliminarEnPosicion(pos_2);
                pLista.eliminarUltimoElemento();

                pN2.setEsFinal();
                pLista.insertarNodoAlFinal(pN2);

                pLista.insertarNodoEnPosicion(pos_2,pN1);
            }

            // caso F) n1 del medio,    n2 inicial
            else if(pN1.getEsDelMedio() && pN2.getEsInicial()){
                pLista.eliminarEnPosicion(pos_1);
                pLista.eliminarPrimerElemento();

                pN1.setEsInicial();
                pLista.insertarNodoAlInicio(pN1);

                pLista.insertarNodoEnPosicion(pos_1,pN2);
            }

            // caso G) n1 del medio,     n2 final
            else if(pN1.getEsDelMedio() && pN2.getEsFinal()){
                pLista.eliminarEnPosicion(pos_1);
                pLista.eliminarUltimoElemento();

                pN1.setEsFinal();
                pLista.insertarNodoAlFinal(pN1);

                pLista.insertarNodoEnPosicion(pos_1,pN2);
            }
        }
    }

    public MySort(){
        mDir = Enums.Dir.Ascendente;
    }
}
