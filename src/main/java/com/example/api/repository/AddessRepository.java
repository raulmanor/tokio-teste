package com.example.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.api.domain.Address;

public interface AddessRepository extends PagingAndSortingRepository<Address, Long> {

}
