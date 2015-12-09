package org.npc.lion_client_ui.commands;

import org.npc.lion_client_ui.api.enums.TransactionEntryApiRequestStatus;
import org.npc.lion_client_ui.api.models.TransactionEntry;
import org.npc.lion_client_ui.api.services.TransactionEntryService;
import org.npc.lion_client_ui.commands.interfaces.ResultCommandInterface;

public class SaveTransactionEntryCommand implements ResultCommandInterface<TransactionEntry>  {
    @Override
    public TransactionEntry execute() {
        if (this.transactionEntry == null) {
            return (new TransactionEntry()).setApiRequestStatus(TransactionEntryApiRequestStatus.INVALID_INPUT);
        }
        return (new TransactionEntryService()).putTransactionEntry(this.transactionEntry);
    }

    public TransactionEntry getTransactionEntry(){
        return this.transactionEntry;
    }

    public SaveTransactionEntryCommand setTransactionEntry(TransactionEntry transactionEntry){
        this.transactionEntry = transactionEntry;
        return this;
    }

    private TransactionEntry transactionEntry;
}
