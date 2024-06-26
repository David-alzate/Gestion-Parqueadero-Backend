package co.com.park.gp.data.dao.entity.concrete.postgresql.parqueaderos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.parqueaderos.ParqueaderoDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;

public class ParqueaderoPostgresqlDAO extends SqlConnection implements ParqueaderoDAO {

    public ParqueaderoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<ParqueaderoEntity> consultar(ParqueaderoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT p.id, p.nombre");
        sentenciaSql.append(" FROM parqueadero p");
        sentenciaSql.append(" WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND p.id = ?");
            parametros.add(data.getId());
        }

        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND p.nombre = ?");
            parametros.add(data.getNombre());
        }

        final List<ParqueaderoEntity> parqueaderos = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    ParqueaderoEntity parqueadero = ParqueaderoEntity.build();
                    parqueadero.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    parqueadero.setNombre(resultado.getString("nombre"));

                    parqueaderos.add(parqueadero);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return parqueaderos;
    }

    @Override
    public void crear(ParqueaderoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("INSERT INTO parqueadero (id, nombre)");
        sentenciaSql.append("VALUES (?, ?)");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, data.getId());
            sentenciaSqlPreparada.setString(2, data.getNombre());

            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00070);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00070);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

    }

    @Override
    public void eliminar(UUID id) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("DELETE FROM parqueadero WHERE id = ?");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setObject(1, id);
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar El parqueadero...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Delete de la sede en la tabla \"Parqueadero\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar el parqueadero...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Delete de la sede en la tabla \"Parqueadero\" de la base de datoss.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }


    @Override
    public void modificar(ParqueaderoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("UPDATE parqueadero SET nombre=? WHERE id=? ");

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {

            sentenciaSqlPreparada.setString(1, data.getNombre());
            sentenciaSqlPreparada.setObject(2, data.getId());
            sentenciaSqlPreparada.executeUpdate();

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Parqueadero...";
            var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el Update de la sede en la tabla \"Parqueadero\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar el Parqueadero...";
            var mensajeTecnico = "Se ha presentado una INESPERADO tratando de realizar el Update de la sede en la tabla \"Parqueadero\" de la base de datos.";
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }
    }
}
