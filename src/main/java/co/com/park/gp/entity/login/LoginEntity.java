package co.com.park.gp.entity.login;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;
import co.com.park.gp.entity.empleados.TipoEmpleadoEntity;

public final class LoginEntity {

    private TipoEmpleadoEntity tipoEmpleado;
    private Long numeroIdentificacion;
    private String password;

    public LoginEntity() {
        super();
        setTipoEmpleado(new TipoEmpleadoEntity());
        setNumeroIdentificacion(0L);
        setPassword(TextHelper.EMPTY);
    }

    public static LoginEntity build() {
        return new LoginEntity();
    }

    public LoginEntity(TipoEmpleadoEntity tipoEmpleado, String password, Long numeroIdentificacion) {
        setTipoEmpleado(tipoEmpleado);
        setNumeroIdentificacion(numeroIdentificacion);
        setPassword(password);
    }

    public LoginEntity setTipoEmpleado(TipoEmpleadoEntity tipoEmpleado) {
        this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoEntity.build());
        return this;
    }

    public LoginEntity setNumeroIdentificacion(final Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public LoginEntity setPassword(final String password) {
        this.password = TextHelper.applyTrim(password);
        return this;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public TipoEmpleadoEntity getTipoEmpleado() {
        return tipoEmpleado;
    }

    public String getPassword() {
        return password;
    }

}
