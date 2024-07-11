package co.com.park.gp.business.assembler.dto.impl.vehiculos;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.comunes.TipoVehiculoAssemblerDTO;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.vehiculos.VehiculoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;
import co.com.park.gp.dto.vehiculos.VehiculoDTO;

import java.util.ArrayList;
import java.util.List;

public class VehiculoAssemblerDTO implements AssemblerDTO<VehiculoDomain, VehiculoDTO> {

    private static final AssemblerDTO<VehiculoDomain, VehiculoDTO> instance = new VehiculoAssemblerDTO();

    private static final AssemblerDTO<TipoVehiculoDomain, TipoVehiculoDTO> tipoVeiculoAssemblerDTO = TipoVehiculoAssemblerDTO.getInstance();

    private VehiculoAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<VehiculoDomain, VehiculoDTO> getInstance() {
        return instance;
    }

    @Override
    public VehiculoDTO toDto(VehiculoDomain domain) {
        var vehiculoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, VehiculoDomain.build());
        var tipoVehiculoDTO = tipoVeiculoAssemblerDTO.toDto(vehiculoDomainTmp.getTipoVehiculo());
        return VehiculoDTO.build().setId(vehiculoDomainTmp.getId())
                .setTipoVehiculo(tipoVehiculoDTO)
                .setPlaca(vehiculoDomainTmp.getPlaca());
    }

    @Override
    public List<VehiculoDTO> toDTOCollection(List<VehiculoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<VehiculoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public VehiculoDomain toDomain(VehiculoDTO data) {
        var vehiculoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, VehiculoDTO.build());
        var tipoVehiculoDomain = tipoVeiculoAssemblerDTO.toDomain(vehiculoDtoTmp.getTipoVehiculo());
        return VehiculoDomain.build(vehiculoDtoTmp.getId(), tipoVehiculoDomain, vehiculoDtoTmp.getPlaca());
    }

    @Override
    public List<VehiculoDomain> toDomainCollection(List<VehiculoDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<VehiculoDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

}
