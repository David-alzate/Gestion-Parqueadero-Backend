package co.com.park.gp.data.dao.entity.concrete.postgresql.empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.empleados.EmpleadoDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

public class EmpleadoPostgresqlDAO extends SqlConnection implements EmpleadoDAO {

    public EmpleadoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(EmpleadoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO empleado (id, nombre, apellido, correoelectronico, ");
        sentenciaSql.append("sede_id, tipoidentificacion_id, tipoempleado_id, password, numeroidentificacion)");
        sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setString(2, data.getNombre());
            sentenciaSqlPreparada.setString(3, data.getApellido());
            sentenciaSqlPreparada.setString(4, data.getCorreoElectronico());
            sentenciaSqlPreparada.setObject(5, data.getSede().getId());
            sentenciaSqlPreparada.setObject(6, data.getTipoIdentificacion().getId());
            sentenciaSqlPreparada.setObject(7, data.getTipoEmpleado().getId());
            sentenciaSqlPreparada.setString(8, data.getPassword());
            sentenciaSqlPreparada.setLong(9, data.getNumeroIdentificacion());

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
    public List<EmpleadoEntity> consultar(EmpleadoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT e.id, e.numeroidentificacion, e.nombre, e.apellido, ");
        sentenciaSql.append("e.correoelectronico, e.password, s.id as idSede, s.nombresede as nombreSede, ");
        sentenciaSql.append("ti.id as idTipoIdentificacion, ti.nombre as nombreTipoIdentificacion, ");
        sentenciaSql.append("te.id as idTipoEmpleado, te.nombre as nombreTipoEmpleado ");
        sentenciaSql.append("FROM empleado e ");
        sentenciaSql.append("INNER JOIN tipoidentificacion ti ON e.tipoidentificacion_id = ti.id ");
        sentenciaSql.append("INNER JOIN tipoempleado te ON e.tipoempleado_id = te.id ");
        sentenciaSql.append("INNER JOIN sede s ON e.sede_id = s.id ");
        sentenciaSql.append("WHERE 1=1 ");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND e.id = ?");
            parametros.add(data.getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoEmpleado())
                && !TextHelper.isNullOrEmpty(data.getTipoEmpleado().getNombre())) {
            sentenciaSql.append(" AND te.nombre = ?");
            parametros.add(data.getTipoEmpleado().getNombre());
        }

        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND e.nombre = ?");
            parametros.add(data.getNombre());
        }

        if (data.getNumeroIdentificacion() != 0) {
            sentenciaSql.append(" AND e.numeroidentificacion = ?");
            parametros.add(data.getNumeroIdentificacion());
        }

        if (!TextHelper.isNullOrEmpty(data.getPassword())) {
            sentenciaSql.append(" AND e.password = ?");
            parametros.add(data.getPassword());
        }

        if (!TextHelper.isNullOrEmpty(data.getCorreoElectronico())) {
            sentenciaSql.append(" AND LOWER(e.correoelectronico) = ?");
            parametros.add(TextHelper.convertToLowercase(data.getCorreoElectronico()));
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getTipoEmpleado())
                && !ObjectHelper.getObjectHelper().isNull(data.getTipoEmpleado().getId())
                && !data.getTipoEmpleado().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND te.id = ?");
            parametros.add(data.getTipoEmpleado().getId());
        }

        if (!ObjectHelper.getObjectHelper().isNull(data.getSede())
                && !ObjectHelper.getObjectHelper().isNull(data.getSede().getId())
                && !data.getSede().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND s.id = ?");
            parametros.add(data.getSede().getId());
        }

        final List<EmpleadoEntity> empleados = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    EmpleadoEntity empleado = new EmpleadoEntity();
                    empleado.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    empleado.setNombre(resultado.getString("nombre"));
                    empleado.setApellido(resultado.getString("apellido"));
                    empleado.setNumeroIdentificacion(resultado.getLong("numeroidentificacion"));
                    empleado.setCorreoElectronico(resultado.getString("correoelectronico").toLowerCase());

                    empleado.setPassword(resultado.getString("password"));

                    SedeEntity sede = SedeEntity.build();
                    sede.setId(UUIDHelper.convertToUUID(resultado.getString("idSede")));
                    sede.setNombre(resultado.getString("nombreSede"));
                    empleado.setSede(sede);

                    TipoIdentificacionEntity tipoIdentificacion = TipoIdentificacionEntity.build();
                    tipoIdentificacion.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoIdentificacion")));
                    tipoIdentificacion.setNombre(resultado.getString("nombreTipoIdentificacion"));
                    empleado.setTipoIdentificacion(tipoIdentificacion);

                    TipoEmpleadoEntity tipoEmpleado = TipoEmpleadoEntity.build();
                    tipoEmpleado.setId(UUIDHelper.convertToUUID(resultado.getString("idTipoEmpleado")));
                    tipoEmpleado.setNombre(resultado.getString("nombreTipoEmpleado"));
                    empleado.setTipoEmpleado(tipoEmpleado);

                    empleados.add(empleado);
                }
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00086);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00086);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return empleados;
    }


    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM empleado WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el Empleado...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete del Empleado en la tabla \"Empleado\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar El Empleado...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete del Empleado en la tabla \"Empleado\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public void modificar(EmpleadoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE empleado SET nombre=?, apellido=?, correoelectronico=?, sede_id=?, tipoidentificacion_id=?,  ");
        sentenciaSql.append("tipoempleado_id=?, password=?, numeroidentificacion=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setString(1, data.getNombre());
            sentenciaSqlPreparada.setString(2, data.getApellido());
            sentenciaSqlPreparada.setString(3, data.getCorreoElectronico());
            sentenciaSqlPreparada.setObject(4, data.getSede().getId());
            sentenciaSqlPreparada.setObject(5, data.getTipoIdentificacion().getId());
            sentenciaSqlPreparada.setObject(6, data.getTipoEmpleado().getId());
            sentenciaSqlPreparada.setString(7, data.getPassword());
            sentenciaSqlPreparada.setLong(8, data.getNumeroIdentificacion());
            sentenciaSqlPreparada.setObject(9, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Empleado...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update del Empleado en la tabla \"Empleado\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Empleado...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update Empleado en la tabla \"Empleado\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }
}
