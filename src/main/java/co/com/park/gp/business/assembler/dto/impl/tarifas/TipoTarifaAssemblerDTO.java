package co.com.park.gp.business.assembler.dto.impl.tarifas;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;

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
        return null;
    }

    @Override
    public List<TipoTarifaDTO> toDTOCollection(List<TipoTarifaDomain> domainCollection) {
        return List.of();
    }

    @Override
    public TipoTarifaDomain toDomain(final TipoTarifaDTO data) {
        return null;
    }

    @Override
    public List<TipoTarifaDomain> toDomainCollection(List<TipoTarifaDTO> entityCollection) {
        return List.of();
    }
}
