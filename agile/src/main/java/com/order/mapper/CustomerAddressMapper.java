package com.order.mapper;

import org.springframework.stereotype.Repository;

import com.order.entity.CustomerAddress;

@Repository
public interface CustomerAddressMapper {
	int deleteByPrimaryKey(Integer addressId);

	int insert(CustomerAddress record);

	int insertSelective(CustomerAddress record);

	CustomerAddress selectByPrimaryKey(Integer addressId);

	int updateByPrimaryKeySelective(CustomerAddress record);

	int updateByPrimaryKey(CustomerAddress record);
}