package co.com.park.gp.business.assembler.dto.impl.tarifas;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.dto.tarifas.EstadoDTO;

import java.util.List;

public class EstadoAssemblerDTO implements AssemblerDTO<EstadoDomain, EstadoDTO> {

    private static final AssemblerDTO<EstadoDomain, EstadoDTO> instance = new EstadoAssemblerDTO();

    private EstadoAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<EstadoDomain, EstadoDTO> getInstance() {
        return instance;
    }

    @Override
    public EstadoDTO toDto(final EstadoDomain domain) {
        return null;
    }

    @Override
    public List<EstadoDTO> toDTOCollection(List<EstadoDomain> domainCollection) {
        return List.of();
    }

    @Override
    public EstadoDomain toDomain(final EstadoDTO data) {
        return null;
    }

    @Override
    public List<EstadoDomain> toDomainCollection(List<EstadoDTO> entityCollection) {
        return List.of();
    }
}
