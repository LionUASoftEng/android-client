package org.npc.lion_client_ui;

/**
 * Created by Selver's Blackbird on 11/14/2015.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Transaction implements Serializable
{
    //private uuid cashierid
    private double amount;
    private double paidamount;
    private ArrayList<TransactionEntry> entrylist;
    //private string transactiontype
    //private uuid parentid
    //private localdatetime transactiondate

    public Transaction()
    {
        this.amount = 0.0000;
        this.paidamount = 0.0000;
        this.entrylist = new ArrayList<TransactionEntry>();
    }

    public void addEntry(TransactionEntry entry)
    {
        amount += (entry.getPrice() * entry.getQuantity());
        entrylist.add(entry);
    }

    public double addPayment(double p)
    {
        this.paidamount += p;
        return getRemBalance();
    }


    public double getRemBalance()
    {
        return (amount - paidamount);
    }

    public double getAmount(){ return amount; }
    public double getPaidAmount(){ return paidamount; }
    public ArrayList<TransactionEntry> getEntries(){ return entrylist; }


}
