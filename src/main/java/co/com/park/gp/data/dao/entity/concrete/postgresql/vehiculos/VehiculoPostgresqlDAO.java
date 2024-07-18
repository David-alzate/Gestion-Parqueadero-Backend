package co.com.park.gp.data.dao.entity.concrete.postgresql.vehiculos;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.vehiculos.VehiculoDAO;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VehiculoPostgresqlDAO extends SqlConnection implements VehiculoDAO {

    public VehiculoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(VehiculoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO vehiculo (id, tipovehiculo_id, placa)");
        sentenciaSql.append("VALUES (?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getTipoVehiculo().getId());
            sentenciaSqlPreparada.setString(3, data.getPlaca());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro del vehiculo...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert del vehiculo en la tabla \"vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar un registro del vehiculo...";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert del vehiculo en la tabla \"vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM vehiculo WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el Vehiculo...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete del Vehiculo en la tabla \"Vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar El Vehiculo...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete del Vehiculo en la tabla \"Vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public List<VehiculoEntity> consultar(VehiculoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT v.id, tv.id as idTipoVehiculo, tv.tipovehiculo as tipoVehiculo, v.placa ");
        sentenciaSql.append("FROM vehiculo v ");
        sentenciaSql.append("INNER JOIN tipovehiculo tv ON tv.id = v.tipovehiculo_id ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND v.id = ?");
            parametros.add(data.getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo())
                && !ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo().getId())
                && !data.getTipoVehiculo().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND tv.id = ?");
            parametros.add(data.getTipoVehiculo().getId());
        }

        if (!TextHelper.isNullOrEmpty(data.getPlaca())) {
            sentenciaSql.append(" AND v.placa = ?");
            parametros.add(data.getPlaca());
        }

        final List<VehiculoEntity> vehiculos = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    VehiculoEntity vehiculo = new VehiculoEntity();
                    vehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    vehiculo.setPlaca(resultado.getString("placa"));

                    TipoVehiculoEntity tipoVehiculo = TipoVehiculoEntity.build();
                    tipoVehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoVehiculo")));
                    tipoVehiculo.setTipoVehiculo(resultado.getString("tipoVehiculo"));
                    vehiculo.setTipoVehiculo(tipoVehiculo);

                    vehiculos.add(vehiculo);
                }
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Vehiculo...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select del Vehiculo en la tabla \"Vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Vehiculo...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Select Vehiculo en la tabla \"Vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return vehiculos;
    }


    @Override
    public void modificar(VehiculoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE vehiculo SET tipovehiculo_id=?, placa=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getTipoVehiculo().getId());
            sentenciaSqlPreparada.setString(2, data.getPlaca());
            sentenciaSqlPreparada.setObject(3, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Vehiculo...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update del Vehiculo en la tabla \"Vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Vehiculo...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update Vehiculo en la tabla \"Vehiculo\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

}

