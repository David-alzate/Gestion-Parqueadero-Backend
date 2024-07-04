package co.com.park.gp.data.dao.entity.concrete.postgresql.comunes;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.comunes.TipoVehiculoDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoVehiculoPostgresqlDAO extends SqlConnection implements TipoVehiculoDAO {

    public TipoVehiculoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<TipoVehiculoEntity> consultar(TipoVehiculoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT t.id, t.tipovehiculo");
        sentenciaSql.append(" FROM tipovehiculo t");
        sentenciaSql.append(" WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND t.id = ?");
            parametros.add(data.getId());
        }
        if (!TextHelper.isNullOrEmpty(data.getTipoVehiculo())) {
            sentenciaSql.append(" AND t.estado = ?");
            parametros.add(data.getTipoVehiculo());
        }

        final List<TipoVehiculoEntity> tipoVehiculos = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    TipoVehiculoEntity tipoVehiculo = TipoVehiculoEntity.build();
                    tipoVehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    tipoVehiculo.setTipoVehiculo(resultado.getString("tipoVehiculo"));

                    tipoVehiculos.add(tipoVehiculo);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los Tipos De Vehiculo.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de los Tipo De Vehiculo en la tabla \"tipoVehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO al consultar la información de los Tipos De Vehiculo.";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar la consulta de los Tipos De Vehiculo en la tabla \"tipoVehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return tipoVehiculos;
    }
}
