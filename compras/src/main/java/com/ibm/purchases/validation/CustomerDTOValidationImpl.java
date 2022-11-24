package com.ibm.purchases.validation;

import com.ibm.purchases.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDTOValidationImpl implements CustomerDTOValidation{

    public List<CustomerDTO> validCustomer(List<CustomerDTO> value) {

        return value.stream()
                .filter(v -> v.getPurchases().size() != 0)
                .collect(Collectors.toList());
    }
}
