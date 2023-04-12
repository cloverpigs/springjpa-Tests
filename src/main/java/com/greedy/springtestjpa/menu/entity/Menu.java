package com.greedy.springtestjpa.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="TBL_MENU")
@SequenceGenerator    // 시퀀스를 만들어주는 어노테이션
	(
		name="SEQ_MENU_CODE_GENERATOR", // 해당 시퀀스 설정에 대한 이름
		sequenceName="SEQ_MENU_CODE",	// 사용할 시퀀스 이름(DB에서 사용되는 실제 이름)
		initialValue=100,				// 초기값인데 새로 만드는 경우가 아닌 기존 시퀀스와 매핑이 되는경우 어떤 값을 넣어도 기존 DB설정을 따르고 변경이 되지 않는다.
		allocationSize=1				// 증가값
		
	)  
public class Menu {
	
	@Id							//PK에 해당하는 속성에 지정
	@Column(name="MENU_CODE")   //데이터베이스에 대응되는 컬럼명 지정
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,		// 값 생성 시 시퀀스 전략을 이용하겠다는 설정
			generator="SEQ_MENU_CODE_GENERATOR"		// 사용할 시퀀스 설정 이름
			)
	private int menuCode;
	
	@Column(name="MENU_NAME")
	private String menuName;
	
	@Column(name="MENU_PRICE")
	private int menuPrice;
	
	@Column(name="CATEGORY_CODE")
	private int categoryCode;
	
	@Column(name="ORDERABLE_STATUS")
	private String orderableStatus;

	public Menu() {}

	public Menu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
		super();
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.categoryCode = categoryCode;
		this.orderableStatus = orderableStatus;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getOrderableStatus() {
		return orderableStatus;
	}

	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	@Override
	public String toString() {
		return "Menu [menuCode=" + menuCode + ", menuName=" + menuName + ", menuPrice=" + menuPrice + ", categoryCode="
				+ categoryCode + ", orderableStatus=" + orderableStatus + "]";
	}
	
	
	

}
