package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Housing {
    public final String value;

    public static final String MESSAGE_CONSTRAINTS = "Housing type should be either 'HDB', 'Condo', 'Landed' or 'nil'";

    public static final String VALIDATION_REGEX = "^(HDB|Condo|Landed|nil)$";

    public Housing(String value) {
        requireNonNull(value);
        checkArgument(isValidHousing(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public static boolean isValidHousing(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Housing otherHousing = (Housing) other;
        return value.equals(otherHousing.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
