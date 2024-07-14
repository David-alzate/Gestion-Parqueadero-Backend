package co.com.park.gp.business.usecase.impl.planes.tipoplan;

import co.com.park.gp.business.assembler.entity.impl.planes.TipoPlanAssemblerEntity;
import co.com.park.gp.business.domain.planes.TipoPlanDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarTipoPlan implements UseCaseWithReturn<TipoPlanDomain, List<TipoPlanDomain>> {

    private final DAOFactory factory;

    public ConsultarTipoPlan(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la consulta de los tipos de planes...";
            var mensajeTecnico = "El dao factory para consultar el tipo de sede llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<TipoPlanDomain> execute(TipoPlanDomain data) {
        var tipoPlanEntityFilter = TipoPlanAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getTipoPlanDAO().consultar(tipoPlanEntityFilter);

        return TipoPlanAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
