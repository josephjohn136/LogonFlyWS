package com.bolster.repository;

import org.springframework.data.repository.CrudRepository;

import com.bolster.model.Tenant;

public interface TenantRespository extends CrudRepository<Tenant, Integer>  {
	
}
