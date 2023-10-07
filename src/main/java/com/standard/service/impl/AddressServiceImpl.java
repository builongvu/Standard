package com.standard.service.impl;

import com.standard.constant.ErrorEnum;
import com.standard.dto.request.AddressRequest;
import com.standard.entity.Address;
import com.standard.exception.ApplicationException;
import com.standard.dto.mapper.AddressMapper;
import com.standard.repository.AddressRepository;
import com.standard.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(() ->
                        new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Address", "id", id.toString()));
    }

    @Override
    public Address create(AddressRequest addressRequest) {
        Address address = AddressMapper.INSTANCE.toEntity(addressRequest);
        return addressRepository.save(address);
    }

    @Override
    public Address update(long id, AddressRequest addressRequest) {
        Address address = AddressMapper.INSTANCE.toEntity(addressRequest);
        address.setId(id);
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        Address address = getById(id);
        addressRepository.delete(address);
    }

}
