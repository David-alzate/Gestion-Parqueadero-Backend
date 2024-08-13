package co.com.park.gp.crosscutting.exceptions.custom;


import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.enums.Lugar;

import java.io.Serial;

public final class CroscuttingGPException extends GPException {

    @Serial
    private static final long serialVersionUID = 2325104002394078932L;

    public CroscuttingGPException(final String mensajeUsuario) {
        super(mensajeUsuario, Lugar.CROSCUTTING);
    }

    public CroscuttingGPException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, Lugar.CROSCUTTING);
    }

    public CroscuttingGPException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
        super(mensajeTecnico, mensajeUsuario, Lugar.CROSCUTTING, exeptionRaiz);
    }

}
