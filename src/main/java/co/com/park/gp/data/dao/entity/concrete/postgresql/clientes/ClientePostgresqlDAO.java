package co.com.park.gp.data.dao.entity.concrete.postgresql.clientes;

import co.com.park.gp.data.dao.entity.clientes.ClienteDAO;
import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.entity.clientes.ClienteEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class ClientePostgresqlDAO extends SqlConnection implements ClienteDAO {

    public ClientePostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ClienteEntity data) {

    }

    @Override
    public void eliminar(UUID data) {

    }

    @Override
    public List<ClienteEntity> consultar(ClienteEntity data) {
        return List.of();
    }

    @Override
    public void modificar(ClienteEntity data) {

    }
}
