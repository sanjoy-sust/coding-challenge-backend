package com.flask.app.enums;

public enum EmploymentModeEnum {
    FREELANCE("freelance"),EMPLOYED("employed");
    EmploymentModeEnum(String mode){
        this.mode = mode;
    }
    private String mode;

    public String getMode() {
        return mode;
    }
}
