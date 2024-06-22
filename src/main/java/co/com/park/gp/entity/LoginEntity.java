package co.com.park.gp.entity;

import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.crosscutting.helpers.TextHelper;

public final class LoginEntity {

    private TipoEmpleadoEntity tipoEmpleado;
    private int numeroIdentificacion;
    private String password;

    public LoginEntity() {
        super();
        setTipoEmpleado(new TipoEmpleadoEntity());
        setNumeroIdentificacion(0);
        setPassword(TextHelper.EMPTY);
    }

    public static LoginEntity build() {
        return new LoginEntity();
    }

    public LoginEntity(TipoEmpleadoEntity tipoEmpleado, String password, int numeroIdentificacion) {
        setTipoEmpleado(tipoEmpleado);
        setNumeroIdentificacion(numeroIdentificacion);
        setPassword(password);
    }

    public LoginEntity setTipoEmpleado(TipoEmpleadoEntity tipoEmpleado) {
        this.tipoEmpleado = ObjectHelper.getObjectHelper().getDefaultValue(tipoEmpleado, TipoEmpleadoEntity.build());
        return this;
    }

    public LoginEntity setNumeroIdentificacion(final int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public LoginEntity setPassword(final String password) {
        this.password = TextHelper.applyTrim(password);
        return this;
    }

    public int getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public TipoEmpleadoEntity getTipoEmpleado() {
        return tipoEmpleado;
    }

    public String getPassword() {
        return password;
    }

}
