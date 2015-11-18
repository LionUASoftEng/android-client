package org.npc.lion_client_ui.api.services;

import org.json.JSONObject;
import org.npc.lion_client_ui.api.enums.ApiLevel;
import org.npc.lion_client_ui.api.enums.TransactionApiMethod;
import org.npc.lion_client_ui.api.enums.TransactionApiRequestStatus;
import org.npc.lion_client_ui.api.interfaces.PathElementInterface;
import org.npc.lion_client_ui.api.models.Transaction;
import org.npc.lion_client_ui.api.models.TransactionListing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService extends BaseRemoteService {
    public Transaction getTransaction(UUID transactionId) {
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { ApiLevel.ONE, TransactionApiMethod.TRANSACTION }), transactionId
        );

        if (rawJsonObject != null) {
            return (new Transaction()).loadFromJson(rawJsonObject);
        } else {
            return new Transaction().setApiRequestStatus(TransactionApiRequestStatus.UNKNOWN_ERROR);
        }
    }


    public List<Transaction> getTransactions() {
        List<Transaction> transactions;
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { ApiLevel.ONE, TransactionApiMethod.TRANSACTIONS})
        );

        if (rawJsonObject != null) {
            transactions = (new TransactionListing()).loadFromJson(rawJsonObject).getTransactions();
        } else {
            transactions = new ArrayList<Transaction>(0);
        }

        return transactions;
    }

    public Transaction putTransaction(Transaction transaction) {
        JSONObject rawJsonObject = this.putSingle(
                (new PathElementInterface[] { ApiLevel.ONE }), transaction.convertToJson()
        );

        if (rawJsonObject != null) {
            return (new Transaction()).loadFromJson(rawJsonObject);
        } else {
            return new Transaction().setApiRequestStatus(TransactionApiRequestStatus.UNKNOWN_ERROR);
        }
    }
}


