package actions;

import context.ContextManager;
import context.transfer.CountryTransferContextInformation;
import utils.Utils;

import java.util.List;
import java.util.Set;

/**
 * Created by sebastian on 20.03.17.
 */
public class TransferFinalizeAction implements Action {
    @Override
    public int perform() {
        final Set<String> dataNames = ContextManager.getInformationNamesFromContext();
        if (allRequiredInformationArePresent(dataNames)) {
            performCountryTransfer();
            return -1;
        } else {
            //Go back to initial state, let -1 be initial state
            return -1;
        }
    }

    private boolean allRequiredInformationArePresent(final Set<String> dataNames) {
        return dataNames.containsAll(CountryTransferContextInformation.getInformationAsList());
    }

    private void performCountryTransfer() {
        final StringBuilder builder = new StringBuilder("Running country transfer with following data:");
        final List<String> dataNames = CountryTransferContextInformation.getInformationAsList();
        for (String dataName : dataNames) {
            builder.append(String.format(
                    "\t%s: %s",
                    dataName,
                    ContextManager.getInformationFromContext(dataName)
            ));
        }
        Utils.printLineMessageToUser(builder.toString());
    }
}
