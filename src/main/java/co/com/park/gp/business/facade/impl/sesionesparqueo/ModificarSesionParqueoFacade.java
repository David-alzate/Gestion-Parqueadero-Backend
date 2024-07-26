package co.com.park.gp.business.facade.impl.sesionesparqueo;

import co.com.park.gp.business.assembler.dto.impl.sesionparqueo.SesionParqueoAssemblerDTO;
import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo.ModificarSesionParqueo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.dto.sesionesparqueo.SesionParqueoDTO;

public class ModificarSesionParqueoFacade implements FacadeWhitoutReturn<SesionParqueoDTO> {

    private final DAOFactory daoFactory;

    public ModificarSesionParqueoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(SesionParqueoDTO dto) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new ModificarSesionParqueo(daoFactory);
            var sesionParqueoDomain = SesionParqueoAssemblerDTO.getInstance().toDomain(dto);

            useCase.execute(sesionParqueoDomain);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información de la sesion de parqueo.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Modificar la información de la sesion de parqueo.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
