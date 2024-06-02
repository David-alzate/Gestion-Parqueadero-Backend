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
import co.com.park.gp.data.dao.entity.TipoSedeDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.TipoSedeEntity;

public class TipoSedePostgresqlDAO extends SqlConnection implements TipoSedeDAO {

	public TipoSedePostgresqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<TipoSedeEntity> consultar(TipoSedeEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SELECT ts.id, ts.nombre");
		sentenciaSql.append(" FROM tiposede ts");
		sentenciaSql.append(" WHERE 1=1");

		final List<Object> parametros = new ArrayList<>();

		if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND ts.id = ?");
			parametros.add(data.getId());
		}
		if (!TextHelper.isNullOrEmpty(data.getNombre())) {
			sentenciaSql.append(" AND ts.nombre = ?");
			parametros.add(data.getNombre());
		}

		final List<TipoSedeEntity> tiposSede = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
			}

			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					TipoSedeEntity tipoSede = TipoSedeEntity.build();
					tipoSede.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
					tipoSede.setNombre(resultado.getString("nombre"));

					tiposSede.add(tipoSede);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
			var mensajeTecnico =MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
			throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return tiposSede;
	}

}
