package com.example.api.web.rest;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import com.example.api.service.exception.CustomerNotFoundException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public Page<Customer> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String sortBy) {
		return service.findAll(pageNo, pageSize, sortBy);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) throws CustomerNotFoundException {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
		customer = service.save(customer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(customer.getId()).toUri();
		
		return ResponseEntity.created(uri).body(customer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws CustomerNotFoundException {
		service.remove(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Customer customer, @PathVariable Long id) throws CustomerNotFoundException {
		customer.setId(id);
		service.update(customer);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}/addres")
	public ResponseEntity<Address> saveAddress(@PathVariable Long id, @Valid @RequestBody Address address) throws CustomerNotFoundException {
		 address = service.saveAddress(id , address);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(address.getId()).toUri();
		
		return ResponseEntity.created(uri).body(address);
	}

}
