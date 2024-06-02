package co.com.park.gp.data.dao.entity.concrete;

import java.sql.Connection;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.SQLHelper;

public class SqlConnection {

	private Connection conexion;

	protected SqlConnection(final Connection conexion) {
		setConexion(conexion);
	}

	protected SqlConnection() {
		super();
	}

	protected final Connection getConexion() {
		return conexion;
	}

	protected final void setConexion(Connection conexion) {
		if (!SQLHelper.isOpen(conexion)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var MensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);

			throw new DataGPException(mensajeUsuario, MensajeTecnico);
		}
		this.conexion = conexion;
	}

}
