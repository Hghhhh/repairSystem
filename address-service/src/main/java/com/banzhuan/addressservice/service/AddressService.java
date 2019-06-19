package com.banzhuan.addressservice.service;

import com.banzhuan.addressservice.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddress();

    Address insertAddress(Address address);

}
