package co.com.park.gp.business.facade.impl.facturacion.metodopago;

import java.util.List;

import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;

public class ConsultarMetodoPagoFacade implements FacadeWhitReturn<MetodoPagoDTO, List<MetodoPagoDTO>>{
	
	private final DAOFactory daoFactory;

    public ConsultarMetodoPagoFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

	@Override
	public List<MetodoPagoDTO> execute(MetodoPagoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
