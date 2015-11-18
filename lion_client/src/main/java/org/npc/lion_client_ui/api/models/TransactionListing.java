package org.npc.lion_client_ui.api.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.npc.lion_client_ui.api.interfaces.LoadFromJsonInterface;
import org.npc.lion_client_ui.api.models.fieldnames.TransactionListingFieldNames;

import java.util.LinkedList;
import java.util.List;

public class TransactionListing implements LoadFromJsonInterface<TransactionListing> {
    private List<Transaction> transactions;
    public List<Transaction> getTransactions() {
        return this.transactions;
    }
    public TransactionListing setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }
    public TransactionListing addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        return this;
    }

    @Override
    public TransactionListing loadFromJson(JSONObject rawJsonObject) {
        JSONArray transactions = rawJsonObject.optJSONArray(TransactionListingFieldNames.TRANSACTIONS);

        if (transactions != null) {
            try {
                for (int i = 0; i < transactions.length(); i++) {
                    JSONObject jsonTransaction = transactions.getJSONObject(i);

                    this.addTransaction((new Transaction()).loadFromJson(jsonTransaction));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public TransactionListing() {
        this.transactions = new LinkedList<Transaction>();
    }
}
