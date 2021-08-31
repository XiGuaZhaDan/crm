package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.CustomerDao;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao = (CustomerDao) SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);

    public List<String> getCustomerName(String name) {

        List<String> list = customerDao.getCustomerName(name);
        return list;
    }
}
