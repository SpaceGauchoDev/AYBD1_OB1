package mda.obligatorio;

public class Prueba {
    static int	cantCorrectas, cantIncorrectas, cantNoImplementadas;
    void inicializarResultadosPrueba() {
        cantCorrectas = cantIncorrectas = cantNoImplementadas = 0;
    }

    public void ver(Retorno.TipoRet tipoRetornoReal, Retorno.TipoRet tipoRetornoEsperado, String pComentario) {
        System.out.println("...................TESTEO...................");
        imprimirComentario(pComentario);
        imprimirRetorno(tipoRetornoReal,tipoRetornoEsperado);
        System.out.println();
        System.out.println("............................................");
        System.out.println();

        if (tipoRetornoReal.equals(tipoRetornoEsperado))
            cantCorrectas++;
        else
        {
            if (tipoRetornoReal.equals(Retorno.TipoRet.NO_IMPLEMENTADA))
                cantNoImplementadas++;
            else
                cantIncorrectas++;
        }
    }

    void imprimirComentario(String comentario)
    {
        if ( comentario != null && !comentario.equals(""))
            System.out.println("\n  msg: " + comentario );
    }

    public void imprimirRetorno(Retorno.TipoRet pRetornoReal, Retorno.TipoRet pRetornoEsperado)
    {
        if ( pRetornoReal == pRetornoEsperado )
        {
            System.out.println( "....................OK......................" );
        }
        else
        {
            System.out.println( "...................ERROR...................." );
            System.out.println("Retorno real: "+ pRetornoReal +" / Retorno esperado: " + getStringRetorno(pRetornoEsperado));
        }
    }

    public String getStringRetorno(Retorno.TipoRet Resultado)
    {
        switch( Resultado )
        {
            case OK:
                return "OK";
            case ERROR:
                return "ERROR";

            case NO_IMPLEMENTADA:

                return "NO_IMPLEMENTADA";
            default:
                return "NO_IMPLEMENTADA";
        }
    }

    void imprimirResultadosPrueba()
    {
        System.out.println();
        System.out.println("  +------------------------------+");
        System.out.println("    RESULTADOS DE LA PRUEBA    ");
        System.out.println("    Pruebas Correctas: " + cantCorrectas);
        System.out.println("    Pruebas Incorrectas: " + cantIncorrectas );
        System.out.println("    Pruebas NI: " + cantNoImplementadas);
        System.out.println("  +------------------------------+");
        System.out.println();
    }

    public void tituloPrueba(String s){
        System.out.println("");
        System.out.println("**************************************************************************************");
        System.out.println("****************************************"+ s +"***************************************");
        System.out.println("**************************************************************************************");
    }

    public void finPrueba(String s){
        System.out.println("****************************************"+ s +"***************************************");
        System.out.println("**************************************************************************************");
        System.out.println();
    }
}
