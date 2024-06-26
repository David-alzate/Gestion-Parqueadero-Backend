package co.com.park.gp.data.dao.entity.parqueaderos;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;

import java.util.UUID;

public interface ParqueaderoDAO extends RetriveDAO<ParqueaderoEntity>, CreateDAO<ParqueaderoEntity>, UpdateDAO<ParqueaderoEntity>, DeleteDAO<UUID> {

}
