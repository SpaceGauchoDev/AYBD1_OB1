package mda.obligatorio;

import java.util.List;

public class Lista implements ILista{
    //public enum TipoDato {STRING, INT, LIST, UNDEFINED};

    // VARIABLES MIEMBRO
    Enums.TipoDato mTipoDato;
    Nodo mInicial;
    Nodo mFinal;

    Nodo mInicialExpansion;
    Nodo mFinalExpansion;

    int mLength;
    int mTope;
    boolean mEsTopeEstricto; // para ficheros en sistema
    String mNombre;

    // GETTERS
    @Override
    public boolean getEsVacia() {
        return (mLength == 0);
    }
    @Override
    public int getLargo(){
        return  mLength;
    }
    @Override
    public String getNombre(){
        return  mNombre;
    }
    @Override
    public Enums.TipoDato getTipo(){
        return  mTipoDato;
    }
    @Override
    public int getTope(){
        return  mTope;
    }

    @Override
    public Nodo getInicial(){return  mInicial;}
    @Override
    public Nodo getFinal(){
        return  mFinal;
    }
    @Override
    public Nodo getInicialExp(){
        return  mInicial;
    }
    @Override
    public Nodo getFinalExp(){
        return  mFinal;
    }


    @Override
    public Nodo getNodoEnPos(int pos){
        Nodo nodoBuscador = null;
        if(pos < mLength && mLength > 0 && pos >= 0){
            if(pos >= (int)mLength/2){
                // si pos está mas cerca del final que del principio
                // buscamos la posicion desde el final hacia atras
                int cont = mLength-1;
                nodoBuscador = mFinal;
                while (cont > pos){
                    nodoBuscador = nodoBuscador.getAnterior();
                    cont--;
                }
            }else{
                // si pos está mas cerca del principio que del final
                // buscamos la posicion desde el principio hacia adelante
                int cont = 0;
                nodoBuscador = mInicial;
                while (cont < pos){
                    nodoBuscador = nodoBuscador.getSiguiente();
                    cont++;
                }
            }
        }else{
            imprimirErrorDeRango("getNodoEnPos", pos);
        }
        return nodoBuscador;
    }

    // SETTERS
    @Override
    public void setNombre(String pNombre){
        mNombre = pNombre;
    }

    @Override
    public void setInicial(Nodo nodo){
        mInicial = nodo;
    }

    @Override
    public void setFinal(Nodo nodo){
        mFinal = nodo;
    }

    @Override
    public void setInicialExp(Nodo nodo){
        mInicialExpansion = nodo;
    }

    @Override
    public void setFinalExp(Nodo nodo){
        mFinalExpansion = nodo;
    }

    // METODOS PUBLICOS
    @Override
    public StringBuilder imprimirAscendente(boolean printToConsole) {
        if(Enums.TipoDato.INT == mTipoDato || Enums.TipoDato.STRING == mTipoDato){
            StringBuilder s = new StringBuilder();
            Nodo nodoAImprimir = mInicial;
            while (nodoAImprimir != null){
                if(Enums.TipoDato.INT == mTipoDato){
                    s.append(nodoAImprimir.getDatoInt());
                }else{
                    s.append(nodoAImprimir.getDatoString());
                }
                s.append(" | ");
                nodoAImprimir = nodoAImprimir.getSiguiente();
            }
            if(printToConsole){
                System.out.println(s);
            }
            return s;
        }

        if(Enums.TipoDato.LIST == mTipoDato){
            Nodo nodoAImprimir = mInicial;
            while(nodoAImprimir != null){
                nodoAImprimir.getDatoLista().imprimirAscendente(printToConsole);
                nodoAImprimir = nodoAImprimir.getSiguiente();
            }
        }
        return null;
    }

