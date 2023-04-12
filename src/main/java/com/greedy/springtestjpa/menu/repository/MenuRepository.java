package com.greedy.springtestjpa.menu.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.greedy.springtestjpa.menu.entity.Category;
import com.greedy.springtestjpa.menu.entity.Menu;

@Repository
public class MenuRepository {
	
	public Menu findMenuByCode(EntityManager entityManager, int menuCode) {
		
		return entityManager.find(Menu.class, menuCode);
	}

	public List<Menu> findAllMenu(EntityManager entityManager){
		
		String jpql = "SELECT m FROM Menu m ORDER BY m.menuCode ASC";
		
		return entityManager.createQuery(jpql,Menu.class).getResultList();
	}
	
	public void registNewMenu(EntityManager entityManager, Menu menu) {
		
		entityManager.persist(menu);
		
	}
	
	public List<Category> findAllcategory(EntityManager entityManager){
		
		String jpql = "SELECT c FROM Category c ORDER BY c.categoryCode ASC";
		
		return entityManager.createQuery(jpql, Category.class).getResultList();
	}
	
	public void modifyMenu(EntityManager entityManager, Menu menu) {
		
		Menu selectedMenu = entityManager.find(Menu.class, menu.getMenuCode());
		
		selectedMenu.setMenuName(menu.getMenuName());
		selectedMenu.setMenuPrice(menu.getMenuPrice());
		selectedMenu.setCategoryCode(menu.getCategoryCode());
		selectedMenu.setOrderableStatus(menu.getOrderableStatus());
		
	}
	
	public void deleteMenu(EntityManager entityManager, int menuCode) {
		
		Menu deleteMenu = entityManager.find(Menu.class, menuCode);
		entityManager.remove(deleteMenu);
		
	}
	
}
