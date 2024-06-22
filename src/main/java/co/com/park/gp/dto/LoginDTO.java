package co.com.park.gp.dto;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;

public final class LoginDTO {

    private TipoEmpleadoDTO tipoEmpleado;
    private Long numeroIdentificacion;
    private String password;

    public LoginDTO() {
        super();
        setTipoEmpleado(TipoEmpleadoDTO.build());
        setNumeroIdentificacion(0L);
        setPassword(TextHelper.EMPTY);
    }

    public LoginDTO(TipoEmpleadoDTO tipoEmpleado, String password, Long numeroIdentificacion) {
        setTipoEmpleado(tipoEmpleado);
        setNumeroIdentificacion(numeroIdentificacion);
        setPassword(password);
    }

    public static LoginDTO build() {
        return new LoginDTO();
    }

    public LoginDTO setTipoEmpleado(TipoEmpleadoDTO tipoEmpleado) {
        this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoDTO.build());
        return this;
    }

    public LoginDTO setNumeroIdentificacion(final Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public LoginDTO setPassword(final String password) {
        this.password = TextHelper.applyTrim(password);
        return this;
    }

    public TipoEmpleadoDTO getTipoEmpleado() {
        return tipoEmpleado;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getPassword() {
        return password;
    }

}
