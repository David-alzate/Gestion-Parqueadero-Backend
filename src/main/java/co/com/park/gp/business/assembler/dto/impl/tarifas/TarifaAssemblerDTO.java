package co.com.park.gp.business.assembler.dto.impl.tarifas;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.comunes.TipoVehiculoAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.domain.comunes.TipoVehiculoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.tarifas.TarifaDomain;
import co.com.park.gp.business.domain.tarifas.TipoTarifaDomain;
import co.com.park.gp.dto.comunes.TipoVehiculoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import co.com.park.gp.dto.tarifas.TarifaDTO;
import co.com.park.gp.dto.tarifas.TipoTarifaDTO;

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
        return null;
    }

    @Override
    public List<TarifaDTO> toDTOCollection(List<TarifaDomain> domainCollection) {
        return List.of();
    }

    @Override
    public TarifaDomain toDomain(TarifaDTO data) {
        return null;
    }

    @Override
    public List<TarifaDomain> toDomainCollection(List<TarifaDTO> entityCollection) {
        return List.of();
    }
}
