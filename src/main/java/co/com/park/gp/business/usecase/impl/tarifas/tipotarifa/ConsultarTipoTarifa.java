package co.com.park.gp.business.usecase.impl.tarifas.tipotarifa;

import co.com.park.gp.business.assembler.entity.impl.tarifas.TipoTarifaAssemblerEntity;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarTipoTarifa implements UseCaseWithReturn<TipoTarifaDomain, List<TipoTarifaDomain>> {

    private final DAOFactory factory;

    public ConsultarTipoTarifa(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta del tipo De Tarifa...";
            var mensajeTecnico = "El dao factory para consultar los tipo De Tarifa llego nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<TipoTarifaDomain> execute(TipoTarifaDomain data) {
        var tipoTarifaEntityFilter = TipoTarifaAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getTipoTarifaDAO().consultar(tipoTarifaEntityFilter);

        return TipoTarifaAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
