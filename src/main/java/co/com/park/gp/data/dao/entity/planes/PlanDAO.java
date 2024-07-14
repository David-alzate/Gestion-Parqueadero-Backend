package co.com.park.gp.data.dao.entity.planes;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.planes.PlanEntity;

import java.util.UUID;

public interface PlanDAO extends CreateDAO<PlanEntity>, RetriveDAO<PlanEntity>,UpdateDAO<PlanEntity>, DeleteDAO<UUID>{
}
