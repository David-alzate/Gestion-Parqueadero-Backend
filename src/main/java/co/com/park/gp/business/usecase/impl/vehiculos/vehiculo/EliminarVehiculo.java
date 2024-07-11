package co.com.park.gp.business.usecase.impl.vehiculos.vehiculo;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarVehiculo implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarVehiculo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Eliminacion de los vehiculos...";
            var mensajeTecnico = "El dao factory para modificar el vehiculo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(UUID data) {
        factory.getVehiculoDAO().eliminar(data);
    }
}
