package co.com.park.gp.dto.clientes;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.dto.comunes.TipoIdentificacionDTO;

import java.util.UUID;

public final class ClienteDTO {

    private UUID id;
    private TipoIdentificacionDTO tipoIdentificacion;
    private Long numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    public ClienteDTO() {
        setId(UUIDHelper.getDefault());
        setTipoIdentificacion(TipoIdentificacionDTO.build());
        setNumeroIdentificacion(0L);
        setNombre(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setCorreoElectronico(TextHelper.EMPTY);
    }

    public ClienteDTO(final UUID id, final TipoIdentificacionDTO tipoIdentificacion, final Long numeroIdentificacion, final String nombre,
                      final String apellido, final String correoElectronico) {
        setId(id);
        setTipoIdentificacion(tipoIdentificacion);
        setNumeroIdentificacion(numeroIdentificacion);
        setNombre(nombre);
        setApellido(apellido);
        setCorreoElectronico(correoElectronico);
    }

    public static ClienteDTO build() {
        return new ClienteDTO();
    }

    public ClienteDTO setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public ClienteDTO setTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacion) {
        this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion, TipoIdentificacionDTO.build());
        return this;
    }

    public ClienteDTO setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public ClienteDTO setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public ClienteDTO setApellido(String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }

    public ClienteDTO setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public TipoIdentificacionDTO getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
}
