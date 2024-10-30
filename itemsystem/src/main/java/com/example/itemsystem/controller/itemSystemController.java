package com.example.itemsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.entity.ItemEntity;
import com.example.itemsystem.entity.ItemLargeCategoryEntity;
import com.example.itemsystem.entity.ManufacturesEntity;
import com.example.itemsystem.entity.OrderHistoryEntity;
import com.example.itemsystem.entity.ShopEntity;
import com.example.itemsystem.entity.ShopItemEntity;
import com.example.itemsystem.form.AdminForm;
import com.example.itemsystem.form.ManufacturesForn;
import com.example.itemsystem.form.ShopForm;
import com.example.itemsystem.repository.AdminRepository;
import com.example.itemsystem.repository.ItemLargeCategoryRepository;
import com.example.itemsystem.repository.ItemRepository;
import com.example.itemsystem.repository.ItemSmallCategoryRepository;
import com.example.itemsystem.repository.ItemUnderCategoryRepository;
import com.example.itemsystem.repository.OrderHistoryReopsitory;
import com.example.itemsystem.repository.ShopItemRepository;
import com.example.itemsystem.service.AdminService;
import com.example.itemsystem.service.AdminServiceImpl;
import com.example.itemsystem.service.ItemLargeCategoryService;
import com.example.itemsystem.service.ItemLargeCategoryServiceImpl;
import com.example.itemsystem.service.ItemService;
import com.example.itemsystem.service.ItemServiceImpl;
import com.example.itemsystem.service.ManufactureServiceImpl;
import com.example.itemsystem.service.ManufacturesService;
import com.example.itemsystem.service.OrderHistoryServiceImpl;
import com.example.itemsystem.service.ShopItemService;
import com.example.itemsystem.service.ShopItemServiceServiceImpl;
import com.example.itemsystem.service.ShopService;
import com.example.itemsystem.service.ShopServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class itemSystemController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminServiceImpl adminServiceImp;
	@Autowired
	private ManufacturesService manufacturesService;
	@Autowired
	private ManufactureServiceImpl manufacturesServiceImpl;
	@Autowired
	private ItemLargeCategoryService itemLargeCategoryService;
	@Autowired
	private ItemLargeCategoryServiceImpl itemLargeCategoryServiceImpl;
	@Autowired
    private ItemService itemService;
	@Autowired
	private ItemServiceImpl itemServiceImpl;
	@Autowired
    private ShopItemService shopItemService;
	@Autowired
	private ShopItemServiceServiceImpl shopItemServiceImpl;
    @Autowired
    private ItemLargeCategoryRepository itemLargeCategoryRepository;
    @Autowired
    private ItemUnderCategoryRepository itemUnderCategoryRepository;
    @Autowired
    private ItemSmallCategoryRepository itemSmallCategoryRepository;
    @Autowired
    private ShopItemRepository shopItemRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderHistoryReopsitory orderHistoryRepository;
	@Autowired
	private OrderHistoryServiceImpl orderHistoryServiceImpl;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopServiceImpl shopServiceImpl;

    
    


	@GetMapping("/login")
	public String login() {
		return("login");
	}
	
	@PostMapping("/login")
	public String logintop(){
		return("top");
	}

	@GetMapping("/top")
	public String top() {
		return("top");
	}
	@PostMapping("/top")
	public String toppost() {
		return("top");
	}
	
	
	
	//管理者情報処理↓
	@GetMapping("/admin/signup")
	public String adminsignup(Model model) {
		model.addAttribute("adminForm",new AdminForm());
		return("admin/signup");
	}
	@PreAuthorize("hasRole('1')")
	@PostMapping("/admin/signup")
	public String adminsignuppost(){
		return("redirect:signup");
	}
	
	@PostMapping("/admin/signup/register")	
	public String admin(@Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult, Model model,HttpServletRequest request){
		
		 if (errorResult.hasErrors()) {
			 model.addAttribute("adminForm", adminForm); 
	          return "admin/signup";
	        }
	     adminService.saveAdmin(adminForm);
		 
		return("top");
	}
	
	//管理者一覧画面へポスト処理
	@PostMapping("/admin/list")
	public String adminlistpost() {
		return "redirect:list";
	}
	//管理者一覧画面でのゲット処理
	@GetMapping("/admin/list")
	public String adminlistget(Model model) {
		//adminエンティティのデータを取得する為に、サービスクラスから取得
		 List<AdminEntity> adminList = adminServiceImp.getAllAdmins();
		 System.out.println("Admin List Size: " + adminList.size()); 
		 model.addAttribute("adminList", adminList);
		return "admin/list";
		
	}
	
	@PreAuthorize("hasRole('1')")
	 @GetMapping("/admin/edit/:id")
	    public String adminedit(@RequestParam("id") Long id,Model model) {
			Optional<AdminEntity> adminEntity = adminServiceImp.getAdminEntityById(id);
			   if (adminEntity.isPresent()) {
		            AdminEntity admin = adminEntity.get();
		            model.addAttribute("adminForm", admin); // `contact`オブジェクトを渡す
		            model.addAttribute("admin", List.of(admin)); // adminリストを設定
		            return "admin/edit";
		        } 
			   else {
				   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
			        return "error"; // エラーページを返す
			   }
	    }
	@PreAuthorize("hasRole('1')")
    @PostMapping("/admin/edit/:id/update")
    public String adminupdate(@RequestParam("id") Long id, @Valid @ModelAttribute AdminForm adminForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminList", adminServiceImp.getAllAdmins());
            return "admin/edit"; // エラーがある場合、編集画面を再表示
        }
        
        Optional<AdminEntity> AdminEntity = adminServiceImp.getAdminEntityById(id);
		   if (AdminEntity.isPresent()) {
			   adminServiceImp.updateAdmin(adminForm);
	        } 
		   else {
			   
		   }
		   model.addAttribute("adminentity", adminForm);
        return "redirect:/admin/list"; // 更新後に一覧画面にリダイレクト
    }
	 @GetMapping("/admin/detail/{id}")
	 	public String showAdminDetails(@PathVariable Long id, Model model) {
	   	Optional<AdminEntity> adminEntity = adminServiceImp.getAdminEntityById(id);
	   		if (adminEntity.isPresent()) {
		        AdminEntity admin = adminEntity.get();
		        model.addAttribute("admin", admin); // `contact`オブジェクトを渡す
		        return "admin/detail";
	   		} 
	   		else {
			   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
		        return "error"; // エラーページを返す
		   }
	 }
	 @PreAuthorize("hasRole('1')")
	 @PostMapping("/admin/delete/:id")
	public String deleteAdmin(@RequestParam("id") Long id, Model model){
		 adminServiceImp.deleteAdmin(id);
		    return "redirect:/admin/list";
	 }
	 //管理者情報の処理↑
	
	 
	 
	 
 //メーカ情報の処理
	//メーカ一覧画面へポスト処理
	@PostMapping("/manufactures/list")
	public String manufactureslistpost() {
		return "redirect:list";
	}
	//メーカ一覧画面でのゲット処理
	@GetMapping("/manufactures/list")
	public String manufactureslistget(Model model) {
		//manufacturesエンティティのデータを取得する為に、サービスクラスから取得
		 List<ManufacturesEntity> manufacturesList = manufacturesServiceImpl.getAllManufactures();
		 System.out.println("Manufactures List Size: " + manufacturesList.size()); 
		 model.addAttribute("manufacturesList", manufacturesList);
		return "/manufactures/list";
		
	}
	
	@GetMapping("/manufactures/signup")
	public String manufacturessignup(Model model) {
		model.addAttribute("manufacturesForm",new ManufacturesForn());
		return("manufactures/signup");
	}
	@PreAuthorize("hasRole('ROLE1')")
	@PostMapping("/manufactures/signup")
	public String manufacturessignuppost(){
		return("redirect:signup");
	}
	@PostMapping("/manufactures/signup/register")	
	public String manufactures(@Validated @ModelAttribute("manufacturesForm") ManufacturesForn manufacturesForm, BindingResult errorResult, Model model,HttpServletRequest request){
		
		 if (errorResult.hasErrors()) {
			 model.addAttribute("manufacturesForm", manufacturesForm); 
	          return "admin/signup";
	        }
	     manufacturesService.saveManufactures(manufacturesForm);
		 
		return("top");
		
	}
	@PreAuthorize("hasRole('1')")
	 @GetMapping("/manufactures/edit/:id")
	    public String manufacturesedit(@RequestParam("id") Long id,Model model) {
			Optional<ManufacturesEntity> manufactuersEntity = manufacturesServiceImpl.getManufacturesEntityById(id);
			   if (manufactuersEntity.isPresent()) {
		            ManufacturesEntity manufactures = manufactuersEntity.get();
		            model.addAttribute("manufacturesForm", manufactures);
		            model.addAttribute("manufactures", List.of(manufactures)); 
		            return "manufactures/edit";
		        } 
			   else {
				   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
			        return "error"; // エラーページを返す
			   }
	    }
	 @PreAuthorize("hasRole('1')")
	 @PostMapping("/manufactures/edit/:id/update")
	    public String manufacturesupdate(@RequestParam("id") Long id, @Valid @ModelAttribute ManufacturesForn manufacturesForm, BindingResult bindingResult, Model model) {
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("manufacturesList", manufacturesServiceImpl.getAllManufactures());
	            return "admin/edit"; // エラーがある場合、編集画面を再表示
	        }
	        
	        Optional<ManufacturesEntity> ManufacturesEntity = manufacturesServiceImpl.getManufacturesEntityById(id);
			   if (ManufacturesEntity.isPresent()) {
				   manufacturesServiceImpl.updateManufactures(manufacturesForm);
		        } 
			   else {
				   
			   }
			   model.addAttribute("manufacturesentity", manufacturesForm);
	        return "redirect:/manufactures/list"; // 更新後に一覧画面にリダイレクト
	    }
 //
	@PostMapping("/category/list")
		public String categorylistpost() {
			return "redirect:list";
		}
		//管理者一覧画面でのゲット処理
		@GetMapping("/category/list")
		public String categorylistget(Model model) {
			//ItemLargeategoryエンティティのデータを取得する為に、サービスクラスから取得
			 List<ItemLargeCategoryEntity> itemLargeCategoryList = itemLargeCategoryServiceImpl.getAllItemLargeCategory();
			 System.out.println("Manufactures List Size: " + itemLargeCategoryList.size()); 
			 model.addAttribute("itemLargeCategoryList", itemLargeCategoryList);
			return "/category/list";
			
		}
	 
		 @GetMapping("/category/detail/:id")
		 	public String showCategoryDetails(@RequestParam("id") Long id, Model model) {
		   	Optional<ItemLargeCategoryEntity> itemLargeCategoryEntity = itemLargeCategoryServiceImpl.getItemLargeCategoryEntityById(id);
		   		if (itemLargeCategoryEntity.isPresent()) {
			        ItemLargeCategoryEntity itemLargeCategory = itemLargeCategoryEntity.get();
			        model.addAttribute("itemLargeCategoryForm", itemLargeCategory); // `contact`オブジェクトを渡す
			        model.addAttribute("itemLargeCategory", List.of(itemLargeCategory)); // adminリストを設定
			        return "category/detail";
		   		} 
		   		else {
				   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
			        return "error"; // エラーページを返す
			   }
		 } 
 //
