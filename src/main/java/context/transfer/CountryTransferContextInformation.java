package context.transfer;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by sebastian on 20.03.17.
 */
public class CountryTransferContextInformation {
    public static final String RECIPIENT_NAME = "Recipient Name";
    public static final String RECIPIENT_SURNAME = "Recipient Surname";
    public static final String ACCOUNT_NUMBER = "Account Number";
    public static final String TRANSFER_TITLE = "Transfer State";

    public static List<String> getInformationAsList() {
        return ImmutableList.of(
                RECIPIENT_NAME,
                RECIPIENT_SURNAME,
                ACCOUNT_NUMBER,
                TRANSFER_TITLE
        );
    }
}
