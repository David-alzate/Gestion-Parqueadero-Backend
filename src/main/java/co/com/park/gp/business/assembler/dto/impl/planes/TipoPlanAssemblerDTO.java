package co.com.park.gp.business.assembler.dto.impl.planes;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.planes.TipoPlanDomain;
import co.com.park.gp.dto.planes.TipoPlanDTO;

import java.util.List;

public class TipoPlanAssemblerDTO implements AssemblerDTO<TipoPlanDomain, TipoPlanDTO> {

    private static final AssemblerDTO<TipoPlanDomain, TipoPlanDTO> instance = new TipoPlanAssemblerDTO();

    private TipoPlanAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<TipoPlanDomain, TipoPlanDTO> getInstance() {
        return instance;
    }

    @Override
    public TipoPlanDomain toDomain(final TipoPlanDTO data) {
        return TipoPlanDomain.build(data.getId(), data.getNombre());
    }

    @Override
    public TipoPlanDTO toDto(final TipoPlanDomain domain) {
        return TipoPlanDTO.build().setId(domain.getId()).setNombre(domain.getNombre());
    }

    @Override
    public List<TipoPlanDomain> toDomainCollection(List<TipoPlanDTO> dtoCollection) {
        return dtoCollection.stream().map(this::toDomain).toList();
    }

    @Override
    public List<TipoPlanDTO> toDTOCollection(List<TipoPlanDomain> domainCollection) {
        return domainCollection.stream().map(this::toDto).toList();
    }
}
