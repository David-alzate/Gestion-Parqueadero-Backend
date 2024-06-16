package co.com.park.gp.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.LoginDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.LoginEntity;

public final class LoginAssemblerEntity implements AssemblerEntity<LoginDomain, LoginEntity> {

    private static final AssemblerEntity<LoginDomain, LoginEntity> instance = new LoginAssemblerEntity();

    private LoginAssemblerEntity() {
        super();
    }

    public static AssemblerEntity<LoginDomain, LoginEntity> getInstance() {
        return instance;
    }

    @Override
    public LoginDomain toDomain(final LoginEntity data) {
        var loginEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, LoginEntity.build());
        return LoginDomain.build(loginEntityTmp.getCorreoElectronico(), loginEntityTmp.getPassword());
    }

    @Override
    public List<LoginDomain> toDomainCollection(List<LoginEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
                new ArrayList<LoginEntity>());
        return entityCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public LoginEntity toEntity(final LoginDomain domain) {
        var loginDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, LoginDomain.build());
        return LoginEntity.build().setCorreoElectronico(loginDomainTmp.getCorreoElectronico())
                .setPassword(loginDomainTmp.getPassword());
    }

    @Override
    public List<LoginEntity> toEntityCollection(List<LoginDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<LoginDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
    }

}
