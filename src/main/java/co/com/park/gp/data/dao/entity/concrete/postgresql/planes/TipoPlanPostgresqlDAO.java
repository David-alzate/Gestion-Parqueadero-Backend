package co.com.park.gp.data.dao.entity.concrete.postgresql.planes;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.planes.TipoPlanDAO;
import co.com.park.gp.entity.planes.PlanEntity;

import java.sql.Connection;
import java.util.List;

public class TipoPlanPostgresqlDAO extends SqlConnection implements TipoPlanDAO {

    public TipoPlanPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public List<PlanEntity> consultar(PlanEntity data) {
        return List.of();
    }
}
