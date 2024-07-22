package co.com.park.gp.business.assembler.dto.impl.sesionparqueo;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.empleados.EmpleadoAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.tarifas.EstadoAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.vehiculos.VehiculoAssemblerDTO;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.sesionparqueo.SesionParqueoDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.empleados.EmpleadoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.sesionparqueo.SesionParqueoDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.util.ArrayList;
import java.util.List;

public class SesionParqueoAssemblerDTO implements AssemblerDTO<SesionParqueoDomain, SesionParqueoDTO> {

    private static final AssemblerDTO<SedeDomain, SedeDTO> sedeAssembler = SedeAssemblerDTO.getInstance();

    private static final AssemblerDTO<VehiculoDomain, VehiculoDTO> vehiculoAssembler = VehiculoAssemblerDTO.getInstance();

    private static final AssemblerDTO<EmpleadoDomain, EmpleadoDTO> empleadoAssembler = EmpleadoAssemblerDTO.getInstance();

    private static final AssemblerDTO<EstadoDomain, EstadoDTO> estadoAssembler = EstadoAssemblerDTO.getInstance();

    private static final AssemblerDTO<SesionParqueoDomain, SesionParqueoDTO> instance = new SesionParqueoAssemblerDTO();

    private SesionParqueoAssemblerDTO() {
        super();
    }


    public static AssemblerDTO<SesionParqueoDomain, SesionParqueoDTO> getInstance() {
        return instance;
    }


    @Override
    public SesionParqueoDTO toDto(SesionParqueoDomain domain) {
        var sesionParqueoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, SesionParqueoDomain.build());
        var sedeDto = sedeAssembler.toDto(sesionParqueoDomainTmp.getSede());
        var vehiculoDto = vehiculoAssembler.toDto(sesionParqueoDomainTmp.getVehiculo());
        var empleadoDto = empleadoAssembler.toDto(sesionParqueoDomainTmp.getEmpleado());
        var estadoDto = estadoAssembler.toDto(sesionParqueoDomainTmp.getEstado());
        return SesionParqueoDTO.build().setId(sesionParqueoDomainTmp.getId()).setSede(sedeDto).setVehiculo(vehiculoDto).setEmpleado(empleadoDto).setEstado(estadoDto).
                setFechaHoraIngreso(sesionParqueoDomainTmp.getFechaHoraIngreso()).setFechaHoraSalida(sesionParqueoDomainTmp.getFechaHoraSalida());
    }

    @Override
    public List<SesionParqueoDTO> toDTOCollection(List<SesionParqueoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<SesionParqueoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public SesionParqueoDomain toDomain(SesionParqueoDTO data) {
        var sesionParqueoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, SesionParqueoDTO.build());
        var sedeDomain = sedeAssembler.toDomain(sesionParqueoDtoTmp.getSede());
        var vehiculoDomain = vehiculoAssembler.toDomain(sesionParqueoDtoTmp.getVehiculo());
        var empleadoDomain = empleadoAssembler.toDomain(sesionParqueoDtoTmp.getEmpleado());
        var estadoDomain = estadoAssembler.toDomain(sesionParqueoDtoTmp.getEstado());
        return SesionParqueoDomain.build(sesionParqueoDtoTmp.getId(), sedeDomain, vehiculoDomain, empleadoDomain, estadoDomain,
                sesionParqueoDtoTmp.getFechaHoraIngreso(), sesionParqueoDtoTmp.getFechaHoraSalida());
    }

    @Override
    public List<SesionParqueoDomain> toDomainCollection(List<SesionParqueoDTO> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<SesionParqueoDTO>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
