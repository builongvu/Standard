package com.standard.service;

import com.standard.dto.request.AddressRequest;
import com.standard.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAll();

    Address getById(Long id);

    Address create(AddressRequest addressRequest);

    Address update(long id, AddressRequest addressRequest);

    void delete(Long id);

}
