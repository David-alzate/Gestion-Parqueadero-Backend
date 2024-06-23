package co.com.park.gp.data.dao.entity;

import co.com.park.gp.entity.SedeEntity;

import java.util.UUID;

public interface SedeDAO extends CreateDAO<SedeEntity>,RetriveDAO<SedeEntity>, UpdateDAO<SedeEntity>, DeleteDAO<UUID> {

}
