package interaction.interfaces;

import interaction.validators.Validator;

/**
 * Created by sebastian on 06.12.16.
 */
public interface Interaction {
    String interact(final Validator validator, final String messageToUser);
}
