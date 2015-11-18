package org.npc.lion_client_ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.npc.lion_client_ui.R;
import org.npc.lion_client_ui.api.models.Product;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.product_list_view_item, parent, false);
        }

        Product product = this.getItem(position);
        if (product != null) {
            TextView lookupCodeTextView = (TextView) view.findViewById(R.id.lookup_code_textView);
            if (lookupCodeTextView != null) {
                lookupCodeTextView.setText(product.getLookupCode());
            }

            TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_textview);
            if (quantityTextView != null) {
                quantityTextView.setText(Integer.toString(product.getQuantity()));
            }
        }

        return view;
    }

    public ProductListAdapter(Context context, List<Product> products) {
        super(context, R.layout.activity_product_details, products);
    }
}