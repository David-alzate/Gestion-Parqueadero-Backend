package co.com.park.gp.data.dao.entity.parqueaderos;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

import java.util.UUID;

public interface SedeDAO extends CreateDAO<SedeEntity>, RetriveDAO<SedeEntity>, UpdateDAO<SedeEntity>, DeleteDAO<UUID> {

}
