package com.banzhuan.accountservice.service;

import com.banzhuan.accountservice.entity.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> getAllResource();

    Resource insertResource(Resource resource);

    Integer updateResource(Resource resource);

}
