/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanhp.util;

/**
 *
 * @author ASUS
 */
public class MyApplicationConstants {
    public class DispatchFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String START_UP_CONTROLLER = "startUpController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
        public static final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String SEARCH_ITEMS_CONTROLLER = "shoppingController";
        public static final String VIEW_PAGE_CART = "viewCartController";
        public static final String CHECK_OUT_CONTROLLER = "checkOutController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
    }
    
    public class LoginFeatures {
        public static final String SEARCH_PAGE = "searchHTMLPage";
        public static final String INVALID_PAGE = "invalidPage";
    }
    public class LogoutFeatures {
       public static final String LOGIN_PAGE = "";
    }
    public class CreateAccountFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String ERROR_PAGE = "createAndErrorAccountController";
        public static final String CREATE_ACCOUNT_PAGE = "createAccountPage";
    }
    public class CheckOutFeatures {
        public static final String VIEW_CART_PAGE = "viewCartController";
        public static final String CHECK_OUT_PAGE = "checkOutPage";
    }
    public class DeleteAccountFeatures {
        public static final String ERROR_PAGE = "errorPage";
    }
    public class SearchLastNameFeatures {
        public static final String SEARCH_PAGE = "searchHTMLPage";
        public static final String RESULT_PAGE = "searchPage";
    }
    public class ShoppingFeatures {
        public static final String SHOPPING_PAGE = "shoppingPage";
    }
    public class StartUpFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "searchPage";
    }
    public class UpdateAccountFeatures {
        public static final String UPDATE_ERROR = "updateErrorPage";
    }
}
