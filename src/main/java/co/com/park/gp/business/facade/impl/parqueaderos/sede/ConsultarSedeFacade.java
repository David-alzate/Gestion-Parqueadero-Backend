package co.com.park.gp.business.facade.impl.parqueaderos.sede;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.sede.ConsultarSedes;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

public class ConsultarSedeFacade implements FacadeWhitReturn<SedeDTO, List<SedeDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarSedeFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<SedeDTO> execute(final SedeDTO dto) {

        try {

            var useCase = new ConsultarSedes(daoFactory);
            var sedeDomain = SedeAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(sedeDomain);

            return SedeAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
