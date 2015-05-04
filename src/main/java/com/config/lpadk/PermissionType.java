package com.config.lpadk;

/**
 * Created by asih on 30/04/2015.
 */
public enum PermissionType {

    ADMINISTRATOR("0"),
    AGENT("1"),
    AGENT_MANAGER("2"),
    CAMPAIGN_MANAGER("3");

    private String permissionType;

    PermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionType() {
        return permissionType;
    }
}
