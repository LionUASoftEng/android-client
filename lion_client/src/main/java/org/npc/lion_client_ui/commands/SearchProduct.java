package org.npc.lion_client_ui.commands;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.npc.lion_client_ui.R;
import org.npc.lion_client_ui.api.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchProduct extends AppCompatActivity {

    private List<Product> products;
    private ListView productsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        //Listview for under the search button
        productsLV = (ListView) findViewById(R.id.products_list_view);

        //temp testing of listview displaying
        List<String> tempStringList = new ArrayList<String>();
        for (int x = 0; x < 25; x++){
            tempStringList.add("Product #" + x);
        }

        //populate listview with an adapter
        ArrayAdapter<String> tempArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempStringList);

        productsLV.setAdapter(tempArrayAdapter);

    }

    public void productsDetailsOnClick(View view) {
        final EditText inputSearchEditText =(EditText)findViewById(R.id.inputSearchEditText);
        final String inputSearch = inputSearchEditText.getText().toString();
        final Intent intent = new Intent(this, ProductDetails.class);
        intent.putExtra(
                this.getResources().getString(R.string.lookup_code_extras_key),
                inputSearch);

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
}
