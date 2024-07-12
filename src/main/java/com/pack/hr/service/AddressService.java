package com.pack.hr.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pack.hr.dto.AddressDTO;

public interface AddressService {

	AddressDTO createAddress(AddressDTO addressDTO);

	AddressDTO updateAddress(Long id, AddressDTO addressDTO);

	void deleteAddress(Long id);

	AddressDTO getAddressById(Long id);

	List<AddressDTO> getAllAddresses();
	
	boolean isDuplicateAddress(String city, Long employeeId);
	
//	List<AddressDTO> getAddressByNameAndDateRange(String employeeName, LocalDateTime fromDate, LocalDateTime toDate);

	List<AddressDTO> getAddressByNameAndDate(String employeeName, LocalDateTime date);

	
}
