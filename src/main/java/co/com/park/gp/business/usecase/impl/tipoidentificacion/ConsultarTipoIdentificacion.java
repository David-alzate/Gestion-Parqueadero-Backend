package co.com.park.gp.business.usecase.impl.tipoidentificacion;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.TipoIdentificacionAssemblerEntity;
import co.com.park.gp.business.domain.TipoIdentificacionDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarTipoIdentificacion
        implements UseCaseWithReturn<TipoIdentificacionDomain, List<TipoIdentificacionDomain>> {

    private final DAOFactory factory;

    public ConsultarTipoIdentificacion(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<TipoIdentificacionDomain> execute(TipoIdentificacionDomain data) {
        var tipoIdentificacionEntityFilter = TipoIdentificacionAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.geTipoIdentificacionDAO().consultar(tipoIdentificacionEntityFilter);

        return TipoIdentificacionAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }

}
