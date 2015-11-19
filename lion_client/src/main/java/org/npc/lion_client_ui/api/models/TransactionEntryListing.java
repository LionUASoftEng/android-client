package org.npc.lion_client_ui.api.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.npc.lion_client_ui.api.interfaces.LoadFromJsonInterface;
import org.npc.lion_client_ui.api.models.fieldnames.TransactionEntryListingFieldNames;

import java.util.LinkedList;
import java.util.List;

public class TransactionEntryListing implements LoadFromJsonInterface<TransactionEntryListing> {
    private List<TransactionEntry> transactionEntries;
    public List<TransactionEntry> getTransactionEntries() {
        return this.transactionEntries;
    }
    public TransactionEntryListing setTransactionEntries(List<TransactionEntry> transactionEntries) {
        this.transactionEntries = transactionEntries;
        return this;
    }
    public TransactionEntryListing addTransactionEntry(TransactionEntry transactionEntry) {
        this.transactionEntries.add(transactionEntry);
        return this;
    }

    @Override
    public TransactionEntryListing loadFromJson(JSONObject rawJsonObject) {
        JSONArray transactionEntries = rawJsonObject.optJSONArray(TransactionEntryListingFieldNames.TRANSACTIONENTRIES);

        if (transactionEntries != null) {
            try {
                for (int i = 0; i < transactionEntries.length(); i++) {
                    JSONObject jsonTransactionEntry = transactionEntries.getJSONObject(i);

                    this.addTransactionEntry((new TransactionEntry()).loadFromJson(jsonTransactionEntry));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public TransactionEntryListing() {
        this.transactionEntries = new LinkedList<TransactionEntry>();
    }
}
