package com.pack.hr.serviceimpl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.hr.dto.HRDTO;
import com.pack.hr.entities.HR;
import com.pack.hr.repository.HRRepository;
import com.pack.hr.service.HRService;

@Service
public class HRServiceImpl implements HRService {

	@Autowired
	private HRRepository hrRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public HRDTO addHR(HRDTO hrdto) {
		HR hr = this.dtoToEntity(hrdto);
		HR saveHR = this.hrRepository.save(hr);
		return this.entityToDTO(saveHR);
	}

	@Override
	public HRDTO getHRById(Long id) {
		Optional<HR> hrId = this.hrRepository.findById(id);
		if (hrId.isPresent()) {
			HR hr = hrId.get();
			return this.entityToDTO(hr);
		} else {
			throw new ResolutionException("HR id is not present: " + id);
		}
	}

	@Override
	public List<HRDTO> getAllHR() {
		List<HR> hr = this.hrRepository.findAll();
		return hr.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public HRDTO updateHR(Long id, HRDTO hrdto) {
		HR existingHR = hrRepository.findById(id)
				.orElseThrow(() -> new ResolutionException("HR id is not present:  " + id));
		// update HR
		modelMapper.map(hrdto, existingHR);

		HR saveUpdatedHR = hrRepository.save(existingHR);
		return this.entityToDTO(saveUpdatedHR);
	}

	@Override
	public void deleteHR(Long id) {
		if (hrRepository.existsById(id)) {
			hrRepository.deleteById(id);
		} else {
			throw new ResolutionException("HR id is not present: " + id);
		}

	}

	// convert dto to entity
	public HR dtoToEntity(HRDTO hrdto) {
		HR hr = modelMapper.map(hrdto, HR.class);
		return hr;
	}

	// convert entity to dto
	public HRDTO entityToDTO(HR hr) {
		HRDTO dto = modelMapper.map(hr, HRDTO.class);
		return dto;
	}

}
