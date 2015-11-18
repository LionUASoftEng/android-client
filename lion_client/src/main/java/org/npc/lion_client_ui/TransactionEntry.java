package org.npc.lion_client_ui;

import java.io.Serializable;

/**
 * Created by Selver's Blackbird on 11/14/2015.
 */
public class TransactionEntry implements Serializable
{
    //private uuid transactionid
    private String lookup_code;
    private String description;
    private double price;
    private int quantity;

    public TransactionEntry(double p, int q, String l, String d)
    {
        price = p;
        quantity = q;
        lookup_code = l;
        description = d;
    }

    public double getPrice(){ return price; }
    public int getQuantity(){ return quantity; }
    public String getLookup_code(){ return lookup_code; }
    public String getDescription(){ return description; }
}
