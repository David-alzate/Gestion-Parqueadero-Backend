package co.com.park.gp.entity.clientes;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;
import co.com.park.gp.entity.comunes.TipoIdentificacionEntity;

import java.util.UUID;

public final class ClienteEntity {

    private UUID id;
    private TipoIdentificacionEntity tipoIdentificacion;
    private Long numeroIdentificacion;
    private String nombre;
    private String apellido;    
    private String correoElectronico;

    public ClienteEntity() {
        setId(UUIDHelper.getDefault());
        setTipoIdentificacion(TipoIdentificacionEntity.build());
        setNumeroIdentificacion(0L);
        setNombre(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setCorreoElectronico(TextHelper.EMPTY);
    }

    public ClienteEntity(final UUID id, final TipoIdentificacionEntity tipoIdentificacion, final Long numeroIdentificacion, final String nombre,
                         final String apellido, final String correoElectronico) {
        setId(id);
        setTipoIdentificacion(tipoIdentificacion);
        setNumeroIdentificacion(numeroIdentificacion);
        setNombre(nombre);
        setApellido(apellido);
        setCorreoElectronico(correoElectronico);
    }

    public static ClienteEntity build() {
        return new ClienteEntity();
    }

    public ClienteEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public ClienteEntity setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
        this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion, TipoIdentificacionEntity.build());
        return this;
    }

    public ClienteEntity setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public ClienteEntity setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public ClienteEntity setApellido(String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }

    public ClienteEntity setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public TipoIdentificacionEntity getTipoIdentificacion() {
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
