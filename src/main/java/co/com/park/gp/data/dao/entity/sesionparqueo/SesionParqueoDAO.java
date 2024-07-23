package co.com.park.gp.data.dao.entity.sesionparqueo;

import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;

import java.util.UUID;

public interface SesionParqueoDAO extends IngresarVehiculoDAO<SesionParqueoEntity>, SalidaVehiculoDAO<SesionParqueoEntity>,
        RetriveDAO<SesionParqueoEntity>, DeleteDAO<UUID>, UpdateDAO<SesionParqueoEntity> {
}
