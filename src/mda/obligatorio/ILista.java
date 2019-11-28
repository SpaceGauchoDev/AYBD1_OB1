package mda.obligatorio;

public interface ILista {

    // GETTERS
    boolean getEsVacia();
    int getLargo();
    String getNombre();
    Enums.TipoDato getTipo();
    Nodo getNodoEnPos(int pos);
    int getTope();

    Nodo getInicial();
    Nodo getFinal();
    Nodo getInicialExp();
    Nodo getFinalExp();

    // SETTERS
    void setNombre(String pNombre);

    public void setInicial(Nodo nodo);
    public void setFinal(Nodo nodo);
    public void setInicialExp(Nodo nodo);
    public void setFinalExp(Nodo nodo);

    // METODOS PUBLICOS
    StringBuilder imprimirAscendente(boolean printToConsole);
    StringBuilder imprimirDescendente(boolean printToConsole);
    void insertarNodoAlInicio(Nodo nodo);
    void insertarNodoAlFinal(Nodo nodo);
    void crearEInsertarNodoStringOrdenadoAsc(Object dato);
    void crearEInsertarNodoStringOrdenadoDes(Object dato);
    void crearNodoAlInicio(Object dato);
    void crearNodoAlFinal(Object dato);
    void crearNodoEnPosicion(int pos, Object dato);
    void insertarNodoEnPosicion(int pos, Nodo nodo);
    void crearYSobreescribirNodoEnPosicion(int pos, Object dato);
    void eliminarPrimerElemento();
    void eliminarUltimoElemento();
    void eliminarEnPosicion(int pos);
    void ordenar(Enums.Dir pDir);
    void vaciar();

    // METODOS CREADOS PARA SATISFACER REQUERIMIENTOS
    Retorno crearEInsertarNodoCarpetaOrdenadoAsc(Lista carpeta);
    Retorno eliminarNodoCarpetaPorNombre(String nombreCarpetaAEliminar);
    Retorno imprimirCarpetaPorNombre(String nombreCarpetaAImprimir, Enums.Dir pDir);
    Retorno insertarArchivoEnCarpetaPorNombreDeCarpetaYPosicion(String pNombreCarpeta, int pPos, Nodo archivo);
    Retorno insertarArchivoEnCarpetaConOpcionAExp(String pNombreCarpeta, int pPos, Nodo archivo );
    Retorno eliminarArchivoEnCarpetaPorNombreDeCarpetaYPosicion(String pNombreCarpeta, int pPos);
    Retorno eliminarArchivosEnCarpetasEnFichero();
    Retorno borrarEnTodosLosFicherosCarpetasConNombre(String pNom);
    Retorno imprimirTodosLosFicheros();
    Retorno imprimirTodosLosArchivosOrdenados();

}
