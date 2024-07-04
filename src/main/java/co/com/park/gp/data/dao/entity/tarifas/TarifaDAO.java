package co.com.park.gp.data.dao.entity.tarifas;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.tarifas.TarifaEntity;

import java.util.UUID;

public interface TarifaDAO extends CreateDAO<TarifaEntity>, RetriveDAO<TarifaEntity>, UpdateDAO<TarifaEntity>, DeleteDAO<UUID> {
}
