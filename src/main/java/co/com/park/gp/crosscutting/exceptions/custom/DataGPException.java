package co.com.park.gp.crosscutting.exceptions.custom;


import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.enums.Lugar;

import java.io.Serial;

public final class DataGPException extends GPException {

    @Serial
    private static final long serialVersionUID = 2325104002394078932L;

    public DataGPException(final String mensajeUsuario) {
        super(mensajeUsuario, Lugar.DATA);
    }

    public DataGPException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, Lugar.DATA);
    }

    public DataGPException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
        super(mensajeTecnico, mensajeUsuario, Lugar.DATA, exeptionRaiz);
    }

}
