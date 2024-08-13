package co.com.park.gp.data.dao.entity.concrete.postgresql.parqueaderos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.crosscutting.exceptions.custom.DataGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.entity.parqueaderos.DepartamentoDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.parqueaderos.DepartamentoEntity;
import co.com.park.gp.entity.parqueaderos.PaisEntity;

public class DepartamentoPostgresqlDAO extends SqlConnection implements DepartamentoDAO {

    public DepartamentoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<DepartamentoEntity> consultar(DepartamentoEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();
        sentenciaSql.append("SELECT d.id, d.nombre, p.id as idPais, p.nombre as nombrePais");
        sentenciaSql.append(" FROM departamento d");
        sentenciaSql.append(" INNER JOIN pais p ON d.pais_id = p.id");
        sentenciaSql.append(" WHERE 1=1");

        final List<Object> parametros = new ArrayList<>();

        if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND d.id = ?");
            parametros.add(data.getId());
        }
        if (!TextHelper.isNullOrEmpty(data.getNombre())) {
            sentenciaSql.append(" AND d.nombre = ?");
            parametros.add(data.getNombre());
        }
        if (!ObjectHelper.getObjectHelper().isNull(data.getPais())
                && !ObjectHelper.getObjectHelper().isNull(data.getPais().getId())
                && !data.getPais().getId().equals(UUIDHelper.getDefault())) {
            sentenciaSql.append(" AND d.pais_id = ?");
            parametros.add(data.getPais().getId());
        }

        final List<DepartamentoEntity> departamentos = new ArrayList<>();

        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
            }

            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                while (resultado.next()) {
                    DepartamentoEntity departamento = DepartamentoEntity.build();
                    departamento.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
                    departamento.setNombre(resultado.getString("nombre"));

                    PaisEntity pais = PaisEntity.build();
                    pais.setId(UUIDHelper.convertToUUID(resultado.getString("idPais")));
                    pais.setNombre(resultado.getString("nombrePais"));

                    departamento.setPais(pais);

                    departamentos.add(departamento);
                }
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
            throw new DataGPException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return departamentos;
    }

}
