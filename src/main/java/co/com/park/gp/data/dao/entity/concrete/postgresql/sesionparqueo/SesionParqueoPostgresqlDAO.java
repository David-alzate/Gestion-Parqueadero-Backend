package co.com.park.gp.data.dao.entity.concrete.postgresql.sesionparqueo;

import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.sesionparqueo.SesionParqueoDAO;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SesionParqueoPostgresqlDAO extends SqlConnection implements SesionParqueoDAO {
	
	private final DAOFactory factory;


	public SesionParqueoPostgresqlDAO(final Connection conexion, DAOFactory factory) {
		super(conexion);
		this.factory = factory;
	}

	@Override
	public void ingresarVehiculo(SesionParqueoEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append(
				"INSERT INTO sesionparqueo (id, sede_id, placa, empleado_id, fechahoraingreso,tipovehiculo_id, estado_id)");
		sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setObject(2, data.getSede().getId());
			sentenciaSqlPreparada.setObject(3, data.getPlaca().toUpperCase());
			sentenciaSqlPreparada.setObject(4, data.getEmpleado().getId());
			sentenciaSqlPreparada.setObject(5, data.getFechaHoraIngreso());
			sentenciaSqlPreparada.setObject(6, data.getTipoVehiculo().getId());
			sentenciaSqlPreparada.setObject(7, EstadoEnum.ACTIVO.getId(factory));

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de llevar el ingreso del vehiculo...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la sesionParqueo en la tabla \"sesionparqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar el ingreso del vehiculo...";
			var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public void salidaVehiculo(SesionParqueoEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("UPDATE sesionparqueo SET fechahorasalida=?, estado_id=? WHERE id=?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getFechaHoraSalida());
			sentenciaSqlPreparada.setObject(2, data.getEstado().getId());
			sentenciaSqlPreparada.setObject(3, data.getId());
			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de realizar la salida del vehiculo...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update de salida de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de realizar la salida del vehiculo";
			var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update de salida de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}

	}

	@Override
	public void eliminar(UUID id) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("DELETE FROM sesionparqueo WHERE id = ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, id);
			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la sesionParqueo...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la sesionParqueo...";
			var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public List<SesionParqueoEntity> consultar(SesionParqueoEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SELECT s.id, se.id as idSede, se.nombresede as nombreSede, ");
		sentenciaSql.append("em.id as idEmpleado, em.numeroidentificacion as numeroIdentificacion, ");
		sentenciaSql.append("es.id as idEstado, es.estado, ");
		sentenciaSql.append(
				"s.fechahoraingreso, s.fechahorasalida, s.placa, tv.tipovehiculo as tipoVehiculo, tv.id as idTipoVehiculo ");
		sentenciaSql.append("FROM sesionparqueo s ");
		sentenciaSql.append("INNER JOIN sede se ON se.id = s.sede_id ");
		sentenciaSql.append("INNER JOIN empleado em ON em.id = s.empleado_id ");
		sentenciaSql.append("INNER JOIN estado es ON es.id = s.estado_id ");
		sentenciaSql.append("INNER JOIN tipovehiculo tv ON tv.id = s.tipovehiculo_id ");
		sentenciaSql.append("WHERE 1=1 ");

		final List<Object> parametros = new ArrayList<>();

		if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND s.id = ?");
			parametros.add(data.getId());
		}

		if (!ObjectHelper.getObjectHelper().isNull(data.getSede())
				&& !ObjectHelper.getObjectHelper().isNull(data.getSede().getId())
				&& !data.getSede().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND se.id = ?");
			parametros.add(data.getSede().getId());
		}

		if (!ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo())
				&& !ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo().getId())
				&& !data.getTipoVehiculo().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND tv.id = ?");
			parametros.add(data.getTipoVehiculo().getId());
		}

		if (!ObjectHelper.getObjectHelper().isNull(data.getEmpleado())
				&& !ObjectHelper.getObjectHelper().isNull(data.getEmpleado().getId())
				&& !data.getEmpleado().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND em.id = ?");
			parametros.add(data.getEmpleado().getId());
		}

		if (!ObjectHelper.getObjectHelper().isNull(data.getEstado())
				&& !ObjectHelper.getObjectHelper().isNull(data.getEstado().getId())
				&& !data.getEstado().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND es.id = ?");
			parametros.add(data.getEstado().getId());
		}

		if (!TextHelper.isNullOrEmpty(data.getPlaca())) {
			sentenciaSql.append(" AND s.placa = ?");
			parametros.add(data.getPlaca());
		}

		final List<SesionParqueoEntity> sesiones = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
			}

			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					SesionParqueoEntity sesion = SesionParqueoEntity.build();
					sesion.setId(UUIDHelper.convertToUUID(resultado.getString("id")));

					SedeEntity sede = SedeEntity.build();
					sede.setId(UUIDHelper.convertToUUID(resultado.getString("idSede")));
					sede.setNombre(resultado.getString("nombreSede"));
					sesion.setSede(sede);

					EmpleadoEntity empleado = EmpleadoEntity.build();
					empleado.setId(UUIDHelper.convertToUUID(resultado.getString("idEmpleado")));
					empleado.setNumeroIdentificacion(resultado.getLong("numeroIdentificacion"));
					sesion.setEmpleado(empleado);

					EstadoEntity estado = EstadoEntity.build();
					estado.setId(UUIDHelper.convertToUUID(resultado.getString("idEstado")));
					estado.setEstado(resultado.getString("estado"));
					sesion.setEstado(estado);

					TipoVehiculoEntity tipoVehiculo = TipoVehiculoEntity.build();
					tipoVehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoVehiculo")));
					tipoVehiculo.setTipoVehiculo(resultado.getString("tipoVehiculo"));
					sesion.setTipoVehiculo(tipoVehiculo);

					java.sql.Timestamp sqlFechaHoraIngreso = resultado.getTimestamp("fechahoraingreso");
					if (sqlFechaHoraIngreso != null) {
						sesion.setFechaHoraIngreso(sqlFechaHoraIngreso.toLocalDateTime());
					}

					java.sql.Timestamp sqlFechaHoraSalida = resultado.getTimestamp("fechahorasalida");
					if (sqlFechaHoraSalida != null) {
						sesion.setFechaHoraSalida(sqlFechaHoraSalida.toLocalDateTime());
					}

					sesion.setPlaca(resultado.getString("placa").toUpperCase());

					sesiones.add(sesion);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar la Sesión de Parqueo...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select de la Sesión de Parqueo en la tabla \"SesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de consultar la Sesión de Parqueo...";
			var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Select Sesión de Parqueo en la tabla \"SesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return sesiones;
	}

	@Override
	public void modificar(SesionParqueoEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("UPDATE sesionparqueo SET sede_id=?, placa=?, empleado_id=?,  ");
		sentenciaSql.append("fechahoraingreso=?, fechahorasalida=?, tipovehiculo_id=? WHERE id=? ");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getSede().getId());
			sentenciaSqlPreparada.setObject(2, data.getPlaca());
			sentenciaSqlPreparada.setObject(3, data.getEmpleado().getId());
			sentenciaSqlPreparada.setObject(4, data.getFechaHoraIngreso());
			sentenciaSqlPreparada.setObject(5, data.getFechaHoraSalida());
			sentenciaSqlPreparada.setObject(6, data.getTipoVehiculo().getId());
			sentenciaSqlPreparada.setObject(7, data.getId());
			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de modificar la sesion de parqueo...";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "Se ha presentado un problema tratando de modificar la sesion Parqueo...";
			var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update de la sesionParqueo en la tabla \"sesionParqueo\" de la base de datos.";
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public int consultaCeldasOcupadas(UUID idSede, UUID idTipoVehiculo) {
		SesionParqueoEntity sesionParqueoEntity = SesionParqueoEntity.build().setSede(SedeEntity.build().setId(idSede))
				.setTipoVehiculo(TipoVehiculoEntity.build().setId(idTipoVehiculo))
				.setEstado(EstadoEntity.build().setId(EstadoEnum.ACTIVO.getId(factory)));
		List<SesionParqueoEntity> resultados = consultar(sesionParqueoEntity);
		if (resultados.isEmpty()) {
			return 0;
		}
		return resultados.size();
	}
}
