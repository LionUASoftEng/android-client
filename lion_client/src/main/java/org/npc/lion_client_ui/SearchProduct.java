package org.npc.lion_client_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.Serializable;

public class SearchProduct extends AppCompatActivity {

    private Transaction curTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        curTrans = (Transaction)intent.getSerializableExtra("trans");
        setContentView(R.layout.activity_search_product);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void productsDetailsOnClick(View view) {
        final EditText inputSearchEditText =(EditText)findViewById(R.id.inputSearchEditText);
        final String inputSearch = inputSearchEditText.getText().toString();
        final Intent intent = new Intent(this, ProductDetails.class);
        intent.putExtra(
                this.getResources().getString(R.string.lookup_code_extras_key),
                inputSearch);
        intent.putExtra("trans", curTrans);

        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cancelSearch(View view)
    {
        Intent returnToTrans = new Intent(this, TransactionScreen.class);
        returnToTrans.putExtra("trans", curTrans);
        startActivity(returnToTrans);
    }
}
