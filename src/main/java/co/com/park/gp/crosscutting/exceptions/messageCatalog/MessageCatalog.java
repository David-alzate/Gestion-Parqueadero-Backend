package co.com.park.gp.crosscutting.exceptions.messageCatalog;

import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.Mensaje;

public interface MessageCatalog {
	
	void inicializar();
	
	String obtenerContenidoMensaje(final CodigoMensaje codigo,String ...parametros);
	Mensaje obtenerMensaje(final CodigoMensaje codigo,String ...parametros);

}
