package co.com.park.gp.data.dao.entity.concrete.postgresql.comunes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.comunes.TipoIdentificacionDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

public class TipoIdentificacionPostgresqlDAO extends SqlConnection implements TipoIdentificacionDAO {

    public TipoIdentificacionPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<TipoIdentificacionEntity> consultar(TipoIdentificacionEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT ti.id, ti.nombre");
        sentenciaSql.append(" FROM tipoidentificacion ti");
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

        final List<TipoIdentificacionEntity> tiposIdentificacion = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    TipoIdentificacionEntity tipoIdentificacion = TipoIdentificacionEntity.build();
                    tipoIdentificacion.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    tipoIdentificacion.setNombre(resultado.getString("nombre"));

                    tiposIdentificacion.add(tipoIdentificacion);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00091);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00092);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00091);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00092);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return tiposIdentificacion;
    }

}
