package org.npc.lion_client_ui.commands;

import org.npc.lion_client_ui.CurrentTransactionEntry;
import org.npc.lion_client_ui.api.models.TransactionEntry;
import org.npc.lion_client_ui.commands.interfaces.ResultCommandInterface;

import java.util.UUID;

public class CreateTransactionEntryCommand implements ResultCommandInterface<TransactionEntry> {
    @Override
    public TransactionEntry execute() {
        return (new TransactionEntry()).
                setTransactionId(this.transactionEntry.getTransactionId()).
                setProductId(this.transactionEntry.getProductId()).
                setPrice(this.transactionEntry.getPrice()).
                setQuantity(this.transactionEntry.getQuantity());
    }

    public CreateTransactionEntryCommand setCurrentTransactionEntry(UUID TransactionID, CurrentTransactionEntry currentTransactionEntry){
        this.transactionEntry.setTransactionId(TransactionID);
        this.transactionEntry.setProductId(currentTransactionEntry.getProductid());
        this.transactionEntry.setQuantity(currentTransactionEntry.getQuantity());
        this.transactionEntry.setPrice(currentTransactionEntry.getPrice());
        return this;
    }

    private TransactionEntry transactionEntry;
}
