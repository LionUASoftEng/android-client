package org.npc.lion_client_ui.api.models;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.npc.lion_client_ui.api.interfaces.ConvertToJsonInterface;
import org.npc.lion_client_ui.api.interfaces.LoadFromJsonInterface;
import org.npc.lion_client_ui.api.models.fieldnames.TransactionFieldNames;
import org.npc.lion_client_ui.api.enums.TransactionApiRequestStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transaction implements ConvertToJsonInterface, LoadFromJsonInterface<Transaction> {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public Transaction setId(UUID id) {
        this.id = id;
        return this;
    }

    private UUID cashier;
    public UUID getCashier() {
        return this.cashier;
    }

    public Transaction setCashier(UUID cashier) {
        this.cashier = cashier;
        return this;
    }

    private double amount;
    public double getAmount() {
        return this.amount;
    }
    public Transaction setPrice(double amount) {
        this.amount = amount;
        return this;
    }

    private String transactiontype;
    public String getTransactionType() {
        return this.transactiontype;
    }
    public Transaction setTransactionType(String transactiontype) {
        this.transactiontype = transactiontype;
        return this;
    }

    private UUID parentid;
    public UUID getParentId() {
        return this.parentid;
    }
    public Transaction setParentId(UUID parentid) {
        this.parentid = parentid;
        return this;
    }

    private Date transactionDate;
    public Date getTransactionDate() {
        return this.transactionDate;
    }
    public Transaction setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    private TransactionApiRequestStatus apiRequestStatus;
    public TransactionApiRequestStatus getApiRequestStatus() {
        return this.apiRequestStatus;
    }
    public Transaction setApiRequestStatus(TransactionApiRequestStatus apiRequestStatus) {
        if (this.apiRequestStatus != apiRequestStatus) {
            this.apiRequestStatus = apiRequestStatus;
        }

        return this;
    }

    private String apiRequestMessage;
    public String getApiRequestMessage() {
        return this.apiRequestMessage;
    }
    public Transaction setApiRequestMessage(String apiRequestMessage) {
        if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
            this.apiRequestMessage = apiRequestMessage;
        }

        return this;
    }

    @Override
    public Transaction loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(TransactionFieldNames.TRANSACTIONID);
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }
        String cashierValue = rawJsonObject.optString(TransactionFieldNames.CASHIERID);
        if(!StringUtils.isBlank(cashierValue)){
            this.cashier = UUID.fromString((cashierValue));
        }
        this.transactiontype = rawJsonObject.optString(TransactionFieldNames.TRANSACTIONTYPE);
        this.amount = rawJsonObject.optDouble(TransactionFieldNames.AMOUNT);
        String parentTransactionValue = rawJsonObject.optString(TransactionFieldNames.PARENTID);
        if (!StringUtils.isBlank(parentTransactionValue)) {
            this.parentid = UUID.fromString(parentTransactionValue);
        }

        value = rawJsonObject.optString(TransactionFieldNames.TRANSACTIONDATE);
        if (!StringUtils.isBlank(value)) {
            try {
                this.transactionDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        this.apiRequestMessage = rawJsonObject.optString(TransactionFieldNames.API_REQUEST_MESSAGE);

        value = rawJsonObject.optString(TransactionFieldNames.API_REQUEST_STATUS);
        if (!StringUtils.isBlank(value)) {
            this.apiRequestStatus = TransactionApiRequestStatus.mapName(value);
        }

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(TransactionFieldNames.TRANSACTIONID, this.id.toString());
            jsonObject.put(TransactionFieldNames.CASHIERID, this.cashier.toString());
            jsonObject.put(TransactionFieldNames.TRANSACTIONTYPE, this.transactiontype);
            jsonObject.put(TransactionFieldNames.AMOUNT, Double.toString(this.amount));
            jsonObject.put(TransactionFieldNames.PARENTID, this.parentid.toString());
            jsonObject.put(TransactionFieldNames.TRANSACTIONDATE,
                    (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).format(this.transactionDate));
            jsonObject.put(TransactionFieldNames.API_REQUEST_MESSAGE, this.apiRequestMessage);
            jsonObject.put(TransactionFieldNames.API_REQUEST_STATUS, this.apiRequestStatus.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public Transaction() {
        this.id = new UUID(0, 0);
        this.cashier = new UUID(0, 0);
        this.transactiontype = "";
        this.amount = 0.0;
        this.parentid = new UUID(0, 0);
        this.transactionDate = new Date();
        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = TransactionApiRequestStatus.OK;
    }
}
