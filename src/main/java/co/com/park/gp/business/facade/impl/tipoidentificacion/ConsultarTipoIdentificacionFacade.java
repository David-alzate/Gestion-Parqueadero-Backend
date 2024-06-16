package co.com.park.gp.business.facade.impl.tipoidentificacion;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.TipoIdentificacionAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.tipoidentificacion.ConsultarTipoIdentificacion;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.TipoIdentificacionDTO;

public class ConsultarTipoIdentificacionFacade implements FacadeWhitReturn<TipoIdentificacionDTO, List<TipoIdentificacionDTO>>{
	
	private DAOFactory daoFactory;

    public ConsultarTipoIdentificacionFacade() {
        this.daoFactory = DAOFactory.getFactory();
    }

	@Override
	public List<TipoIdentificacionDTO> execute(TipoIdentificacionDTO dto) {
		 try {
	            var useCase = new ConsultarTipoIdentificacion(daoFactory);
	            var tipoIdentificacionDomain = TipoIdentificacionAssemblerDTO.getInstance().toDomain(dto);
	            var resultadosDomain = useCase.execute(tipoIdentificacionDomain);
	            return TipoIdentificacionAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
	        } catch (GPException exception) {
	            throw exception;
	        } catch (Exception exception) {
	            var mensajeUsuario = "Se ha presentado un problema al consultar la información de los tipos de identificacion.";
	            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar los tipos de identificacion.";
	            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
	        } finally {
	            daoFactory.cerrarConexion();
	        }
	    }


}
