package co.com.park.gp.crosscutting.messagecatalog;

import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.messagecatalog.data.Mensaje;

public interface MessageCatalog {
	
	void inicializar();
	
	String obtenerContenidoMensaje(final CodigoMensaje codigo, String ...parametros);
	Mensaje obtenerMensaje(final CodigoMensaje codigo,String ...parametros);

}
