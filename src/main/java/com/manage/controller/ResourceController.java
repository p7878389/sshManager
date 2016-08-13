package com.manage.controller;

import com.manage.entity.Resource;
import com.manage.serviceImp.ResourceServiceImpl;
import com.manage.util.BaseResult;
import com.manage.util.ErrorCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Administrator on 2016/6/26.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceServiceImpl resourceService;

    @RequestMapping(value = "/getResource/{id}",method = RequestMethod.POST)
    @ResponseBody
    public void getResource(Integer id){
        BaseResult result=new BaseResult();
        Resource resource=resourceService.getResource(id);
        if(resource==null){
            result=ErrorCodeInfo.getBaseResult(ErrorCodeInfo.RESOURCE_NULLPOINTER);
        }else{
            result.setObject(resource);
        }
    }

    public ResourceServiceImpl getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceServiceImpl resourceService) {
        this.resourceService = resourceService;
    }
}
