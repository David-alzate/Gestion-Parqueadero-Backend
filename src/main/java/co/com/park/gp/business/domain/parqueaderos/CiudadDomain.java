package co.com.park.gp.business.domain.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class CiudadDomain {

    private UUID id;
    private String nombre;
    private DepartamentoDomain departamento;

    private CiudadDomain(final UUID id, final String nombre, final DepartamentoDomain departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);

    }

    public static CiudadDomain build(final UUID id, final String nombre, final DepartamentoDomain departamento) {
        return new CiudadDomain(id, nombre, departamento);

    }

    public static CiudadDomain build(final UUID id) {
        return new CiudadDomain(id, TextHelper.EMPTY, DepartamentoDomain.build());

    }

    public static CiudadDomain build() {
        return new CiudadDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, DepartamentoDomain.build());

    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    private void setDepartamento(DepartamentoDomain departamento) {
        this.departamento = ObjectHelper.getObjectHelper().getDefaultValue(departamento, DepartamentoDomain.build());
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDomain getDepartamento() {
        return departamento;
    }
}