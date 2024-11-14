package com.example.itemsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.itemsystem.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	List<ItemEntity> findByNameContaining(String name);

	@Query("SELECT i FROM ItemEntity i WHERE "
		       + "(:name IS NULL OR i.name LIKE %:name%) AND "
		       + "(:largeCategoryId IS NULL OR i.itemSmallCategory.itemUnderCategory.itemLargeCategory.id = :largeCategoryId) AND "
		       + "(:underCategoryId IS NULL OR i.itemSmallCategory.itemUnderCategory.id = :underCategoryId) AND "
		       + "(:smallCategoryId IS NULL OR i.itemSmallCategory.id = :smallCategoryId)")
		List<ItemEntity> findByNameContaining(@Param("name") String name,
		                                      @Param("largeCategoryId") Long largeCategoryId,
		                                      @Param("underCategoryId") Long underCategoryId,
		                                      @Param("smallCategoryId") Long smallCategoryId);
}
