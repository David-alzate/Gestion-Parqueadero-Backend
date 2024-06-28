package co.com.park.gp.business.assembler.dto.impl.tarifas;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.tarifas.EstadoDTO;

import java.util.ArrayList;
import java.util.List;

public class EstadoAssemblerDTO implements AssemblerDTO<EstadoDomain, EstadoDTO> {

    private static final AssemblerDTO<EstadoDomain, EstadoDTO> instance = new EstadoAssemblerDTO();

    private EstadoAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<EstadoDomain, EstadoDTO> getInstance() {
        return instance;
    }

    @Override
    public EstadoDTO toDto(final EstadoDomain domain) {
        var estadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain,
                EstadoDomain.build());
        return EstadoDTO.build().setId(estadoDomainTmp.getId()).setEstado(estadoDomainTmp.getEstado());
    }

    @Override
    public List<EstadoDTO> toDTOCollection(List<EstadoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<EstadoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public EstadoDomain toDomain(final EstadoDTO data) {
        var estadoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data,
                EstadoDTO.build());
        return EstadoDomain.build(estadoDtoTmp.getId(), estadoDtoTmp.getEstado());
    }

    @Override
    public List<EstadoDomain> toDomainCollection(List<EstadoDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<EstadoDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }
}
