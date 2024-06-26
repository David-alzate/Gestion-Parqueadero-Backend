package co.com.park.gp.business.facade.impl.parqueaderos.sede;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.sede.ModificarSede;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

public class ModificarSedeFacade implements FacadeWhitoutReturn<SedeDTO> {

    private final DAOFactory daoFactory;

    public ModificarSedeFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(SedeDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarSede(daoFactory);
            var sedeDomain = SedeAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(sedeDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información de la sede.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar la información de la sede.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }

    }
}
