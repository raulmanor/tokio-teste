package com.example.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;

import com.example.api.repository.AddessRepository;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.exception.CustomerNotFoundException;

@Service
public class CustomerService {

	private CustomerRepository repository;
	private AddessRepository addressRepository;

	@Autowired
	private RestTemplate client;

	@Autowired
	public CustomerService(CustomerRepository repository, AddessRepository addressRepository) {
		this.repository = repository;
		this.addressRepository = addressRepository;
	}

	public Page<Customer> findAll(int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return repository.findAll(paging);
	}

	public Customer findById(Long id) throws CustomerNotFoundException {
		return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
	}

	public Customer save(Customer customer) {
		return repository.save(customer);
	}

	public Customer update(Customer customer) throws CustomerNotFoundException {
		checkExistence(customer);
		return repository.save(customer);
	}

	public void remove(Long id) throws CustomerNotFoundException {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CustomerNotFoundException();
		}
	}

	private void checkExistence(Customer customer) throws CustomerNotFoundException {
		findById(customer.getId());
	}

	public Address saveAddress(Long customerId, Address address) throws CustomerNotFoundException {
		Customer customer = findById(customerId);
		

		String cep = address.getCep();

		address = getViaCEP(cep);

		address.setCustomer(customer);
		
		try {
			return addressRepository.save(address);
			
		} catch (Exception CustomerNotFoundException) {
		 
		}
		

		return address;

	}

	// serviço de conexão da API VIA CEP
	
	public Address getViaCEP(String cep) throws CustomerNotFoundException {

		ResponseEntity<Address> responseEntity = client.exchange("https://viacep.com.br/ws/" + cep + "/json/",
				HttpMethod.GET, null, Address.class);
		return responseEntity.getBody();

	}

}
