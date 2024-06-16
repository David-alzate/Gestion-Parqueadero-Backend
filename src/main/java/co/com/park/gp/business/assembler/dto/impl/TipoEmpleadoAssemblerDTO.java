package co.com.park.gp.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.TipoEmpleadoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.TipoEmpleadoDTO;

public final class TipoEmpleadoAssemblerDTO implements AssemblerDTO<TipoEmpleadoDomain, TipoEmpleadoDTO> {

    private static final AssemblerDTO<TipoEmpleadoDomain, TipoEmpleadoDTO> instance = new TipoEmpleadoAssemblerDTO();

    private TipoEmpleadoAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<TipoEmpleadoDomain, TipoEmpleadoDTO> getInstance() {
        return instance;
    }

    @Override
    public TipoEmpleadoDomain toDomain(final TipoEmpleadoDTO data) {
        var tipoEmpleadoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TipoEmpleadoDTO.build());
        return TipoEmpleadoDomain.build(tipoEmpleadoDtoTmp.getId(), tipoEmpleadoDtoTmp.getNombre());
    }

    @Override
    public List<TipoEmpleadoDomain> toDomainCollection(List<TipoEmpleadoDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<TipoEmpleadoDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public TipoEmpleadoDTO toDto(final TipoEmpleadoDomain domain) {
        var tipoEmpleadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TipoEmpleadoDomain.build());
        return TipoEmpleadoDTO.build().setId(tipoEmpleadoDomainTmp.getId()).setNombre(tipoEmpleadoDomainTmp.getNombre());
    }

    @Override
    public List<TipoEmpleadoDTO> toDTOCollection(List<TipoEmpleadoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<TipoEmpleadoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}
