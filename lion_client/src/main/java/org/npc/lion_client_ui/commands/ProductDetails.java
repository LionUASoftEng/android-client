package org.npc.lion_client_ui.commands;

import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.npc.lion_client_ui.api.enums.ProductApiRequestStatus;
import org.npc.lion_client_ui.api.models.Product;
import org.npc.lion_client_ui.api.services.ProductService;
import org.npc.lion_client_ui.R;

public class ProductDetails extends AppCompatActivity {

    public String description = "Product not found!";
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

        lookup_code_textview = (TextView) findViewById(R.id.lookup_code_textView);
        description_content = (TextView) findViewById(R.id.description_content_textview);
        description_content.setText(description);
        price_textview = (TextView) findViewById(R.id.price_textview);
        quantity_textview = (TextView) findViewById(R.id.quantity_textview);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //*********************************
        //commented out temporarily because without a valid UUID the app will crash here
        //
        //(new RetrieveProductTask()).execute(this.lookup_code);
    }

    private String lookup_code;
    private class RetrieveProductTask extends AsyncTask<String, Void, Product>{
        protected Product doInBackground(String... lookupCodes){
            return (new ProductService()).getProductbyLookupCode(lookupCodes[0]);
        }

        protected void onPostExecute(Product result) {
            if (result.getApiRequestStatus() == ProductApiRequestStatus.OK) {
                getLookupCodeTextView().setText(result.getLookupCode());
                getDescriptionTextView().setText(result.getDescription());
                String dPrice = Double.toString(result.getPrice());
                getPriceTextView().setText(dPrice);
                String iQuantity = Integer.toString(result.getQuantity());
                getQuantityTextView().setText(iQuantity);
            } else {
                getLookupCodeTextView().setText(result.getApiRequestStatus().name());
            }
        }
    }

    //return to SearchProduct screen 3
    public void cancelOnClick(View view){
        finish();
    }

    public void applyOnClick(View view){
        //take quantity from inputAddQuantity textview and add to actual quantity
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


}