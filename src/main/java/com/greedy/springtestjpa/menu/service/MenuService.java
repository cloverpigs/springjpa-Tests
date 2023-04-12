package com.greedy.springtestjpa.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.springtestjpa.menu.DTO.CategoryDTO;
import com.greedy.springtestjpa.menu.DTO.MenuDTO;
import com.greedy.springtestjpa.menu.entity.Category;
import com.greedy.springtestjpa.menu.entity.Menu;
import com.greedy.springtestjpa.menu.repository.MenuRepository;

@Service
public class MenuService {
	
	
	private MenuRepository menuRepository;
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}
	
	
	public List<MenuDTO> findAllMeun() {
		
		List<Menu> menuList = menuRepository.findAllMenu(entityManager);
		
		return menuList.stream().map(menu -> modelMapper.map(menu, MenuDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional
	public void registNewMenu(MenuDTO newMenu) {
		
		menuRepository.registNewMenu(entityManager, modelMapper.map(newMenu, Menu.class));
	}


	public List<CategoryDTO> findAllCategory() {
		
		List<Category> categoryList = menuRepository.findAllcategory(entityManager);
		
		
		return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional
	public void modifyMenu(MenuDTO menu) {
		
		menuRepository.modifyMenu(entityManager, modelMapper.map(menu, Menu.class));
		
	}

	public MenuDTO findMenuByMenuCode(int MenuCode) {
		
		return modelMapper.map(menuRepository.findMenuByCode(entityManager, MenuCode), MenuDTO.class);
	}
	
	@Transactional
	public void deleteMenu(int menuCode) {
		
		menuRepository.deleteMenu(entityManager, menuCode);
	}
	
}
