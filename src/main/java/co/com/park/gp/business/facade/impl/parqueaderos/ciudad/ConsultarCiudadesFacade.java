package co.com.park.gp.business.facade.impl.parqueaderos.ciudad;

import java.util.List;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.CiudadAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.ciudad.ConsultarCiudades;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.CiudadDTO;

public class ConsultarCiudadesFacade implements FacadeWhitReturn<CiudadDTO, List<CiudadDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarCiudadesFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<CiudadDTO> execute(final CiudadDTO dto) {

        try {
            var usecase = new ConsultarCiudades(daoFactory);
            var ciudadDomain = CiudadAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = usecase.execute(ciudadDomain);
            return CiudadAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);

        } catch (final GPException exception) {
            throw exception;
        } catch (final Exception exception) {

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
