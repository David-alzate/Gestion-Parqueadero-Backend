package co.com.park.gp.data.dao.entity.empleados;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.empleados.EmpleadoEntity;

import java.util.UUID;

public interface EmpleadoDAO extends CreateDAO<EmpleadoEntity>, RetriveDAO<EmpleadoEntity>, UpdateDAO<EmpleadoEntity>, DeleteDAO<UUID> {

}
