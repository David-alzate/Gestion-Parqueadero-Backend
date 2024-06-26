package co.com.park.gp.business.domain.login;

import co.com.park.gp.business.domain.empleados.TipoEmpleadoDomain;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;

public class LoginDomain {

    private TipoEmpleadoDomain tipoEmpleado;
    private Long numeroIdentificacion;
    private String password;

    public LoginDomain(final TipoEmpleadoDomain tipoEmpleado, final Long numeroIdentificacion, final String password) {
        setTipoEmpleado(tipoEmpleado);
        setNumeroIdentificacion(numeroIdentificacion);
        setPassword(password);
    }

    public static LoginDomain build(final TipoEmpleadoDomain tipoEmpleado, final Long numeroIdentificacion, final String password) {
        return new LoginDomain(tipoEmpleado, numeroIdentificacion, password);
    }

    public static LoginDomain build() {
        return new LoginDomain(TipoEmpleadoDomain.build(), 0L, TextHelper.EMPTY);
    }

    private void setTipoEmpleado(TipoEmpleadoDomain tipoEmpleado) {
        this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoDomain.build());
    }

    private void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    private final void setPassword(String password) {
        this.password = TextHelper.applyTrim(password);
    }

    public TipoEmpleadoDomain getTipoEmpleado() {
        return tipoEmpleado;
    }

    public long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public final String getPassword() {
        return password;
    }

}