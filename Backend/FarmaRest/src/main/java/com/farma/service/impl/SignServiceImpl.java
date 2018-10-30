package com.farma.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farma.entities.Sign;
import com.farma.repository.SignRepository;
import com.farma.service.SignService;

@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignRepository signRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Sign> findAll() {
		return signRepository.findAll();
	}

	@Transactional
	@Override
	public Sign save(Sign t) {
		return signRepository.save(t);
	}

	@Transactional
	@Override
	public Sign update(Sign t) {
		return signRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Sign> findById(Integer id) {
		return signRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		signRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		signRepository.deleteAll();
	}

}
