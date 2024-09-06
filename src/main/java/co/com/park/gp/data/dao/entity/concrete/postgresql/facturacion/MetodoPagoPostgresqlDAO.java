package co.com.park.gp.data.dao.entity.concrete.postgresql.facturacion;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.facturacion.MetodoPagoDAO;
import co.com.park.gp.entity.facturacion.MetodoPagoEntity;

import java.sql.Connection;
import java.util.List;

public class MetodoPagoPostgresqlDAO extends SqlConnection implements MetodoPagoDAO {

    public MetodoPagoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<MetodoPagoEntity> consultar(MetodoPagoEntity data) {
        return List.of();
    }
}
