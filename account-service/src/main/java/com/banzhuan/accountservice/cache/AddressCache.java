package com.banzhuan.accountservice.cache;

import com.banzhuan.accountservice.entity.Address;
import com.banzhuan.accountservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AddressCache {

    @Autowired
    private AddressService addressService;

    private List<Address> addresses;

    @PostConstruct
    private void init(){
        if(addresses==null){
            addresses = addressService.address().getData();
        }
    }

    @Scheduled(cron = "0 */10 * * * *")
    private void addressJob(){
        synchronized (addresses){
            addresses = addressService.address().getData();
        }
    }

    public List<Address> getAddresses(){
        return addresses;
    }
}
