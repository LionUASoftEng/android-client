package org.npc.lion_client_ui.api.services;

import org.json.JSONObject;
import org.npc.lion_client_ui.api.enums.ApiLevel;
import org.npc.lion_client_ui.api.enums.TransactionEntryApiMethod;
import org.npc.lion_client_ui.api.enums.TransactionEntryApiRequestStatus;
import org.npc.lion_client_ui.api.interfaces.PathElementInterface;
import org.npc.lion_client_ui.api.models.TransactionEntry;
import org.npc.lion_client_ui.api.models.TransactionEntryListing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionEntryService extends BaseRemoteService {
    public TransactionEntry getTransactionEntry(UUID transactionEntryId) {
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { TransactionEntryApiMethod.TRANSACTIONENTRY, ApiLevel.ONE, TransactionEntryApiMethod.TRANSACTIONENTRY }), transactionEntryId
        );

        if (rawJsonObject != null) {
            return (new TransactionEntry()).loadFromJson(rawJsonObject);
        } else {
            return new TransactionEntry().setApiRequestStatus(TransactionEntryApiRequestStatus.UNKNOWN_ERROR);
        }
    }

    public List<TransactionEntry> getTransactionEntries(UUID transactionId) {
        List<TransactionEntry> transactionEntries;
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { TransactionEntryApiMethod.TRANSACTIONENTRY, ApiLevel.ONE, TransactionEntryApiMethod.TRANSACTIONENTRIES }), transactionId
        );

        if (rawJsonObject != null) {
            transactionEntries = (new TransactionEntryListing()).loadFromJson(rawJsonObject).getTransactionEntries();
        } else {
            transactionEntries = new ArrayList<TransactionEntry>(0);
        }

        return transactionEntries;
    }

    public TransactionEntry putTransactionEntry(TransactionEntry transactionEntry) {
        JSONObject rawJsonObject = this.putSingle(
                (new PathElementInterface[] { TransactionEntryApiMethod.TRANSACTIONENTRY, ApiLevel.ONE }), transactionEntry.convertToJson()
        );

        if (rawJsonObject != null) {
            return (new TransactionEntry()).loadFromJson(rawJsonObject);
        } else {
            return new TransactionEntry().setApiRequestStatus(TransactionEntryApiRequestStatus.UNKNOWN_ERROR);
        }
    }
}


