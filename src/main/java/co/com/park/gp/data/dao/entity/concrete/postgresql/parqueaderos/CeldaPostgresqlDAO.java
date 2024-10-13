package co.com.park.gp.data.dao.entity.concrete.postgresql.parqueaderos;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.parqueaderos.CeldaDAO;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CeldaPostgresqlDAO extends SqlConnection implements CeldaDAO {

	private final DAOFactory factory;

	public CeldaPostgresqlDAO(final Connection conexion, DAOFactory factory) {
		super(conexion);
		this.factory = factory;
	}

	@Override
	public void crear(CeldaEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("INSERT INTO celda (id, sede_id, tipovehiculo_id, cantidadceldas )");
		sentenciaSql.append("VALUES (?, ?, ?, ?)");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setObject(2, data.getSede().getId());
			sentenciaSqlPreparada.setObject(3, data.getTipoVehiculo().getId());
			sentenciaSqlPreparada.setInt(4, data.getCantidadCeldas());

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro de la celda...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la celda en la tabla \"Celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar un registro de la celda...";
			var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert de la celda en la tabla \"celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public void eliminar(UUID id) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("DELETE FROM celda WHERE id = ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, id);
			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la celda...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete de la celda en la tabla \"Celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la celda...";
			var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete de la celda en la tabla \"celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public List<CeldaEntity> consultar(CeldaEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SELECT c.id, s.id as idSede, s.nombresede as sedeNombre, ");
		sentenciaSql.append("tv.id as idTipoVehiculo, tv.tipovehiculo as tipoVehiculoNombre, ");
		sentenciaSql.append("c.cantidadceldas ");
		sentenciaSql.append("FROM celda c ");
		sentenciaSql.append("INNER JOIN sede s ON s.id = c.sede_id ");
		sentenciaSql.append("INNER JOIN tipovehiculo tv ON tv.id = c.tipovehiculo_id ");
		sentenciaSql.append("WHERE 1=1 ");

		final List<Object> parametros = new ArrayList<>();

		if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND c.id = ?");
			parametros.add(data.getId());
		}

		if (!ObjectHelper.getObjectHelper().isNull(data.getSede())
				&& !ObjectHelper.getObjectHelper().isNull(data.getSede().getId())
				&& !data.getSede().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND s.id = ?");
			parametros.add(data.getSede().getId());
		}

		if (!ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo())
				&& !ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo().getId())
				&& !data.getTipoVehiculo().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND tv.id = ?");
			parametros.add(data.getTipoVehiculo().getId());
		}

		if (data.getCantidadCeldas() > 0) {
			sentenciaSql.append(" AND c.cantidadceldas = ?");
			parametros.add(data.getCantidadCeldas());
		}

		final List<CeldaEntity> celdas = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
			}

			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					CeldaEntity celda = new CeldaEntity();
					celda.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
					celda.setCantidadCeldas(resultado.getInt("cantidadceldas"));

					SedeEntity sede = SedeEntity.build();
					sede.setId(UUIDHelper.convertToUUID(resultado.getString("idSede")));
					sede.setNombre(resultado.getString("sedeNombre"));
					celda.setSede(sede);

					TipoVehiculoEntity tipoVehiculo = TipoVehiculoEntity.build();
					tipoVehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoVehiculo")));
					tipoVehiculo.setTipoVehiculo(resultado.getString("tipoVehiculoNombre"));
					celda.setTipoVehiculo(tipoVehiculo);

					celdas.add(celda);
				}
			}

		} catch (SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar las Celdas...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select de la Celda en la tabla \"Celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar las Celdas...";
			var mensajeTecnico = "Se ha presentado un error INESPERADO tratando de realizar el Select Celda en la tabla \"Celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return celdas;
	}

	@Override
	public void modificar(CeldaEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("UPDATE celda SET sede_id=?, tipovehiculo_id=?, cantidadceldas=? WHERE id=?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getSede().getId());
			sentenciaSqlPreparada.setObject(2, data.getTipoVehiculo().getId());
			sentenciaSqlPreparada.setInt(3, data.getCantidadCeldas());
			sentenciaSqlPreparada.setObject(4, data.getId());
			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de modificar la celda...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update de la celda en la tabla \"celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de modificar la celda...";
			var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update de la celda en la tabla \"celda\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public int celdasDisponibles(UUID idSede, UUID idTipoVehiculo) {

		if (idSede.equals(UUIDHelper.getDefault()) || idTipoVehiculo.equals(UUIDHelper.getDefault())
				|| ObjectHelper.getObjectHelper().isNull(idSede)
				|| ObjectHelper.getObjectHelper().isNull(idTipoVehiculo)) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar las celdas disponibles";
			throw new DataGPException(mensajeUsuario);
		}

		var celdasOcupadaSesionesParqueo = factory.getSesionParqueoDAO().consultaCeldasOcupadas(idSede, idTipoVehiculo);
		var celdasOcupadasPlanes = factory.getPlanDAO().consultaCeldasOcupadasPlan(idSede, idTipoVehiculo);
		var resultadoCeldas = factory.getCeldaDao()
				.consultar(CeldaEntity.build().setSede(SedeEntity.build().setId(idSede))
						.setTipoVehiculo(TipoVehiculoEntity.build().setId(idTipoVehiculo)));

		var totalCeldas = resultadoCeldas.get(0).getCantidadCeldas();
		return totalCeldas - celdasOcupadaSesionesParqueo - celdasOcupadasPlanes;
	}
}
