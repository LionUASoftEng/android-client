package org.npc.lion_client_ui.api.models;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.npc.lion_client_ui.api.enums.*;
import org.npc.lion_client_ui.api.interfaces.ConvertToJsonInterface;
import org.npc.lion_client_ui.api.interfaces.LoadFromJsonInterface;
import org.npc.lion_client_ui.api.models.fieldnames.ProductFieldNames;

public class Product implements ConvertToJsonInterface, LoadFromJsonInterface<Product> {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public Product setId(UUID id) {
        this.id = id;
        return this;
    }

    private String description;

    public String getDescription() {
        return this.description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    private String itemLookupCode;
    public String getLookupCode() {
        return this.itemLookupCode;
    }
    public Product setLookupCode(String lookupCode) {
        this.itemLookupCode = itemLookupCode;
        return this;
    }

    private int itemType;
    public int getItemType(){
        return this.itemType;
    }
    public Product setItemType(int itemType){
        this.itemType = itemType;
        return this;
    }

    private double price;
    public double getPrice() {
        return this.price;
    }
    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    private double cost;
    public double getCost() {
        return this.cost;
    }
    public Product setCost(double cost) {
        this.cost = cost;
        return this;
    }

    private int quantity;
    public int getQuantity() {
        return this.quantity;
    }
    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    private int reorderPoint;
    public int getReorderPoint() {
        return reorderPoint;
    }
    public Product setReorderPoint(int reorderPoint){
        this.reorderPoint = reorderPoint;
        return this;
    }

    private int restockLevel;

    public int getRestockLevel() {
        return restockLevel;
    }
    public Product setRestockLevel(int restockLevel){
        this.restockLevel = restockLevel;
        return this;
    }

    private UUID parentItem;
    public UUID getParentItem() {
        return parentItem;
    }
    public Product setParentItem(){
        this.parentItem = parentItem;
        return this;
    }

    private String extendedDescription;
    public String getExtendedDescription() {
        return extendedDescription;
    }
    public Product setextendedDescription(String extendedDescription){
        this.extendedDescription = extendedDescription;
        return this;
    }

    private boolean inactive;
    public boolean getInactive(){
        return inactive;
    }
    public Product setInactive(boolean inactive){
        this.inactive = inactive;
        return this;
    }

    private double MSRP;

    public double getMSRP() {
        return MSRP;
    }
    public Product setMSRP(double MSRP){
        this.MSRP = MSRP;
        return this;
    }

    private Date dateCreated;
    public Date getDateCreated() {
        return this.dateCreated;
    }
    public Product setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    private ProductApiRequestStatus apiRequestStatus;
    public ProductApiRequestStatus getApiRequestStatus() {
        return this.apiRequestStatus;
    }
    public Product setApiRequestStatus(ProductApiRequestStatus apiRequestStatus) {
        if (this.apiRequestStatus != apiRequestStatus) {
            this.apiRequestStatus = apiRequestStatus;
        }

        return this;
    }

    private String apiRequestMessage;
    public String getApiRequestMessage() {
        return this.apiRequestMessage;
    }
    public Product setApiRequestMessage(String apiRequestMessage) {
        if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
            this.apiRequestMessage = apiRequestMessage;
        }

        return this;
    }

    @Override
    public Product loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(ProductFieldNames.ID);
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }

        this.description = rawJsonObject.optString(ProductFieldNames.DESCRIPTION);
        this.itemLookupCode = rawJsonObject.optString(ProductFieldNames.ITEM_LOOKUP_CODE);
        this.price = rawJsonObject.optDouble(ProductFieldNames.PRICE);
        this.itemType = rawJsonObject.optInt(ProductFieldNames.ITEMTYPE);
        this.cost = rawJsonObject.optDouble(ProductFieldNames.COST);
        this.quantity = rawJsonObject.optInt(ProductFieldNames.QUANTITY, -1);
        this.reorderPoint = rawJsonObject.optInt(ProductFieldNames.REORDER_POINT);
        this.restockLevel = rawJsonObject.optInt(ProductFieldNames.RESTOCK_LEVEL);
        String PIValue = rawJsonObject.optString(ProductFieldNames.PARENT_ITEM);
        if (!StringUtils.isBlank(PIValue)) {
            this.parentItem = UUID.fromString(PIValue);
        }
        this.extendedDescription = rawJsonObject.optString(ProductFieldNames.EXTENDED_DESCRIPTION);
        this.inactive = rawJsonObject.optBoolean(ProductFieldNames.INACTIVE);
        this.MSRP = rawJsonObject.optDouble(ProductFieldNames.MSRP);

        value = rawJsonObject.optString(ProductFieldNames.DATE_CREATED);
        if (!StringUtils.isBlank(value)) {
            try {
                this.dateCreated = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        this.apiRequestMessage = rawJsonObject.optString(ProductFieldNames.API_REQUEST_MESSAGE);

        value = rawJsonObject.optString(ProductFieldNames.API_REQUEST_STATUS);
        if (!StringUtils.isBlank(value)) {
            this.apiRequestStatus = ProductApiRequestStatus.mapName(value);
        }

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(ProductFieldNames.ID, this.id.toString());
            jsonObject.put(ProductFieldNames.DESCRIPTION, this.description);
            jsonObject.put(ProductFieldNames.ITEM_LOOKUP_CODE, this.itemLookupCode);
            jsonObject.put(ProductFieldNames.PRICE, Double.toString(this.price));
            jsonObject.put(ProductFieldNames.ITEMTYPE, Integer.toString(this.itemType));
            jsonObject.put(ProductFieldNames.QUANTITY, Integer.toString(this.quantity));
            jsonObject.put(ProductFieldNames.REORDER_POINT, Integer.toString(this.reorderPoint));
            jsonObject.put(ProductFieldNames.RESTOCK_LEVEL, Integer.toString(this.restockLevel));
            jsonObject.put(ProductFieldNames.PARENT_ITEM, this.parentItem.toString());
            jsonObject.put(ProductFieldNames.EXTENDED_DESCRIPTION, this.extendedDescription);
            jsonObject.put(ProductFieldNames.INACTIVE, this.inactive);
            jsonObject.put(ProductFieldNames.MSRP, Double.toString(this.MSRP));
            jsonObject.put(ProductFieldNames.DATE_CREATED, (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).format(this.dateCreated));
            jsonObject.put(ProductFieldNames.API_REQUEST_MESSAGE, this.apiRequestMessage);
            jsonObject.put(ProductFieldNames.API_REQUEST_STATUS, this.apiRequestStatus.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public Product() {
        this.description = "";
        this.price = -0.999;
        this.itemType = 0;
        this.itemLookupCode = "";
        this.cost = -0.999;
        this.quantity = -1;
        this.reorderPoint = 0;
        this.restockLevel = 0;
        this.parentItem = UUID.fromString("00000000-00000000-00000000-00000000");
        this.extendedDescription = "";
        this.inactive = false;
        this.MSRP = 0.0;
        this.id = new UUID(0, 0);
        this.dateCreated = new Date();
        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = ProductApiRequestStatus.OK;
    }
}
