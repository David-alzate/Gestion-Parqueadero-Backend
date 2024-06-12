package co.com.park.gp.entity;

import co.com.park.gp.crosscutting.helpers.TextHelper;

public final class LoginEntity {

    private String correoElectronico;
    private String password;

    public LoginEntity() {
        super();
        setCorreoElectronico(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
    }

    public LoginEntity(final String correoElectronico, final String password) {
        setCorreoElectronico(correoElectronico);
        setPassword(password);
    }

    public static LoginEntity build() {
        return new LoginEntity();
    }

    public final LoginEntity setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public final LoginEntity setPassword(final String password) {
        this.password = TextHelper.applyTrim(password);
        return this;
    }

    public final String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPassword() {
        return password;
    }

}
