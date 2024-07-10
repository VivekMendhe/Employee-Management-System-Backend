package com.pack.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.hr.dto.AddressDTO;
import com.pack.hr.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
		AddressDTO createdAddress = addressService.createAddress(addressDTO);
		return ResponseEntity.ok(createdAddress);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
		AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
		return ResponseEntity.ok(updatedAddress);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
		AddressDTO address = addressService.getAddressById(id);
		return ResponseEntity.ok(address);
	}

	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAllAddresses() {
		List<AddressDTO> addresses = addressService.getAllAddresses();
		return ResponseEntity.ok(addresses);
	}
	
	@GetMapping("/duplicate")
    public ResponseEntity<Boolean> isDuplicateAddress(@RequestParam String city, @RequestParam Long employeeId) {
        boolean isDuplicate = addressService.isDuplicateAddress(city, employeeId);
        return ResponseEntity.ok(isDuplicate);
    }
}
