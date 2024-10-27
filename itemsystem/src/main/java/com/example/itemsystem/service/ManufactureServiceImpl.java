package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entiry.ManufacturesEntity;
import com.example.itemsystem.form.ManufacturesForn;
import com.example.itemsystem.repository.ManufactresRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ManufactureServiceImpl implements ManufacturesService {
	@Autowired
	private ManufactresRepository manufacturesRepository;
	
	@Override
	public void saveManufactures(ManufacturesForn manufacturesForm) {
		    ManufacturesEntity manufacturesEntity = new ManufacturesEntity();
		    	
		    manufacturesEntity.setId(manufacturesForm.getId());
		    manufacturesEntity.setName(manufacturesForm.getName());  // permissionを設定
		    
		    manufacturesRepository.save(manufacturesEntity);
	}
	
	 public List<ManufacturesEntity> getAllManufactures() {
	        return manufacturesRepository.findAll();
   }
	 
	 public String findAll() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
	}
		
	public ManufacturesEntity findById(Long id) {
		        return manufacturesRepository.findById(id).orElse(null); // IDが存在しない場合はnullを返す
	}
		    
    public Optional<ManufacturesEntity> getManufacturesEntityById(Long id) {
	   		   return manufacturesRepository.findById(id);
   }
    public void updateManufactures(ManufacturesForn manufacturesForm) {
		    ManufacturesEntity manufacturesEntity = new ManufacturesEntity();
		    	
		    manufacturesEntity.setId(manufacturesForm.getId());
		    manufacturesEntity.setName(manufacturesForm.getName());  // permissionを設定
		    
		    manufacturesRepository.save(manufacturesEntity);
	}
}
