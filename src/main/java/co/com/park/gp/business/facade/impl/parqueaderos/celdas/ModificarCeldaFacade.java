package co.com.park.gp.business.facade.impl.parqueaderos.celdas;

import co.com.park.gp.business.assembler.dto.impl.parqueaderos.CeldaAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.celdas.ModificarCelda;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.parqueaderos.CeldaDTO;

public class ModificarCeldaFacade implements FacadeWhitoutReturn<CeldaDTO> {

    private final DAOFactory daoFactory;

    public ModificarCeldaFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(CeldaDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarCelda(daoFactory);
            var celdaDomain = CeldaAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(celdaDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar las celdas.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar las celdas.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
