package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes a previously undone command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redoes a previously undone command."
            + "Example: " + COMMAND_WORD;

    //TODO: include information about redone command
    public static final String MESSAGE_SUCCESS = "Redid previously undone command.";

    public static final String MESSAGE_CANNOT_REDO = "Unable to redo since there were no previously undone commands.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.canRedoAddressBook()) {
            model.redoAddressBook();
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(MESSAGE_CANNOT_REDO);
        }
    }
}