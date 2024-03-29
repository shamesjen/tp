package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidTelegramHandle_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null address
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid addresses
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidTelegramHandle("yeoh yeoh01")); // spaces within telegram handle

        // valid addresses
        assertTrue(TelegramHandle.isValidTelegramHandle("a")); // one character
        assertTrue(TelegramHandle.isValidTelegramHandle(
                "thequickbrownfox123456789jumpsoverthelazydog")); // long telegram handle
    }

    @Test
    public void equals() {
        TelegramHandle telegramHandle = new TelegramHandle("validTelegramHandle");

        // same values -> returns true
        assertTrue(telegramHandle.equals(new TelegramHandle("validTelegramHandle")));

        // same object -> returns true
        assertTrue(telegramHandle.equals(telegramHandle));

        // null -> returns false
        assertFalse(telegramHandle.equals(null));

        // different types -> returns false
        assertFalse(telegramHandle.equals(5.0f));

        // different values -> returns false
        assertFalse(telegramHandle.equals(new TelegramHandle("otherValidTelegramHandle")));
    }
}
