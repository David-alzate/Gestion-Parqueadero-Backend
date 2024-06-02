package co.com.park.gp.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.SQLHelper;
import co.com.park.gp.data.dao.entity.CiudadDAO;
import co.com.park.gp.data.dao.entity.DepartamentoDAO;
import co.com.park.gp.data.dao.entity.PaisDAO;
import co.com.park.gp.data.dao.entity.ParqueaderoDAO;
import co.com.park.gp.data.dao.entity.SedeDAO;
import co.com.park.gp.data.dao.entity.TipoSedeDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.concrete.postgresql.CiudadPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.DepartamentoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.PaisPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.ParqueaderoPostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.SedePostgresqlDAO;
import co.com.park.gp.data.dao.entity.concrete.postgresql.TipoSedePostgresqlDAO;
import co.com.park.gp.data.dao.factory.DAOFactory;

public final class PostgresqlDAOFactory extends SqlConnection implements DAOFactory {

	public PostgresqlDAOFactory() {
		super();
		abrirConexion();
	}

	private void abrirConexion() {
		final String connectionUrl = "jdbc:postgresql://localhost:5432/DOO?user=postgres&password=000";
		try {
			setConexion(DriverManager.getConnection(connectionUrl));
		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00055);

			throw new DataGPException(mensajeTecnico, mensajeUsuario, excepcion);
		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00055);

			throw new DataGPException(mensajeTecnico, mensajeUsuario, excepcion);
		}
	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(getConexion());
	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(getConexion());
	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(getConexion());

	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(getConexion());

	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisPostgresqlDAO(getConexion());
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoPostgresqlDAO(getConexion());
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadPostgresqlDAO(getConexion());
	}

	@Override
	public ParqueaderoDAO getParqueaderoDAO() {
		return new ParqueaderoPostgresqlDAO(getConexion());
	}

	@Override
	public SedeDAO getSedeDAO() {
		return new SedePostgresqlDAO(getConexion());
	}

	@Override
	public TipoSedeDAO getTipoSedeDAO() {
		return new TipoSedePostgresqlDAO(getConexion());
	}
	
	public static void main(String[] args) {
		System.out.println("prueba");
	}

}
