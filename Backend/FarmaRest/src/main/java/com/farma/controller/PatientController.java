package com.farma.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.farma.entities.Patient;
import com.farma.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patients")
@Api(value = "REST información de pacientes")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@ApiOperation("Lista de pacientes")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> fetchPatients() {
		try {
			List<Patient> patients = new ArrayList<>();
			patients = patientService.findAll();
			return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Obtener paciente por id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> fetchPatient(@PathVariable("id") Integer id) {

		try {
			Optional<Patient> patient = patientService.findById(id);

			if (!patient.isPresent()) {
				return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Obtener paciente por dni")
	@GetMapping(value = "/search/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> fetchPatientByDni(@PathVariable("dni") String dni) {
		try {
			List<Patient> patients = new ArrayList<>();
			patients = patientService.findByDni(dni);
			return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Registro de paciente")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> savePatient(@Valid @RequestBody Patient patient) {
		try {
			Patient pat = new Patient();
			pat = patientService.save(patient);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pat.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Actualizar datos de paciente")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePatient(@Valid @RequestBody Patient patient) {
		try {
			patientService.update(patient);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Eliminar paciente por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {

		try {
			Optional<Patient> patient = patientService.findById(id);

			if (!patient.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				patientService.deleteById(id);
				return new ResponseEntity<>("Paciente se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Eliminar todos los pacientes")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAllPatients() {
		try {
			patientService.deleteAll();
			return new ResponseEntity<>("Pacientes eliminados", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
