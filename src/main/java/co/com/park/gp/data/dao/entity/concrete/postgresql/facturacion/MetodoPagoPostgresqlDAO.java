package co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.facturacion.MetodoPagoDAO;
import co.com.park.gp.entity.facturacion.MetodoPagoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoPostgresqlDAO extends SqlConnection implements MetodoPagoDAO {

    public MetodoPagoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<MetodoPagoEntity> consultar(MetodoPagoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT mp.id, mp.nombre ");
        sentenciaSql.append("FROM metodopago mp ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND mp.id = ?");
            parametros.add(data.getId());
        }

        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND LOWER(mp.nombre) = ?");
            parametros.add(TextHelper.convertToLowercase(data.getNombre()));
        }

        final List<MetodoPagoEntity> metodosPago = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    MetodoPagoEntity metodoPago = new MetodoPagoEntity();
                    metodoPago.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    metodoPago.setNombre(resultado.getString("nombre"));
                    metodosPago.add(metodoPago);
                }
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Método de Pago...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select de Método de Pago en la tabla \"Método de Pago\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Método de Pago...";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de realizar el Select Método de Pago en la tabla \"Método de Pago\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return metodosPago;
    }

}
