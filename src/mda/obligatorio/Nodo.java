package mda.obligatorio;

public class Nodo {
    //public enum TipoDato {STRING, INT, LIST, UNDEFINED};

    // VARIABLES MIEMBRO
    String      mDatoString;
    Lista       mDatoLista;
    int         mDatoInt;
    String      mNomCarpeta; // solo para archivos

    Enums.TipoDato    mTipoDato;

    Nodo        mSiguiente;
    Nodo        mAnterior;

    // GETTERS
    public String getDatoString(){
        return mDatoString;
    }
    public String getNomCarpeta(){
        return mNomCarpeta;
    }

    public Lista getDatoLista(){
        return mDatoLista;
    }

    public Enums.TipoDato getTipoDato(){
        return mTipoDato;
    }

    public int getDatoInt(){
        return mDatoInt;
    }
    public Nodo getSiguiente(){
        return mSiguiente;
    }
    public Nodo getAnterior(){
        return mAnterior;
    }
    public boolean getEsFinal(){
        return (null == mSiguiente);
    }
    public boolean getEsInicial(){
        return (null == mAnterior);
    }
    public boolean getEsDelMedio(){
        return (null != mAnterior) && (null != mSiguiente);
    }

    // SETTERS
    public void setDato(Object pDato){
        if(Enums.TipoDato.STRING == evaluarTipoDato(pDato) && ((String) pDato).equals("") || ((String) pDato).equals(" ")){
            System.out.println("insercion no exitosa tipo de strings vacios");
            return;
        }

        if(Enums.TipoDato.UNDEFINED == mTipoDato){
            setTipoDato(pDato);
        }
        else{
            switch (evaluarTipoDato((pDato))) {
                case STRING:
                    mDatoString = (String)pDato;
                    break;
                case INT:
                    mDatoInt = (int)pDato;
                    break;
                case LIST:
                    mDatoLista = (Lista)pDato;
                    break;
            }
        }
    }

    public void setSiguiente(Nodo pSiguiente){
        mSiguiente = pSiguiente;
    }

    public void setAnterior(Nodo pAnterior){
        mAnterior = pAnterior;
    }

    public void setEsFinal(){
        mSiguiente = null;
    }

    public void setEsInicial(){
        mAnterior = null;
    }

    // METODOS PUBLICOS
    public void desconectar(){
        mSiguiente = null;
        mAnterior = null;
    }

    // METODOS PRIVADOS
    private void nullifyData(){
        mDatoInt = 0;
        mDatoLista = null;
        mDatoString = null;
        mTipoDato = Enums.TipoDato.UNDEFINED;
    }

    // establece el tipo de dato que maneja el nodo y guarda el dato
    private void setTipoDato(Object pDato){
        if(pDato instanceof String){
            mTipoDato = Enums.TipoDato.STRING;
            mDatoString = (String) pDato;
            mDatoInt = 0;
            mDatoLista = null;
        }
        else if(pDato instanceof Integer){
            mTipoDato = Enums.TipoDato.INT;
            mDatoInt = (int) pDato;
            mDatoLista = null;
            mDatoString = null;
        }
        else if(pDato instanceof Lista){
            mTipoDato = Enums.TipoDato.LIST;
            mDatoLista = (Lista) pDato;
            mDatoInt = 0;
            mDatoString = null;
        }
    }

    // devuelve el tipo de dato del objeto que le pasemos
    private Enums.TipoDato evaluarTipoDato(Object dato){
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

    // CONSTRUCTORES
    // constructor vacío
    public Nodo() {
        nullifyData();
        mTipoDato = Enums.TipoDato.UNDEFINED;
        mAnterior = null;
        mSiguiente = null;
    }

    // crear un nodo pasando datos
    public Nodo(Object pDato) {
        setTipoDato(pDato);
        mAnterior = null;
        mSiguiente = null;
    }

    // crear un nodo pasando datos
    public Nodo(Object pDato, String pCarpetaContenedora) {
        setTipoDato(pDato);
        mNomCarpeta = pCarpetaContenedora;
        mAnterior = null;
        mSiguiente = null;
    }

    // crear un nodo pasando datos y vecinos
    public Nodo(Object pDato, Nodo pSiguiente, Nodo pAnterior) {
        setTipoDato(pDato);
        mAnterior = pAnterior;
        mSiguiente = pSiguiente;
    }
}
