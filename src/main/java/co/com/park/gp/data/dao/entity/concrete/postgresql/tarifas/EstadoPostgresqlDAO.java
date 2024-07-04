package co.com.park.gp.data.dao.entity.concrete.postgresql.tarifas;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.tarifas.EstadoDAO;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoPostgresqlDAO extends SqlConnection implements EstadoDAO {

    public EstadoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<EstadoEntity> consultar(EstadoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT e.id, e.estado");
        sentenciaSql.append(" FROM estado e");
        sentenciaSql.append(" WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND e.id = ?");
            parametros.add(data.getId());
        }
        if (!TextHelper.isNullOrEmpty(data.getEstado())) {
            sentenciaSql.append(" AND e.estado = ?");
            parametros.add(data.getEstado());
        }

        final List<EstadoEntity> estados = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    EstadoEntity estado = EstadoEntity.build();
                    estado.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    estado.setEstado(resultado.getString("estado"));

                    estados.add(estado);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los Estados.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de los Estados en la tabla \"estado\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO al consultar la información de los Estados.";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar la consulta de los Estados en la tabla \"estado\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return estados;
    }
}
