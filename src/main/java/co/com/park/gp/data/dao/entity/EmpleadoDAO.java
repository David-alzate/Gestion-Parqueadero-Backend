package co.com.park.gp.data.dao.entity;

import co.com.park.gp.entity.EmpleadoEntity;

import java.util.UUID;

public interface EmpleadoDAO extends CreateDAO<EmpleadoEntity>, RetriveDAO<EmpleadoEntity>, UpdateDAO<EmpleadoEntity>, DeleteDAO<UUID>{

}
