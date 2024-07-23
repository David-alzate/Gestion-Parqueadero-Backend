package co.com.park.gp.business.assembler.entity.impl.sesionparqueo;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.empleados.EmpleadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.parqueaderos.SedeAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.tarifas.EstadoAssemblerEntity;
import co.com.park.gp.business.assembler.entity.impl.vehiculos.VehiculoAssemblerEntity;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.empleados.EmpleadoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.sesionesparqueo.SesionParqueoEntity;
import co.com.park.gp.entity.tarifas.EstadoEntity;
import co.com.park.gp.entity.vehiculos.VehiculoEntity;

import java.util.ArrayList;
import java.util.List;

public class SesionParqueoAssemblerEntity implements AssemblerEntity<SesionParqueoDomain, SesionParqueoEntity> {

    private static final AssemblerEntity<SesionParqueoDomain, SesionParqueoEntity> instance = new SesionParqueoAssemblerEntity();

    private static final AssemblerEntity<SedeDomain, SedeEntity> sedeAssembler = SedeAssemblerEntity.getInstance();

    private static final AssemblerEntity<VehiculoDomain, VehiculoEntity> vehiculoAssembler = VehiculoAssemblerEntity.getInstance();

    private static final AssemblerEntity<EmpleadoDomain, EmpleadoEntity> empleadoAssembler = EmpleadoAssemblerEntity.getInstance();

    private static final AssemblerEntity<EstadoDomain, EstadoEntity> estadoAssembler = EstadoAssemblerEntity.getInstance();

    private SesionParqueoAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<SesionParqueoDomain, SesionParqueoEntity> getInstance() {
        return instance;
    }


    @Override
    public SesionParqueoEntity toEntity(SesionParqueoDomain domain) {
        var domainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, SesionParqueoDomain.build());
        var sedeEntity = sedeAssembler.toEntity(domainTmp.getSede());
        var vehiculoEntity = vehiculoAssembler.toEntity(domainTmp.getVehiculo());
        var empleadoEntity = empleadoAssembler.toEntity(domainTmp.getEmpleado());
        var estadoEntity = estadoAssembler.toEntity(domainTmp.getEstado());
        return SesionParqueoEntity.build().setId(domainTmp.getId()).setSede(sedeEntity).setVehiculo(vehiculoEntity).setEmpleado(empleadoEntity)
                .setEstado(estadoEntity).setFechaHoraIngreso(domainTmp.getFechaHoraIngreso()).setFechaHoraSalida(domainTmp.getFechaHoraSalida());
    }

    @Override
    public List<SesionParqueoEntity> toEntityCollection(List<SesionParqueoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<SesionParqueoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

    @Override
    public SesionParqueoDomain toDomain(SesionParqueoEntity data) {
        var entityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, SesionParqueoEntity.build());
        var sedeDomain = sedeAssembler.toDomain(entityTmp.getSede());
        var vehiculoDomain = vehiculoAssembler.toDomain(entityTmp.getVehiculo());
        var empleadoDomain = empleadoAssembler.toDomain(entityTmp.getEmpleado());
        var estadoDomain = estadoAssembler.toDomain(entityTmp.getEstado());
        return SesionParqueoDomain.build(entityTmp.getId(), sedeDomain, vehiculoDomain, empleadoDomain, estadoDomain, entityTmp.getFechaHoraIngreso(), entityTmp.getFechaHoraSalida());
    }

    @Override
    public List<SesionParqueoDomain> toDomainCollection(List<SesionParqueoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<SesionParqueoEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
