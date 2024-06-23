package co.com.park.gp.business.usecase.impl.sede;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarSede implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarSede(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una Eliminacion de la sede...";
            var mensajeTecnico = "El DAO factory para eliminar la sede llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(UUID id) {

        factory.getSedeDAO().eliminar(id);

    }
}
