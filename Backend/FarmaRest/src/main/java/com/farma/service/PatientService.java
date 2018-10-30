package com.farma.service;

import java.util.List;

import com.farma.entities.Patient;

public interface PatientService extends CrudService<Patient> {
	List<Patient> findByDni(String dni) throws Exception;
}
