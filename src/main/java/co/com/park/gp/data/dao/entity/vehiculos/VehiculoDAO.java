package co.com.park.gp.data.dao.entity.vehiculos;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.util.UUID;

public interface VehiculoDAO extends CreateDAO<VehiculoEntity>, RetriveDAO<VehiculoEntity>, UpdateDAO<VehiculoEntity>, DeleteDAO<UUID> {
}
