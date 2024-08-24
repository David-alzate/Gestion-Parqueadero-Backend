package co.com.park.gp.business.facade.impl.parqueaderos.celdas;

import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.celdas.EliminarCelda;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarCeldaFacade implements FacadeWhitoutReturn<UUID> {

    private final DAOFactory daoFactory;

    public EliminarCeldaFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(UUID id) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new EliminarCelda(daoFactory);
            useCase.execute(id);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de Eliminar las celdas.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Eliminar las celdas.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
