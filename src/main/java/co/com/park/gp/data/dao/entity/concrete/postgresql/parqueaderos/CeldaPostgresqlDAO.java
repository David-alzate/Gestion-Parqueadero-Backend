package co.com.park.gp.data.dao.entity.concrete.postgresql.parqueaderos;

import co.com.park.gp.data.dao.entity.concrete.SqlConnection;
import co.com.park.gp.data.dao.entity.parqueaderos.CeldaDAO;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class CeldaPostgresqlDAO extends SqlConnection implements CeldaDAO {

    public CeldaPostgresqlDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(CeldaEntity data) {

    }

    @Override
    public void eliminar(UUID data) {

    }

    @Override
    public List<CeldaEntity> consultar(CeldaEntity data) {
        return List.of();
    }

    @Override
    public void modificar(CeldaEntity data) {

    }
}
