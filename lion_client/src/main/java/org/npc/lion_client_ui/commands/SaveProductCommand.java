package org.npc.lion_client_ui.commands;

import org.npc.lion_client_ui.api.enums.ProductApiRequestStatus;
import org.npc.lion_client_ui.api.models.Product;
import org.npc.lion_client_ui.api.services.ProductService;
import org.npc.lion_client_ui.commands.interfaces.ResultCommandInterface;

public class SaveProductCommand implements ResultCommandInterface<Product> {
    @Override
    public Product execute() {
        if (this.product == null) {
            return (new Product()).setApiRequestStatus(ProductApiRequestStatus.INVALID_INPUT);
        }

        return (new ProductService()).putProduct(this.product);
    }

    private Product product;
    public Product getProduct() {
        return this.product;
    }
    public SaveProductCommand setProduct(Product product) {
        this.product = product;
        return this;
    }
}
