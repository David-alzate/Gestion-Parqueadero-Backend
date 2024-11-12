package co.com.park.gp.crosscutting.helpers;

import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Pattern;

public class TextHelper {

    public static final String EMPTY = "";
    public static final String UNDERLINE = "_";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PLACA_CARRO_PATTERN = Pattern.compile("^[A-Z]{3}\\d{3}$");
    private static final Pattern PLACA_MOTO_PATTERN = Pattern.compile("^[A-Z]{3}\\d{2}[A-Z]$");

    private TextHelper() {
        super();
    }

    public static boolean isNull(final String string) {
        return ObjectHelper.getObjectHelper().isNull(string);
    }

    public static boolean isNullOrEmpty(final String string) {
        return isNull(string) || EMPTY.equals(applyTrim(string));
    }

    public static String getDefaultValue(final String string, final String defaultValue) {
        return ObjectHelper.getObjectHelper().getDefaultValue(string, defaultValue);
    }

    public static String getDefaultValue(final String string) {
        return getDefaultValue(string, EMPTY);
    }

    public static String applyTrim(final String string) {
        return getDefaultValue(string).trim();
    }

    public static String concatenate(final String... strings) {
        final var sb = new StringBuilder(EMPTY);

        if (!ObjectHelper.getObjectHelper().isNull(strings)) {
            for (final var string : strings) {
                sb.append(applyTrim(string));
            }
        }
        return sb.toString();
    }

    public static String reemplazarParametro(String mensaje, String... parametros) {
        String mensajeReemplazado = mensaje;
        for (int i = 0; i < parametros.length; i++) {
            String marcador = "${" + (i + 1) + "}";
            mensajeReemplazado = mensajeReemplazado.replace(marcador, parametros[i]);
        }
        return mensajeReemplazado;
    }

    public static boolean emailValido(final String email) {
        return !isNullOrEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean placaCarroValida(final String placa) {
        return !isNullOrEmpty(placa) && PLACA_CARRO_PATTERN.matcher(placa).matches();
    }

    public static boolean placaMotoValida(final String placa) {
        return !isNullOrEmpty(placa) && PLACA_MOTO_PATTERN.matcher(placa).matches();
    }

    public static String convertToLowercase(String string) {
        return string.toLowerCase();
    }
    
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}