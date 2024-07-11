package co.com.park.gp.data.dao.entity.concrete.postgresql.vehiculos;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.vehiculos.VehiculoDAO;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class VehiculoPostgresqlDAO extends SqlConnection implements VehiculoDAO {

    public VehiculoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(VehiculoEntity data) {

    }

    @Override
    public void eliminar(UUID data) {

    }

    @Override
    public List<VehiculoEntity> consultar(VehiculoEntity data) {
        return List.of();
    }

    @Override
    public void modificar(VehiculoEntity data) {

    }
}
