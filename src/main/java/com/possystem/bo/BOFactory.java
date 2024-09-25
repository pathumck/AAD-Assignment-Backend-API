package com.possystem.bo;

import com.possystem.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, LOGIN, ORDER, ORDER_DETAILS
    }

    public SuperBO getBO(BOFactory.BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER :
                return  new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailBOImpl();
            default:
                return null;
        }
    }
}
