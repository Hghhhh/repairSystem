package com.banzhuan.bankendservice.service;



import com.banzhuan.bankendservice.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddress();

    Address insertAddress(Address address);

}
