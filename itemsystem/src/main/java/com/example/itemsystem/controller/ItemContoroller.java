package com.example.itemsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entity.AdminEntity;
import com.example.itemsystem.entity.ItemEntity;
import com.example.itemsystem.entity.OrderHistoryEntity;
import com.example.itemsystem.entity.ShopItemEntity;
import com.example.itemsystem.repository.AdminRepository;
import com.example.itemsystem.repository.OrderHistoryReopsitory;
import com.example.itemsystem.repository.ShopItemRepository;
import com.example.itemsystem.service.ItemService;
import com.example.itemsystem.service.OlderHistoryService;
import com.example.itemsystem.service.ShopItemService;

@Controller
public class ItemContoroller {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ShopItemService shopItemService;
    @Autowired
    private OlderHistoryService olderHistoryServie;
    @Autowired
    private ShopItemRepository shopItemRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrderHistoryReopsitory orderHistoryRepository;

    // 商品一覧表示と検索機能
    @PostMapping("/item/list")
    public String itemlistpost() {
        return "redirect:list";
    }

    @GetMapping("/item/list")
    public String itemlistget(Model model) {
        // manufacturesエンティティのデータを取得する為に、サービスクラスから取得
        List<ItemEntity> itemList = itemService.getAllItem();
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
        List<ItemEntity> itemList = itemService.findItems(page, searchQuery, category);

        // ページ情報の取得
        int totalPages = itemService.getTotalPages(searchQuery, category);

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
        Optional<ItemEntity> itemEntity = itemService.getItemEntityById(id);
        if (itemEntity.isPresent()) {
            ItemEntity item = itemEntity.get();
            model.addAttribute("item", item); // adminリストを設定
            return "item/detail";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
        }
        return null;
    }

    @PostMapping("/item/order/{id}")
    public String orderItemPage(@PathVariable Long id, Model model) {
        // ItemEntityを取得
        Optional<ItemEntity> itemEntity = itemService.getItemEntityById(id);
        if (itemEntity.isPresent()) {
            // ShopItemEntityを取得
            Optional<ShopItemEntity> shopItemOptional = shopItemService.findById(id);

            if (shopItemOptional.isPresent()) {
                ShopItemEntity shopItem = shopItemOptional.get();
                model.addAttribute("shopitem", shopItem); // 中身のエンティティを追加
            } else {
                model.addAttribute("shopitem", new ShopItemEntity()); // デフォルト値として新規インスタンスを追加
            }

            // itemEntityの中身を取り出してModelに追加
            ItemEntity item = itemEntity.get();
            model.addAttribute("item", item); // アイテムリストを設定
            return "item/order"; // アイテムの注文ページを返す
        } else {
            model.addAttribute("errorMessage", "指定されたアイテムが見つかりません。");
            return "item/error"; // エラーページを返す
        }
    }

    @PostMapping("/item/older/complete")
    public String orderItem(@RequestParam("id") Long id, @RequestParam("quantityOfStock") Long quantityOfStock, Principal principal, Long salePrice) {

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
            shopItem.setSalePrice(salePrice);
        } else {
            // 既存データがある場合、現在の在庫数に発注数を追加
            shopItem.setQuantityOfStock(shopItem.getQuantityOfStock() + quantityOfStock);
            shopItem.setSalePrice(salePrice);
        }

        // shopItemテーブルに保存
        shopItemRepository.save(shopItem);

        // 発注履歴の作成と保存
        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        orderHistory.setAdmin(admin);
        orderHistory.setShop(shopItem.getShop());
        orderHistory.setItem(shopItem.getItem());
        orderHistory.setNumberOfOrders(quantityOfStock);
        orderHistory.setTotalAmount(calculateTotalAmount(quantityOfStock, salePrice));
        orderHistoryRepository.save(orderHistory);

        System.out.println("Received id: " + id + ", Quantity: " + quantityOfStock);
        return "redirect:/item/list";
    }

    // 合計金額の計算メソッド（必要に応じて金額を取得して計算）
    private Long calculateTotalAmount(Long quantity, Long salePrice) {
        return salePrice * quantity;
    }

    @PostMapping("/orderhistory/list")
    public String orderhistorylistpost() {
        return "redirect:list";
    }

    @GetMapping("/orderhistory/list")
    public String orderhistorylistget(Model model) {
        // manufacturesエンティティのデータを取得する為に、サービスクラスから取得
        List<OrderHistoryEntity> orderhistoryList = olderHistoryServie.getAllItem();
        System.out.println("Manufactures List Size: " + orderhistoryList.size());
        model.addAttribute("orderhistoryList", orderhistoryList);
        return "orderhistory/list";
    }
}

