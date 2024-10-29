package com.example.itemsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.itemsystem.entity.ItemEntity;
import com.example.itemsystem.repository.ItemLargeCategoryRepository;
import com.example.itemsystem.repository.ItemRepository;
import com.example.itemsystem.repository.ItemSmallCategoryRepository;
import com.example.itemsystem.repository.ItemUnderCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemLargeCategoryRepository itemLargeCategoryRepository;
	@Autowired
	private ItemUnderCategoryRepository itemUnderCategoryRepository;
	@Autowired
	private ItemSmallCategoryRepository itemSmallCategoryRepository;

		
		
	    public List<ItemEntity> findItems(int page, String searchQuery, String category) {
	        // ページングと検索を考慮して商品を取得
	        Pageable pageable = PageRequest.of(page, 10); // 1ページあたり10件
	        if (searchQuery != null && !searchQuery.isEmpty()) {
	            return itemRepository.findByNameContaining(searchQuery);
	        }
	        return itemRepository.findAll(pageable).getContent();
	    }

	    public int getTotalPages(String searchQuery, String category) {
	        // 検索結果の総ページ数を取得
	        long totalItems = searchQuery != null && !searchQuery.isEmpty() 
	            ? itemRepository.findByNameContaining(searchQuery).size() 
	            : itemRepository.count();
	        return (int) Math.ceil((double) totalItems / 10);
	    }

	    public List<ItemEntity> searchItems(String name, String largeCategoryId, String underCategoryId, String smallCategoryId) {
	        // 複数条件による商品検索（必要に応じて実装）
	        return itemRepository.findByNameContaining(name);
	    }

		public Page<ItemEntity> searchItems(int page, String largeCategory, String UnderCategory, String smallCategory,
				String search) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		public List<ItemEntity> getAllItem() {
			// TODO 自動生成されたメソッド・スタブ
			return itemRepository.findAll();
		}

		public Optional<ItemEntity> getItemEntityById(Long id) {
	    	
			   return itemRepository.findById(id);
		   }

}
