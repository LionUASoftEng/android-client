package org.npc.lion_client_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    public String lookup_code = null;
    public String description = "Product not found!";
    public String price = "$0.00";
    public String quantity = "0 units";
    TextView lookup_code_textview;
    TextView description_content;
    TextView price_textview;
    TextView quantity_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //TODO Get product info from server and put it into strings
        Intent intent = getIntent();
        lookup_code = intent.getStringExtra(
                this.getResources().getString(R.string.lookup_code_extras_key)
        );

        lookup_code_textview = (TextView) findViewById(R.id.lookup_code_textView);
        lookup_code_textview.setText(R.string.lookup_code + lookup_code);
        description_content = (TextView) findViewById(R.id.description_content_textview);
        description_content.setText( description );
        price_textview = (TextView) findViewById(R.id.price_textview);
        price_textview.setText(R.string.price + price );
        quantity_textview = (TextView) findViewById(R.id.quantity_textview);
        quantity_textview.setText(R.string.quantity + quantity );

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
