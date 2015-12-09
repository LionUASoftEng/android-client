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
                setAmount(transaction.getAmount()).
                setParentId(UUID.fromString(DEFAULT_PARENTID)); //hard coded parentId until use established.
    }

    public CreateTransactionCommand setCurrentTransactionAmount(Double currentTransactionAmount){
        this.transaction.setAmount(currentTransactionAmount);
        return this;
    }

    private Transaction transaction;
    private static final String DEFAULT_CASHIER = "fd6c0fce-9090-470b-9993-2c9ed8ec22fa";
    private static final String DEFAULT_PARENTID = "2c8c2c1d-a077-4e0c-9a70-02aa0139a2e0";
}
