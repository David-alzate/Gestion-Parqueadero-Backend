package co.com.park.gp.dto.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class CiudadDTO {

    private UUID id;
    private String nombre;
    private DepartamentoDTO departamento;

    public CiudadDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
        setDepartamento(DepartamentoDTO.build());
    }

    public CiudadDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public static CiudadDTO build() {
        return new CiudadDTO();
    }

    public CiudadDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public CiudadDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, new DepartamentoDTO());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

}
