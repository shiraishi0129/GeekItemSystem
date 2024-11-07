package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import com.example.itemsystem.entity.ManufacturesEntity;
import com.example.itemsystem.form.ManufacturesForn;

import jakarta.validation.Valid;

public interface ManufactureService {
	void saveManufactures(ManufacturesForn manufactuersForm);

	List<ManufacturesEntity> getAllManufactures();

	Optional<ManufacturesEntity> getManufacturesEntityById(Long id);

	void updateManufactures(@Valid ManufacturesForn manufacturesForm);
}
