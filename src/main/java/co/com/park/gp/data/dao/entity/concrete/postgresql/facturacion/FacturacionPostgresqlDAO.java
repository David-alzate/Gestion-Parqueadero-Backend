package co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.facturacion.FacturacionDAO;
import co.com.park.gp.entity.facturacion.FacturacionEntitiy;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class FacturacionPostgresqlDAO extends SqlConnection implements FacturacionDAO {

    public FacturacionPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(FacturacionEntitiy data) {

    }

    @Override
    public void eliminar(UUID data) {

    }

    @Override
    public List<FacturacionEntitiy> consultar(FacturacionEntitiy data) {
        return List.of();
    }

    @Override
    public void modificar(FacturacionEntitiy data) {

    }
}
