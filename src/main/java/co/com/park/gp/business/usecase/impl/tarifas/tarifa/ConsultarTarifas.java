package co.com.park.gp.business.usecase.impl.tarifas.tarifa;

import co.com.park.gp.business.assembler.entity.impl.tarifas.TarifaAssemblerEntity;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarTarifas implements UseCaseWithReturn<TarifaDomain, List<TarifaDomain>> {

    private final DAOFactory factory;

    public ConsultarTarifas(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }

        this.factory = factory;
    }

    @Override
    public List<TarifaDomain> execute(TarifaDomain data) {
        var tarifaEntityFilter = TarifaAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getTarifaDAO().consultar(tarifaEntityFilter);

        return TarifaAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
