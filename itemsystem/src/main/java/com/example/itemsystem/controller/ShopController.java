package com.example.itemsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.itemsystem.entity.ShopEntity;
import com.example.itemsystem.entity.ShopItemEntity;
import com.example.itemsystem.form.ShopForm;
import com.example.itemsystem.service.ShopItemService;
import com.example.itemsystem.service.ShopService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ShopController {

    @Autowired
    private ShopItemService shopItemService;
    @Autowired
    private ShopService shopService;

    @PostMapping("/shop/list")
    public String shoplistpost() {
        return "redirect:list";
    }

    // 管理者一覧画面でのゲット処理
    @GetMapping("/shop/list")
    public String shoplistget(Model model) {
        // adminエンティティのデータを取得する為に、サービスクラスから取得
        List<ShopEntity> shopList = shopService.getAllShop();
        System.out.println("Shop List Size: " + shopList.size());
        model.addAttribute("shopList", shopList);
        return "shop/list";
    }

    @GetMapping("/shop/signup")
    public String shopsignup(Model model) {
        model.addAttribute("shopForm", new ShopForm());
        return "shop/signup";
    }

    @PostMapping("/shop/signup")
    public String shopsignuppost() {
        return "redirect:signup";
    }

    @PostMapping("/shop/signup/register")
    public String shop(@Validated @ModelAttribute("shopForm") ShopForm shopForm, BindingResult errorResult, Model model, HttpServletRequest request) {

        if (errorResult.hasErrors()) {
            model.addAttribute("shopForm", shopForm);
            return "shop/signup";
        }
        shopService.saveShop(shopForm);
        return "top";
    }

    @GetMapping("/shop/edit/:id")
    public String shopedit(@RequestParam("id") Long id, Model model) {
        Optional<ShopEntity> shopEntity = shopService.getShopEntityById(id);
        if (shopEntity.isPresent()) {
            ShopEntity shop = shopEntity.get();
            model.addAttribute("shopForm", shop); // `contact`オブジェクトを渡す
            model.addAttribute("shop", List.of(shop)); // adminリストを設定
            return "shop/edit";
        } else {
            model.addAttribute("errorMessage", "指定された管理者が見つかりません。");
            return "error"; // エラーページを返す
        }
    }

    @PostMapping("/shop/edit/:id/update")
    public String shopupdate(@RequestParam("id") Long id, @Valid @ModelAttribute ShopForm shopForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("shopList", shopService.getAllShop());
            return "shop/edit"; // エラーがある場合、編集画面を再表示
        }

        Optional<ShopEntity> ShopEntity = shopService.getShopEntityById(id);
        if (ShopEntity.isPresent()) {
            shopService.updateAdmin(shopForm);
        } else {
            // Handle error
        }
        model.addAttribute("shopentity", shopForm);
        return "redirect:/shop/list"; // 更新後に一覧画面にリダイレクト
    }

    @GetMapping("/shop/detail/{id}")
    public String shopDetail(@PathVariable Long id, Model model) {
        Optional<ShopEntity> shopEntity = shopService.getShopEntityById(id); // ShopEntityをidで取得
        if (shopEntity.isPresent()) {
            ShopEntity shop = shopEntity.get(); // Optionalを解除
            List<ShopItemEntity> shopItems = shopItemService.getShopItemsByShopId(id); // shopIdで取得したShopItemのリスト
            model.addAttribute("shop", shop); // 店舗情報をモデルに追加
            model.addAttribute("shopItems", shopItems); // 店舗に対応する商品情報をモデルに追加
            return "shop/detail"; // 商品一覧ページを返す
        } else {
            model.addAttribute("errorMessage", "指定された店舗が見つかりません。");
            return "error"; // 店舗が見つからない場合はエラーページを返す
        }
    }

    @PostMapping("/shop/delete/:id")
    public String deleteShop(@RequestParam("id") Long id, Model model) {
        shopService.deleteShop(id);
        return "redirect:/shop/list";
    }
}
