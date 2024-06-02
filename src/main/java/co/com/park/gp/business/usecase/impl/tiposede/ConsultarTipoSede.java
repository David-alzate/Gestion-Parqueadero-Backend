package co.com.park.gp.business.usecase.impl.tiposede;

import java.util.List;

import co.com.park.gp.business.assembler.entity.impl.TipoSedeAssemblerEntity;
import co.com.park.gp.business.domain.TipoSedeDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messageCatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

public class ConsultarTipoSede implements UseCaseWithReturn<TipoSedeDomain, List<TipoSedeDomain>> {

    private DAOFactory factory;

    public ConsultarTipoSede(final DAOFactory factory) {
        if(ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario =MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        
        this.factory = factory;
    }

    @Override
    public List<TipoSedeDomain> execute(final TipoSedeDomain data) {
        var tipoSedeEntityFilter = 
                TipoSedeAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getTipoSedeDAO().consultar(tipoSedeEntityFilter);
        
        return TipoSedeAssemblerEntity.getInstance().
                toDomainCollection(resultadosEntity);
    }
}

