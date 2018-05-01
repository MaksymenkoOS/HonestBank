package com.sandromax.honestbank.domain.service;

import java.util.HashMap;

/**
 * Created by sandro on 01.05.18.
 */
public class JspParams {
    private HashMap<String, String> params = new HashMap<>();

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
