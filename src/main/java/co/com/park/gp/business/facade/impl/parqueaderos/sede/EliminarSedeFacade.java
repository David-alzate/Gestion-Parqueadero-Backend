package co.com.park.gp.business.facade.impl.parqueaderos.sede;

import co.com.park.gp.business.facade.FacadeWhitoutReturn;
import co.com.park.gp.business.usecase.impl.parqueaderos.sede.EliminarSede;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarSedeFacade implements FacadeWhitoutReturn<UUID> {

    private final DAOFactory daoFactory;

    public EliminarSedeFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(UUID id) {
        daoFactory.iniciarTransaccion();

        try {
            var useCase = new EliminarSede(daoFactory);
            useCase.execute(id);

            daoFactory.confirmarTransaccion();
        } catch (GPException excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de Eliminar la información de la sede.";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de Eliminar la información de la sede.";

            throw new BusinessGPException(mensajeTecnico, mensajeUsuario, excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }

    }
}
