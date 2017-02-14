package com.naruku.fisher.cart;

/**
 * Created by apandy on 12/30/16.
 */
public class CartList {

 /*   public String product_title=;

    public String product_count="10";

    public int product_value=300;
*/
    private String strItemName="TUNA FISH";
    private String strQuantity="10";
    private int strAmount=300;

    public CartList(String itemName, String quantity, int amount, String itemType) {
        this.strItemName = itemName;
        this.strQuantity = quantity;
        this.strAmount = amount;
    }

    public String getStrItemName() {
        return strItemName;
    }

    public void setStrItemName(String strItemName) {
        this.strItemName = strItemName;

    }

    public String getStrQuantity() {
        return strQuantity;
    }

    public void setStrQuantity(String strQuantity) {
        this.strQuantity = strQuantity;
    }

    public int getStrAmount() {
        return strAmount;
    }

    public void setStrAmount(int strAmount) {
        this.strAmount = strAmount;

    }

}
