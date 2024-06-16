package co.com.park.gp.crosscutting.exceptions.messagecatalog.impl;

import java.util.HashMap;
import java.util.Map;

import co.com.park.gp.crosscutting.exceptions.custom.CroscuttingGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalog;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.Mensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;


public class MessageCatalogExternalService implements MessageCatalog {

    private final Map<String, Mensaje> mensajes = new HashMap<>();

    @Override
    public void inicializar() {

        mensajes.clear();
        mensajes.put(CodigoMensaje.M00007.getIdentificador(),
                new Mensaje(CodigoMensaje.M00007, "la transaccion se a completado de forma satisfactoria..."));

    }

    @Override
    public String obtenerContenidoMensaje(CodigoMensaje codigo, String... parametros) {

        return obtenerMensaje(codigo, parametros).getContenido();
    }

    @Override
    public Mensaje obtenerMensaje(CodigoMensaje codigo, String... parametros) {

        if (ObjectHelper.getObjectHelper().isNull(codigo)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
            throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);

        }

        if (codigo.isBase()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005, codigo.getIdentificador());
            throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
        }

        if (!mensajes.containsKey(codigo.getIdentificador())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006, codigo.getIdentificador());
            throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
        }
        return mensajes.get(codigo.getIdentificador());
    }


}
