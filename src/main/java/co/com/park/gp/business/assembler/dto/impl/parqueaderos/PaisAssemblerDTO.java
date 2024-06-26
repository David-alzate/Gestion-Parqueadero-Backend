package co.com.park.gp.business.assembler.dto.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.parqueaderos.PaisDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.parqueaderos.PaisDTO;

public final class PaisAssemblerDTO implements AssemblerDTO<PaisDomain, PaisDTO> {

    private static final AssemblerDTO<PaisDomain, PaisDTO> instance = new PaisAssemblerDTO();

    private PaisAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<PaisDomain, PaisDTO> getInstance() {
        return instance;
    }

    @Override
    public final PaisDomain toDomain(final PaisDTO data) {
        var paisDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, PaisDTO.build());
        return PaisDomain.build(paisDtoTmp.getId(), paisDtoTmp.getNombre());
    }

    @Override
    public final PaisDTO toDto(final PaisDomain domain) {
        var paisDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, PaisDomain.build());
        return PaisDTO.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
    }

    @Override
    public List<PaisDomain> toDomainCollection(List<PaisDTO> dtoCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<PaisDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public List<PaisDTO> toDTOCollection(List<PaisDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<PaisDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}