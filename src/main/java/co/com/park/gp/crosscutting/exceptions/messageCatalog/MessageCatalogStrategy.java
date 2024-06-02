package co.com.park.gp.crosscutting.exceptions.messagecatalog;

import co.com.park.gp.crosscutting.exceptions.custom.CroscuttingGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.Mensaje;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.impl.MessageCatalogBase;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.impl.MessageCatalogExternalService;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;

public class MessageCatalogStrategy {

	private static final MessageCatalog base = new MessageCatalogBase();
	private static final MessageCatalog externalService = new MessageCatalogExternalService();

	static {
		inicializar();
	}

	private MessageCatalogStrategy() {
		super();
	}

	public static void inicializar() {
		base.inicializar();
		externalService.inicializar();

	}

	private static final MessageCatalog getStrategy(final boolean isBase) {
		return isBase ? base : externalService;
	}

	private static final Mensaje getMensaje(final CodigoMensaje codigo, final String... parametros) {
		if (ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
		}

		return getStrategy(codigo.isBase()).obtenerMensaje(codigo, parametros);

	}

	public static final String getContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
		return getMensaje(codigo, parametros).getContenido();

	}

}
