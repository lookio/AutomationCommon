package com.config.entity.legacy;

import com.config.base.BaseLegacyConfigItems;
import com.config.data.legacy.LegacyConfigData;
import com.config.data.legacy.LegacyConfigData.Site.UsersData;

import java.util.List;

/**
* Created by asih on 09/02/2015.
*/
public class LegacyUsers extends BaseLegacyConfigItems<LegacyUsers, UsersData> {

    private List<UsersData> usersData;

    public LegacyUsers(){
        usersData = super.parseObjects(LegacyUsers.class);
    }


    public void create() {

        create(usersData);
    }

    public void modify() {

        modify(usersData);
    }

    public void delete() {

        delete(usersData);
    }

    @Override
    public <E> void create(List<E> ConfigItem) {
        LegacyConfigData.Site.UsersData.CreateUser createUsers;
        for(LegacyConfigData.Site.UsersData user : usersData){
            createUsers = user.getCreateUser();
            createUsers.getSkill();
        }
    }

    @Override
    public <E> void modify(List<E> ConfigItem) {
        LegacyConfigData.Site.UsersData.ModifyUser modifyUsers;
        for(LegacyConfigData.Site.UsersData user : usersData){
            modifyUsers = user.getModifyUser();
//            modifyUsers.getSkill();
        }
    }

    @Override
    public <E> void delete(List<E> ConfigItem) {
        LegacyConfigData.Site.UsersData.DeleteUser deleteUsers;
        for(LegacyConfigData.Site.UsersData user : usersData){
            deleteUsers = user.getDeleteUser();
//            deleteUsers.getSkill();
        }
    }

    @Override
    public boolean isAllreadyContains() {
        return false;
    }
}
