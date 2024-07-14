package co.com.park.gp.data.dao.entity.concrete.postgresql.planes;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.planes.TipoPlanDAO;
import co.com.park.gp.entity.planes.TipoPlanEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPlanPostgresqlDAO extends SqlConnection implements TipoPlanDAO {

    public TipoPlanPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<TipoPlanEntity> consultar(TipoPlanEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT tp.id, tp.nombre");
        sentenciaSql.append(" FROM tipoplan tp");
        sentenciaSql.append(" WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND tp.id = ?");
            parametros.add(data.getId());
        }
        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND tp.nombre = ?");
            parametros.add(data.getNombre());
        }

        final List<TipoPlanEntity> tiposPlan = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    TipoPlanEntity tipoPlan = TipoPlanEntity.build();
                    tipoPlan.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    tipoPlan.setNombre(resultado.getString("nombre"));

                    tiposPlan.add(tipoPlan);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de los tipos de plan.";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de los tipos de plan en la tabla \"TipoPlan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información de los tipos de plan.";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar la consulta de los tipos de plan en la tabla \"TipoPlan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return tiposPlan;
    }

}
