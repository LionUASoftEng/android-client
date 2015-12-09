package org.npc.lion_client_ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.npc.lion_client_ui.R;
import org.npc.lion_client_ui.CurrentTransactionEntry;

import java.util.List;

public class TransactionEntryListAdapter extends ArrayAdapter<CurrentTransactionEntry> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.activity_search_product, parent, false);
        }

        CurrentTransactionEntry transactionEntry = this.getItem(position);
        if (transactionEntry != null) {
            TextView lookupCodeTextView = (TextView) view.findViewById(R.id.lookup_code_textView);
            if (lookupCodeTextView != null) {
                lookupCodeTextView.setText(transactionEntry.getLookup_code());
            }

            TextView descriptionTextView = (TextView) view.findViewById(R.id.description_content_textview);
            if(descriptionTextView != null){
                descriptionTextView.setText(transactionEntry.getDescription());
            }

            TextView priceTextView = (TextView) view.findViewById(R.id.price_textview);
            if(priceTextView != null){
                priceTextView.setText(Double.toString(transactionEntry.getPrice()));
            }

            TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_textview);
            if (quantityTextView != null) {
                quantityTextView.setText(Integer.toString(transactionEntry.getQuantity()));
            }
        }

        return view;
    }

    public TransactionEntryListAdapter(Context context, List<CurrentTransactionEntry> transactionEntries) {
        super(context, R.layout.activity_search_product, transactionEntries);
    }
}
