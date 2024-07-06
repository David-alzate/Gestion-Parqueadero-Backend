package co.com.park.gp.data.dao.entity.concrete.postgresql.tarifas;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.tarifas.TarifaDAO;
import co.com.park.gp.entity.comunes.TipoVehiculoEntity;
import co.com.park.gp.entity.parqueaderos.*;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.tarifas.TarifaEntity;
import co.com.park.gp.entity.tarifas.TipoTarifaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TarifaPostgresqlDAO extends SqlConnection implements TarifaDAO {

    public TarifaPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(TarifaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("INSERT INTO tarifa (id, sede_id, tipovehiculo_id, tipotarifa_id, ");
        sentenciaSql.append("tarifa, estado_id, fechainiciovigencia, fechafinvigencia) ");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getSede().getId());
            sentenciaSqlPreparada.setObject(3, data.getTipoVehiculo().getId());
            sentenciaSqlPreparada.setObject(4, data.getTipoTarifa().getId());
            sentenciaSqlPreparada.setLong(5, data.getTarifa());
            sentenciaSqlPreparada.setObject(6, data.getEstado().getId());
            sentenciaSqlPreparada.setObject(7, data.getFechaInicioVigencia());
            sentenciaSqlPreparada.setObject(8, data.getFechaFinVigencia());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro de la Tarifa...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la tarifa en la tabla \"tarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar un registro de la Tarifa...";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert de la tarifa en la tabla \"tarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM tarifa WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la tarifa...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete de la tarifa en la tabla \"tarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la Tarifa...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete de la Tarifa en la tabla \"tarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public List<TarifaEntity> consultar(TarifaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("select t.id, s.id as idSede, s.nombreSede as nombreSede, ");
        sentenciaSql.append("tv.id as idTipoVehiculo, tv.tipoVehiculo as tipoVehiculo, ");
        sentenciaSql.append("tt.id as idTipoTarifa, tt.tipotarifa as tipoTarifa, t.tarifa, ");
        sentenciaSql.append("e.id as idEstado, e.estado as estado, t.fechainiciovigencia, t.fechafinvigencia ");
        sentenciaSql.append("from tarifa t ");
        sentenciaSql.append("inner join sede s on s.id = t.sede_id ");
        sentenciaSql.append("inner join tipovehiculo tv on tv.id = t.tipovehiculo_id ");
        sentenciaSql.append("inner join tipotarifa tt on tt.id = t.tipotarifa_id ");
        sentenciaSql.append("inner join estado e on e.id = t.estado_id ");
        sentenciaSql.append("WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND t.id = ?");
            parametros.add(data.getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getSede()) && !ObjectHelper.getObjectHelper().isNull(data.getSede().getId()) && !data.getSede().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND s.id = ?");
            parametros.add(data.getSede().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo()) && !ObjectHelper.getObjectHelper().isNull(data.getTipoVehiculo().getId()) && !data.getTipoVehiculo().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND tv.id = ?");
            parametros.add(data.getTipoVehiculo().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoTarifa()) && !ObjectHelper.getObjectHelper().isNull(data.getTipoTarifa().getId()) && !data.getTipoTarifa().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND tt.id = ?");
            parametros.add(data.getTipoTarifa().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getEstado()) && !ObjectHelper.getObjectHelper().isNull(data.getEstado().getId()) && !data.getEstado().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND e.id = ?");
            parametros.add(data.getEstado().getId());
        }

        final List<TarifaEntity> tarifas = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    TarifaEntity tarifa = new TarifaEntity();
                    tarifa.setId(UUIDHelper.convertToUUID(resultado.getString("id")));

                    SedeEntity sede = new SedeEntity();
                    sede.setId(UUIDHelper.convertToUUID(resultado.getString("idSede")));
                    sede.setNombre(resultado.getString("nombreSede"));
                    tarifa.setSede(sede);

                    TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntity();
                    tipoVehiculo.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoVehiculo")));
                    tipoVehiculo.setTipoVehiculo(resultado.getString("tipoVehiculo"));
                    tarifa.setTipoVehiculo(tipoVehiculo);

                    TipoTarifaEntity tipoTarifa = new TipoTarifaEntity();
                    tipoTarifa.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoTarifa")));
                    tipoTarifa.setTipoTarifa(resultado.getString("tipoTarifa"));
                    tarifa.setTipoTarifa(tipoTarifa);

                    tarifa.setTarifa(resultado.getLong("tarifa"));

                    EstadoEntity estado = new EstadoEntity();
                    estado.setId(UUIDHelper.convertToUUID(resultado.getString("idEstado")));
                    estado.setEstado(resultado.getString("estado"));
                    tarifa.setEstado(estado);

                    java.sql.Date sqlFechaInicio = resultado.getDate("fechainiciovigencia");
                    if (sqlFechaInicio != null) {
                        tarifa.setFechaInicioVigencia(sqlFechaInicio.toLocalDate());
                    }

                    java.sql.Date sqlFechaFin = resultado.getDate("fechafinvigencia");
                    if (sqlFechaFin != null) {
                        tarifa.setFechaFinVigencia(sqlFechaFin.toLocalDate());
                    }

                    tarifas.add(tarifa);
                }
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de las tarifas";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de las tarifas en la tabla 'tarifas' de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO al consultar la información de las tarifas";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADO tratando de realizar la consulta de las tarifas en la tabla 'tarifas' de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return tarifas;
    }

    @Override
    public void modificar(TarifaEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE tarifa SET sede_id=?, tipovehiculo_id=?, tipotarifa_id=?, tarifa=?, ");
        sentenciaSql.append("estado_id=?, fechainiciovigencia=?, fechafinvigencia=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getSede().getId());
            sentenciaSqlPreparada.setObject(2, data.getTipoVehiculo().getId());
            sentenciaSqlPreparada.setObject(3, data.getTipoTarifa().getId());
            sentenciaSqlPreparada.setLong(4, data.getTarifa());
            sentenciaSqlPreparada.setObject(5, data.getEstado().getId());
            sentenciaSqlPreparada.setObject(6, data.getFechaInicioVigencia());
            sentenciaSqlPreparada.setObject(7, data.getFechaFinVigencia());
            sentenciaSqlPreparada.setObject(8, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la tarifa...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update de la tarifa en la tabla \"tarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la tarifa...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update de la sede en la tabla \"tarifa\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    }
