package co.com.park.gp.crosscutting.messagecatalog.data;


public final class Mensaje {

    private CodigoMensaje codigo;
    private String contenido;

    public Mensaje(final CodigoMensaje codigo, final String contenido) {
        setCodigo(codigo);
        setContenido(contenido);
    }

    public final boolean esBase() {
        return getCodigo().isBase();
    }

    private final CodigoMensaje getCodigo() {
        return codigo;
    }

    public final String getContenido() {
        return contenido;
    }

    public final TipoMensaje getTipo() {
        return getCodigo().getTipo();
    }

    public final CategoriaMensaje getCategoria() {
        return getCodigo().getCategoria();
    }

    private final void setCodigo(final CodigoMensaje codigo) {
        this.codigo = codigo;
    }

    private final void setContenido(final String contenido) {
        this.contenido = contenido;
    }

    public final String getIdentificador() {
        return getCodigo().getIdentificador();
    }

}

