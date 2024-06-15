package co.com.park.gp.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.domain.EmpleadoDomain;
import co.com.park.gp.business.domain.SedeDomain;
import co.com.park.gp.business.domain.TipoEmpleadoDomain;
import co.com.park.gp.business.domain.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.EmpleadoDTO;
import co.com.park.gp.dto.SedeDTO;
import co.com.park.gp.dto.TipoEmpleadoDTO;
import co.com.park.gp.dto.TipoIdentificacionDTO;

public class EmpleadoAssemblerDTO implements AssemblerDTO<EmpleadoDomain, EmpleadoDTO> {

	private static final AssemblerDTO<TipoIdentificacionDomain, TipoIdentificacionDTO> tipoIdentificacionAssembler = TipoIdentificacionAssemblerDTO
			.getInstance();

	private static final AssemblerDTO<TipoEmpleadoDomain, TipoEmpleadoDTO> tipoEmpleadoAssembler = TipoEmpleadoAssemblerDTO
			.getInstance();

	private static final AssemblerDTO<SedeDomain, SedeDTO> sedeAssembler = SedeAssemblerDTO.getInstance();

	private static final AssemblerDTO<EmpleadoDomain, EmpleadoDTO> instance = new EmpleadoAssemblerDTO();

	private EmpleadoAssemblerDTO() {
		super();
	}

	public static final AssemblerDTO<EmpleadoDomain, EmpleadoDTO> getInstance() {
		return instance;
	}

	@Override
	public EmpleadoDomain toDomain(EmpleadoDTO data) {
		var empleadoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, EmpleadoDTO.build());
		var tipoIdentificacionDomain = tipoIdentificacionAssembler.toDomain(empleadoDtoTmp.getTipoIdentificacion());
		var tipoEmpleadoDomain = tipoEmpleadoAssembler.toDomain(empleadoDtoTmp.getTipoEmpleado());
		var sedeDomain = sedeAssembler.toDomain(empleadoDtoTmp.getSede());
		return EmpleadoDomain.build(empleadoDtoTmp.getId(), tipoIdentificacionDomain,
				empleadoDtoTmp.getNumeroIdentificacion(), empleadoDtoTmp.getNombre(), empleadoDtoTmp.getApellido(),
				empleadoDtoTmp.getCorreoElectronico(), tipoEmpleadoDomain, sedeDomain, empleadoDtoTmp.getPassword());
	}

	@Override
	public List<EmpleadoDomain> toDomainCollection(List<EmpleadoDTO> entityCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<EmpleadoDTO>());
		return dtoCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public EmpleadoDTO toDto(EmpleadoDomain domain) {
		var empleadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, EmpleadoDomain.build());
		var tipoIdentificacionDTO = tipoIdentificacionAssembler.toDto(empleadoDomainTmp.getTipoIdentificacion());
		var tipoEmpleadoDTO = tipoEmpleadoAssembler.toDto(empleadoDomainTmp.getTipoEmpleado());
		var sedeDTO = sedeAssembler.toDto(empleadoDomainTmp.getSede());
		return EmpleadoDTO.build().setId(empleadoDomainTmp.getId()).setTipoIdentificacion(tipoIdentificacionDTO)
				.setNumeroIdentificacion(empleadoDomainTmp.getNumeroIdentificacion())
				.setNombre(empleadoDomainTmp.getNombre()).setApellido(empleadoDomainTmp.getApellido())
				.setCorreoElectronico(empleadoDomainTmp.getCorreoElectronico()).setTipoEmpleado(tipoEmpleadoDTO)
				.setSede(sedeDTO).setPassword(empleadoDomainTmp.getPassword());
	}

	@Override
	public List<EmpleadoDTO> toDTOCollection(List<EmpleadoDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<EmpleadoDomain>());
		return domainCollectionTmp.stream().map(this::toDto).toList();
	}

}
