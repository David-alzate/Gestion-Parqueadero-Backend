package co.com.park.gp.crosscutting.helpers;

import java.util.UUID;

public final class UUIDHelper {

	public static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000";

	private UUIDHelper() {
		super();
	}

	public static final UUID convertToUUID(final String uuidAsString) {
		return UUID.fromString(uuidAsString);
	}

	public static final UUID getDefault(final UUID value, final UUID defaultValue) {
		return ObjectHelper.getObjectHelper().getDefaultValue(value, defaultValue);
	}

	public static final UUID getDefault() {
		return convertToUUID(DEFAULT_UUID_STRING);
	}

	public static final UUID generate() {
		return UUID.randomUUID();
	}
}
