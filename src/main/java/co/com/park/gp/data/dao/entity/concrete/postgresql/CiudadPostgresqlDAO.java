package co.com.park.gp.data.dao.entity.concrete.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.CiudadDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.CiudadEntity;
import co.com.park.gp.entity.DepartamentoEntity;
import co.com.park.gp.entity.PaisEntity;

public class CiudadPostgresqlDAO extends SqlConnection implements CiudadDAO {

	public CiudadPostgresqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<CiudadEntity> consultar(CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append(
				"SELECT c.id, c.nombre, d.id as idDepartamento, d.nombre as nombreDepartamento, p.id as idPais, p.nombre as nombrePais");
		sentenciaSql.append(" FROM ciudad c");
		sentenciaSql.append(" INNER JOIN departamento d ON c.departamento_id = d.id");
		sentenciaSql.append(" INNER JOIN pais p ON d.pais_id = p.id");
		sentenciaSql.append(" WHERE 1=1");

		final List<Object> parametros = new ArrayList<>();

		if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND c.id = ?");
			parametros.add(data.getId());
		}
		if (!TextHelper.isNullOrEmpty(data.getNombre())) {
			sentenciaSql.append(" AND c.nombre = ?");
			parametros.add(data.getNombre());
		}
		if (!ObjectHelper.getObjectHelper().isNull(data.getDepartamento())
				&& !ObjectHelper.getObjectHelper().isNull(data.getDepartamento().getId())
				&& !data.getDepartamento().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND c.departamento_id = ?");
			parametros.add(data.getDepartamento().getId());
		}

		final List<CiudadEntity> ciudades = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
			}

			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					CiudadEntity ciudad = CiudadEntity.build();
					ciudad.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
					ciudad.setNombre(resultado.getString("nombre"));

					DepartamentoEntity departamento = DepartamentoEntity.build();
					departamento.setId(UUIDHelper.convertToUUID(resultado.getString("idDepartamento")));
					departamento.setNombre(resultado.getString("nombreDepartamento"));

					PaisEntity pais = PaisEntity.build();
					pais.setId(UUIDHelper.convertToUUID(resultado.getString("idPais")));
					pais.setNombre(resultado.getString("nombrePais"));

					departamento.setPais(pais);
					ciudad.setDepartamento(departamento);

					ciudades.add(ciudad);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario =MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return ciudades;
	}

}
