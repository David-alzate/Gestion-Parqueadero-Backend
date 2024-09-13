package co.com.park.gp.business.usecase.impl.planes.plan;

import co.com.park.gp.business.assembler.entity.impl.planes.PlanAssemblerEntity;
import co.com.park.gp.business.domain.planes.PlanDomain;
import co.com.park.gp.business.usecase.UseCaseWithReturn;
import co.com.park.gp.crosscutting.enums.EstadoEnum;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.tarifas.EstadoEntity;

import java.util.List;

public class ConsultarPlanActivo implements UseCaseWithReturn<PlanDomain, List<PlanDomain>> {

    private final DAOFactory factory;

    public ConsultarPlanActivo(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Consulta de los planes del parqueo Activas...";
            var mensajeTecnico = "El dao factory para consultar los planes del parqueo Activas llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }


    @Override
    public List<PlanDomain> execute(PlanDomain data) {
        var estadoActivo = factory.getEstadoDAO().consultarPorDescripcion(EstadoEnum.ACTIVO.getNombre());

        var planFilter = PlanAssemblerEntity.getInstance().toEntity(data);
        var planActivo = planFilter.setEstado(EstadoEntity.build().setId(estadoActivo.getId()));
        var resultadosEntity = factory.getPlanDAO().consultar(planActivo);
        return PlanAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
