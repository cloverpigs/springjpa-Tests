package com.greedy.springtestjpa.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.springtestjpa.menu.DTO.CategoryDTO;
import com.greedy.springtestjpa.menu.DTO.MenuDTO;
import com.greedy.springtestjpa.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private MenuService menuService;
	
	public MenuController (MenuService menuService) {
		this.menuService = menuService;
		
	}
	
	/* 메뉴 조회하기 */
	@GetMapping("/list")
	public String findAllMenu(Model model) {
		List<MenuDTO> menuList = menuService.findAllMeun();
		
		model.addAttribute("menuList", menuList);
		
		return "menu/list";
	}
	
	/*메뉴 등록하기 페이지 */
	@GetMapping("/regist")
	public void registPage() {};
	
	/* 메뉴 등록 진행*/
	@PostMapping("/regist")
	public String registMenu(@ModelAttribute MenuDTO newMenu) {
		menuService.registNewMenu(newMenu);
		
		return "redirect:/menu/list";
	}
	
	/* 카테고리리스트에 대한 값 비동기 통신 */
	@GetMapping(value="category", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<CategoryDTO> findAllCategoryList(){
		
		return menuService.findAllCategory();
	}
	
	/* 메뉴 수정하기 페이지 */
	@GetMapping("/modify")
	public void modifyPage() {}
	
	/* 메뉴리스트에 대한 값 비동기 통시*/
	@GetMapping(value="menuList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<MenuDTO> findAllMenuList() {
		
		return menuService.findAllMeun();
	}
	
	/* 메뉴 수정 등록*/
	@PostMapping("/modify")
	public String modifyMenu(@ModelAttribute MenuDTO menu, RedirectAttributes rttr) {
		
		MenuDTO beforeModifyMenu = menuService.findMenuByMenuCode(menu.getMenuCode());
		
		rttr.addFlashAttribute("beforeModifyMenu", beforeModifyMenu);
		
		menuService.modifyMenu(menu);
		
		return "redirect:/menu/" + menu.getMenuCode();
	}
	
	/*메뉴코드에 맞는 메뉴값 가져오기 비동기 통신*/
	@GetMapping(value="modifyOneMenu", produces="application/json; charset=UTF-8")
	@ResponseBody
	public MenuDTO findOneMenu(@RequestParam int menuCode) {
		
		return menuService.findMenuByMenuCode(menuCode);
	}
	
	/* 수정된 결과 보여주는 페이지*/
	@GetMapping("/{menuCode}")
	public String findMenuByMenuCode(@PathVariable int menuCode, Model model) {
		
		MenuDTO menu = menuService.findMenuByMenuCode(menuCode);
		
		model.addAttribute("menu", menu);
		
		return "menu/modifyResult";
		
	}
	
	/* 메뉴 삭제하기 페이지 */
	@GetMapping("/delete")
	public void deletePage() {}
	
	
	/* 메뉴 삭제 진행*/
	@PostMapping("delete")
	public String deleteMenu(@RequestParam(value="menuCode") int menuCode, RedirectAttributes rttr) {
		
		MenuDTO menu = menuService.findMenuByMenuCode(menuCode);
		
		String message = menu.getMenuName() + "이(가) 삭제 되었습니다.";
		rttr.addFlashAttribute("message", message);
		
		menuService.deleteMenu(menuCode);
		
		return "redirect:/menu/list";
	}
	

}
