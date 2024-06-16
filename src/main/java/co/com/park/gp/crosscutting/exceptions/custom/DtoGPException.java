package co.com.park.gp.crosscutting.exceptions.custom;


import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.enums.Lugar;

import java.io.Serial;

public final class DtoGPException extends GPException {

    @Serial
    private static final long serialVersionUID = 2325104002394078932L;

    public DtoGPException(final String mensajeUsuario) {
        super(mensajeUsuario, Lugar.DTO);
    }

    public DtoGPException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, Lugar.DTO);
    }

    public DtoGPException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
        super(mensajeTecnico, mensajeUsuario, Lugar.DTO, exeptionRaiz);
    }

}
