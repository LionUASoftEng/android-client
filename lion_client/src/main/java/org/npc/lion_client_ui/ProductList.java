package org.npc.lion_client_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.npc.lion_client_ui.adapters.ProductListAdapter;
import org.npc.lion_client_ui.api.models.Product;
import org.npc.lion_client_ui.api.services.ProductService;
import org.npc.lion_client_ui.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {

    private List<Product> products;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view_item);

        this.products = new ArrayList<>();
        this.productListAdapter = new ProductListAdapter(this, this.products);

        this.getProductsListView().setAdapter(this.productListAdapter);
        this.getProductsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), org.npc.lion_client_ui.ProductDetails.class);
                Product selectedProduct = (Product) getProductsListView().getItemAtPosition(position);

                intent.putExtra(getString(R.string.lookup_code_extras_key), selectedProduct.getLookupCode());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        (new RetrieveProductsTask()).execute();
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

    private ListView getProductsListView() {
        return (ListView) this.findViewById(R.id.transaction_entry_list_view);
    }

    private class RetrieveProductsTask extends AsyncTask<Void, Void, List<Product>> {
        protected List<Product> doInBackground(Void... params) {
            return (new ProductService()).getProducts();
        }

        protected void onPostExecute(List<Product> results) {
            products.clear();

            for (Product product : results) {
                products.add(product);
            }

            productListAdapter.notifyDataSetChanged();
        }
    }
}
