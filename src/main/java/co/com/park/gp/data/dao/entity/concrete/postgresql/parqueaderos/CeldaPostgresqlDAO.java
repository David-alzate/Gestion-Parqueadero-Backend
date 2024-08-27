package co.com.park.gp.data.dao.entity.concrete.postgresql.parqueaderos;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.parqueaderos.CeldaDAO;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CeldaPostgresqlDAO extends SqlConnection implements CeldaDAO {

    public CeldaPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(CeldaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO celda (id, sede_id, tipovehiculo_id, cantidadceldas");
        sentenciaSql.append("VALUES (?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getSede().getId());
            sentenciaSqlPreparada.setObject(3, data.getTipoVehiculo().getId());
            sentenciaSqlPreparada.setInt(4, data.getCantidadCeldas());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro de la celda...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la celda en la tabla \"Celda\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar un registro de la celda...";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert de la celda en la tabla \"celda\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM celda WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la celda...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete de la celda en la tabla \"Celda\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la celda...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete de la celda en la tabla \"celda\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public List<CeldaEntity> consultar(CeldaEntity data) {
        return List.of();
    }

    @Override
    public void modificar(CeldaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE celda SET sede_id=?, tipovehiculo_id=?, cantidadceldas=? WHERE id=?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getSede().getId());
            sentenciaSqlPreparada.setObject(2, data.getTipoVehiculo().getId());
            sentenciaSqlPreparada.setInt(3, data.getCantidadCeldas());
            sentenciaSqlPreparada.setObject(4, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la celda...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update de la celda en la tabla \"celda\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la celda...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update de la celda en la tabla \"celda\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }
}
