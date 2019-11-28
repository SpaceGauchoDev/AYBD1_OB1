package mda.obligatorio;

public interface ISistema {
    Retorno crearSistemaArchivos(int cantFicheros,int maxArchivosPorCarpeta);
    Retorno destruirSistemaArchivos();
    Retorno agregarCarpeta(int fichero, String nombreCarpeta);
    Retorno eliminarCarpeta(int fichero, String nombreCarpeta);
    Retorno listarCarpeta(int fichero, String nombreCarpeta);
    Retorno insertarArchivo(int fichero, String nombreCarpeta, int posicionArchivo, String nomArchivo);
    Retorno borrarArchivo(int fichero, String nombreCarpeta, int posicionArchivo);
    Retorno borrarTodo(int fichero);
    Retorno borrarCarpetasConNombre(String nombreCarpeta);
    Retorno imprimirFichero();
    Retorno insertarArchivoExpansivo(int fichero, String nombreCarpeta, int posicionArchivo, String nomArchivo);
    Retorno imprimirArchivosOrdenados();
}
