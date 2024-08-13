package co.com.park.gp.business.usecase.impl.parqueaderos.sede;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarSedes implements UseCaseWithReturn<SedeDomain, List<SedeDomain>> {

    private final DAOFactory factory;

    public ConsultarSedes(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<SedeDomain> execute(final SedeDomain data) {
        var sedeEntityFilter =
                SedeAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getSedeDAO().consultar(sedeEntityFilter);

        return SedeAssemblerEntity.getInstance().
                toDomainCollection(resultadosEntity);
    }
}
