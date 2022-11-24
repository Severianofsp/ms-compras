package com.ibm.purchases.validation;

import com.ibm.purchases.rest.response.ShippingItem;
import com.ibm.purchases.rest.response.ShippingResponse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

public class ShippingResponseValidationImpl implements ConstraintValidator<ShippingResponseValidation, ShippingResponse> {


    @Override
    public boolean isValid(ShippingResponse value, ConstraintValidatorContext context) {

        removeProductWithoutCode(value);

        recalculeTotal(value);

        return true;
    }

    private void recalculeTotal(ShippingResponse value) {
        if (value.getItems().size() == 0) {
            value.setTotal(ZERO);
        }

        if (value.getItems().size() > 0) {
            BigDecimal total = value
                    .getItems().stream()
                    .map(ShippingItem::getPrice)
                    .reduce(ZERO, BigDecimal::add);

            value.setTotal(total);
        }
    }

    private void removeProductWithoutCode(ShippingResponse value) {
        value
                .setItems(value
                        .getItems()
                        .stream()
                        .filter(v -> v.getCode() != null)
                        .collect(Collectors.toList()));
    }
}
