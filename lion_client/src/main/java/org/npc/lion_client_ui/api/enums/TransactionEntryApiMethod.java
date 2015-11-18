package org.npc.lion_client_ui.api.enums;

import org.npc.lion_client_ui.api.interfaces.PathElementInterface;

import java.util.HashMap;
import java.util.Map;

public enum TransactionEntryApiMethod implements PathElementInterface {
    NONE(""),
    TRANSACTIONENTRY("transactionentry"),
    TRANSACTIONENTRIES("transactionentries");

    @Override
    public String getPathValue() {
        return value;
    }

    public static TransactionEntryApiMethod map(String key) {
        if (valueMap == null) {
            valueMap = new HashMap<>();

            for (TransactionEntryApiMethod value : TransactionEntryApiMethod.values()) {
                valueMap.put(value.getPathValue(), value);
            }
        }

        return (valueMap.containsKey(key) ? valueMap.get(key) : TransactionEntryApiMethod.NONE);
    }

    private String value;

    private static Map<String, TransactionEntryApiMethod> valueMap = null;

    private TransactionEntryApiMethod(String value) {
        this.value = value;
    }
}
