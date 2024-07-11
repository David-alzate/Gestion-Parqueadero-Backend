package co.com.park.gp.business.usecase.impl.clientes.cliente;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarCliente implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarCliente(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Eliminacion de los clientes...";
            var mensajeTecnico = "El dao factory para Eliminar el cliente llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(UUID data) {
        factory.getClienteDAO().eliminar(data);
    }
}
