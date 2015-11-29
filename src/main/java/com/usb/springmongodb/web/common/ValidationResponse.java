package com.usb.springmongodb.web.common;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {
    private Object entity;

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public ValidationResponse() {

    }

    public ValidationResponse(BindingResult bindingResult) {
        this();
        this.fill(bindingResult);
    }

    private String status;
    private List errorMessageList = new ArrayList();

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List getErrorMessageList() {
        return this.errorMessageList;
    }
    public void setErrorMessageList(List errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    private void fill(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            errorMessageList.addAll(bindingResult.getAllErrors());
            this.status = "FAIL";
        }
        else
            this.status = "SUCCESS";
    }
}
