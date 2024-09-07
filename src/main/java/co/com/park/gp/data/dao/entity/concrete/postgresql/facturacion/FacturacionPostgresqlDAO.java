package co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.facturacion.FacturacionDAO;
import co.com.park.gp.entity.facturacion.FacturacionEntitiy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class FacturacionPostgresqlDAO extends SqlConnection implements FacturacionDAO {

    public FacturacionPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(FacturacionEntitiy data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO facturacion (id, sesionparqueo_id, duracion, tarifa_id, ");
        sentenciaSql.append("valorPagar, metodoPago_id)");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getSesionParqueo().getId());
            sentenciaSqlPreparada.setObject(3, data.getDuracion());
            sentenciaSqlPreparada.setObject(4, data.getTarifa().getId());
            sentenciaSqlPreparada.setObject(5, data.getValorPagar());
            sentenciaSqlPreparada.setObject(6, data.getMetodoPago().getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00087);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00088);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00087);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00088);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM facturacion WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la factura...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete del Empleado en la tabla \"factura\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la factura...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete del Empleado en la tabla \"factura\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public List<FacturacionEntitiy> consultar(FacturacionEntitiy data) {
        return List.of();
    }

    @Override
    public void modificar(FacturacionEntitiy data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE factura SET sesionparqueo_id=?, duracion=?, tarifa_id=?,  ");
        sentenciaSql.append("valorpagar=?, metodopago=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getSesionParqueo().getId());
            sentenciaSqlPreparada.setObject(2, data.getDuracion());
            sentenciaSqlPreparada.setObject(3, data.getTarifa().getId());
            sentenciaSqlPreparada.setObject(4, data.getValorPagar());
            sentenciaSqlPreparada.setObject(4, data.getValorPagar());
            sentenciaSqlPreparada.setObject(5, data.getMetodoPago().getId());
            sentenciaSqlPreparada.setObject(6, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la factura...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update del Empleado en la tabla \"factura\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la factura...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update Empleado en la tabla \"factura\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }
}
