package co.com.park.gp.business.facade.impl.sesionesparqueo;

import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo.EliminarSesionParqueo;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarSesionParqueoFacade implements FacadeWhitoutReturn<UUID> {

    private final DAOFactory daoFactory;

    public EliminarSesionParqueoFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(UUID id) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new EliminarSesionParqueo(daoFactory);
            useCase.execute(id);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de Eliminar la información de la sesion de parqueo.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Eliminar la información de la sesion de parqueo.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
