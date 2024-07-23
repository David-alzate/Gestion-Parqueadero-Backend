package co.com.park.gp.data.dao.entity.concrete.postgresql.sesionparqueo;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.sesionparqueo.SesionParqueoDAO;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class SesionParqueoPostgresqlDAO extends SqlConnection implements SesionParqueoDAO {

    public SesionParqueoPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void ingresarVehiculo(SesionParqueoEntity data) {

    }

    @Override
    public void salidaVehiculo(SesionParqueoEntity data) {

    }

    @Override
    public void eliminar(UUID data) {

    }

    @Override
    public List<SesionParqueoEntity> consultar(SesionParqueoEntity data) {
        return List.of();
    }

    @Override
    public void modificar(SesionParqueoEntity data) {

    }
}
