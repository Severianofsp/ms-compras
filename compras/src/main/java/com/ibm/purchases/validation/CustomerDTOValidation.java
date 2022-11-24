package com.ibm.purchases.validation;

import com.ibm.purchases.dto.CustomerDTO;

import java.util.List;

public interface CustomerDTOValidation {

    List<CustomerDTO> validCustomer(List<CustomerDTO> value);
}