// 商品一覧表示と検索機能
 @PostMapping("/item/list")
public String itemlistpost() {
	return "redirect:list";
	}
 
	@GetMapping("/item/list")
public String itemlistget(Model model) {
	//manufacturesエンティティのデータを取得する為に、サービスクラスから取得
	 List<ItemEntity> itemList = itemServiceImpl.getAllItem();
	 System.out.println("Manufactures List Size: " + itemList.size()); 
	 model.addAttribute("itemList", itemList);
	return "item/list";
	
}


@GetMapping("/items")
public String getItemList(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(required = false) String searchQuery,
        @RequestParam(required = false) String category,
        Model model) {
    
    // 商品リストを取得
    List<ItemEntity> itemList = itemServiceImpl.findItems(page, searchQuery, category);
    
    // ページ情報の取得
    int totalPages = itemServiceImpl.getTotalPages(searchQuery, category);
    
    // モデルにデータを追加
    model.addAttribute("itemList", itemList);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("searchQuery", searchQuery);
    model.addAttribute("category", category);
    
    return "item/list"; // itemList.htmlに遷移
}

@GetMapping("/search")
public String searchItems(
        @RequestParam String name,
        @RequestParam(required = false) String largeCategoryId,
        @RequestParam(required = false) String underCategoryId,
        @RequestParam(required = false) String smallCategoryId,
        Model model) {
    
    // 商品を検索
    List<ItemEntity> searchResults = itemService.searchItems(name, largeCategoryId, underCategoryId, smallCategoryId);
    
    // モデルにデータを追加
    model.addAttribute("itemList", searchResults);
    model.addAttribute("searchQuery", name);
    model.addAttribute("largeCategoryId", largeCategoryId);
    model.addAttribute("underCategoryId", underCategoryId);
    model.addAttribute("smallCategoryId", smallCategoryId);
    
    return "item/list"; // itemList.htmlに遷移
    }
 
    // 商品詳細ページにリダイレクトする
