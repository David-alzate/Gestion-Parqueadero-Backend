package co.com.park.gp.data.dao.factory;

import co.com.park.gp.data.dao.entity.CiudadDAO;
import co.com.park.gp.data.dao.entity.DepartamentoDAO;
import co.com.park.gp.data.dao.entity.EmpleadoDAO;
import co.com.park.gp.data.dao.entity.PaisDAO;
import co.com.park.gp.data.dao.entity.ParqueaderoDAO;
import co.com.park.gp.data.dao.entity.SedeDAO;
import co.com.park.gp.data.dao.entity.TipoEmpleadoDAO;
import co.com.park.gp.data.dao.entity.TipoIdentificacionDAO;
import co.com.park.gp.data.dao.entity.TipoSedeDAO;
import co.com.park.gp.data.dao.factory.concrete.PostgresqlDAOFactory;

public interface DAOFactory {

	static DAOFactory getFactory() {
		return new PostgresqlDAOFactory();
	}

	void cerrarConexion();

	void iniciarTransaccion();

	void confirmarTransaccion();

	void cancelarTransaccion();

	PaisDAO getPaisDAO();

	DepartamentoDAO getDepartamentoDAO();

	CiudadDAO getCiudadDAO();

	ParqueaderoDAO getParqueaderoDAO();

	SedeDAO getSedeDAO();

	TipoSedeDAO getTipoSedeDAO();

	EmpleadoDAO getEmpleadoDAO();
	
	TipoEmpleadoDAO getTipoEmpleadoDAO();
	
	TipoIdentificacionDAO geTipoIdentificacionDAO();

}
