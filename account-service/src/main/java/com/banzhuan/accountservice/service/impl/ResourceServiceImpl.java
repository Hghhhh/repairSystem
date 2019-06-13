package com.banzhuan.accountservice.service.impl;

import com.banzhuan.accountservice.entity.Resource;
import com.banzhuan.accountservice.repository.ResourceDao;
import com.banzhuan.accountservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Resource> getAllResource() {
        return resourceDao.findAll();
    }

    @Override
    public Resource insertResource(Resource resource) {
        return resourceDao.save(resource);
    }

    @Override
    @Transactional
    public Integer updateResource(Resource resource) {
        return resourceDao.updateResource(resource.getId(),resource.getUrl(),resource.getDescription());
    }
}
