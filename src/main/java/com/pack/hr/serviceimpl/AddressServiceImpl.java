package com.pack.hr.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.hr.dto.AddressDTO;
import com.pack.hr.entities.Address;
import com.pack.hr.entities.Employee;
import com.pack.hr.repository.AddressRepository;
import com.pack.hr.repository.EmployeeRepository;
import com.pack.hr.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /*@Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        Address address = dtoToEntity(addressDTO);
        address.setEmployee(employee);
        Address savedAddress = addressRepository.save(address);

        return entityToDTO(savedAddress);
    }*/
    
   /* @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Find the current address for the employee and update its endDate
        List<Address> currentAddresses = addressRepository.findByEmployeeId(employee.getId());
        currentAddresses.forEach(address -> {
            address.setEndDate(LocalDateTime.now());
            addressRepository.save(address);
        });

        // Create the new address with effectiveDate as current date and endDate as null
        Address address = dtoToEntity(addressDTO);
        address.setEmployee(employee);
        address.setEffectiveDate(LocalDateTime.now());
        address.setEndDate(null);
        Address savedAddress = addressRepository.save(address);

        return entityToDTO(savedAddress);
    } */


    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Find the current address for the employee and update its endDate
        List<Address> currentAddresses = addressRepository.findByEmployeeIdAndEndDateIsNull(employee.getId());
        currentAddresses.forEach(address -> {
            address.setEndDate(LocalDateTime.now());
            addressRepository.save(address);
        });

        // Create the new address with effectiveDate as current date and endDate as null
        Address address = dtoToEntity(addressDTO);
        address.setEmployee(employee);
        address.setEffectiveDate(LocalDateTime.now());
        address.setEndDate(null);
        Address savedAddress = addressRepository.save(address);

        return entityToDTO(savedAddress);
    }

    
    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));

        address.setCity(addressDTO.getCity());
        address.setEffectiveDate(addressDTO.getEffectiveDate());
        address.setEndDate(addressDTO.getEndDate());

        if (!address.getEmployee().getId().equals(addressDTO.getEmployeeId())) {
            Employee employee = employeeRepository.findById(addressDTO.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
            address.setEmployee(employee);
        }

        Address updatedAddress = addressRepository.save(address);
        return entityToDTO(updatedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));
        return entityToDTO(address);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
    
    @Override
    public boolean isDuplicateAddress(String city, Long employeeId) {
        List<Address> addresses = addressRepository.findByEmployeeId(employeeId);
        return addresses.stream().anyMatch(address -> address.getCity().equalsIgnoreCase(city));
    }

    // Convert dto to entity
    private Address dtoToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    // Convert entity to dto
    private AddressDTO entityToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }
}
