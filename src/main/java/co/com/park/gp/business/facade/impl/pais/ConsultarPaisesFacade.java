package co.com.park.gp.business.facade.impl.pais;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.PaisAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.pais.ConsultarPaises;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.PaisDTO;

public class ConsultarPaisesFacade implements FacadeWhitReturn<PaisDTO, List<PaisDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarPaisesFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<PaisDTO> execute(PaisDTO dto) {
        try {
            var useCase = new ConsultarPaises(daoFactory);
            var paisDomain = PaisAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(paisDomain);
            return PaisAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (GPException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00028);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
