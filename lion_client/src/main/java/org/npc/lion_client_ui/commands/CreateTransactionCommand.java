package org.npc.lion_client_ui.commands;

import org.npc.lion_client_ui.api.models.Transaction;
import org.npc.lion_client_ui.commands.interfaces.ResultCommandInterface;

import java.util.UUID;

public class CreateTransactionCommand implements ResultCommandInterface<Transaction> {
    @Override
    public Transaction execute() {
        return (new Transaction()).
                setCashier(UUID.fromString(DEFAULT_CASHIER)). //hard coded cashier until login is established
                setTransactionType("sale").
                setAmount(0).setParentId(UUID.fromString("00000000-00000000-00000000-00000000"));
    }

    private static final String DEFAULT_CASHIER = "fd6c0fce-9090-470b-9993-2c9ed8ec22fa";
}
