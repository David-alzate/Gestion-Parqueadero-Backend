package co.com.park.gp.entity.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class DepartamentoEntity {

    private UUID id;
    private String nombre;
    private PaisEntity pais;

    public DepartamentoEntity() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
        setPais(PaisEntity.build());
    }

    public DepartamentoEntity(final UUID id, final String nombre, final PaisEntity pais) {
        setId(id);
        setNombre(nombre);
        setPais(pais);
    }

    public static DepartamentoEntity build() {
        return new DepartamentoEntity();
    }

    public DepartamentoEntity setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public DepartamentoEntity setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public DepartamentoEntity setPais(final PaisEntity pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, new PaisEntity());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public PaisEntity getPais() {
        return pais;
    }

}
