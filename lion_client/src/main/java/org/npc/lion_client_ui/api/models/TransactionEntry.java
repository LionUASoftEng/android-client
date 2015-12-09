package org.npc.lion_client_ui.api.models;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.npc.lion_client_ui.api.enums.TransactionEntryApiRequestStatus;
import org.npc.lion_client_ui.api.interfaces.ConvertToJsonInterface;
import org.npc.lion_client_ui.api.interfaces.LoadFromJsonInterface;
import org.npc.lion_client_ui.api.models.fieldnames.ProductFieldNames;
import org.npc.lion_client_ui.api.models.fieldnames.TransactionEntryFieldNames;

import java.util.UUID;

public class TransactionEntry implements ConvertToJsonInterface, LoadFromJsonInterface<TransactionEntry> {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public TransactionEntry setId(UUID id) {
        this.id = id;
        return this;
    }

    private UUID transactionId;
    public UUID getTransactionId() {
        return transactionId;
    }
    public TransactionEntry setTransactionId(UUID transactionId){
        this.transactionId = transactionId;
        return this;
    }

    private UUID productId;
    public UUID getProductId() {
        return productId;
    }
    public TransactionEntry setProductId(UUID productId){
        this.productId = productId;
        return this;
    }

    private double price;
    public double getPrice() {
        return this.price;
    }
    public TransactionEntry setPrice(double price) {
        this.price = price;
        return this;
    }

    private int quantity;
    public int getQuantity() {
        return this.quantity;
    }
    public TransactionEntry setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    private TransactionEntryApiRequestStatus apiRequestStatus;
    public TransactionEntryApiRequestStatus getApiRequestStatus() {
        return this.apiRequestStatus;
    }
    public TransactionEntry setApiRequestStatus(TransactionEntryApiRequestStatus apiRequestStatus) {
        if (this.apiRequestStatus != apiRequestStatus) {
            this.apiRequestStatus = apiRequestStatus;
        }

        return this;
    }

    private String apiRequestMessage;
    public String getApiRequestMessage() {
        return this.apiRequestMessage;
    }
    public TransactionEntry setApiRequestMessage(String apiRequestMessage) {
        if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
            this.apiRequestMessage = apiRequestMessage;
        }

        return this;
    }

    @Override
    public TransactionEntry loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(TransactionEntryFieldNames.TRANSACTIONENTRYID);
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }

        String transactionIdValue = rawJsonObject.optString(TransactionEntryFieldNames.TRANSACTIONID);
        if (!StringUtils.isBlank(transactionIdValue)) {
            this.transactionId = UUID.fromString(transactionIdValue);
        }

        String productIdValue = rawJsonObject.optString(TransactionEntryFieldNames.PRODUCTID);
        if (!StringUtils.isBlank(productIdValue)) {
            this.productId = UUID.fromString(productIdValue);
        }

        this.price = rawJsonObject.optDouble(TransactionEntryFieldNames.PRICE);
        this.quantity = rawJsonObject.optInt(TransactionEntryFieldNames.QUANTITY, -1);

        this.apiRequestMessage = rawJsonObject.optString(TransactionEntryFieldNames.API_REQUEST_MESSAGE);

        value = rawJsonObject.optString(TransactionEntryFieldNames.API_REQUEST_STATUS);
        if (!StringUtils.isBlank(value)) {
            this.apiRequestStatus = TransactionEntryApiRequestStatus.mapName(value);
        }

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(TransactionEntryFieldNames.TRANSACTIONENTRYID, this.id.toString());
            jsonObject.put(TransactionEntryFieldNames.TRANSACTIONID, this.transactionId.toString());
            jsonObject.put(TransactionEntryFieldNames.PRODUCTID, this.productId.toString());
            jsonObject.put(TransactionEntryFieldNames.PRICE, Double.toString(this.price));
            jsonObject.put(TransactionEntryFieldNames.QUANTITY, Integer.toString(this.quantity));
            jsonObject.put(ProductFieldNames.API_REQUEST_MESSAGE, this.apiRequestMessage);
            jsonObject.put(ProductFieldNames.API_REQUEST_STATUS, this.apiRequestStatus.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public TransactionEntry() {
        this.id = new UUID(0, 0);
        this.transactionId = new UUID(0, 0);
        this.productId = new UUID (0, 0);
        this.price = 0.0;
        this.quantity = 0;

        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = TransactionEntryApiRequestStatus.OK;
    }
}
