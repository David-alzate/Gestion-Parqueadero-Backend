package co.com.park.gp.business.facade.impl.parqueaderos.parqueadero;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.ParqueaderoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.parqueadero.ConsultarParqueaderos;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.ParqueaderoDTO;

public class ConsultarParqueaderosFacade implements FacadeWhitReturn<ParqueaderoDTO, List<ParqueaderoDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarParqueaderosFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<ParqueaderoDTO> execute(final ParqueaderoDTO dto) {
        try {

            var useCase = new ConsultarParqueaderos(daoFactory);
            var parqueaderoDomain = ParqueaderoAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = useCase.execute(parqueaderoDomain);

            return ParqueaderoAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}