package co.com.park.gp.business.facade.impl.parqueaderos.sede;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.sede.RegistrarSede;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

public final class RegistrarSedeFacade implements FacadeWhitoutReturn<SedeDTO> {

    private final DAOFactory daoFactory;

    public RegistrarSedeFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(SedeDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new RegistrarSede(daoFactory);
            var sedeDomain = SedeAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(sedeDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034);

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
