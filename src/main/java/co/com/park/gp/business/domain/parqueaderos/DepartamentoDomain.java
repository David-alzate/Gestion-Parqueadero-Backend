package co.com.park.gp.business.domain.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class DepartamentoDomain {

    private UUID id;
    private String nombre;
    private PaisDomain pais;

    private DepartamentoDomain(final UUID id, final String nombre, final PaisDomain pais) {
        setId(id);
        setNombre(nombre);
        setPais(pais);
    }

    public static DepartamentoDomain build(final UUID id, final String nombre, final PaisDomain pais) {
        return new DepartamentoDomain(id, nombre, pais);

    }

    public static DepartamentoDomain build(final UUID id) {
        return new DepartamentoDomain(id, TextHelper.EMPTY, PaisDomain.build());

    }

    public static DepartamentoDomain build() {
        return new DepartamentoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, PaisDomain.build());

    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    private void setPais(PaisDomain pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, PaisDomain.build());
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public PaisDomain getPais() {
        return pais;
    }

    public String getNombre() {
        return nombre;
    }

}