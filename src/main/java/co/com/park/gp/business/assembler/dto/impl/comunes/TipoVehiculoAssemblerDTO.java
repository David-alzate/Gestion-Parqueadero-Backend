package co.com.park.gp.business.assembler.dto.impl.comunes;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;

import java.util.ArrayList;
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
        var tipoVehiculoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain,
                TipoVehiculoDomain.build());
        return TipoVehiculoDTO.build().setId(tipoVehiculoDomainTmp.getId()).setTipoVehiculo(tipoVehiculoDomainTmp.getTipoVehiculo());

    }

    @Override
    public List<TipoVehiculoDTO> toDTOCollection(List<TipoVehiculoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<TipoVehiculoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public TipoVehiculoDomain toDomain(final TipoVehiculoDTO data) {
        var tipoVehiculoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data,
                TipoVehiculoDTO.build());
        return TipoVehiculoDomain.build(tipoVehiculoDtoTmp.getId(), tipoVehiculoDtoTmp.getTipoVehiculo());
    }

    @Override
    public List<TipoVehiculoDomain> toDomainCollection(List<TipoVehiculoDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<TipoVehiculoDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }
}
