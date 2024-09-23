package co.com.park.gp.data.dao.entity.concrete.postgresql.planes;

import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.planes.PlanDAO;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.planes.PlanEntity;
import co.com.park.gp.entity.planes.TipoPlanEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlanPostgresqlDAO extends SqlConnection implements PlanDAO {

    private final DAOFactory factory;

    public PlanPostgresqlDAO(final Connection conexion, final DAOFactory factory) {
        super(conexion);
        this.factory = factory;
    }

    @Override
    public void crear(PlanEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        sentenciaSql.append("INSERT INTO plan (id, sede_id, vehiculo_id, cliente_id, ");
        sentenciaSql.append("tipoplan_id, fechainicio, fechafin, estado_id)");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?, ?,? )");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getSede().getId());
            sentenciaSqlPreparada.setObject(3, data.getVehiculo().getId());
            sentenciaSqlPreparada.setObject(4, data.getCliente().getId());
            sentenciaSqlPreparada.setObject(5, data.getTipoPlan().getId());
            sentenciaSqlPreparada.setObject(6, data.getFechaInicio());
            sentenciaSqlPreparada.setObject(7, data.getFechaFin());
            sentenciaSqlPreparada.setObject(8, estadoActivo.getId());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro del plan...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert del plan en la tabla \"plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar un registro del plan...";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert del plan en la tabla \"plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM plan WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el plan...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete del plan en la tabla \"plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar El plan...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete del plan en la tabla \"plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public List<PlanEntity> consultar(PlanEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT p.id, s.id as idSede, s.nombresede as nombreSede, ");
        sentenciaSql.append("v.id as idVehiculo, v.placa as placaVehiculo, v.tipovehiculo_id as idTipoVehiculo, ");
        sentenciaSql.append("c.id as idCliente, c.nombre as nombreCliente, c.numeroidentificacion as numeroIdentificacionCliente, ");
        sentenciaSql.append("tp.id as idTipoPlan, tp.nombre as tipoPlanNombre, ");
        sentenciaSql.append("p.fechainicio, p.fechafin, ");
        sentenciaSql.append("es.id as idEstado, es.estado as Estado ");
        sentenciaSql.append("FROM plan p ");
        sentenciaSql.append("INNER JOIN sede s ON s.id = p.sede_id ");
        sentenciaSql.append("INNER JOIN vehiculo v ON v.id = p.vehiculo_id ");
        sentenciaSql.append("INNER JOIN cliente c ON c.id = p.cliente_id ");
        sentenciaSql.append("INNER JOIN tipoplan tp ON tp.id = p.tipoplan_id ");
        sentenciaSql.append("INNER JOIN estado es ON es.id = p.estado_id ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND p.id = ?");
            parametros.add(data.getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getSede()) && !ObjectHelper.getObjectHelper().isNull(data.getSede().getId()) && !data.getSede().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND s.id = ?");
            parametros.add(data.getSede().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getVehiculo()) && !ObjectHelper.getObjectHelper().isNull(data.getVehiculo().getId()) && !data.getVehiculo().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND v.id = ?");
            parametros.add(data.getVehiculo().getId());
        }

        if (!TextHelper.isNullOrEmpty(data.getVehiculo().getPlaca())) {
            sentenciaSql.append(" AND v.placa = ?");
            parametros.add(data.getVehiculo().getPlaca());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getCliente()) && !ObjectHelper.getObjectHelper().isNull(data.getCliente().getId()) && !data.getCliente().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND c.id = ?");
            parametros.add(data.getCliente().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoPlan()) && !ObjectHelper.getObjectHelper().isNull(data.getTipoPlan().getId()) && !data.getTipoPlan().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND tp.id = ?");
            parametros.add(data.getTipoPlan().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getEstado()) && !ObjectHelper.getObjectHelper().isNull(data.getEstado().getId()) && !data.getEstado().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND es.id = ?");
            parametros.add(data.getEstado().getId());
        }

        final List<PlanEntity> planes = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    PlanEntity plan = PlanEntity.build();
                    plan.setId(UUIDHelper.convertToUUID(resultado.getString("id")));

                    SedeEntity sede = SedeEntity.build();
                    sede.setId(UUIDHelper.convertToUUID(resultado.getString("idSede")));
                    sede.setNombre(resultado.getString("nombreSede"));
                    plan.setSede(sede);

                    VehiculoEntity vehiculo = VehiculoEntity.build();
                    vehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("idVehiculo")));
                    vehiculo.setPlaca(resultado.getString("placaVehiculo"));
                    vehiculo.setTipoVehiculo(TipoVehiculoEntity.build().setId(UUIDHelper.convertToUUID(resultado.getString("idTipoVehiculo"))));
                    plan.setVehiculo(vehiculo);

                    ClienteEntity cliente = ClienteEntity.build();
                    cliente.setId(UUIDHelper.convertToUUID(resultado.getString("idCliente")));
                    cliente.setNombre(resultado.getString("nombreCliente"));
                    cliente.setNumeroIdentificacion(resultado.getLong("numeroIdentificacionCliente"));
                    plan.setCliente(cliente);

                    TipoPlanEntity tipoPlan = TipoPlanEntity.build();
                    tipoPlan.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoPlan")));
                    tipoPlan.setNombre(resultado.getString("tipoPlanNombre"));
                    plan.setTipoPlan(tipoPlan);

                    EstadoEntity estado = EstadoEntity.build();
                    estado.setId(UUIDHelper.convertToUUID(resultado.getString("idEstado")));
                    estado.setEstado(resultado.getString("Estado"));
                    plan.setEstado(estado);

                    java.sql.Date sqlFechaInicio = resultado.getDate("fechainicio");
                    if (sqlFechaInicio != null) {
                        plan.setFechaInicio(sqlFechaInicio.toLocalDate());
                    }

                    java.sql.Date sqlFechaFin = resultado.getDate("fechafin");
                    if (sqlFechaFin != null) {
                        plan.setFechaFin(sqlFechaFin.toLocalDate());
                    }

                    planes.add(plan);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Plan...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select del Plan en la tabla \"Plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Plan...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Select Plan en la tabla \"Plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return planes;
    }


    @Override
    public void modificar(PlanEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE plan SET sede_id=?, vehiculo_id=?, cliente_id=?,  ");
        sentenciaSql.append("tipoplan_id=?, fechainicio=?, fechafin=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getSede().getId());
            sentenciaSqlPreparada.setObject(2, data.getVehiculo().getId());
            sentenciaSqlPreparada.setObject(3, data.getCliente().getId());
            sentenciaSqlPreparada.setObject(4, data.getTipoPlan().getId());
            sentenciaSqlPreparada.setObject(5, data.getFechaInicio());
            sentenciaSqlPreparada.setObject(6, data.getFechaFin());
            sentenciaSqlPreparada.setObject(7, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el plan...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update del plan en la tabla \"plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el plan...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update plan en la tabla \"plan\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }
}
