package co.com.park.gp.business.assembler.dto.impl.tarifas;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.comunes.TipoVehiculoAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import co.com.park.gp.dto.tarifas.TarifaDTO;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;

import java.util.ArrayList;
import java.util.List;

public class TarifaAssemblerDTO implements AssemblerDTO<TarifaDomain, TarifaDTO> {

    private static final AssemblerDTO<TipoTarifaDomain, TipoTarifaDTO> tipoTarifaAssembler = TipoTarifaAssemblerDTO
            .getInstance();
    private static final AssemblerDTO<EstadoDomain, EstadoDTO> estadoAssembler = EstadoAssemblerDTO
            .getInstance();
    private static final AssemblerDTO<TipoVehiculoDomain, TipoVehiculoDTO> tipoVehiculoAssembler = TipoVehiculoAssemblerDTO
            .getInstance();
    private static final AssemblerDTO<SedeDomain, SedeDTO> sedeAssembler = SedeAssemblerDTO
            .getInstance();

    private static final AssemblerDTO<TarifaDomain, TarifaDTO> instance = new TarifaAssemblerDTO();

    private TarifaAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<TarifaDomain, TarifaDTO> getInstance() {
        return instance;
    }

    @Override
    public TarifaDTO toDto(TarifaDomain domain) {
        var tarifaDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, TarifaDomain.build());
        var tipoTarifaDto = tipoTarifaAssembler.toDto(tarifaDomainTmp.getTipoTarifa());
        var estadoDto = estadoAssembler.toDto(tarifaDomainTmp.getEstado());
        var tipoVehiculoDto = tipoVehiculoAssembler.toDto(tarifaDomainTmp.getTipoVehiculo());
        var sedeDto = sedeAssembler.toDto(tarifaDomainTmp.getSede());
        return TarifaDTO.build().setId(tarifaDomainTmp.getId()).setSede(sedeDto).setTipoVehiculo(tipoVehiculoDto).
                setTipoTarifa(tipoTarifaDto).setTarifa(tarifaDomainTmp.getTarifa()).setEstado(estadoDto).
                setFechaInicioVigencia(tarifaDomainTmp.getFechaInicioVigencia()).setFechaFinVigencia(tarifaDomainTmp.getFechaFinVigencia());

    }

    @Override
    public List<TarifaDTO> toDTOCollection(List<TarifaDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<TarifaDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

    @Override
    public TarifaDomain toDomain(TarifaDTO data) {
        var tarifaDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, TarifaDTO.build());
        var tipoTarifaDomain = tipoTarifaAssembler.toDomain(tarifaDtoTmp.getTipoTarifa());
        var estadoDomain = estadoAssembler.toDomain(tarifaDtoTmp.getEstado());
        var tipoVehiculoDomain = tipoVehiculoAssembler.toDomain(tarifaDtoTmp.getTipoVehiculo());
        var sedeDomain = sedeAssembler.toDomain(tarifaDtoTmp.getSede());
        return TarifaDomain.build(tarifaDtoTmp.getId(), sedeDomain, tipoVehiculoDomain, tipoTarifaDomain,
                tarifaDtoTmp.getTarifa(), estadoDomain, tarifaDtoTmp.getFechaInicioVigencia(),
                tarifaDtoTmp.getFechaFinVigencia());
    }

    @Override
    public List<TarifaDomain> toDomainCollection(List<TarifaDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<TarifaDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }
}
