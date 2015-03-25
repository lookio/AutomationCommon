package com.config.entity.le;

import com.config.base.BaseLEConfigItems;
import com.config.data.le.LeConfigData;
import com.config.data.le.LeConfigData.Site.UsersData;

import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LEUsers extends BaseLEConfigItems<LEUsers,UsersData> {

    private List<UsersData> usersData;

    public LEUsers(){
        usersData = super.parseObjects(LEUsers.class);
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
        LeConfigData.Site.UsersData.CreateUser createUsers;
        for(LeConfigData.Site.UsersData user : usersData){
            createUsers = user.getCreateUser();
            createUsers.getSkill();
        }
    }

    @Override
    public <E> void modify(List<E> ConfigItem) {
        LeConfigData.Site.UsersData.ModifyUser modifyUsers;
        for(LeConfigData.Site.UsersData user : usersData){
            modifyUsers = user.getModifyUser();
//            modifyUsers.getSkill();
        }
    }

    @Override
    public <E> void delete(List<E> ConfigItem) {
        LeConfigData.Site.UsersData.DeleteUser deleteUsers;
        for(LeConfigData.Site.UsersData user : usersData){
            deleteUsers = user.getDeleteUser();
//            deleteUsers.getSkill();
        }
    }


}