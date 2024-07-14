package co.com.park.gp.business.usecase.impl.planes.plan;

import co.com.park.gp.business.assembler.entity.impl.planes.PlanAssemblerEntity;
import co.com.park.gp.business.domain.planes.PlanDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;

import java.util.List;

public class ConsultarPlan implements UseCaseWithReturn<PlanDomain, List<PlanDomain>> {

    private final DAOFactory factory;

    public ConsultarPlan(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de los Planes...";
            var mensajeTecnico = "El dao factory para consultar el cliente llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public List<PlanDomain> execute(PlanDomain data) {
        var planEntityFilter = PlanAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getPlanDAO().consultar(planEntityFilter);

        return PlanAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
