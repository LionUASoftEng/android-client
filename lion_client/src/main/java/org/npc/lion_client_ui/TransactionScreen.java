package org.npc.lion_client_ui;

import android.os.AsyncTask;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

import org.npc.lion_client_ui.api.enums.TransactionApiRequestStatus;
import org.npc.lion_client_ui.api.models.Transaction;
import org.npc.lion_client_ui.api.models.TransactionEntry;
import org.npc.lion_client_ui.commands.CreateTransactionCommand;
import org.npc.lion_client_ui.commands.CreateTransactionEntryCommand;
import org.npc.lion_client_ui.commands.SaveTransactionCommand;
import org.npc.lion_client_ui.commands.SaveTransactionEntryCommand;


public class TransactionScreen extends AppCompatActivity {

    private CurrentTransaction curTrans;
    private UUID savedTransactionId;
    private CurrentTransactionEntry tempCurrentTransactionEntry;

    TextView transtotal;
    TextView paytotal;
    TextView rembalance;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_screen);

        Intent intent = getIntent();
        curTrans = (CurrentTransaction)intent.getSerializableExtra(getString(R.string.CURRENT_TRANSACTION));

        transtotal = (TextView) findViewById(R.id.transtotal);
        transtotal.setText("CurrentTransaction Total: $" + (String.valueOf(curTrans.getAmount())));
        paytotal = (TextView) findViewById(R.id.paytotal);
        paytotal.setText("Payment Total: $" + (String.valueOf(curTrans.getPaidAmount())));
        rembalance = (TextView) findViewById(R.id.rembalance);
        rembalance.setText("Remaining Balance: $" + (String.valueOf(curTrans.getRemBalance())));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Intent intent = getIntent();
        curTrans = (CurrentTransaction)intent.getSerializableExtra(getString(R.string.CURRENT_TRANSACTION));

        transtotal.setText("CurrentTransaction Total: $" + (String.valueOf(curTrans.getAmount())));
        paytotal.setText("Payment Total: $" + (String.valueOf(curTrans.getPaidAmount())));
        rembalance.setText("Remaining Balance: $" + (String.valueOf(curTrans.getRemBalance())));
    }

    public void addProductButtonOnClick(View view)
    {
        Intent intentRequestProduct = new Intent(this, SearchProduct.class).
                putExtra(getString(R.string.CURRENT_TRANSACTION), curTrans);
        startActivity(intentRequestProduct);
    }

    public void completeTransactionButtonOnClick(View view)
    {
        (new CreateTransactionTask()).execute();
        for(int i = 0; i < curTrans.getEntries().size(); i++)
        {
            tempCurrentTransactionEntry = curTrans.getEntries().get(i);
            (new CreateTransactionEntryTask()).execute();
        }
    }

    public void doNothing(View view) {
        //this is doing nothing
    }

    private class CreateTransactionTask extends AsyncTask<Void, Void, Transaction> {
        protected org.npc.lion_client_ui.api.models.Transaction doInBackground(Void... params)
        {
            return (new SaveTransactionCommand()).
                    setTransaction(
                            (new CreateTransactionCommand()).
                                    setCurrentTransactionAmount(curTrans.getAmount()).
                                    execute()
                    ).
                    execute();
        }

        protected void onPostExecute(Transaction result) {
            boolean successfulSave = (result.getApiRequestStatus() == TransactionApiRequestStatus.OK);

            if(successfulSave){
                savedTransactionId = result.getId();


            }

            new AlertDialog.Builder(TransactionScreen.this).
                    setTitle(getString(R.string.TRANSACTION)).setMessage(successfulSave ? getString(R.string.transaction_save_success) : getString(R.string.transaction_save_failure)).
                    show();
        }
    }

    private class CreateTransactionEntryTask extends AsyncTask<Void, Void, TransactionEntry>
    {
        protected org.npc.lion_client_ui.api.models.TransactionEntry doInBackground(Void... params){
            return (new SaveTransactionEntryCommand()).
                    setTransactionEntry(
                            (new CreateTransactionEntryCommand()).
                                    setCurrentTransactionEntry(savedTransactionId, tempCurrentTransactionEntry).
                                    execute()
                    ).
                    execute();
        }
    }
}

