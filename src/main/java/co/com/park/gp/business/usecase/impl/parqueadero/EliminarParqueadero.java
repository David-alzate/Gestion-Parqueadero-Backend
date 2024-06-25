package co.com.park.gp.business.usecase.impl.parqueadero;

import co.com.park.gp.business.domain.ParqueaderoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.ParqueaderoEntity;

import java.util.UUID;

public class EliminarParqueadero implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarParqueadero(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una Eliminacion del Parqueadero...";
            var mensajeTecnico = "El DAO factory para eliminar el Parqueadero llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(UUID id) {
        factory.getParqueaderoDAO().eliminar(id);
    }
}
