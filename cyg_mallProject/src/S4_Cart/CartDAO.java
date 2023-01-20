package S4_Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartDAO {

	private ArrayList<Cart> cartList;
	private int cartNumber;
	
	public CartDAO() {
		cartList = new ArrayList<Cart>();
		cartNumber = 1000;
	}

	public int getCartNumber() {
		cartNumber += 1;
		return cartNumber;
	}

	public void insertCart(Cart cart) {
		cartList.add(cart);
	}

	public ArrayList<Cart> getOneCartList(String memberLoginID) {
		ArrayList<Cart> oneCartList = new ArrayList<Cart>();


		for (int i = 0; i < cartList.size(); i++) {
			if(cartList.get(i).getMemberID().equals(memberLoginID)) {
				oneCartList.add(cartList.get(i));
			}
		}
	
		return oneCartList;
	}
	
	public void deleteCart(String memberLoginID) {
		for(int i = 0; i<cartList.size(); i++) {
			if(cartList.get(i).getMemberID().equals(memberLoginID)) {
				cartList.remove(i);
				i--;
			}
		}
	}
	
	public void deleteCartitem(int i) {
		if(cartNumber == 1000) {
			System.err.println("장바구니에 물품이 없습니다.");
			return;
		}
		cartList.remove(i);
	}
	
	public void printOneCartList(ArrayList<Cart> oneCartList) {
		for (int i = 0; i < oneCartList.size(); i++) {
			System.out.printf("%2d) %s\n",i+1 , oneCartList.get(i));
		}
	}
	
	public void printAllCartList() {
		for (int i = 0; i < cartList.size(); i++) {
			System.out.printf("%2d) + %s\n" ,i+1 , cartList.get(i));
		}
	}
	
	
	
}