@GetMapping("/item/detail/:id")
public String itemDetail(@RequestParam Long id, Model model) {
	Optional<ItemEntity> itemEntity = itemServiceImpl.getItemEntityById(id);
	if (itemEntity.isPresent()) {
        ItemEntity item = itemEntity.get();
        model.addAttribute("item", item); // adminリストを設定
        return "item/detail";
	} 
	else {
	   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");}
	return null;
}


@PostMapping("/item/order/{id}")
public String orderItemPage(@PathVariable Long id, Model model) {
    Optional<ItemEntity> itemEntity = itemServiceImpl.getItemEntityById(id);
    if (itemEntity.isPresent()) {
        ItemEntity item = itemEntity.get();
        model.addAttribute("item", item); // アイテムリストを設定
        return "item/order"; // アイテムの注文ページを返す
    } else {
        model.addAttribute("errorMessage", "指定されたアイテムが見つかりません。");
        return "item/error"; // エラーページを返す
    }
}
@PostMapping("/item/older/complete")
public String orderItem(@RequestParam("id") Long id, @RequestParam("quantityOfStock") Long quantityOfStock, Principal principal) {
    
    // ログイン中のユーザーからshopIDを取得
    AdminEntity admin = adminRepository.findByEmail(principal.getName());
    Long shopId = admin.getShopId();

    // shopItemテーブルからshopIdとitemIdに一致するデータを検索
    ShopItemEntity shopItem = shopItemRepository.findByShop_IdAndItem_Id(shopId, id);
    if (shopItem == null) {
        shopItem = new ShopItemEntity();
        shopItem.setShopId(shopId);
        shopItem.setItemId(id);
        shopItem.setQuantityOfStock(quantityOfStock); // 初回の場合、そのまま設定
    } else {
        // 既存データがある場合、現在の在庫数に発注数を追加
        shopItem.setQuantityOfStock(shopItem.getQuantityOfStock() + quantityOfStock);
    }

    // shopItemテーブルに保存
    shopItemRepository.save(shopItem);

    // 発注履歴の作成と保存
    OrderHistoryEntity orderHistory = new OrderHistoryEntity();
    orderHistory.setAdmin(admin);
    orderHistory.setShop(shopItem.getShop());
    orderHistory.setItem(shopItem.getItem());
    orderHistory.setNumberOfOrders(quantityOfStock);
    orderHistory.setTotalAmount(calculateTotalAmount(quantityOfStock, shopItem.getItem()));
    orderHistoryRepository.save(orderHistory);

    System.out.println("Received id: " + id + ", Quantity: " + quantityOfStock);
    return "redirect:/item/list"; 
}

// 合計金額の計算メソッド（必要に応じて金額を取得して計算）
		    private Long calculateTotalAmount(Long quantity, ItemEntity item) {
		        return item.getPurchasePrice() * quantity;
		    }
 //

//
 @PostMapping("/orderhistory/list")
	public String orderhistorylistpost() {
		return "redirect:list";
	}
 
	@GetMapping("/orderhistory/list")
	public String orderhistorylistget(Model model) {
		//manufacturesエンティティのデータを取得する為に、サービスクラスから取得
		 List<OrderHistoryEntity> orderhistoryList = orderHistoryServiceImpl.getAllItem();
		 System.out.println("Manufactures List Size: " + orderhistoryList.size()); 
		 model.addAttribute("orderhistoryList", orderhistoryList);
		return "orderhistory/list";
		
	}
//
	
	
//
	@PostMapping("/shop/list")
	public String shoplistpost() {
		return "redirect:list";
	}
	//管理者一覧画面でのゲット処理
	@GetMapping("/shop/list")
	public String shoplistget(Model model) {
		//adminエンティティのデータを取得する為に、サービスクラスから取得
		 List<ShopEntity> shopList = shopServiceImpl.getAllShop();
		 System.out.println("Shop List Size: " + shopList.size()); 
		 model.addAttribute("shopList", shopList);
		return "shop/list";
		
	}			
	
	@GetMapping("/shop/signup")
	public String shopsignup(Model model) {
		model.addAttribute("shopForm",new ShopForm());
		return("shop/signup");
	}
	@PostMapping("/shop/signup")
	public String shopsignuppost(){
		return("redirect:signup");
	}
	
	@PostMapping("/shop/signup/register")	
	public String shop(@Validated @ModelAttribute("shopForm") ShopForm shopForm, BindingResult errorResult, Model model,HttpServletRequest request){
		
		 if (errorResult.hasErrors()) {
			 model.addAttribute("shopForm", shopForm); 
	          return "shop/signup";
	        }
	     shopService.saveShop(shopForm);
		 
		return("top");
	}
	@GetMapping("/shop/edit/:id")
    public String shopedit(@RequestParam("id") Long id,Model model) {
		Optional<ShopEntity> shopEntity = shopServiceImpl.getShopEntityById(id);
		   if (shopEntity.isPresent()) {
	            ShopEntity shop = shopEntity.get();
	            model.addAttribute("shopForm", shop); // `contact`オブジェクトを渡す
	            model.addAttribute("shop", List.of(shop)); // adminリストを設定
	            return "shop/edit";
	        } 
		   else {
			   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
		        return "error"; // エラーページを返す
		   }
    }

@PostMapping("/shop/edit/:id/update")
public String shopupdate(@RequestParam("id") Long id, @Valid @ModelAttribute ShopForm shopForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("shopList", shopServiceImpl.getAllShop());
        return "shop/edit"; // エラーがある場合、編集画面を再表示
    }
    
    Optional<ShopEntity> ShopEntity = shopServiceImpl.getShopEntityById(id);
	   if (ShopEntity.isPresent()) {
		   shopServiceImpl.updateAdmin(shopForm);
        } 
	   else {
		   
	   }
	   model.addAttribute("shopentity", shopForm);
    return "redirect:/shop/list"; // 更新後に一覧画面にリダイレクト
}
@GetMapping("/shop/detail/{id}")
public String shopDetail(@PathVariable Long id, Model model) {
    Optional<ShopEntity> shopEntity = shopServiceImpl.getShopEntityById(id);
    if (shopEntity.isPresent()) {
        ShopEntity shop = shopEntity.get(); // Optionalを解除
        ShopItemEntity shopItems = shopItemServiceImpl.findByShopId(id); // shopIdで取得したリスト
        model.addAttribute("shop", shop);
        model.addAttribute("shopItems", shopItems);
        return "shop/detail";
    } else {
        model.addAttribute("errorMessage", "指定された店舗が見つかりません。");
        return "error";
    }
}
@PostMapping("/shop/delete/:id")
public String deleteShop(@RequestParam("id") Long id, Model model){
	 shopServiceImpl.deleteShop(id);
	    return "redirect:/shop/list";
}

