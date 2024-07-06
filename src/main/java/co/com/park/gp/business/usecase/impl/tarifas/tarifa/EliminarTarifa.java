package co.com.park.gp.business.usecase.impl.tarifas.tarifa;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.UUID;

public class EliminarTarifa implements UseCaseWithoutReturn<UUID> {

    private final DAOFactory factory;

    public EliminarTarifa(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una Eliminacion de la Tarifa...";
            var mensajeTecnico = "El DAO factory para eliminar la tarifa llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public void execute(UUID data) {
        factory.getTarifaDAO().eliminar(data);
    }
}


