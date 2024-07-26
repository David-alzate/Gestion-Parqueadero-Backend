package co.com.park.gp.business.usecase.impl.sesionesparqueo.sesionparqueo;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarSesionParqueo implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarSesionParqueo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Eliminacion de la sesion parqueo...";
            var mensajeTecnico = "El dao factory para Eliminar la sesion parqueo llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(UUID id) {
        factory.getSesionParqueoDAO().eliminar(id);
    }
}
