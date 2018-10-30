package com.farma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "signs")
public class Sign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "temperature", columnDefinition = "Decimal(8,2)", nullable = false)
	private double temperature;

	@Column(name = "pulse", columnDefinition = "Decimal(8,2)", nullable = false)
	private double pulse;

	@Column(name = "respitory_rhythm", columnDefinition = "Decimal(8,2)", nullable = false)
	private double respitoryRhythm;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patientId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPulse() {
		return pulse;
	}

	public void setPulse(double pulse) {
		this.pulse = pulse;
	}

	public double getRespitoryRhythm() {
		return respitoryRhythm;
	}

	public void setRespitoryRhythm(double respitoryRhythm) {
		this.respitoryRhythm = respitoryRhythm;
	}

	public Patient getPatientId() {
		return patientId;
	}

	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}

}
