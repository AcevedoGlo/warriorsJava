package com.globant.granmaRestaurant.controllers.IControllerEndpoints;

public interface ICustomerPath {


        String URL_BASE = "/customers";
        String CREATE_CUSTOMER = "/create";
        String GET_CUSTOMER = "/{document}";
        String UPDATE_CUSTOMER = "/update/{document}";
        String DELETE_CUSTOMER = "/delete/{document}";
        String GET_LIST = "/getAllCustomers";

}
