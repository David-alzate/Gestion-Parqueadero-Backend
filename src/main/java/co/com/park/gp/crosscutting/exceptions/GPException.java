package co.com.park.gp.crosscutting.exceptions;

import co.com.park.gp.crosscutting.exceptions.enums.Lugar;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;

import java.io.Serial;

public class GPException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4029244336874739730L;
    protected String mensajeUsuario;
    protected Lugar lugar;

    public GPException(final String mensajeTecnico, final String mensajeUsuario, final Lugar lugar,
                       final Throwable exeptionRaiz) {
        super(mensajeTecnico, exeptionRaiz);
        setMensajeUsuario(mensajeUsuario);
        setLugar(lugar);
    }

    public GPException(final String mensajeUsuario, final Lugar lugar) {
        super(mensajeUsuario);
        setMensajeUsuario(mensajeUsuario);
        setLugar(lugar);
    }

    public GPException(final String mensajeTecnico, final String mensajeUsuario, final Lugar lugar) {
        super(mensajeTecnico);
        setMensajeUsuario(mensajeUsuario);
        setLugar(lugar);
    }

    private final void setMensajeUsuario(final String mensajeUsuario) {
        this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
    }

    private final void setLugar(final Lugar lugar) {
        this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
    }

    public final String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public final Lugar getLugar() {
        return lugar;
    }

}
