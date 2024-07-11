package co.com.park.gp.data.dao.entity.clientes;

import co.com.park.gp.data.dao.entity.crud.CreateDAO;
import co.com.park.gp.data.dao.entity.crud.DeleteDAO;
import co.com.park.gp.data.dao.entity.crud.RetriveDAO;
import co.com.park.gp.data.dao.entity.crud.UpdateDAO;
import co.com.park.gp.entity.clientes.ClienteEntity;

import java.util.UUID;

public interface ClienteDAO extends CreateDAO<ClienteEntity>, RetriveDAO<ClienteEntity>, UpdateDAO<ClienteEntity>, DeleteDAO<UUID> {

}
