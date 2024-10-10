package co.com.park.gp.business.facade.impl.facturacion.metodopago;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.facturacion.MetodoPagoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.facturacion.metodopago.ConsultarMetodoPago;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.facturacion.MetodoPagoDTO;

public class ConsultarMetodoPagoFacade implements FacadeWhitReturn<MetodoPagoDTO, List<MetodoPagoDTO>>{
	
	private final DAOFactory daoFactory;

    public ConsultarMetodoPagoFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

	@Override
	public List<MetodoPagoDTO> execute(MetodoPagoDTO dto) {
        try {
            var useCase = new ConsultarMetodoPago(daoFactory);
            var metodoPagoDomain = MetodoPagoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(metodoPagoDomain);
            return MetodoPagoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los metodos de pago.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los metodos de pago.";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
	}

}