    @Override
    public StringBuilder imprimirDescendente(boolean printToConsole) {
        if(Enums.TipoDato.INT == mTipoDato || Enums.TipoDato.STRING == mTipoDato){
            StringBuilder s = new StringBuilder();
            Nodo nodoAImprimir = mFinal;
            while (nodoAImprimir != null){
                if(Enums.TipoDato.INT == mTipoDato){
                    s.append(nodoAImprimir.getDatoInt());
                }else{
                    s.append(nodoAImprimir.getDatoString());
                }
                s.append(" | ");
                nodoAImprimir = nodoAImprimir.getAnterior();
            }
            if(printToConsole){
                System.out.println(s);
            }
            return s;
        }

        if(Enums.TipoDato.LIST == mTipoDato){
            Nodo nodoAImprimir = mFinal;
            while(nodoAImprimir != null){
                nodoAImprimir.getDatoLista().imprimirDescendente(printToConsole);
                nodoAImprimir = nodoAImprimir.getAnterior();
            }
        }

        return null;
    }

    @Override
    public void insertarNodoAlInicio(Nodo nodo){
        if(Enums.TipoDato.UNDEFINED == mTipoDato){
            setTipoDeLista(nodo.getTipoDato());
        }

        if(Enums.TipoDato.UNDEFINED != mTipoDato && nodo.getTipoDato() == mTipoDato){
            switch (mLength) {
                case 0:
                    // si la lista es vacia
                    mInicial = nodo;
                    mFinal = nodo;
                    break;
                case 1:
                    // el nodo que ya existía es final
                    mFinal.setAnterior(nodo);

                    // el nuevo nodo es inicial
                    nodo.setSiguiente(mFinal);
                    nodo.setEsInicial();
                    mInicial = nodo;
                    break;
                default:
                    nodo.setSiguiente(mInicial);
                    nodo.setEsInicial();
                    mInicial.setAnterior(nodo);
                    mInicial = nodo;
                    break;
            }
            mLength ++;
        }else{
            System.out.println("insercion no exitosa tipo de dato no soportado");
        }
    }

    @Override
    public void crearEInsertarNodoStringOrdenadoAsc(Object dato){
        crearNodoAlFinal(dato);
        MySort.ordenarListaDeDatoString(this,Enums.Dir.Ascendente);
    }

    @Override
    public void crearEInsertarNodoStringOrdenadoDes(Object dato){
        crearNodoAlFinal(dato);
        MySort.ordenarListaDeDatoString(this,Enums.Dir.Descendente);
    }

