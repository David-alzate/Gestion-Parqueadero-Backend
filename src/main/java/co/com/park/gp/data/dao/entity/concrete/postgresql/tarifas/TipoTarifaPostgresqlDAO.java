package co.com.park.gp.data.dao.entity.concrete.postgresql.tarifas;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.tarifas.TipoTarifaDAO;
import co.com.park.gp.entity.tarifas.TipoTarifaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoTarifaPostgresqlDAO extends SqlConnection implements TipoTarifaDAO {

    public TipoTarifaPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<TipoTarifaEntity> consultar(TipoTarifaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT t.id, t.tipotarifa");
        sentenciaSql.append(" FROM tipotarifa t");
        sentenciaSql.append(" WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND t.id = ?");
            parametros.add(data.getId());
        }
        if (!TextHelper.isNullOrEmpty(data.getTipoTarifa())) {
            sentenciaSql.append(" AND t.estado = ?");
            parametros.add(data.getTipoTarifa());
        }

        final List<TipoTarifaEntity> tipoTarifas = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    TipoTarifaEntity tipoTarifa = TipoTarifaEntity.build();
                    tipoTarifa.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    tipoTarifa.setTipoTarifa(resultado.getString("tipoTarifa"));

                    tipoTarifas.add(tipoTarifa);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los Tipos De Tarifa.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de los Tipo De Tarifa en la tabla \"tipoTarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO al consultar la información de los Tipos De Tarifa.";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar la consulta de los Tipos De Tarifa en la tabla \"tipoTarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return tipoTarifas;
    }
}
