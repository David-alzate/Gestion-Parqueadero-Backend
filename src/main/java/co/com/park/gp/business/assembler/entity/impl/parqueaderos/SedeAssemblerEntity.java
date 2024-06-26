package co.com.park.gp.business.assembler.entity.impl.parqueaderos;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.entity.AssemblerEntity;
import co.com.park.gp.business.domain.parqueaderos.CiudadDomain;
import co.com.park.gp.business.domain.parqueaderos.DepartamentoDomain;
import co.com.park.gp.business.domain.parqueaderos.PaisDomain;
import co.com.park.gp.business.domain.parqueaderos.ParqueaderoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.parqueaderos.TipoSedeDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.entity.parqueaderos.CiudadEntity;
import co.com.park.gp.entity.parqueaderos.DepartamentoEntity;
import co.com.park.gp.entity.parqueaderos.PaisEntity;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;
import co.com.park.gp.entity.parqueaderos.SedeEntity;
import co.com.park.gp.entity.parqueaderos.TipoSedeEntity;

public final class SedeAssemblerEntity implements AssemblerEntity<SedeDomain, SedeEntity> {

	private static final AssemblerEntity<ParqueaderoDomain, ParqueaderoEntity> parqueaderoAssembler = ParqueaderoAssemblerEntity
			.getInstance();
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> ciudadAssembler = CiudadAssemblerEntity
			.getInstance();
	private static final AssemblerEntity<TipoSedeDomain, TipoSedeEntity> tipoSedeAssembler = TipoSedeAssemblerEntity
			.getInstance();
	private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler = PaisAssemblerEntity.getInstance();
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity
			.getInstance();

	private static final AssemblerEntity<SedeDomain, SedeEntity> INSTANCE = new SedeAssemblerEntity();

	private SedeAssemblerEntity() {
		super();
	}

	public static AssemblerEntity<SedeDomain, SedeEntity> getInstance() {
		return INSTANCE;
	}

	@Override
	public SedeDomain toDomain(SedeEntity data) {
		var sedeEntityTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, SedeEntity.build());
		var parqueaderoDomain = parqueaderoAssembler.toDomain(sedeEntityTmp.getParqueadero());
		var ciudadDomain = ciudadAssembler.toDomain(sedeEntityTmp.getCiudad());
		var tipoSedeDomain = tipoSedeAssembler.toDomain(sedeEntityTmp.getTipoSede());
		var paisDomain = paisAssembler.toDomain(sedeEntityTmp.getPais());
		var departamentoDomain = departamentoAssembler.toDomain(sedeEntityTmp.getDepartamento());
		return SedeDomain.build(sedeEntityTmp.getId(), parqueaderoDomain, sedeEntityTmp.getNombre(), ciudadDomain,
				sedeEntityTmp.getDireccion(), sedeEntityTmp.getCorreoElectronico(), sedeEntityTmp.getCeldasCarro(),
				sedeEntityTmp.getCeldasMoto(), sedeEntityTmp.getCeldascamion(), tipoSedeDomain, paisDomain,
				departamentoDomain);
	}

	@Override
	public SedeEntity toEntity(final SedeDomain domain) {
		var sedeDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, SedeDomain.build());
		var parqueaderoEntity = parqueaderoAssembler.toEntity(sedeDomainTmp.getParqueadero());
		var ciudadEntity = ciudadAssembler.toEntity(sedeDomainTmp.getCiudad());
		var tipoSedeEntity = tipoSedeAssembler.toEntity(sedeDomainTmp.getTipoSede());
		var paisEntity = paisAssembler.toEntity(sedeDomainTmp.getPais());
		var departamentoEntity = departamentoAssembler.toEntity(sedeDomainTmp.getDepartamento());
		return SedeEntity.build().setId(sedeDomainTmp.getId()).setParqueadero(parqueaderoEntity)
				.setNombre(sedeDomainTmp.getNombre()).setCiudad(ciudadEntity).setDireccion(sedeDomainTmp.getDireccion())
				.setCorreoElectronico(sedeDomainTmp.getCorreoElectronico())
				.setCeldasCarro(sedeDomainTmp.getCeldasCarro()).setCeldasMoto(sedeDomainTmp.getCeldasMoto())
				.setCeldascamion(sedeDomainTmp.getCeldascamion()).setTipoSede(tipoSedeEntity).setPais(paisEntity)
				.setDepartamento(departamentoEntity);
	}

	@Override
	public List<SedeDomain> toDomainCollection(List<SedeEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<SedeEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public List<SedeEntity> toEntityCollection(List<SedeDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<SedeDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
