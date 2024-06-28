package co.com.park.gp.business.assembler.dto.impl.tarifas;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;

import java.util.ArrayList;
import java.util.List;

public class TipoTarifaAssemblerDTO implements AssemblerDTO<TipoTarifaDomain, TipoTarifaDTO> {

    private static final AssemblerDTO<TipoTarifaDomain, TipoTarifaDTO> instance = new TipoTarifaAssemblerDTO();

    private TipoTarifaAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<TipoTarifaDomain, TipoTarifaDTO> getInstance() {
        return instance;
    }

    @Override
    public TipoTarifaDTO toDto(final TipoTarifaDomain domain) {
        var tipoTarifaDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain,
                TipoTarifaDomain.build());
        return TipoTarifaDTO.build().setId(tipoTarifaDomainTmp.getId()).setTipoTarifa(tipoTarifaDomainTmp.getTipoTarifa());
    }

    @Override
    public List<TipoTarifaDTO> toDTOCollection(List<TipoTarifaDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<TipoTarifaDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public TipoTarifaDomain toDomain(final TipoTarifaDTO data) {
        var tipoTarifaDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data,
                TipoTarifaDTO.build());
        return TipoTarifaDomain.build(tipoTarifaDtoTmp.getId(), tipoTarifaDtoTmp.getTipoTarifa());
    }

    @Override
    public List<TipoTarifaDomain> toDomainCollection(List<TipoTarifaDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<TipoTarifaDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }
}
