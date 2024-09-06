package co.com.park.gp.data.dao.entity.facturacion;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.facturacion.FacturacionEntitiy;

import java.util.UUID;

public interface FacturacionDAO extends CreateDAO<FacturacionEntitiy>, RetriveDAO<FacturacionEntitiy>, UpdateDAO<FacturacionEntitiy>, DeleteDAO<UUID> {
}
