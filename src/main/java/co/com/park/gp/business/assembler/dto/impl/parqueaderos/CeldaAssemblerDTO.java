package co.com.park.gp.business.assembler.dto.impl.parqueaderos;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.comunes.TipoVehiculoAssemblerDTO;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.parqueaderos.CeldaDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;
import co.com.park.gp.dto.parqueaderos.CeldaDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;

import java.util.ArrayList;
import java.util.List;

public class CeldaAssemblerDTO implements AssemblerDTO<CeldaDomain, CeldaDTO> {

    private static final AssemblerDTO<SedeDomain, SedeDTO> sedeAssembler = SedeAssemblerDTO.getInstance();

    private static final AssemblerDTO<TipoVehiculoDomain, TipoVehiculoDTO> tipoVehiculoAssembler = TipoVehiculoAssemblerDTO.getInstance();

    private static final AssemblerDTO<CeldaDomain, CeldaDTO> instance = new CeldaAssemblerDTO();

    private CeldaAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<CeldaDomain, CeldaDTO> getInstance() {
        return instance;
    }

    @Override
    public CeldaDTO toDto(CeldaDomain domain) {
        var celdaDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, CeldaDomain.build());
        var sedeDTO = sedeAssembler.toDto(celdaDomainTmp.getSede());
        var tipoVehiculoDTO = tipoVehiculoAssembler.toDto(celdaDomainTmp.getTipoVehiculo());
        return CeldaDTO.build().setId(celdaDomainTmp.getId()).setSede(sedeDTO).setTipoVehiculo(tipoVehiculoDTO)
                .setCantidadCeldas(celdaDomainTmp.getCantidadCeldas());
    }

    @Override
    public List<CeldaDTO> toDTOCollection(List<CeldaDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<CeldaDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public CeldaDomain toDomain(CeldaDTO data) {
        var celdaDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, CeldaDTO.build());
        var sedeDomain = sedeAssembler.toDomain(celdaDtoTmp.getSede());
        var tipoVehiculoDomain = tipoVehiculoAssembler.toDomain(celdaDtoTmp.getTipoVehiculo());
        return CeldaDomain.build(celdaDtoTmp.getId(), sedeDomain, tipoVehiculoDomain, celdaDtoTmp.getCantidadCeldas());
    }

    @Override
    public List<CeldaDomain> toDomainCollection(List<CeldaDTO> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<CeldaDTO>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }
}
