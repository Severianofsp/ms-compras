package com.ibm.winehouse.validation;

import com.ibm.winehouse.rest.response.OrderItemResponse;
import com.ibm.winehouse.rest.response.OrderResponse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

public class OrderResponseValidationImpl implements ConstraintValidator<OrderResponseValidation, OrderResponse> {


    @Override
    public boolean isValid(OrderResponse value, ConstraintValidatorContext context) {

        removeProductWithoutCode(value);

        recalculeTotal(value);

        formatDocument(value);

        return true;
    }

    private void formatDocument(OrderResponse value) {
        int documentLength = value.getClient().length();
        if (documentLength == 15) {
            value.setClient(
                    value
                            .getClient()
                            .substring(1, documentLength));
        }
        String formatedDocument = value
                .getClient()
                .replaceAll("\\D", "")
                .replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        value.setClient(formatedDocument);
    }

    private void recalculeTotal(OrderResponse value) {
        if (value.getItems().size() == 0) {
            value.setTotal(ZERO);
        }

        if (value.getItems().size() > 0) {
            BigDecimal total = value
                    .getItems().stream()
                    .map(OrderItemResponse::getPrice)
                    .reduce(ZERO, BigDecimal::add);

            value.setTotal(total);
        }
    }

    private void removeProductWithoutCode(OrderResponse value) {
        value
                .setItems(value
                        .getItems()
                        .stream()
                        .filter(v -> v.getCode() != null)
                        .collect(Collectors.toList()));
    }
}
