package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's type of animal fostered, if any, in the address book.
 */
public class AnimalType {
    public static final String MESSAGE_CONSTRAINTS = "If fosterer is available, animal type should be "
            + "'able.Dog' / 'able.Cat'.\n"
            + "If animal type information is not available, it should be inputted as 'nil'.\n"
            + "If fosterer is NOT available and is currently fostering, animal type should be "
            + "'current.Dog' / 'current.Cat'.\n"
            + "If fosterer is currently unable to foster, "
            + "animal type should be inputted as 'nil'.\n"
            + "If availability is 'nil', animal type should be 'nil' too. ";

    public static final String VALIDATION_REGEX_AVAILABLE = "^(able\\.Dog|able\\.Cat|nil)$";
    public static final String VALIDATION_REGEX_NOT_AVAILABLE = "^(current\\.Dog|current\\.Cat|nil)$";

    public static final String ABLE_CAT_WORD = "able.Cat";
    public static final String ABLE_DOG_WORD = "able.Dog";
    public static final String CURRENT_CAT_WORD = "current.Cat";
    public static final String CURRENT_DOG_WORD = "current.Dog";
    public static final AnimalType ABLE_CAT = new AnimalType(ABLE_CAT_WORD, Availability.AVAILABLE);
    public static final AnimalType ABLE_DOG = new AnimalType(ABLE_DOG_WORD, Availability.AVAILABLE);
    public static final AnimalType CURRENT_CAT = new AnimalType(CURRENT_CAT_WORD, Availability.NOT_AVAILABLE);
    public static final AnimalType CURRENT_DOG = new AnimalType(CURRENT_DOG_WORD, Availability.NOT_AVAILABLE);

    public static final String VALIDATION_REGEX_NIL = "^(nil)$";


    public final Availability availability;
    public final String value;

    /**
     * Constructs an {@code AnimalType}.
     *
     * @param value A valid animal type.
     * @param availability The availability of the fosterer.
     */
    public AnimalType(String value, Availability availability) {
        requireNonNull(availability);
        requireNonNull(value);

        if (availability.equals(new Availability("Available"))) {
            checkArgument(isValidAnimalType(value, VALIDATION_REGEX_AVAILABLE), MESSAGE_CONSTRAINTS);
        } else if (availability.equals(new Availability("NotAvailable"))) {
            checkArgument(isValidAnimalType(value, VALIDATION_REGEX_NOT_AVAILABLE), MESSAGE_CONSTRAINTS);
        } else if (availability.equals(new Availability("nil"))) {
            checkArgument(isValidAnimalType(value, VALIDATION_REGEX_NIL), MESSAGE_CONSTRAINTS);
        }

        this.value = value;
        this.availability = availability;
    }

    public static boolean isValidAnimalType(String test, String validationRegex) {
        return test.matches(validationRegex);
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
        AnimalType otherAnimalType = (AnimalType) other;
        return value.equals(otherAnimalType.value) && availability.equals(otherAnimalType.availability);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
