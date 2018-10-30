package com.farma.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farma.entities.Patient;
import com.farma.repository.PatientRepository;
import com.farma.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Patient> findAll() throws Exception {
		return patientRepository.findAll();
	}

	@Transactional
	@Override
	public Patient save(Patient t) throws Exception {
		return patientRepository.save(t);
	}

	@Transactional
	@Override
	public Patient update(Patient t) throws Exception {
		return patientRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Patient> findById(Integer id) throws Exception {
		return patientRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		patientRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		patientRepository.deleteAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Patient> findByDni(String dni) throws Exception {
		return patientRepository.findByDni(dni);
	}

}
