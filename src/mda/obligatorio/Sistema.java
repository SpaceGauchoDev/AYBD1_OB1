package mda.obligatorio;
import mda.obligatorio.Retorno.TipoRet;

public class Sistema implements  ISistema{

    public Sistema(){
        mSistemaInicializado = false;
        mCantFicheros = 0;
        mTopeArchivosEnCarpetas = 0;
        mFicheros = null;
    }

    boolean mSistemaInicializado;
    int mCantFicheros;
    int mTopeArchivosEnCarpetas;
    Lista mFicheros;
    Lista mArchivosOrdenados;

    @Override
    public Retorno crearSistemaArchivos(int cantFicheros,int maxArchivosPorCarpeta) {
        if(!mSistemaInicializado){
            if(cantFicheros <= 0 ){
                return new Retorno(TipoRet.ERROR, -1, "crearSistemaArchivos() - Parametro cantFicheros debe ser positivo.");
            }

            mSistemaInicializado = true;
            mTopeArchivosEnCarpetas = maxArchivosPorCarpeta;
            mCantFicheros = cantFicheros;
            mFicheros = new Lista(mCantFicheros, "ficheros");

            for (int i = 0; i<mCantFicheros; i++){
                Lista fichero = new Lista(maxArchivosPorCarpeta, "fichero_"+i);
                mFicheros.crearNodoAlFinal(fichero);
            }

            mArchivosOrdenados = new Lista("todos los archivos");

            return new Retorno(TipoRet.OK, 0, "crearSistemaArchivos() - Sistema de archivos creado exitosamente.");
        }
        else{
            return new Retorno(TipoRet.ERROR, -1, "crearSistemaArchivos() - El sistema de archivos ya fue creado.");
        }
    }

    @Override
    public Retorno destruirSistemaArchivos() {
        if(mSistemaInicializado){
            mSistemaInicializado = false;
            mCantFicheros = 0;
            mTopeArchivosEnCarpetas = 0;
            mFicheros = null;
            mArchivosOrdenados = null;

            return new Retorno(TipoRet.OK, 0, "crearSistemaArchivos() - Sistema de archivos destruido exitosamente.");
        }else{
            return new Retorno(TipoRet.ERROR, -1, "destruirSistemaArchivos() - El sistema de archivos ya fue creado.");
        }
    }

    @Override
    public Retorno agregarCarpeta(int fichero, String nombreCarpeta) {
        Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
        int tope = nodoFichero.getDatoLista().getTope();
        if( nodoFichero != null ){
            Lista carpeta = new Lista (tope, nombreCarpeta);
            return nodoFichero.getDatoLista().crearEInsertarNodoCarpetaOrdenadoAsc(carpeta);
        }else{
            return new Retorno(TipoRet.ERROR, -1, "agregarCarpeta() - No se encuentra el fichero en la posicion indicada.");
        }
    }

    @Override
    public Retorno eliminarCarpeta(int fichero, String nombreCarpeta) {
        Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
        if( nodoFichero != null ){
            return nodoFichero.getDatoLista().eliminarNodoCarpetaPorNombre(nombreCarpeta);
        }else{
            return new Retorno(TipoRet.ERROR, -1, "eliminarCarpeta() - No se encuentra el fichero en la posicion indicada.");
        }
    }

    @Override
    public Retorno listarCarpeta(int fichero, String nombreCarpeta) {
        Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
        if( nodoFichero != null ){
            return nodoFichero.getDatoLista().imprimirCarpetaPorNombre(nombreCarpeta,Enums.Dir.Ascendente);
        }else{
            return new Retorno(TipoRet.ERROR, -1, "listarCarpeta() - No se encuentra el fichero en la posicion indicada.");
        }
    }

    @Override
    public Retorno insertarArchivo(int fichero, String nombreCarpeta, int posicionArchivo, String nomArchivo) {
        // aca difiero de la letra "if (posicionArchivo >= 1)" porque necesito poder insertar archivos en la posicion 0
        if(posicionArchivo >= 0){
            Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
            if( nodoFichero != null ){
                Nodo nodo = new Nodo(nomArchivo, nombreCarpeta);

                return nodoFichero.getDatoLista().insertarArchivoEnCarpetaPorNombreDeCarpetaYPosicion(nombreCarpeta,posicionArchivo,nodo);
            }else{
                return new Retorno(TipoRet.ERROR, -1, "insertarArchivo() - No se encuentra el fichero en la posicion indicada.");
            }
        }else{
            return new Retorno(TipoRet.ERROR, -1, "insertarArchivo() - Posición no es válida respecto al rango estipulado.");
        }
    }

    @Override
    public Retorno borrarArchivo(int fichero, String nombreCarpeta, int posicionArchivo) {
        if(posicionArchivo >= 0){
            Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
            if( nodoFichero != null ){
                return nodoFichero.getDatoLista().eliminarArchivoEnCarpetaPorNombreDeCarpetaYPosicion(nombreCarpeta,posicionArchivo);
            }else{
                return new Retorno(TipoRet.ERROR, -1, "borrarArchivo() - No se encuentra el fichero en la posicion indicada.");
            }
        }else{
            return new Retorno(TipoRet.ERROR, -1, "borrarArchivo() - Posición no es válida respecto al rango estipulado.");
        }
    }

    @Override
    public Retorno borrarTodo(int fichero) {
        Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
        if( nodoFichero != null ){
            return nodoFichero.getDatoLista().eliminarArchivosEnCarpetasEnFichero();
        }else{
            return new Retorno(TipoRet.ERROR, -1, "borrarTodo() - No se encuentra el fichero en la posicion indicada.");
        }

    }

    @Override
    public Retorno borrarCarpetasConNombre(String nombreCarpeta) {
        if( mFicheros != null ){
            return mFicheros.borrarEnTodosLosFicherosCarpetasConNombre(nombreCarpeta);
        }else{
            return new Retorno(TipoRet.ERROR, -1, "borrarCarpetasConNombre() - Ficheros no inicializados.");
        }
    }

    @Override
    public Retorno imprimirFichero() {
        if( mFicheros != null ){
            return mFicheros.imprimirTodosLosFicheros();
        }else{
            return new Retorno(TipoRet.ERROR, -1, "borrarCarpetasConNombre() - Ficheros no inicializados.");
        }
    }

    @Override
    public Retorno insertarArchivoExpansivo(int fichero, String nombreCarpeta, int posicionArchivo, String nomArchivo) {
        // aca difiero de la letra "if (posicionArchivo >= 1)" porque necesito poder insertar archivos en la posicion 0
        if(posicionArchivo >= 0){
            Nodo nodoFichero = mFicheros.getNodoEnPos(fichero);
            if( nodoFichero != null ){
                Nodo nodo = new Nodo(nomArchivo, nombreCarpeta);

                return nodoFichero.getDatoLista().insertarArchivoEnCarpetaConOpcionAExp(nombreCarpeta,posicionArchivo,nodo);
            }else{
                return new Retorno(TipoRet.ERROR, -1, "insertarArchivoExpansivo() - No se encuentra el fichero en la posicion indicada.");
            }
        }else{
            return new Retorno(TipoRet.ERROR, -1, "insertarArchivoExpansivo() - Posición no es válida respecto al rango estipulado.");
        }
    }

    @Override
    public Retorno imprimirArchivosOrdenados() {
        if( mFicheros != null ){
            return mFicheros.imprimirTodosLosArchivosOrdenados();
        }else{
            return new Retorno(TipoRet.ERROR, -1, "borrarCarpetasConNombre() - Ficheros no inicializados.");
        }
    }




}
