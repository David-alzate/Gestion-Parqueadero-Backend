package co.com.park.gp.business.domain;

import co.com.park.gp.crosscutting.helpers.TextHelper;

public class LoginDomain {

	private String correoElectronico;
	private String password;

	public LoginDomain(final String correoElectronico, final String password) {
		setCorreoElectronico(correoElectronico);
		setPassword(password);
	}
	
	public static final LoginDomain build(final String correoElectronico, final String password) {
		return new LoginDomain(correoElectronico, password);
	}
	
	public static final LoginDomain build() {
		return new LoginDomain(TextHelper.EMPTY, TextHelper.EMPTY);
	}

	private final void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = TextHelper.applyTrim(correoElectronico);
	}

	private final void setPassword(String password) {
		this.password = TextHelper.applyTrim(password);
	}

	public final String getCorreoElectronico() {
		return correoElectronico;
	}

	public final String getPassword() {
		return password;
	}

}