//		

//
	@PostMapping("/adminprofile/detail")
	public String adminprofiledetailpost() {
		return "redirect:detail";
	}
    @GetMapping("/adminprofile/detail")
    public String showProfile(Model model, Principal principal) {
        // ログイン中の管理者のメールアドレスを取得
        String email = principal.getName();
        
        // 管理者情報を取得
        AdminEntity admin = adminService.getLoggedInAdmin(email);
        
        // モデルに追加してHTMLに渡す
        model.addAttribute("admin", admin);
        
        return "adminprofile/detail"; // dashboard.htmlに遷移
    }
//
    
 //
    @PostMapping("/adminprofile/edit")
	public String adminprofileeditpost() {
		return "redirect:edit";
	}
    @GetMapping("/adminprofile/edit/{id}")
    public String adminprofileedit(@PathVariable Long id,Model model) {
		Optional<AdminEntity> adminEntity = adminServiceImp.getAdminEntityById(id);
		   if (adminEntity.isPresent()) {
	            AdminEntity admin = adminEntity.get();
	            model.addAttribute("adminForm", admin); // `contact`オブジェクトを渡す
	            model.addAttribute("admin", List.of(admin)); // adminリストを設定
	            return "adminprofile/edit";
	        } 
		   else {
			   model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
		        return "error"; // エラーページを返す
		   }
    }

@PostMapping("/adminprofile/edit/{id}/update")
public String adminprofileupdate(@PathVariable Long id, @Valid @ModelAttribute AdminForm adminForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("adminList", adminServiceImp.getAllAdmins());
        return "adminprofile/edit"; // エラーがある場合、編集画面を再表示
    }
    
    Optional<AdminEntity> AdminEntity = adminServiceImp.getAdminEntityById(id);
	   if (AdminEntity.isPresent()) {
		   adminServiceImp.updateAdmin(adminForm);
        } 
	   else {
		   
	   }
	   model.addAttribute("adminentity", adminForm);
    return "redirect:/top"; // 更新後に一覧画面にリダイレクト
}
 //

	//ログアウト
	@PostMapping("logout")
	public String logout() {
		return("login");
	}
}
