package org.npc.lion_client_ui.api.enums;

import org.npc.lion_client_ui.api.interfaces.PathElementInterface;

import java.util.HashMap;
import java.util.Map;

public enum TransactionApiMethod implements PathElementInterface {
    NONE(""),
    TRANSACTION("transaction"),
    TRANSACTIONS("transactions");

    @Override
    public String getPathValue() {
        return value;
    }

    public static TransactionApiMethod map(String key) {
        if (valueMap == null) {
            valueMap = new HashMap<>();

            for (TransactionApiMethod value : TransactionApiMethod.values()) {
                valueMap.put(value.getPathValue(), value);
            }
        }

        return (valueMap.containsKey(key) ? valueMap.get(key) : TransactionApiMethod.NONE);
    }

    private String value;

    private static Map<String, TransactionApiMethod> valueMap = null;

    private TransactionApiMethod(String value) {
        this.value = value;
    }
}
