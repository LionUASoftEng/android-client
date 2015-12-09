package org.npc.lion_client_ui;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Selver's Blackbird on 11/14/2015.
 */
public class CurrentTransactionEntry implements Serializable
{
    private UUID transactionid;
    private UUID productid;
    private String lookup_code;
    private String description;
    private double price;
    private int quantity;

    public CurrentTransactionEntry( UUID productId, double listPrice, int purchaseQuantity,
                                   String itemDescription)
    {
        this.productid = productId;
        this.price = listPrice;
        this.quantity = purchaseQuantity;
        this.description = itemDescription;
    }

    public CurrentTransactionEntry() {}

    public UUID getTransactionid(){ return this.transactionid; }
    public CurrentTransactionEntry setTransactionid(UUID transactionId){
        this.transactionid = transactionId;
        return this;
    }

    public UUID getProductid(){ return this.productid; }
    public CurrentTransactionEntry setProductid( UUID productid ){
        this.productid = productid;
        return this;
    }

    public double getPrice(){ return price; }
    public CurrentTransactionEntry setPrice(double price){
        this.price = price;
        return this;
    }

    public int getQuantity(){ return quantity; }
    public CurrentTransactionEntry setQuantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public String getLookup_code(){ return lookup_code; }

    public String getDescription(){ return description; }
    public CurrentTransactionEntry setDescription(String description){
        this.description = description;
        return this;
    }

}
