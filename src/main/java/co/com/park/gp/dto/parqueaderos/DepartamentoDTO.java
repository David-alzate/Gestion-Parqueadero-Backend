package co.com.park.gp.dto.parqueaderos;

import java.util.UUID;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

public final class DepartamentoDTO {

    private UUID id;
    private String nombre;
    private PaisDTO pais;

    public DepartamentoDTO() {
        super();
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
        setPais(PaisDTO.build());
    }

    public DepartamentoDTO(final UUID id, final String nombre, final PaisDTO pais) {
        setId(id);
        setNombre(nombre);
        setPais(pais);
    }

    public static DepartamentoDTO build() {
        return new DepartamentoDTO();
    }

    public DepartamentoDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public DepartamentoDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public DepartamentoDTO setPais(final PaisDTO pais) {
        this.pais = ObjectHelper.getObjectHelper().getDefaultValue(pais, new PaisDTO());
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public PaisDTO getPais() {
        return pais;
    }

}
