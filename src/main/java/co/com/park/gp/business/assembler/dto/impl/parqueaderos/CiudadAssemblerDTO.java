package co.com.park.gp.business.assembler.dto.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.parqueaderos.CiudadDomain;
import co.com.park.gp.business.domain.parqueaderos.DepartamentoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.parqueaderos.CiudadDTO;
import co.com.park.gp.dto.parqueaderos.DepartamentoDTO;

public final class CiudadAssemblerDTO implements AssemblerDTO<CiudadDomain, CiudadDTO> {

    private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> departamentoAssembler = DepartamentoAssemblerDTO
            .getInstance();

    private static final AssemblerDTO<CiudadDomain, CiudadDTO> instance = new CiudadAssemblerDTO();

    private CiudadAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<CiudadDomain, CiudadDTO> getInstance() {
        return instance;
    }

    @Override
    public CiudadDomain toDomain(final CiudadDTO data) {
        var ciudadDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, CiudadDTO.build());
        var departamentoDomain = departamentoAssembler.toDomain(ciudadDtoTmp.getDepartamento());
        return CiudadDomain.build(ciudadDtoTmp.getId(), ciudadDtoTmp.getNombre(), departamentoDomain);
    }

    @Override
    public CiudadDTO toDto(final CiudadDomain domain) {
        var ciudadDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, CiudadDomain.build());
        var departamentoDTO = departamentoAssembler.toDto(ciudadDomainTmp.getDepartamento());
        return CiudadDTO.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre())
                .setDepartamento(departamentoDTO);
    }

    @Override
    public List<CiudadDomain> toDomainCollection(List<CiudadDTO> dtoCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection,
                new ArrayList<CiudadDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public List<CiudadDTO> toDTOCollection(List<CiudadDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<CiudadDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}