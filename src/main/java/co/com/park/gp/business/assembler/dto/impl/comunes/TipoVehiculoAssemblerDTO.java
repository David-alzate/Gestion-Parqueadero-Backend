package co.com.park.gp.business.assembler.dto.impl.comunes;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;

import java.util.List;

public class TipoVehiculoAssemblerDTO implements AssemblerDTO<TipoVehiculoDomain, TipoVehiculoDTO> {

    private static final AssemblerDTO<TipoVehiculoDomain, TipoVehiculoDTO> instance = new TipoVehiculoAssemblerDTO();

    private TipoVehiculoAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<TipoVehiculoDomain, TipoVehiculoDTO> getInstance() {
        return instance;
    }

    @Override
    public TipoVehiculoDTO toDto(final TipoVehiculoDomain domain) {
        return null;
    }

    @Override
    public List<TipoVehiculoDTO> toDTOCollection(List<TipoVehiculoDomain> domainCollection) {
        return List.of();
    }

    @Override
    public TipoVehiculoDomain toDomain(final TipoVehiculoDTO data) {
        return null;
    }

    @Override
    public List<TipoVehiculoDomain> toDomainCollection(List<TipoVehiculoDTO> entityCollection) {
        return List.of();
    }
}
