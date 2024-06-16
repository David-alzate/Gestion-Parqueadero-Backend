package co.com.park.gp.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.DepartamentoDomain;
import co.com.park.gp.business.domain.PaisDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.DepartamentoDTO;
import co.com.park.gp.dto.PaisDTO;

public final class DepartamentoAssemblerDTO implements AssemblerDTO<DepartamentoDomain, DepartamentoDTO> {

    private static final AssemblerDTO<PaisDomain, PaisDTO> paisAssembler = PaisAssemblerDTO.getInstance();

    private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> instance = new DepartamentoAssemblerDTO();

    private DepartamentoAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<DepartamentoDomain, DepartamentoDTO> getInstance() {
        return instance;
    }

    @Override
    public DepartamentoDomain toDomain(final DepartamentoDTO data) {
        var departamentoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, DepartamentoDTO.build());
        var paisDomain = paisAssembler.toDomain(departamentoDtoTmp.getPais());
        return DepartamentoDomain.build(departamentoDtoTmp.getId(), departamentoDtoTmp.getNombre(), paisDomain);
    }

    @Override
    public DepartamentoDTO toDto(final DepartamentoDomain domain) {
        var departamentoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, DepartamentoDomain.build());
        var paisDto = paisAssembler.toDto(departamentoDomainTmp.getPais());
        return DepartamentoDTO.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre())
                .setPais(paisDto);
    }

    @Override
    public List<DepartamentoDomain> toDomainCollection(List<DepartamentoDTO> dtoCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection,
                new ArrayList<DepartamentoDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public List<DepartamentoDTO> toDTOCollection(List<DepartamentoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<DepartamentoDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}