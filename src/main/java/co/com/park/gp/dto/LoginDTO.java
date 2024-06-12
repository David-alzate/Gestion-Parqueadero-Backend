package co.com.park.gp.dto;

import co.com.park.gp.crosscutting.helpers.TextHelper;

public final class LoginDTO {

    private String correoElectronico;
    private String password;

    public LoginDTO() {
        super();
        setCorreoElectronico(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
    }

    public LoginDTO(final String correoElectronico, final String password) {
        setCorreoElectronico(correoElectronico);
        setPassword(password);
    }

    public static LoginDTO build() {
        return new LoginDTO();
    }

    public final LoginDTO setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public final LoginDTO setPassword(final String password) {
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
