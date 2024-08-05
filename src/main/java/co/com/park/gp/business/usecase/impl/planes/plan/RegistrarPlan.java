package co.com.park.gp.business.usecase.impl.planes.plan;

import co.com.park.gp.business.assembler.entity.impl.clientes.ClienteAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.planes.TipoPlanAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.vehiculos.VehiculoAssemblerEntity;
import co.com.park.gp.business.domain.planes.PlanDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.planes.PlanEntity;
import co.com.park.gp.entity.planes.TipoPlanEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.time.LocalDate;
import java.util.UUID;

public class RegistrarPlan implements UseCaseWithoutReturn<PlanDomain> {

    private final DAOFactory factory;

    public RegistrarPlan(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo el Registro de los Planes...";
            var mensajeTecnico = "El dao factory para Registrar el Plan llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(PlanDomain data) {

        validarMismaPlacaMismaSede(data.getVehiculo().getPlaca(), data.getSede().getId());
        validarSesionActiva(data.getVehiculo().getPlaca());

        var planEntity = PlanEntity.build().setId(generarIdentificadorPlan())
                .setSede(SedeAssemblerEntity.getInstance().toEntity(data.getSede())).setVehiculo(VehiculoAssemblerEntity.getInstance().toEntity(data.getVehiculo())).
                setCliente(ClienteAssemblerEntity.getInstance().toEntity(data.getCliente())).
                setTipoPlan(TipoPlanAssemblerEntity.getInstance().toEntity(data.getTipoPlan())).setFechaInicio(LocalDate.now())
                .setFechaFin(LocalDate.now());

        factory.getPlanDAO().crear(planEntity);
    }

    private UUID generarIdentificadorPlan() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var planEntity = PlanEntity.build().setId(id);
            var resultados = factory.getPlanDAO().consultar(planEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

    private void validarSesionActiva(String placa){
        var sesionParqueoEntity = SesionParqueoEntity.build().setPlaca(placa);

        var resultados = factory.getSesionParqueoDAO().consultar(sesionParqueoEntity);

        if (!resultados.isEmpty()){
            var mensajeUsuario = "El vehiculo tiene una Sesion de parqueo Activa, Finalice la sesion antes de Crear el plan";
            throw new BusinessGPException(mensajeUsuario);
        }
    }

    private void validarMismaPlacaMismaSede(String placa, UUID idSede){
        var planEntity = PlanEntity.build().setVehiculo(VehiculoEntity.build().setPlaca(placa)).setSede(SedeEntity.build().setId(idSede));

        var resultados = factory.getPlanDAO().consultar(planEntity);

        if(!resultados.isEmpty()){
            var mensajeUsuario = "El vehiculo ya tiene un plan activo en esta sede";
            throw new BusinessGPException(mensajeUsuario);
        }
    }
}
