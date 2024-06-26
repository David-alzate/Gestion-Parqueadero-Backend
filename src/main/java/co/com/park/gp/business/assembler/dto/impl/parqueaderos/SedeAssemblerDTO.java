package co.com.park.gp.business.assembler.dto.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.parqueaderos.CiudadDomain;
import co.com.park.gp.business.domain.parqueaderos.DepartamentoDomain;
import co.com.park.gp.business.domain.parqueaderos.PaisDomain;
import co.com.park.gp.business.domain.parqueaderos.ParqueaderoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.parqueaderos.TipoSedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.parqueaderos.CiudadDTO;
import co.com.park.gp.dto.parqueaderos.DepartamentoDTO;
import co.com.park.gp.dto.parqueaderos.PaisDTO;
import co.com.park.gp.dto.parqueaderos.ParqueaderoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.parqueaderos.TipoSedeDTO;

public final class SedeAssemblerDTO implements AssemblerDTO<SedeDomain, SedeDTO> {

    private static final AssemblerDTO<ParqueaderoDomain, ParqueaderoDTO> parqueaderoAssembler = ParqueaderoAssemblerDTO
            .getInstance();
    private static final AssemblerDTO<CiudadDomain, CiudadDTO> ciudadAssembler = CiudadAssemblerDTO.getInstance();
    private static final AssemblerDTO<TipoSedeDomain, TipoSedeDTO> tipoSedeAssembler = TipoSedeAssemblerDTO
            .getInstance();
    private static final AssemblerDTO<PaisDomain, PaisDTO> paisAssembler = PaisAssemblerDTO.getInstance();
    private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> departamentoAssembler = DepartamentoAssemblerDTO
            .getInstance();

    private static final AssemblerDTO<SedeDomain, SedeDTO> instance = new SedeAssemblerDTO();

    private SedeAssemblerDTO() {
        super();
    }

    public static AssemblerDTO<SedeDomain, SedeDTO> getInstance() {
        return instance;
    }

    @Override
    public SedeDomain toDomain(final SedeDTO data) {
        var sedeDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, SedeDTO.build());
        var parqueaderoDomain = parqueaderoAssembler.toDomain(sedeDtoTmp.getParqueadero());
        var ciudadDomain = ciudadAssembler.toDomain(sedeDtoTmp.getCiudad());
        var tipoSedeDomain = tipoSedeAssembler.toDomain(sedeDtoTmp.getTipoSede());
        var paisDomain = paisAssembler.toDomain(sedeDtoTmp.getPais());
        var departamentoDomain = departamentoAssembler.toDomain(sedeDtoTmp.getDepartamento());
        return SedeDomain.build(sedeDtoTmp.getId(), parqueaderoDomain, sedeDtoTmp.getNombre(), ciudadDomain,
                sedeDtoTmp.getDireccion(), sedeDtoTmp.getCorreoElectronico(), sedeDtoTmp.getCeldasCarro(),
                sedeDtoTmp.getCeldasMoto(), sedeDtoTmp.getCeldascamion(), tipoSedeDomain, paisDomain,
                departamentoDomain);
    }

    @Override
    public SedeDTO toDto(final SedeDomain domain) {
        var sedeDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, SedeDomain.build());
        var parqueaderoDto = parqueaderoAssembler.toDto(sedeDomainTmp.getParqueadero());
        var ciudadDto = ciudadAssembler.toDto(sedeDomainTmp.getCiudad());
        var tipoSedeDto = tipoSedeAssembler.toDto(sedeDomainTmp.getTipoSede());
        var paisDto = paisAssembler.toDto(sedeDomainTmp.getPais());
        var departamentoDto = departamentoAssembler.toDto(sedeDomainTmp.getDepartamento());
        return SedeDTO.build().setId(sedeDomainTmp.getId()).setParqueadero(parqueaderoDto)
                .setNombre(sedeDomainTmp.getNombre()).setCiudad(ciudadDto).setDireccion(sedeDomainTmp.getDireccion())
                .setCorreoElectronico(sedeDomainTmp.getCorreoElectronico())
                .setCeldasCarro(sedeDomainTmp.getCeldasCarro()).setCeldasMoto(sedeDomainTmp.getCeldasMoto())
                .setCeldascamion(sedeDomainTmp.getCeldascamion()).setTipoSede(tipoSedeDto).setPais(paisDto)
                .setDepartamento(departamentoDto);
    }

    @Override
    public List<SedeDomain> toDomainCollection(List<SedeDTO> dtoCollection) {
        var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<SedeDTO>());
        return dtoCollectionTmp.stream().map(this::toDomain).toList();
    }

    @Override
    public List<SedeDTO> toDTOCollection(List<SedeDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
                new ArrayList<SedeDomain>());
        return domainCollectionTmp.stream().map(this::toDto).toList();
    }

}
