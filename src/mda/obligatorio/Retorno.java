package mda.obligatorio;

public class Retorno {
    public enum TipoRet {OK, ERROR, NO_IMPLEMENTADA};

    public int valorEntero;
    public String valorString;
    public TipoRet resultado;

    public Retorno() {
    }

    public Retorno(TipoRet resultado) {
        this.resultado = resultado;
    }

    public Retorno(TipoRet resultado, int valorEntero, String valorString) {
        this.valorEntero = valorEntero;
        this.valorString = valorString;
        this.resultado = resultado;
    }
}
