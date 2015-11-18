package org.npc.lion_client_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class TransactionScreen extends AppCompatActivity {

    private Transaction curTrans;

    TextView transtotal;
    TextView paytotal;
    TextView rembalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_screen);

        Intent intent = getIntent();
        curTrans = (Transaction)intent.getSerializableExtra("trans");

        transtotal = (TextView) findViewById(R.id.transtotal);
        transtotal.setText("Transaction Total: $" + (String.valueOf(curTrans.getAmount())));
        paytotal = (TextView) findViewById(R.id.paytotal);
        paytotal.setText("Payment Total: $" + (String.valueOf(curTrans.getPaidAmount())));
        rembalance = (TextView) findViewById(R.id.rembalance);
        rembalance.setText("Remaining Balance: $" + (String.valueOf(curTrans.getRemBalance())));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        curTrans = (Transaction)intent.getSerializableExtra("trans");

        transtotal.setText("Transaction Total: $" + (String.valueOf(curTrans.getAmount())));
        paytotal.setText("Payment Total: $" + (String.valueOf(curTrans.getPaidAmount())));
        rembalance.setText("Remaining Balance: $" + (String.valueOf(curTrans.getRemBalance())));
    }

    public void addProduct(View view)
    {
        Intent intentRequestProduct = new Intent(this, SearchProduct.class);
        intentRequestProduct.putExtra("trans", curTrans);
        startActivity(intentRequestProduct);
    }

    public void doNothing(View view)
    {

    }
}
