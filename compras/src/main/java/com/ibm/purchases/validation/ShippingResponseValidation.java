package com.ibm.purchases.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Target({TYPE, METHOD, FIELD,})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ShippingResponseValidationImpl.class)
public @interface ShippingResponseValidation {

    String message() default "Validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
