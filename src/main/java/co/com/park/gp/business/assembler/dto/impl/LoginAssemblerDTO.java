package co.com.park.gp.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.LoginDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.LoginDTO;

public final class LoginAssemblerDTO implements AssemblerDTO<LoginDomain, LoginDTO> {

    private static final AssemblerDTO<LoginDomain, LoginDTO> instance = new LoginAssemblerDTO();

    private LoginAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<LoginDomain, LoginDTO> getInstance() {
        return instance;
    }

    @Override
    public LoginDomain toDomain(final LoginDTO data) {
        var loginDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, LoginDTO.build());
        return LoginDomain.build(loginDtoTmp.getCorreoElectronico(), loginDtoTmp.getPassword());
    }

    @Override
    public List<LoginDomain> toDomainCollection(List<LoginDTO> entityCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<LoginDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public LoginDTO toDto(final LoginDomain domain) {
        var loginDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, LoginDomain.build());
        return LoginDTO.build().setCorreoElectronico(loginDomainTmp.getCorreoElectronico())
                .setPassword(loginDomainTmp.getPassword());
    }

    @Override
    public List<LoginDTO> toDTOCollection(List<LoginDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<LoginDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}
