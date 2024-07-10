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
import org.springframework.web.bind.annotation.RestController;

import com.pack.hr.dto.HRDTO;
import com.pack.hr.service.HRService;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin("*")
public class HRController {

	@Autowired
	private HRService hrService;

	@PostMapping
	public ResponseEntity<HRDTO> addHR(@RequestBody HRDTO hrdto) {
		HRDTO addHR = hrService.addHR(hrdto);
		return ResponseEntity.ok(addHR);
	}

	@GetMapping
	public ResponseEntity<List<HRDTO>> getAllHR() {
		List<HRDTO> getHR = hrService.getAllHR();
		return ResponseEntity.ok(getHR);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HRDTO> getHrById(@PathVariable Long id) {
		HRDTO hr = hrService.getHRById(id);
		return ResponseEntity.ok(hr);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HRDTO> updateHR(@PathVariable Long id, @RequestBody HRDTO hrdto) {
		HRDTO updateHR = hrService.updateHR(id, hrdto);
		return ResponseEntity.ok(updateHR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteHR(@PathVariable Long id) {
		hrService.deleteHR(id);
		return ResponseEntity.ok("HR deleted successfully");
	}
}
