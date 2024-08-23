package co.com.park.gp.business.usecase.impl.parqueaderos.celdas;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarCelda implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarCelda(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una Eliminacion de la celda...";
            var mensajeTecnico = "El DAO factory para eliminar la celda llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(UUID id) {
        factory.getCeldaDao().eliminar(id);
    }
}
