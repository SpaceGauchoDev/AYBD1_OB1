package mda.obligatorio;

public class Obligatorio {

    public static void main(String[] args) {
        Sistema s = new Sistema();
        Prueba p = new Prueba();
        //prueba(s,p);
        //prueba2(s,p);
        prueba3(s,p);
    }

    static void prueba(Sistema s, Prueba p){
        System.out.println("...................PRUEBA:....................");
        Retorno r = new Retorno();

        // creo un sistema de 3 ficheros con un maximo de 3 archivos por carpeta no expansiva
        r = s.crearSistemaArchivos(3,3);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // creo una carpeta llamada "Carpeta 1" y en el fichero posicion 0
        r = s.agregarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // trato de crear otra carpeta en el mismo fichero con el mismo nombre
        r = s.agregarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // inserto un archivo "AAAAAA" en la posicion 0 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 0, "AAAAAA");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "BBBBBB" en la posicion 1 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 1, "BBBBBB");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "CCCCCC" en la posicion 2 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 2, "CCCCCC");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "DDDDDD" en la posicion 3 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 3, "DDDDDD");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // listo el contenido de la "Carpeta 1"
        r = s.listarCarpeta(0,"Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // borro el archivo en el fichero 0, "Carpeta 1", posicion 2
        r = s.borrarArchivo(0, "Carpeta 1", 2);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // listo el contenido de la "Carpeta 1" del fichero 0
        r = s.listarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // creo una carpeta llamada "Carpeta 2" y en el fichero posicion 0
        r = s.agregarCarpeta(0, "Carpeta 2");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "FFFFFF" en la posicion 0 de la "Carpeta 2" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 2", 0, "FFFFFF");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "GGGGGG" en la posicion 1 de la "Carpeta 2" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 2", 1, "GGGGGG");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "HHHHHH" en la posicion 2 de la "Carpeta 2" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 2", 2, "HHHHHH");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "IIIIII" en la posicion 3 de la "Carpeta 2" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 2", 3, "IIIIII");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // borro todo el contenido de las carpetas del fichero 0
        r = s.borrarTodo(0);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // listo el contenido de la "Carpeta 1" del fichero 0
        r = s.listarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // listo el contenido de la "Carpeta 2" del fichero 0
        r = s.listarCarpeta(0, "Carpeta 2");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // elimino la "Carpeta 1" del fichero 0
        r = s.eliminarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // destruyo el sistema de archivos
        r = s.destruirSistemaArchivos();
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        p.imprimirResultadosPrueba();
    }

    static void prueba2(Sistema s, Prueba p){
        System.out.println("...................PRUEBA2:...................");
        Retorno r = new Retorno();

        // creo un sistema de 3 ficheros con un maximo de 3 archivos por carpeta no expansiva
        r = s.crearSistemaArchivos(3,3);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // creo una carpeta llamada "Carpeta 1" y en el fichero posicion 0
        r = s.agregarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // trato de crear otra carpeta en el mismo fichero con el mismo nombre
        r = s.agregarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // inserto un archivo "AAAAAA" en la posicion 0 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 0, "AAAAAA");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "BBBBBB" en la posicion 1 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 1, "BBBBBB");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "CCCCCC" en la posicion 2 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 2, "CCCCCC");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "DDDDDD" en la posicion 3 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta 1", 3, "DDDDDD");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // listo el contenido de la "Carpeta 1"
        r = s.listarCarpeta(0,"Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // borro el archivo en el fichero 0, "Carpeta 1", posicion 2
        r = s.borrarArchivo(0, "Carpeta 1", 2);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // listo el contenido de la "Carpeta 1" del fichero 0
        r = s.listarCarpeta(0, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // creo una carpeta llamada "Carpeta 1" y en el fichero posicion 1
        r = s.agregarCarpeta(1, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "FFFFFF" en la posicion 0 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta 1", 0, "FFFFFF");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "GGGGGG" en la posicion 1 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta 1", 1, "GGGGGG");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "HHHHHH" en la posicion 2 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta 1", 2, "HHHHHH");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "IIIIII" en la posicion 3 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta 1", 3, "IIIIII");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // listo el contenido de la "Carpeta 1" del fichero 1
        r = s.listarCarpeta(1, "Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // borro todas las carpetas con el nombre "Carpeta 1" en el sistema
        r = s.borrarCarpetasConNombre("Carpeta 1");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // destruyo el sistema de archivos
        r = s.destruirSistemaArchivos();
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        p.imprimirResultadosPrueba();
    }

    static void prueba3(Sistema s, Prueba p){
        System.out.println("...................PRUEBA3:...................");
        Retorno r = new Retorno();

        // creo un sistema de 3 ficheros con un maximo de 3 archivos por carpeta no expansiva
        r = s.crearSistemaArchivos(3,3);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // creo una carpeta llamada "Carpeta 1" y en el fichero posicion 0
        r = s.agregarCarpeta(0, "Carpeta RED");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        r = s.agregarCarpeta(0, "Carpeta GREEN");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // trato de crear otra carpeta en el mismo fichero con el mismo nombre
        r = s.agregarCarpeta(0, "Carpeta RED");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // inserto un archivo "AAAAAA" en la posicion 0 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta RED", 0, "AAAAAA");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "BBBBBB" en la posicion 1 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta RED", 1, "BBBBBB");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "CCCCCC" en la posicion 2 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta RED", 2, "CCCCCC");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "DDDDDD" en la posicion 3 de la "Carpeta 1" del fichero 0
        r = s.insertarArchivo(0,"Carpeta RED", 3, "DDDDDD");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // listo el contenido de la "Carpeta 1"
        r = s.listarCarpeta(0,"Carpeta RED");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // borro el archivo en el fichero 0, "Carpeta 1", posicion 2
        r = s.borrarArchivo(0, "Carpeta RED", 2);
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // listo el contenido de la "Carpeta 1" del fichero 0
        r = s.listarCarpeta(0, "Carpeta RED");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // creo una carpeta llamada "Carpeta BLU" y en el fichero posicion 1
        r = s.agregarCarpeta(1, "Carpeta BLU");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "FFFFFF" en la posicion 0 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta BLU", 0, "FFFFFF");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "GGGGGG" en la posicion 1 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta BLU", 1, "GGGGGG");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "HHHHHH" en la posicion 2 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta BLU", 2, "HHHHHH");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // inserto un archivo "IIIIII" en la posicion 3 de la "Carpeta 1" del fichero 1
        r = s.insertarArchivo(1,"Carpeta BLU", 3, "IIIIII");
        p.ver(r.resultado, Retorno.TipoRet.ERROR, r.valorString);

        // listo el contenido de la "Carpeta 1" del fichero 1
        r = s.listarCarpeta(1, "Carpeta BLU");
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // imprimo todos los ficheros carpetas y archivos
        r = s.imprimirFichero();
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // imprimo todos los archivos en orden descendiente
        r = s.imprimirArchivosOrdenados();
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        // destruyo el sistema de archivos
        r = s.destruirSistemaArchivos();
        p.ver(r.resultado, Retorno.TipoRet.OK, r.valorString);

        p.imprimirResultadosPrueba();
    }

}
