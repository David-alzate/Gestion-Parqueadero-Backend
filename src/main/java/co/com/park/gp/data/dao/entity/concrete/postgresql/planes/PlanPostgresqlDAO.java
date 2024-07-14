package co.com.park.gp.data.dao.entity.concrete.postgresql.planes;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.planes.PlanDAO;
import co.com.park.gp.entity.planes.PlanEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class PlanPostgresqlDAO extends SqlConnection implements PlanDAO {

    public PlanPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(PlanEntity data) {

    }

    @Override
    public void eliminar(UUID data) {

    }

    @Override
    public List<PlanEntity> consultar(PlanEntity data) {
        return List.of();
    }

    @Override
    public void modificar(PlanEntity data) {

    }
}
