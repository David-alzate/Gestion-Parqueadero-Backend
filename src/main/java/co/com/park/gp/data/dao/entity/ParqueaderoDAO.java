package co.com.park.gp.data.dao.entity;

import co.com.park.gp.entity.ParqueaderoEntity;

import java.util.UUID;

public interface ParqueaderoDAO extends RetriveDAO<ParqueaderoEntity>, CreateDAO<ParqueaderoEntity>, UpdateDAO<ParqueaderoEntity>, DeleteDAO<UUID>{

}
