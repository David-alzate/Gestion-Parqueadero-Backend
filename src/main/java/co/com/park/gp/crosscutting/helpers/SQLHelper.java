package co.com.park.gp.crosscutting.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import co.com.park.gp.crosscutting.exceptions.custom.CroscuttingGPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;



public class SQLHelper {
	
	private SQLHelper() {
		super();
	}

	public static final boolean isNull(final Connection connection) {
		return ObjectHelper.getObjectHelper().isNull(connection);
	}

	public static final boolean isOpen(final Connection connection) {
		try {
			return !isNull(connection) && !connection.isClosed();
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00007);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00008);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void close(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00009);

				throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
			}

			connection.close();
		} catch (final CroscuttingGPException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00010);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00011);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void commit(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);

				throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
			}

			if (connection.getAutoCommit()) {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00013);

				throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
			}

			connection.commit();
		} catch (final CroscuttingGPException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00014);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00015);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void rollback(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00016);

				throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
			}

			if (connection.getAutoCommit()) {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00017);

				throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
			}

			connection.rollback();
		} catch (final CroscuttingGPException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00018);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00019);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		}
	}

	public static final void initTransaction(final Connection connection) {
		try {
			if (!isOpen(connection)) {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00020);

				throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario);
			}

			connection.setAutoCommit(false);
		} catch (final CroscuttingGPException exception) {
			throw exception;
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00021);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		} catch (final Exception exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00022);

			throw new CroscuttingGPException(mensajeTecnico, mensajeUsuario, exception);
		}
	}
}
