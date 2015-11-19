package org.npc.lion_client_ui;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void startTrans(View view){
        //import Transaction model and make a new one here
        Transaction curTrans = new Transaction();
        Intent intentPassTrans = new Intent(this, TransactionScreen.class);
        intentPassTrans.putExtra("trans", curTrans);
        startActivity(intentPassTrans);
    }

}
