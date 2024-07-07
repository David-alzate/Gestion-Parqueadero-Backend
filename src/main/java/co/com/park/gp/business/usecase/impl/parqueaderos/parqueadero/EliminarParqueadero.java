package co.com.park.gp.business.usecase.impl.parqueaderos.parqueadero;

import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;

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
        validarParqueaderoAsignadoSede(id);
        factory.getParqueaderoDAO().eliminar(id);
    }

    void validarParqueaderoAsignadoSede(UUID id){
        var sedeEntity = SedeEntity.build().setParqueadero(ParqueaderoEntity.build().setId(id));
        var resultados = factory.getSedeDAO().consultar(sedeEntity);

        if(!resultados.isEmpty()){
            var mensajeUsuario = "El parqueadero esta asignado a una sede";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}
