package org.npc.lion_client_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    public String product_id = null;
    public String description = "Product not found!";
    public String price = "$0.00";
    public String quantity = "0 units";
    TextView product_id_textview;
    TextView description_content;
    TextView price_textview;
    TextView quantity_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);

        //TODO Get product info from server and put it into strings
        Intent i = getIntent();
        product_id = i.getStringExtra("search_string");

        product_id_textview = (TextView) findViewById(R.id.product_id_textView);
        product_id_textview.setText("Product ID: " + product_id );
        description_content = (TextView) findViewById(R.id.description_content_textview);
        description_content.setText( description );
        price_textview = (TextView) findViewById(R.id.price_textview);
        price_textview.setText("Price: " + price );
        quantity_textview = (TextView) findViewById(R.id.quantity_textview);
        quantity_textview.setText("Quantity: " + quantity );

    }
}
