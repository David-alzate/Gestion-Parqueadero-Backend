package co.com.park.gp.business.usecase.impl.planes.plan;

import co.com.park.gp.business.domain.planes.PlanDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.planes.PlanEntity;
import co.com.park.gp.entity.planes.TipoPlanEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

public class ModificarPlan implements UseCaseWithoutReturn<PlanDomain> {

    private final DAOFactory factory;

    public ModificarPlan(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la Modificaion de los Planes...";
            var mensajeTecnico = "El dao factory para Modificar el Plan llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(PlanDomain data) {

        var planEntity = PlanEntity.build().setId(data.getId())
                .setSede(SedeEntity.build()).setVehiculo(VehiculoEntity.build()).
                setCliente(ClienteEntity.build()).setTipoPlan(TipoPlanEntity.build()).setFechaInicio(data.getFechaInicio())
                .setFechaFin(data.getFechaFin());

        factory.getPlanDAO().modificar(planEntity);
    }
}
