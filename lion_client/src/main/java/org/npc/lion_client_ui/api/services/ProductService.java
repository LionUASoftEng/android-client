package org.npc.lion_client_ui.api.services;

import org.json.JSONObject;
import org.npc.lion_client_ui.api.enums.ApiLevel;
import org.npc.lion_client_ui.api.enums.ProductApiMethod;
import org.npc.lion_client_ui.api.enums.ProductApiRequestStatus;
import org.npc.lion_client_ui.api.interfaces.PathElementInterface;
import org.npc.lion_client_ui.api.models.Product;
import org.npc.lion_client_ui.api.models.ProductListing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService extends BaseRemoteService {
    public Product getProduct(UUID productId) {
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { ApiLevel.ONE, ProductApiMethod.PRODUCT }), productId
        );

        if (rawJsonObject != null) {
            return (new Product()).loadFromJson(rawJsonObject);
        } else {
            return new Product().setApiRequestStatus(ProductApiRequestStatus.UNKNOWN_ERROR);
        }
    }

    public Product getProductbyLookupCode(String itemLookupCode) {
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { ApiLevel.ONE, ProductApiMethod.PRODUCT }), itemLookupCode
        );

        if (rawJsonObject != null) {
            return (new Product()).loadFromJson(rawJsonObject);
        } else {
            return new Product().setApiRequestStatus(ProductApiRequestStatus.UNKNOWN_ERROR);
        }
    }


    public List<Product> getProducts() {
        List<Product> products;
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { ApiLevel.ONE, ProductApiMethod.PRODUCTS })
        );

        if (rawJsonObject != null) {
            products = (new ProductListing()).loadFromJson(rawJsonObject).getProducts();
        } else {
            products = new ArrayList<Product>(0);
        }

        return products;
    }

    public Product putProduct(Product product) {
        JSONObject rawJsonObject = this.putSingle(
                (new PathElementInterface[] { ApiLevel.ONE }), product.convertToJson()
        );

        if (rawJsonObject != null) {
            return (new Product()).loadFromJson(rawJsonObject);
        } else {
            return new Product().setApiRequestStatus(ProductApiRequestStatus.UNKNOWN_ERROR);
        }
    }
}


