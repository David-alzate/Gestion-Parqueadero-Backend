package co.com.park.gp.data.dao.entity.concrete.postgresql.clientes;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.clientes.ClienteDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientePostgresqlDAO extends SqlConnection implements ClienteDAO {

    public ClientePostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ClienteEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO cliente (id, tipoidentificacion_id, numeroidentificacion, ");
        sentenciaSql.append("nombre, apellido, correoelectronico)");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setObject(2, data.getTipoIdentificacion().getId());
            sentenciaSqlPreparada.setLong(3, data.getNumeroIdentificacion());
            sentenciaSqlPreparada.setString(4, data.getNombre());
            sentenciaSqlPreparada.setString(5, data.getApellido());
            sentenciaSqlPreparada.setString(6, data.getCorreoElectronico());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar un registro del cliente...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert del cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de llevar un registro del cliente...";
            var mensajeTecnico = "Se ha presentado una SQLException INESPERADA tratando de realizar el insert del cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM cliente WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el Cliente...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete del cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar El Cliente...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete del Cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public List<ClienteEntity> consultar(ClienteEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT c.id, ti.id as idTipoIdentificacion, ti.nombre as tipoIdentificacion, ");
        sentenciaSql.append("c.numeroidentificacion, c.nombre, c.apellido, c.correoelectronico ");
        sentenciaSql.append("FROM cliente c ");
        sentenciaSql.append("INNER JOIN tipoidentificacion ti ON ti.id = c.tipoidentificacion_id ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND c.id = ?");
            parametros.add(data.getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoIdentificacion())
                && !ObjectHelper.getObjectHelper().isNull(data.getTipoIdentificacion().getId())
                && !data.getTipoIdentificacion().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND ti.id = ?");
            parametros.add(data.getTipoIdentificacion().getId());
        }

        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND c.nombre = ?");
            parametros.add(data.getNombre());
        }

        if (!TextHelper.isNullOrEmpty(data.getApellido())) {
            sentenciaSql.append(" AND c.apellido = ?");
            parametros.add(data.getApellido());
        }

        if ((data.getNumeroIdentificacion() != 0)) {
            sentenciaSql.append(" AND c.numeroidentificacion = ?");
            parametros.add(data.getNumeroIdentificacion());
        }

        if (!TextHelper.isNullOrEmpty(data.getCorreoElectronico())) {
            sentenciaSql.append(" AND LOWER(c.correoelectronico) = ?");
            parametros.add(TextHelper.convertToLowercase(data.getCorreoElectronico()));
        }

        final List<ClienteEntity> clientes = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    ClienteEntity cliente = new ClienteEntity();
                    cliente.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    cliente.setNumeroIdentificacion(resultado.getLong("numeroidentificacion"));
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido(resultado.getString("apellido"));
                    cliente.setCorreoElectronico(resultado.getString("correoelectronico").toLowerCase());

                    TipoIdentificacionEntity tipoIdentificacion = TipoIdentificacionEntity.build();
                    tipoIdentificacion.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoIdentificacion")));
                    tipoIdentificacion.setNombre(resultado.getString("tipoIdentificacion"));
                    cliente.setTipoIdentificacion(tipoIdentificacion);

                    clientes.add(cliente);
                }
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Cliente...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el select del Cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar el Cliente...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Select Cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return clientes;
    }


    @Override
    public void modificar(ClienteEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE cliente SET tipoidentificacion_id=?, numeroidentificacion=?,  ");
        sentenciaSql.append("nombre=?, apellido=?, correoelectronico=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getTipoIdentificacion().getId());
            sentenciaSqlPreparada.setLong(2, data.getNumeroIdentificacion());
            sentenciaSqlPreparada.setString(3, data.getNombre());
            sentenciaSqlPreparada.setString(4, data.getApellido());
            sentenciaSqlPreparada.setString(5, data.getCorreoElectronico());
            sentenciaSqlPreparada.setObject(6, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Cliente...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update del Cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Cliente...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update Cliente en la tabla \"Cliente\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }

    }

