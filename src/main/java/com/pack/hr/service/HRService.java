package com.pack.hr.service;

import java.util.List;

import com.pack.hr.dto.HRDTO;

public interface HRService {

	HRDTO addHR(HRDTO hrdto);

	HRDTO getHRById(Long id);

	List<HRDTO> getAllHR();

	HRDTO updateHR(Long id, HRDTO hrdto);

	void deleteHR(Long id);

}
