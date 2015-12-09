package org.npc.lion_client_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import org.npc.lion_client_ui.api.enums.ProductApiRequestStatus;
import org.npc.lion_client_ui.api.models.Product;
import org.npc.lion_client_ui.api.services.ProductService;
import java.util.UUID;

public class ProductDetails extends AppCompatActivity {

    private CurrentTransaction curTrans;
    private UUID productId;
    private String lookup_code;
    private String description = "Searching for product!";
    private double price = 0.00;
    private int quantity = 0;
    TextView lookup_code_textview;
    TextView description_content;
    TextView price_textview;
    TextView quantity_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        lookup_code = intent.getStringExtra(
                this.getResources().getString(R.string.lookup_code_extras_key)
        );
        curTrans = (CurrentTransaction)intent.getSerializableExtra(getString(R.string.CURRENT_TRANSACTION));

        lookup_code_textview = (TextView) findViewById(R.id.lookup_code_textView);
        lookup_code_textview.setText(R.string.lookup_code + lookup_code);
        description_content = (TextView) findViewById(R.id.description_content_textview);
        description_content.setText( description );
        price_textview = (TextView) findViewById(R.id.price_textview);
        price_textview.setText("Price :$" + String.valueOf(price));
        quantity_textview = (TextView) findViewById(R.id.quantity_textview);
        quantity_textview.setText(R.string.quantity + quantity );
    }

    @Override
    protected void onResume() {
        super.onResume();
        (new RetrieveProductTask()).execute(this.lookup_code);
    }

    private TextView getLookupCodeTextView() {
        return lookup_code_textview;
    }

    private TextView getDescriptionTextView() {
        return description_content;
    }

    private TextView getPriceTextView(){
        return price_textview;
    }

    private TextView getQuantityTextView(){
        return quantity_textview;
    }

    public void addTransEntryOnButtonClick(View view)
    {
        CurrentTransactionEntry tEntry = new CurrentTransactionEntry(productId, price, quantity, description);
        curTrans.addEntry(tEntry);
        Intent returnToTrans = new Intent(this, TransactionScreen.class)
                .putExtra(getString(R.string.CURRENT_TRANSACTION), curTrans);
        startActivity(returnToTrans);
    }

    public void cancelEntryOnButtonClick(View view)
    {
        Intent returnToSearch = new Intent(this, TransactionScreen.class);
        returnToSearch.putExtra(getString(R.string.CURRENT_TRANSACTION), curTrans);
        startActivity(returnToSearch);
    }

    private class RetrieveProductTask extends AsyncTask<String, Void, Product> {
        protected Product doInBackground(String... lookupCodes){
            return (new ProductService()).getProductbyLookupCode(lookupCodes[0]);
        }

        protected void onPostExecute(Product result) {
            if (result.getApiRequestStatus() == ProductApiRequestStatus.OK) {
                getLookupCodeTextView().setText(result.getLookupCode());
                getDescriptionTextView().setText(result.getDescription());
                getPriceTextView().setText(Double.toString(result.getPrice()));
                getQuantityTextView().setText(Integer.toString(result.getQuantity()));
                productId = result.getId();
                price = result.getPrice();
                description = result.getDescription();

            } else {
                getLookupCodeTextView().setText(result.getApiRequestStatus().name());
            }
        }
    }
}
