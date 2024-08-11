package co.com.park.gp.business.assembler.entity.impl.planes;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.clientes.ClienteAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.vehiculos.VehiculoAssemblerEntity;
import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.planes.PlanDomain;
import co.com.park.gp.business.domain.planes.TipoPlanDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.clientes.ClienteEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.planes.PlanEntity;
import co.com.park.gp.entity.planes.TipoPlanEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.util.ArrayList;
import java.util.List;

public class PlanAssemblerEntity implements AssemblerEntity<PlanDomain, PlanEntity> {

    private static final PlanAssemblerEntity INSTANCE = new PlanAssemblerEntity();

    private static final AssemblerEntity<TipoPlanDomain, TipoPlanEntity> TIPO_PLAN_ASSEMBLER = TipoPlanAssemblerEntity.getInstance();

    private static final AssemblerEntity<SedeDomain, SedeEntity> SEDE_ASSEMBLER = SedeAssemblerEntity.getInstance();

    private static final AssemblerEntity<VehiculoDomain, VehiculoEntity> VEHICULO_ASSEMBLER = VehiculoAssemblerEntity.getInstance();

    private static final AssemblerEntity<ClienteDomain, ClienteEntity> CLIENTE_ASSEMBLER = ClienteAssemblerEntity.getInstance();

    private static final AssemblerEntity<EstadoDomain, EstadoEntity> estadoAssembler = EstadoAssemblerEntity.getInstance();

    private PlanAssemblerEntity() {
        super();
    }

    public static PlanAssemblerEntity getInstance() {
        return INSTANCE;
    }

    @Override
    public PlanEntity toEntity(PlanDomain domain) {
        var planDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, PlanDomain.build());
        var tipoPlanEntity = TIPO_PLAN_ASSEMBLER.toEntity(planDomainTmp.getTipoPlan());
        var sedeEntity = SEDE_ASSEMBLER.toEntity(planDomainTmp.getSede());
        var vehiculoEntity = VEHICULO_ASSEMBLER.toEntity(planDomainTmp.getVehiculo());
        var clienteEntity = CLIENTE_ASSEMBLER.toEntity(planDomainTmp.getCliente());
        var estadoEntity = estadoAssembler.toEntity(planDomainTmp.getEstado());
        return PlanEntity.build()
                .setId(planDomainTmp.getId()).setSede(sedeEntity).setVehiculo(vehiculoEntity)
                .setCliente(clienteEntity).setTipoPlan(tipoPlanEntity).setEstado(estadoEntity)
                .setFechaInicio(planDomainTmp.getFechaInicio())
                .setFechaFin(planDomainTmp.getFechaFin());
    }

    @Override
    public List<PlanEntity> toEntityCollection(List<PlanDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<PlanDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public PlanDomain toDomain(PlanEntity data) {
        var planEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, PlanEntity.build());
        var tipoPlanDomain = TIPO_PLAN_ASSEMBLER.toDomain(planEntityTmp.getTipoPlan());
        var sedeDomain = SEDE_ASSEMBLER.toDomain(planEntityTmp.getSede());
        var vehiculoDomain = VEHICULO_ASSEMBLER.toDomain(planEntityTmp.getVehiculo());
        var clienteDomain = CLIENTE_ASSEMBLER.toDomain(planEntityTmp.getCliente());
        var estadoDomain = estadoAssembler.toDomain(planEntityTmp.getEstado());
        return PlanDomain.build(planEntityTmp.getId(), sedeDomain, vehiculoDomain, clienteDomain, tipoPlanDomain, estadoDomain,
                planEntityTmp.getFechaInicio(), planEntityTmp.getFechaFin());
    }

    @Override
    public List<PlanDomain> toDomainCollection(List<PlanEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<PlanEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

}
