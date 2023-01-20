package S4_Cart;

import java.util.ArrayList;
import java.util.Scanner;

import S0_MALL.MallController;
import S2_Item.Item;
import S_MyUtil.Util;

public class CartController {
	private CartController() {}
	static private CartController instance = new CartController();
	static public CartController getInstance() {
		return instance;
	}
	private CartDAO cartDAO;
	private Scanner scan;	
	private MallController mallController;	
	public void init(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
		scan = Util.scan;
		mallController = MallController.getInstance();
	}
	
	public void insertCart(Item item) {
		Cart cart = new Cart();
		cart.setNumber(cartDAO.getCartNumber());
		cart.setMemberID(mallController.getMemberLoginID());
		cart.setItemName(item.getItemName());
		cart.setItemPrice(item.getPrice());
		cartDAO.insertCart(cart);
	}
	
	public void menuCart() {
		
		while(true) {
			System.out.println("===[장바구니관리]===");
			System.out.println("1)장바구니출력 2)구입 3)삭제 0)뒤로가기");
			int sel = scan.nextInt();
			if(sel == 0) {
				break;
			}
			else if(sel == 1) {
				ArrayList<Cart> oneCartList = 
						cartDAO.getOneCartList(mallController.getMemberLoginID());
				cartDAO.printOneCartList(oneCartList);
			} else if (sel == 2) { //구입
				cartDAO.printOneCartList(cartDAO.getOneCartList(mallController.getMemberLoginID()));
				System.out.println("==========================");
				int sum =0;
				for(Cart c : cartDAO.getOneCartList(mallController.getMemberLoginID())) {
					sum += c.getItemPrice();
				}
				System.out.printf("[총액] %d원\n", sum);
				cartDAO.deleteCart(mallController.getMemberLoginID());
				System.out.println("[구입 완료]");
				
				
			} else if ( sel == 3) { // 삭제
				
				cartDAO.getOneCartList(mallController.getMemberLoginID());
				System.out.println("삭제할 아이템 목록번호 입력 >> ");
				sel = scan.nextInt()-1;
				cartDAO.deleteCartitem(sel);
			}
		}
	}
	
	public void printAllCartList() {
		cartDAO.printAllCartList();
	}
	
	public void printOneCartList(String id) {
		cartDAO.printOneCartList(cartDAO.getOneCartList(id));
	}
	
}
