package co.com.park.gp.data.dao.entity.concrete.postgresql.sesionparqueo;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.sesionparqueo.SesionParqueoDAO;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SesionParqueoPostgresqlDAO extends SqlConnection implements SesionParqueoDAO {

    public SesionParqueoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void ingresarVehiculo(SesionParqueoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO sesionparqueo (id, sede_id, vehiculo_id, empleado_id, ");
        sentenciaSql.append("estado_id, fechahoraingreso)");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getSede().getId());
            sentenciaSqlPreparada.setObject(3, data.getVehiculo().getId());
            sentenciaSqlPreparada.setObject(4, data.getEmpleado().getId());
            sentenciaSqlPreparada.setObject(5, data.getEstado().getId());
            sentenciaSqlPreparada.setObject(6, data.getFechaHoraIngreso());

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
        return List.of();
    }

    @Override
    public void modificar(SesionParqueoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE sesionparqueo SET sede_id=?, vehiculo_id=?, empleado_id=?,  ");
        sentenciaSql.append("estado_id=?, fechahoraingreso=?, fechahorasalida=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getSede().getId());
            sentenciaSqlPreparada.setObject(2, data.getVehiculo().getId());
            sentenciaSqlPreparada.setObject(3, data.getEmpleado().getId());
            sentenciaSqlPreparada.setObject(4, data.getEstado().getId());
            sentenciaSqlPreparada.setObject(5, data.getFechaHoraIngreso());
            sentenciaSqlPreparada.setObject(5, data.getFechaHoraSalida());
            sentenciaSqlPreparada.setObject(6, data.getId());
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
}
