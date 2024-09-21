package co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.facturacion.FacturacionDAO;
import co.com.park.gp.entity.facturacion.FacturacionEntitiy;
import co.com.park.gp.entity.facturacion.MetodoPagoEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FacturacionPostgresqlDAO extends SqlConnection implements FacturacionDAO {

    public FacturacionPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(FacturacionEntitiy data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO facturacion (id, sesion_parqueo_id, duracion, tarifa_id, ");
        sentenciaSql.append("valor_Pagar, metodo_pago_id) VALUES (?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getSesionParqueo().getId());

            // Convertir Duration a INTERVAL en formato string
            long horas = data.getDuracion().toHours();
            long minutos = data.getDuracion().toMinutes() % 60;
            long segundos = data.getDuracion().getSeconds() % 60;
            String intervalo = String.format("'%d hours %d minutes %d seconds'", horas, minutos, segundos);

            sentenciaSqlPreparada.setObject(3, intervalo, java.sql.Types.OTHER); // Cambia aquí

            sentenciaSqlPreparada.setObject(4, data.getTarifa().getId());
            sentenciaSqlPreparada.setObject(5, data.getValorPagar());
            sentenciaSqlPreparada.setObject(6, data.getMetodoPago().getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Error crear la factura";
            var mensajeTecnico = "Error en la base de datos creando la factura";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = "Error crear la factura";
            var mensajeTecnico = "Error en la base de datos creando la factura";
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
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT f.id, sp.id AS idSesionParqueo, sp.fechaHoraIngreso, sp.fechaHoraSalida, ");
        sentenciaSql.append("t.id AS idTarifa, t.tarifa AS tarifa, f.valor_pagar, mp.id AS idMetodoPago, mp.nombre AS metodoPago ");
        sentenciaSql.append("FROM facturacion f ");
        sentenciaSql.append("INNER JOIN sesionparqueo sp ON sp.id = f.sesion_parqueo_id ");
        sentenciaSql.append("INNER JOIN tarifa t ON t.id = f.tarifa_id ");
        sentenciaSql.append("INNER JOIN metodopago mp ON mp.id = f.metodo_pago_id ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND f.id = ?");
            parametros.add(data.getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getSesionParqueo())
                && !ObjectHelper.getObjectHelper().isNull(data.getSesionParqueo().getId())
                && !data.getSesionParqueo().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND sp.id = ?");
            parametros.add(data.getSesionParqueo().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getDuracion())) {
            sentenciaSql.append(" AND EXTRACT(EPOCH FROM (sp.fechaHoraSalida - sp.fechaHoraIngreso)) * 1000 = ?");
            parametros.add(data.getDuracion().toMillis()); // Convertimos la duración a milisegundos
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTarifa())
                && !ObjectHelper.getObjectHelper().isNull(data.getTarifa().getId())
                && !data.getTarifa().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND t.id = ?");
            parametros.add(data.getTarifa().getId());
        }

        if (data.getValorPagar() != null) {
            sentenciaSql.append(" AND f.valor_pagar = ?");
            parametros.add(data.getValorPagar());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getMetodoPago())
                && !ObjectHelper.getObjectHelper().isNull(data.getMetodoPago().getId())
                && !data.getMetodoPago().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND mp.id = ?");
            parametros.add(data.getMetodoPago().getId());
        }

        final List<FacturacionEntitiy> facturaciones = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    FacturacionEntitiy facturacion = new FacturacionEntitiy();
                    facturacion.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    facturacion.setValorPagar(resultado.getBigDecimal("valor_pagar"));

                    SesionParqueoEntity sesionParqueo = SesionParqueoEntity.build();
                    sesionParqueo.setId(UUIDHelper.convertToUUID(resultado.getString("idSesionParqueo")));
                    sesionParqueo.setFechaHoraIngreso(resultado.getTimestamp("fechaHoraIngreso").toLocalDateTime());
                    sesionParqueo.setFechaHoraSalida(resultado.getTimestamp("fechaHoraSalida").toLocalDateTime());
                    facturacion.setSesionParqueo(sesionParqueo);

                    TarifaEntity tarifa = TarifaEntity.build();
                    tarifa.setId(UUIDHelper.convertToUUID(resultado.getString("idTarifa")));
                    tarifa.setTarifa(resultado.getLong("tarifa"));
                    facturacion.setTarifa(tarifa);

                    MetodoPagoEntity metodoPago = MetodoPagoEntity.build();
                    metodoPago.setId(UUIDHelper.convertToUUID(resultado.getString("idMetodoPago")));
                    metodoPago.setNombre(resultado.getString("metodoPago"));
                    facturacion.setMetodoPago(metodoPago);

                    facturaciones.add(facturacion);
                }
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la Facturación...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select de Facturación en la tabla \"Facturación\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la Facturación...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Select Facturación en la tabla \"Facturación\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return facturaciones;
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
