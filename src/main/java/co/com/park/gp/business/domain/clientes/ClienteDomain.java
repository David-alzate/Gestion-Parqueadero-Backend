package co.com.park.gp.business.domain.clientes;

import co.com.park.gp.business.domain.comunes.TipoIdentificacionDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public final class ClienteDomain {

    private UUID id;
    private TipoIdentificacionDomain tipoIdentificacion;
    private Long numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    public ClienteDomain(final UUID id, final TipoIdentificacionDomain tipoIdentificacion, final Long numeroIdentificacion,
                         final String nombre, final String apellido, final String correoElectronico) {
        setId(id);
        setTipoIdentificacion(tipoIdentificacion);
        setNumeroIdentificacion(numeroIdentificacion);
        setNombre(nombre);
        setApellido(apellido);
        setCorreoElectronico(correoElectronico);
    }

    public static ClienteDomain build(final UUID id, final TipoIdentificacionDomain tipoIdentificacion, final Long numeroIdentificacion,
                                      final String nombre, final String apellido, final String correoElectronico) {
        return new ClienteDomain(id, tipoIdentificacion, numeroIdentificacion, nombre, apellido, correoElectronico);
    }

    public static ClienteDomain build(final UUID id) {
        return new ClienteDomain(id, TipoIdentificacionDomain.build(), 0L, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY);
    }

    public static ClienteDomain build() {
        return new ClienteDomain(UUIDHelper.getDefault(), TipoIdentificacionDomain.build(), 0L, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY);
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public void setTipoIdentificacion(TipoIdentificacionDomain tipoIdentificacion) {
        this.tipoIdentificacion = ObjectHelper.getObjectHelper().getDefaultValue(tipoIdentificacion, TipoIdentificacionDomain.build());
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public void setApellido(String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
    }

    public UUID getId() {
        return id;
    }

    public TipoIdentificacionDomain getTipoIdentificacion() {
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
