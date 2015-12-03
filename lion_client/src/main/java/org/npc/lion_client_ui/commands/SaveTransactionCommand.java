package org.npc.lion_client_ui.commands;

import org.npc.lion_client_ui.api.enums.TransactionApiRequestStatus;
import org.npc.lion_client_ui.api.models.Transaction;
import org.npc.lion_client_ui.api.services.TransactionService;
import org.npc.lion_client_ui.commands.interfaces.ResultCommandInterface;

public class SaveTransactionCommand implements ResultCommandInterface<Transaction>  {
    @Override
    public Transaction execute() {
        if (this.transaction == null) {
            return (new Transaction()).setApiRequestStatus(TransactionApiRequestStatus.INVALID_INPUT);
        }
        return (new TransactionService()).putTransaction(this.transaction);
    }

    public Transaction getTransaction(){
        return this.transaction;
    }

    public SaveTransactionCommand setTransaction(Transaction transaction){
        this.transaction = transaction;
        return this;
    }

    private Transaction transaction;
}
