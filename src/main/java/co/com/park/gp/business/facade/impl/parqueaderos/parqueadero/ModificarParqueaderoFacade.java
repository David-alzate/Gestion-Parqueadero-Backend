package co.com.park.gp.business.facade.impl.parqueaderos.parqueadero;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.ParqueaderoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.parqueadero.ModificarParqueadero;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.ParqueaderoDTO;

public class ModificarParqueaderoFacade implements FacadeWhitoutReturn<ParqueaderoDTO> {

    private final DAOFactory daoFactory;

    public ModificarParqueaderoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(ParqueaderoDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarParqueadero(daoFactory);
            var parqueaderoDomain = ParqueaderoAssemblerDTO.getInstance().toDomain(dto);
            useCase.execute(parqueaderoDomain);

            daoFactory.confirmarTransaccion();

        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información del Parqueadero.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar la información del Parqueadero.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }

    }
}
