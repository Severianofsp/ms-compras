package com.ibm.winehouse.validation;

import com.ibm.winehouse.dto.CustomerDTO;

import java.util.List;

public interface CustomerDTOValidation {

    List<CustomerDTO> validCustomer(List<CustomerDTO> value);
}
