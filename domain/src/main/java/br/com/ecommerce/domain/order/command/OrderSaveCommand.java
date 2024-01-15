package br.com.ecommerce.domain.order.command;

import br.com.ecommerce.domain.exception.ValidatorHandler;
import br.com.ecommerce.domain.exception.ValidatorUtils;

import java.util.List;

public record OrderSaveCommand(String client, List<String> products) {
    public ValidatorHandler validate(ValidatorHandler validator) {
        ValidatorUtils.stringNotNullOrBlank("Client", client, validator);
        return ValidatorUtils.listNotNullOrEmpty("Products", products, validator);
    }
}
