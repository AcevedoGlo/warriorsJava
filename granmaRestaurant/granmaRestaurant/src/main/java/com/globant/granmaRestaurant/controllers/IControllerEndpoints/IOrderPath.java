package com.globant.granmaRestaurant.controllers.IControllerEndpoints;

public interface IOrderPath {
    String URL_BASE = "/orders";
    String CREATE_ORDER = "/create";
    String GET_ORDER = "/{uuid}";
    String PATCH_ORDER = "/{uuid}/delivered/{timestamp}";

}