    @Override
    public Retorno crearEInsertarNodoCarpetaOrdenadoAsc(Lista carpeta){
        // solo buscamos carpetas repetidas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0){
            String nombreNuevaCarpeta = carpeta.getNombre();
            boolean carpetaExiste = false;

            Nodo nodoBuscador = mInicial;
            while (nodoBuscador != null && !carpetaExiste){
                if (nodoBuscador.getDatoLista().getNombre().equals(nombreNuevaCarpeta)){
                    carpetaExiste = true;
                }
                else{
                    nodoBuscador = nodoBuscador.getSiguiente();
                }
            }

            if(carpetaExiste){
                return new Retorno(Retorno.TipoRet.ERROR, -1, "agregarCarpeta() - Carpeta a crear existe en el fichero.");
            }else{
                crearNodoAlFinal(carpeta);
                MySort.ordenarListaDeDatoListaPorNombre(this,Enums.Dir.Ascendente);
                return new Retorno(Retorno.TipoRet.OK, 0, "agregarCarpeta() - Carpeta agregada al fichero exitosamente.");
            }
        }else{
            // no hay carpetas en el fichero, podemos agregar normalmente
            crearNodoAlFinal(carpeta);
            return new Retorno(Retorno.TipoRet.OK, 0, "agregarCarpeta() - Carpeta agregada al fichero exitosamente.");
        }
    }

    @Override
    public Retorno insertarArchivoEnCarpetaConOpcionAExp(String pNombreCarpeta, int pPos, Nodo archivo ){
        // solo buscamos carpetas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0 ){
            boolean carpetaExiste = false;
            int cont = 0;

            Nodo nodoBuscador = mInicial;
            while (nodoBuscador != null && !carpetaExiste){
                if (nodoBuscador.getDatoLista().getNombre().equals(pNombreCarpeta)){
                    carpetaExiste = true;
                }
                else{
                    cont ++;
                    nodoBuscador = nodoBuscador.getSiguiente();
                }
            }

            if(carpetaExiste){
                // encontramos la carpeta en el fichero
                Lista carpeta = getNodoEnPos(cont).getDatoLista();
                int carpetaLenght =  carpeta.getLargo();

                if (mTope > carpetaLenght){
                    // aca difiero de la letra  "if (posicionArchivo <= cantidad de archivos + 1") porque
                    // en mi implementacion la posicion "cantidad de archivos + 1" nunca existe y tratar de insertar ahì darìa error
                    if(pPos <= carpetaLenght){
                        carpeta.insertarNodoEnPosicion(pPos, archivo);
                        return new Retorno(Retorno.TipoRet.OK, 0, "insertarArchivo() - Archivo insertado correctamente.");
                    }
                    else{
                        return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - Posición no es válida respecto al rango estipulado.");
                    }
                }else{
                    // ya tenemos una expansion
                    if(mInicialExpansion != null && mFinalExpansion != null){
                        // insertamos el archivo nuevo
                        carpeta.insertarNodoEnPosicion(pPos, archivo);
                        return new Retorno(Retorno.TipoRet.OK, -1, "insertarArchivo() - Archivo insertado usando la expansion existente.");
                    }
                    // tenemos que crear una expansion
                    else{
                        String nombreExp = carpeta.getNombre() + "+";
                        Lista carpetaExp = new Lista(-1, nombreExp);
                        carpetaExp.setInicial(mFinal);
                        carpetaExp.getInicial().setAnterior(mFinal);

                        //insertamos el archivo nuevo
                        carpeta.insertarNodoEnPosicion(pPos, archivo);
                        return new Retorno(Retorno.TipoRet.OK, -1, "insertarArchivo() - Expansion de carpeta creada existente.");
                    }
                }
            }
            else{
                return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - Carpeta con el nombre indicado no existe en el fichero.");
            }
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - No hay carpetas agregadas al fichero.");
        }
    }



    @Override
    public Retorno eliminarNodoCarpetaPorNombre(String nombreCarpetaAEliminar){
        // solo buscamos carpetas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0){
            boolean carpetaExiste = false;
            int cont = 0;

            Nodo nodoBuscador = mInicial;
            while (nodoBuscador != null && !carpetaExiste){
                if (nodoBuscador.getDatoLista().getNombre().equals(nombreCarpetaAEliminar)){
                    carpetaExiste = true;
                }
                else{
                    cont ++;
                    nodoBuscador = nodoBuscador.getSiguiente();
                }
            }

            if(carpetaExiste){
                eliminarEnPosicion(cont);
                return new Retorno(Retorno.TipoRet.OK, 0, "eliminarCarpeta() - Carpeta eliminada exitosamente.");
            }else{
                return new Retorno(Retorno.TipoRet.ERROR, -1, "eliminarCarpeta() - Carpeta con el nombre indicado no existe en el fichero.");
            }
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "eliminarCarpeta() - No hay carpetas agregadas al fichero.");
        }
    }

    @Override
    public Retorno imprimirCarpetaPorNombre(String nombreCarpetaAImprimir, Enums.Dir pDir){
        // solo buscamos carpetas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0){
            boolean carpetaExiste = false;
            int cont = 0;

            Nodo nodoBuscador = mInicial;
            while (nodoBuscador != null && !carpetaExiste){
                if (nodoBuscador.getDatoLista().getNombre().equals(nombreCarpetaAImprimir)){
                    carpetaExiste = true;
                }
                else{
                    cont ++;
                    nodoBuscador = nodoBuscador.getSiguiente();
                }
            }

            if(carpetaExiste){
                // encontramos la carpeta en el fichero
                Lista carpeta = getNodoEnPos(cont).getDatoLista();
                int archivosEnCarpeta = carpeta.getLargo();
                if(archivosEnCarpeta > 0 ){
                    if(pDir == Enums.Dir.Ascendente){
                        carpeta.imprimirAscendente(true);
                    }else{
                        carpeta.imprimirDescendente(true);
                    }
                    return new Retorno(Retorno.TipoRet.OK, 0, "listarCarpeta() - Carpeta impresa correctamente.");
                }else{
                    return new Retorno(Retorno.TipoRet.OK, 0, "No existen archivos");
                }
            }else{
                return new Retorno(Retorno.TipoRet.ERROR, -1, "listarCarpeta() - Carpeta con el nombre indicado no existe en el fichero.");
            }
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "listarCarpeta() - No hay carpetas agregadas al fichero.");
        }
    }


    @Override
    public Retorno eliminarArchivoEnCarpetaPorNombreDeCarpetaYPosicion(String pNombreCarpeta, int pPos){
        // solo buscamos carpetas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0){
            boolean carpetaExiste = false;
            int cont = 0;

            Nodo nodoBuscador = mInicial;
            while (nodoBuscador != null && !carpetaExiste){
                if (nodoBuscador.getDatoLista().getNombre().equals(pNombreCarpeta)){
                    carpetaExiste = true;
                }
                else{
                    cont ++;
                    nodoBuscador = nodoBuscador.getSiguiente();
                }
            }

            if(carpetaExiste){
                // encontramos la carpeta en el fichero
                Lista carpeta = getNodoEnPos(cont).getDatoLista();
                int carpetaLenght =  carpeta.getLargo();

                // aca difiero de la letra  "if (posicionArchivo <= cantidad de archivos + 1") porque
                // en mi implementacion la posicion cantidad de archivos + 1 nunca existe y tratar de borrar ahí daría error
                if(pPos <= carpetaLenght){
                    carpeta.eliminarEnPosicion(pPos);
                    return new Retorno(Retorno.TipoRet.OK, 0, "eliminarArchivo() - Archivo eliminado correctamente.");
                }
                else{
                    return new Retorno(Retorno.TipoRet.ERROR, -1, "eliminarArchivo() - Posición no es válida respecto al rango estipulado.");
                }
            }
            else{
                return new Retorno(Retorno.TipoRet.ERROR, -1, "eliminarArchivo() - Carpeta con el nombre indicado no existe en el fichero.");
            }
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - No hay carpetas agregadas al fichero.");
        }
    }

    @Override
    public Retorno eliminarArchivosEnCarpetasEnFichero(){
        // solo buscamos carpetas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0){

            // borramos el contenido de las carpetas de atras para adelante
            int cont = mLength-1;
            while (cont >= 0){
                Lista carpeta = getNodoEnPos(cont).getDatoLista();
                carpeta.vaciar();
                cont--;
            }
            return new Retorno(Retorno.TipoRet.OK, -1, "eliminarArchivos() - Archivos en carpetas del fichero vaiciados.");
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "eliminarArchivos() - No hay carpetas agregadas al fichero.");
        }
    }

    @Override
    public Retorno insertarArchivoEnCarpetaPorNombreDeCarpetaYPosicion(String pNombreCarpeta, int pPos, Nodo archivo ){
        // solo buscamos carpetas si tenemos por lo menos una carpeta en el fichero
        if(mLength > 0 ){
            boolean carpetaExiste = false;
            int cont = 0;

            Nodo nodoBuscador = mInicial;
            while (nodoBuscador != null && !carpetaExiste){
                if (nodoBuscador.getDatoLista().getNombre().equals(pNombreCarpeta)){
                    carpetaExiste = true;
                }
                else{
                    cont ++;
                    nodoBuscador = nodoBuscador.getSiguiente();
                }
            }

            if(carpetaExiste){
                // encontramos la carpeta en el fichero
                Lista carpeta = getNodoEnPos(cont).getDatoLista();
                int carpetaLenght =  carpeta.getLargo();

                if (mTope > carpetaLenght){
                    // aca difiero de la letra  "if (posicionArchivo <= cantidad de archivos + 1") porque
                    // en mi implementacion la posicion "cantidad de archivos + 1" nunca existe y tratar de insertar ahì darìa error
                    if(pPos <= carpetaLenght){
                        carpeta.insertarNodoEnPosicion(pPos, archivo);
                        return new Retorno(Retorno.TipoRet.OK, 0, "insertarArchivo() - Archivo insertado correctamente.");
                    }
                    else{
                        return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - Posición no es válida respecto al rango estipulado.");
                    }
                }else{
                   return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - Limite de archivos en carpeta alcanzado.");
                }
            }
            else{
                return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - Carpeta con el nombre indicado no existe en el fichero.");
            }
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "insertarArchivo() - No hay carpetas agregadas al fichero.");
        }
    }

    @Override
    public Retorno borrarEnTodosLosFicherosCarpetasConNombre(String pNom) {
        if(mLength > 0){
            int contEliminados = 0;

            for (int fichPos = 0; fichPos < mLength; fichPos++){
                Lista nodoFichero = this.getNodoEnPos(fichPos).getDatoLista();

                for (int carpPos = nodoFichero.getLargo()-1; carpPos >= 0; carpPos --){

                    if(nodoFichero.getNodoEnPos(carpPos).getDatoLista().getNombre() == pNom){
                        nodoFichero.eliminarEnPosicion(carpPos);
                        contEliminados++;
                    }
                }
            }

            return new Retorno(Retorno.TipoRet.OK, 0, "borrarEnTodos() - " + contEliminados + " carpetas con el nombre '" + pNom + "' eliminadas en el sistema");
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "borrarEnTodos() - No hay ficheros.");
        }
    }

    @Override
    public Retorno imprimirTodosLosFicheros(){
        if(mLength > 0){

            for (int fichPos = 0; fichPos < mLength; fichPos++){
                StringBuilder concat = new StringBuilder("Fichero pos " + fichPos);

                Lista nodoFichero = this.getNodoEnPos(fichPos).getDatoLista();
                int ficheroLenght = nodoFichero.getLargo();

                if(ficheroLenght != 0){
                    for (int carpPos = 0; carpPos < ficheroLenght; carpPos ++){
                        Lista nodoCarp = nodoFichero.getNodoEnPos(carpPos).getDatoLista();
                        String nombreCarpeta =  nodoCarp.getNombre();
                        concat.append(" ) ").append(nombreCarpeta);

                        if(nodoCarp.getLargo() != 0){
                            concat.append(" ) ").append(nodoCarp.imprimirAscendente(false));
                        }else{
                            concat.append(" ) ").append("< Carpeta vacía>");
                        }
                    }
                }else{
                    concat.append(" ) ").append("< Fichero vacío>");
                }
                System.out.println(concat);
            }
            return new Retorno(Retorno.TipoRet.OK, 0, "imprimirTodosLosFicheros() - ficheros impresos exitosamente");
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "imprimirTodosLosFicheros() - No hay ficheros.");
        }
    }

    @Override
    public Retorno imprimirTodosLosArchivosOrdenados(){
        if(mLength > 0){
            Lista todosLosArchivos = new Lista();

            for (int fichPos = 0; fichPos < mLength; fichPos++){

                Lista nodoFichero = this.getNodoEnPos(fichPos).getDatoLista();
                int ficheroLenght = nodoFichero.getLargo();

                for (int carpPos = 0; carpPos < ficheroLenght; carpPos ++){

                    Lista nodoCarp = nodoFichero.getNodoEnPos(carpPos).getDatoLista();
                    int carpetaLenght = nodoCarp.getLargo();

                    for (int archivoPos = 0; archivoPos < carpetaLenght; archivoPos++){
                        Nodo nodoArchivo = nodoCarp.getNodoEnPos(archivoPos);
                        todosLosArchivos.insertarNodoAlFinal(nodoArchivo);
                    }
                }
            }

            if(todosLosArchivos.getLargo() > 0 ){
                todosLosArchivos.ordenar(Enums.Dir.Descendente);
                todosLosArchivos.imprimirAscendente(true);
                return new Retorno(Retorno.TipoRet.OK, 0, "imprimirTodosLosFicheros() - archivos impresos exitosamente.");
            }else{
                return new Retorno(Retorno.TipoRet.ERROR, -1, "imprimirTodosLosFicheros() - no hay archivos para imprimir.");
            }
        }else{
            return new Retorno(Retorno.TipoRet.ERROR, -1, "imprimirTodosLosFicheros() - No hay ficheros.");
        }
    }

    @Override
    public void crearNodoAlInicio(Object dato) {
        if(Enums.TipoDato.UNDEFINED == mTipoDato){
            setTipoDeLista(dato);
        }

        if(Enums.TipoDato.STRING == mTipoDato && dato.equals("") || dato.equals(" ")){
            System.out.println("insercion no exitosa tipo de strings vacios");
            return;
        }

        if(Enums.TipoDato.UNDEFINED != mTipoDato && getTipoDeDato(dato) == mTipoDato){
            Nodo nodo = new Nodo(dato);

            switch (mLength) {
                case 0:
                    // si la lista es vacia
                    mInicial = nodo;
                    mFinal = nodo;
                    break;
                case 1:
                    // el nodo que ya existía es final
                    mFinal.setAnterior(nodo);

                    // el nuevo nodo es inicial
                    nodo.setSiguiente(mFinal);
                    nodo.setEsInicial();
                    mInicial = nodo;
                    break;
                default:
                    nodo.setSiguiente(mInicial);
                    nodo.setEsInicial();
                    mInicial.setAnterior(nodo);
                    mInicial = nodo;
                    break;
            }
            mLength ++;
        }else{
            System.out.println("insercion no exitosa tipo de dato no soportado");
        }
    }

    @Override
    public void insertarNodoAlFinal(Nodo nodo) {
        // agregar al final de una lista vacia es lo mismo que agregar al inicio
        if(0 == mLength){
            insertarNodoAlInicio(nodo);
            return;
        }

        if(Enums.TipoDato.UNDEFINED != mTipoDato && nodo.getTipoDato() == mTipoDato){
            //Nodo nodo = new Nodo(dato);
            if (mLength == 1) {
                mInicial.setSiguiente(nodo);

                nodo.setAnterior(mInicial);
                nodo.setEsFinal();
                mFinal = nodo;
            } else {
                nodo.setAnterior(mFinal);
                nodo.setEsFinal();
                mFinal.setSiguiente(nodo);
                mFinal = nodo;
            }
            mLength ++;
        }else{
            System.out.println("insercion no exitosa tipo de dato no soportado");
        }
    }

    @Override
    public void crearNodoAlFinal(Object dato) {
        // agregar al final de una lista vacia es lo mismo que agregar al inicio
        if(0 == mLength){
            crearNodoAlInicio(dato);
            return;
        }

        if(Enums.TipoDato.STRING == mTipoDato && dato.equals("") || dato.equals(" ")){
            System.out.println("insercion no exitosa tipo de strings vacios");
            return;
        }

        if(Enums.TipoDato.UNDEFINED != mTipoDato && getTipoDeDato(dato) == mTipoDato){
            Nodo nodo = new Nodo(dato);
            if (mLength == 1) {
                mInicial.setSiguiente(nodo);

                nodo.setAnterior(mInicial);
                nodo.setEsFinal();
                mFinal = nodo;
            } else {
                nodo.setAnterior(mFinal);
                nodo.setEsFinal();
                mFinal.setSiguiente(nodo);
                mFinal = nodo;
            }
            mLength ++;
        }else{
            System.out.println("insercion no exitosa tipo de dato no soportado");
        }
    }

    @Override
    public void crearNodoEnPosicion(int pos, Object dato){
        if(pos <= mLength && mLength > 0 && pos >=0){
            if(pos == 0){
                crearNodoAlInicio(dato);
                return;
            }

            if(Enums.TipoDato.STRING == mTipoDato && dato.equals("") || dato.equals(" ")){
                System.out.println("insercion no exitosa tipo de strings vacios");
                return;
            }

            // si llegamos aca, no fue un caso especial y tenemos que buscar
            // los nodos contiguos al que vamos a insertar
            Nodo nodoAnterior = getNodoEnPos(pos).getAnterior();
            Nodo nodoSiguiente = nodoAnterior.getSiguiente();

            // creamos el nuevo nodo y lo insertamos en lo unimos a los existentes
            Nodo nodo = new Nodo(dato);
            nodo.setAnterior(nodoAnterior);
            nodo.setSiguiente(nodoSiguiente);

            // unimos los existentes al nuevo nodo
            nodoSiguiente.setAnterior(nodo);
            nodoAnterior.setSiguiente(nodo);

            mLength++;
        }else{
            imprimirErrorDeRango("agregarEnPosicion", pos);
        }
    }

    @Override
    public void insertarNodoEnPosicion(int pos, Nodo nodo){
        if(pos <= mLength /*&& mLength > 0*/ && pos >=0){
            if(pos == mLength){
                insertarNodoAlFinal(nodo);
                return;
            }

            // si llegamos aca, no fue un caso especial y tenemos que buscar
            // los nodos contiguos al que vamos a insertar
            Nodo nodoAnterior = getNodoEnPos(pos).getAnterior();
            Nodo nodoSiguiente = nodoAnterior.getSiguiente();

            // creamos el nuevo nodo y lo insertamos en lo unimos a los existentes
            //Nodo nodo = new Nodo(dato);
            nodo.setAnterior(nodoAnterior);
            nodo.setSiguiente(nodoSiguiente);

            // unimos los existentes al nuevo nodo
            nodoSiguiente.setAnterior(nodo);
            nodoAnterior.setSiguiente(nodo);

            mLength++;
        }else{
            imprimirErrorDeRango("agregarEnPosicion", pos);
        }
    }

    @Override
    public void crearYSobreescribirNodoEnPosicion(int pos, Object dato){
        if(pos < mLength && mLength > 0 && pos >=0){
            if(pos == 0){
                eliminarPrimerElemento();
                crearNodoAlInicio(dato);
                return;
            }
            if (pos == mLength -1){
                eliminarUltimoElemento();
                crearNodoAlFinal(dato);
                return;
            }

            // si llegamos aca, no fue un caso especial y tenemos que buscar
            // los nodos contiguos al que vamos a insertar
            Nodo nodoAnterior = getNodoEnPos(pos-1);
            Nodo nodoSiguiente = getNodoEnPos(pos+1);

            // creamos el nuevo nodo y lo insertamos en lo unimos a los existentes
            Nodo nodo = new Nodo(dato);
            nodo.setAnterior(nodoAnterior);
            nodo.setSiguiente(nodoSiguiente);

            // unimos los existentes al nuevo nodo
            nodoSiguiente.setAnterior(nodo);
            nodoAnterior.setSiguiente(nodo);
        }else{
            imprimirErrorDeRango("sustituirEnPosicion", pos);
        }
    }

    @Override
    public void eliminarPrimerElemento() {
        if(mLength == 1){
            mInicial = null;
            mLength = 0;
            return;
        }

        if(mLength != 0) {
            Nodo nuevoPrimerElemento = mInicial.getSiguiente();
            mInicial.desconectar();
            mInicial = nuevoPrimerElemento;
            nuevoPrimerElemento.setEsInicial();
            mLength--;
        }
    }

    @Override
    public void eliminarUltimoElemento() {
        // eliminar al final de una lista de un elemento es lo mismo que eliminar el primer elemento
        if(mLength == 1){
            eliminarPrimerElemento();
            return;
        }

        if(mLength != 0){
            Nodo nuevoUltimoElemento = mFinal.getAnterior();
            mFinal.desconectar();
            mFinal = nuevoUltimoElemento;
            nuevoUltimoElemento.setEsFinal();
            mLength--;
        }
    }

    @Override
    public void eliminarEnPosicion(int pos) {
        if(pos < mLength && mLength > 0 && pos >=0 ){
            if(pos == mLength-1){
                eliminarUltimoElemento();
                return;
            }

            if(pos == 0){
                eliminarPrimerElemento();
                return;
            }

            // si llegamos aca, no fue un caso especial y tenemos que buscar el elemento
            // que vamos a borrar
            Nodo nodoBuscador = getNodoEnPos(pos);

            Nodo nodoSiguienteAlBuscado = nodoBuscador.getSiguiente();
            Nodo nodoAnteriorAlBuscado = nodoBuscador.getAnterior();

            nodoAnteriorAlBuscado.setSiguiente(nodoSiguienteAlBuscado);
            nodoSiguienteAlBuscado.setAnterior(nodoAnteriorAlBuscado);
            nodoBuscador.desconectar();

            mLength--;
        }else{
            imprimirErrorDeRango("eliminarEnPosicion", pos);
        }
    }

    @Override
    public void ordenar(Enums.Dir pDir) {
        switch (mTipoDato){
            case INT:
                MySort.ordenarListaDeDatoInt(this,pDir);
                break;
            case STRING:
                MySort.ordenarListaDeDatoString(this,pDir);
                break;
            case LIST:
                MySort.ordenarListaDeDatoListaPorNombre(this,pDir);
                break;
        }
    }

    @Override
    public void vaciar() {
        mTipoDato = Enums.TipoDato.UNDEFINED;
        mInicial.desconectar();
        mLength = 0;
        //mTope = -1;
        //mNombre = "";
    }

    // METODOS PRIVADOS
    private Enums.TipoDato getTipoDeDato(Object dato){
        Enums.TipoDato result;
        if(dato instanceof String){
            result = Enums.TipoDato.STRING;
        }
        else if(dato instanceof Integer){
            result = Enums.TipoDato.INT;
        }
        else if(dato instanceof Lista){
            result = Enums.TipoDato.LIST;
        }
        else {
            result = Enums.TipoDato.UNDEFINED;
        }
        return result;
    }

    private void setTipoDeLista(Object dato){
        if(dato instanceof String){
            mTipoDato = Enums.TipoDato.STRING;
        }
        else if(dato instanceof Integer){
            mTipoDato = Enums.TipoDato.INT;
        }
        else if(dato instanceof Lista){
            mTipoDato = Enums.TipoDato.LIST;
        }
        else {
            mTipoDato = Enums.TipoDato.UNDEFINED;
        }
    }

    private void setTipoDeLista(Enums.TipoDato pTipoDato){
        if(pTipoDato != Enums.TipoDato.UNDEFINED){
            mTipoDato = pTipoDato;
        }
    }

    private void imprimirErrorDeRango(String contexto, int pos){
        if(pos < 0){
            System.out.println(contexto + " / parametro pos fuera de rango, negative");
        }
        if(pos > 0){
            System.out.println(contexto + " / parametro pos fuera de rango, positive");
        }
    }

    // CONSTRUCTORES
    public Lista(int pTope, String pNombre){
        mTipoDato = Enums.TipoDato.UNDEFINED;
        mTope = pTope;
        mLength = 0;
        mNombre = pNombre;
        mFinalExpansion = null;
        mInicialExpansion = null;
    }

    public Lista(int pTope){
        mTipoDato = Enums.TipoDato.UNDEFINED;
        mTope = pTope;
        mLength = 0;
        mNombre = "lista nueva";

        mFinalExpansion = null;
        mInicialExpansion = null;
    }

    public Lista(String pNombre){
        mTipoDato = Enums.TipoDato.UNDEFINED;
        mTope = -1;
        mLength = 0;
        mNombre = pNombre;

        mFinalExpansion = null;
        mInicialExpansion = null;
    }

    public Lista(){
        mTipoDato = Enums.TipoDato.UNDEFINED;
        mTope = -1;
        mLength = 0;
        mNombre = "lista nueva";

        mFinalExpansion = null;
        mInicialExpansion = null;
    }
}
