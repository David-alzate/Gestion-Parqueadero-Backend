package co.com.park.gp.business.assembler.dto.impl.empleados;

import java.util.ArrayList;
import java.util.List;

import co.com.park.gp.business.assembler.dto.AssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.comunes.TipoIdentificacionAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.parqueaderos.SedeAssemblerDTO;
import co.com.park.gp.business.assembler.dto.impl.tarifas.EstadoAssemblerDTO;
import co.com.park.gp.business.domain.empleados.EmpleadoDomain;
import co.com.park.gp.business.domain.parqueaderos.SedeDomain;
import co.com.park.gp.business.domain.tarifas.EstadoDomain;
import co.com.park.gp.business.domain.empleados.TipoEmpleadoDomain;
import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.dto.empleados.EmpleadoDTO;
import co.com.park.gp.dto.parqueaderos.SedeDTO;
import co.com.park.gp.dto.tarifas.EstadoDTO;
import co.com.park.gp.dto.empleados.TipoEmpleadoDTO;
import co.com.park.gp.dto.comunes.TipoIdentificacionDTO;

public final class EmpleadoAssemblerDTO implements AssemblerDTO<EmpleadoDomain, EmpleadoDTO> {

	private static final AssemblerDTO<TipoIdentificacionDomain, TipoIdentificacionDTO> tipoIdentificacionAssembler = TipoIdentificacionAssemblerDTO
			.getInstance();

	private static final AssemblerDTO<TipoEmpleadoDomain, TipoEmpleadoDTO> tipoEmpleadoAssembler = TipoEmpleadoAssemblerDTO
			.getInstance();

	private static final AssemblerDTO<SedeDomain, SedeDTO> sedeAssembler = SedeAssemblerDTO.getInstance();

	private static final AssemblerDTO<EstadoDomain, EstadoDTO> estadoAssembler = EstadoAssemblerDTO.getInstance();

	private static final AssemblerDTO<EmpleadoDomain, EmpleadoDTO> instance = new EmpleadoAssemblerDTO();

	private EmpleadoAssemblerDTO() {
		super();
	}

	public static AssemblerDTO<EmpleadoDomain, EmpleadoDTO> getInstance() {
		return instance;
	}

	@Override
	public EmpleadoDomain toDomain(final EmpleadoDTO data) {
		var empleadoDtoTmp = ObjectHelper.getObjectHelper().getDefaultValue(data, EmpleadoDTO.build());
		var tipoIdentificacionDomain = tipoIdentificacionAssembler.toDomain(empleadoDtoTmp.getTipoIdentificacion());
		var tipoEmpleadoDomain = tipoEmpleadoAssembler.toDomain(empleadoDtoTmp.getTipoEmpleado());
		var sedeDomain = sedeAssembler.toDomain(empleadoDtoTmp.getSede());
		var estadoDomain = estadoAssembler.toDomain(empleadoDtoTmp.getEstado());
		return EmpleadoDomain.build(empleadoDtoTmp.getId(), tipoIdentificacionDomain,
				empleadoDtoTmp.getNumeroIdentificacion(), empleadoDtoTmp.getNombre(), empleadoDtoTmp.getApellido(),
				empleadoDtoTmp.getCorreoElectronico(), tipoEmpleadoDomain, sedeDomain, estadoDomain,
				empleadoDtoTmp.getPassword());
	}

	@Override
	public List<EmpleadoDomain> toDomainCollection(List<EmpleadoDTO> entityCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection,
				new ArrayList<EmpleadoDTO>());
		return dtoCollectionTmp.stream().map(this::toDomain).toList();
	}

	@Override
	public EmpleadoDTO toDto(final EmpleadoDomain domain) {
		var empleadoDomainTmp = ObjectHelper.getObjectHelper().getDefaultValue(domain, EmpleadoDomain.build());
		var tipoIdentificacionDTO = tipoIdentificacionAssembler.toDto(empleadoDomainTmp.getTipoIdentificacion());
		var tipoEmpleadoDTO = tipoEmpleadoAssembler.toDto(empleadoDomainTmp.getTipoEmpleado());
		var sedeDTO = sedeAssembler.toDto(empleadoDomainTmp.getSede());
		var estadoDTO = estadoAssembler.toDto(empleadoDomainTmp.getEstado());
		return EmpleadoDTO.build().setId(empleadoDomainTmp.getId()).setTipoIdentificacion(tipoIdentificacionDTO)
				.setNumeroIdentificacion(empleadoDomainTmp.getNumeroIdentificacion())
				.setNombre(empleadoDomainTmp.getNombre()).setApellido(empleadoDomainTmp.getApellido())
				.setCorreoElectronico(empleadoDomainTmp.getCorreoElectronico()).setTipoEmpleado(tipoEmpleadoDTO)
				.setSede(sedeDTO).setEstado(estadoDTO).setPassword(empleadoDomainTmp.getPassword());
	}

	@Override
	public List<EmpleadoDTO> toDTOCollection(List<EmpleadoDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection,
				new ArrayList<EmpleadoDomain>());
		return domainCollectionTmp.stream().map(this::toDto).toList();
	}

}
