package co.com.park.gp.business.assembler.dto.impl.planes;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.clientes.ClienteAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.vehiculos.VehiculoAssemblerDTO;
import co.com.park.gp.business.domain.clientes.ClienteDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.planes.PlanDomain;
import co.com.park.gp.business.domain.planes.TipoPlanDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.clientes.ClienteDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.planes.PlanDTO;
import co.com.park.gp.dto.planes.TipoPlanDTO;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.util.ArrayList;
import java.util.List;

public class PlanAssemblerDTO implements AssemblerDTO<PlanDomain, PlanDTO> {

    private static final AssemblerDTO<PlanDomain, PlanDTO> instance = new PlanAssemblerDTO();

    private static final AssemblerDTO<TipoPlanDomain, TipoPlanDTO> tipoPlanInstance = TipoPlanAssemblerDTO.getInstance();

    private static final AssemblerDTO<SedeDomain, SedeDTO> sedeInstance = SedeAssemblerDTO.getInstance();

    private static final AssemblerDTO<VehiculoDomain, VehiculoDTO> vehiculoInstance = VehiculoAssemblerDTO.getInstance();

    private static final AssemblerDTO<ClienteDomain, ClienteDTO> clienteInstance = ClienteAssemblerDTO.getInstance();


    private PlanAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<PlanDomain, PlanDTO> getInstance() {
        return instance;
    }

    @Override
    public PlanDTO toDto(PlanDomain domain) {
        var planDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, PlanDomain.build());
        var sedeDTO = sedeInstance.toDto(planDomainTmp.getSede());
        var vehiculoDTO = vehiculoInstance.toDto(planDomainTmp.getVehiculo());
        var clienteDTO = clienteInstance.toDto(planDomainTmp.getCliente());
        var tipoPlanDTO = tipoPlanInstance.toDto(planDomainTmp.getTipoPlan());
        return PlanDTO.build().setId(planDomainTmp.getId()).setSede(sedeDTO).setVehiculo(vehiculoDTO).setCliente(clienteDTO)
                .setTipoPlan(tipoPlanDTO).setFechaInicio(planDomainTmp.getFechaInicio()).setFechaFin(planDomainTmp.getFechaFin());

    }

    @Override
    public List<PlanDTO> toDTOCollection(List<PlanDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<PlanDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public PlanDomain toDomain(PlanDTO data) {
        var planDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, PlanDTO.build());
        var sedeDomain = sedeInstance.toDomain(planDtoTmp.getSede());
        var vehiculoDomain = vehiculoInstance.toDomain(planDtoTmp.getVehiculo());
        var clienteDomain = clienteInstance.toDomain(planDtoTmp.getCliente());
        var tipoPlanDomain = tipoPlanInstance.toDomain(planDtoTmp.getTipoPlan());
        return PlanDomain.build(planDtoTmp.getId(), sedeDomain, vehiculoDomain, clienteDomain, tipoPlanDomain,
                planDtoTmp.getFechaInicio(), planDtoTmp.getFechaFin());
    }

    @Override
    public List<PlanDomain> toDomainCollection(List<PlanDTO> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<PlanDTO>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
