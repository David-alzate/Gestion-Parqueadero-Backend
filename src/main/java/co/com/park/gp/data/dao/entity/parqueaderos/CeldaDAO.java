package co.com.park.gp.data.dao.entity.parqueaderos;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.parqueaderos.CeldaEntity;

import java.util.UUID;

public interface CeldaDAO extends RetriveDAO<CeldaEntity>, CreateDAO<CeldaEntity>, UpdateDAO<CeldaEntity>, DeleteDAO<UUID> {
    int celdasDisponibles(UUID idSede, UUID idTipoVehiculo);
}
