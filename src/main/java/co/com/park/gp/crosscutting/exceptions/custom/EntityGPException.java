package co.com.park.gp.crosscutting.exceptions.custom;


import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.enums.Lugar;

import java.io.Serial;

public final class EntityGPException extends GPException {

    @Serial
    private static final long serialVersionUID = 2325104002394078932L;

    public EntityGPException(final String mensajeUsuario) {
        super(mensajeUsuario, Lugar.ENTITY);
    }

    public EntityGPException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, Lugar.ENTITY);
    }

    public EntityGPException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
        super(mensajeTecnico, mensajeUsuario, Lugar.ENTITY, exeptionRaiz);
    }

}
