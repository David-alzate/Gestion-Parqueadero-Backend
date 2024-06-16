package co.com.park.gp.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.TipoIdentificacionDTO;

public final class TipoIdentificacionAssemblerDTO implements AssemblerDTO<TipoIdentificacionDomain, TipoIdentificacionDTO> {

    private static final AssemblerDTO<TipoIdentificacionDomain, TipoIdentificacionDTO> instance = new TipoIdentificacionAssemblerDTO();

    private TipoIdentificacionAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<TipoIdentificacionDomain, TipoIdentificacionDTO> getInstance() {
        return instance;
    }

    @Override
    public TipoIdentificacionDomain toDomain(final TipoIdentificacionDTO data) {
        var tipoIdentificacionDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data,
                TipoIdentificacionDTO.build());
        return TipoIdentificacionDomain.build(tipoIdentificacionDtoTmp.getId(), tipoIdentificacionDtoTmp.getNombre());
    }

    @Override
    public List<TipoIdentificacionDomain> toDomainCollection(List<TipoIdentificacionDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<TipoIdentificacionDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public TipoIdentificacionDTO toDto(final TipoIdentificacionDomain domain) {
        var tipoIdentificacionDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain,
                TipoIdentificacionDomain.build());
        return TipoIdentificacionDTO.build().setId(tipoIdentificacionDomainTmp.getId())
                .setNombre(tipoIdentificacionDomainTmp.getNombre());
    }

    @Override
    public List<TipoIdentificacionDTO> toDTOCollection(List<TipoIdentificacionDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<TipoIdentificacionDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}
