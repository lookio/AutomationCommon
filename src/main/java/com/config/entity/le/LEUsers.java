package com.config.entity.le;

import com.config.base.BaseLEConfigItems;
import com.config.data.le.LeConfigData;
import com.config.data.le.LeConfigData.Site.UsersData;
import com.config.lpadk.ConfigInitializer;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;


import java.io.IOException;
import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LEUsers extends BaseLEConfigItems<LEUsers,UsersData> {

    private List<UsersData> usersData;
    private LeConfigData.Site.UsersData.CreateUser createUsers;
    private ConfigInitializer initializer = ConfigInitializer.getInstance();

    private static final Logger logger = Logger.getLogger(LEUsers.class);


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
        for(LeConfigData.Site.UsersData user : usersData){
            createUsers = user.getCreateUser();
            if(createUsers.getSkill().size() == 0){
                if(createUsers.getUserType().equalsIgnoreCase("Administrator")){
                    try {
                        initializer.createAdminUser(createUsers);
                    } catch (IOException e) {
                        GeneralUtils.handleError("Error creating admin user", e);
                        continue;
                    }
                }else{
//                    initializer.createAgent(createUsers);
                }
            }else{
                initializer.createAgent(createUsers, getSkills());
            }
        }
    }

    private JSONArray getSkills(){
        JSONArray skills = new JSONArray();
        for(String skill : createUsers.getSkill()){
            initializer.createSkill(skill);
            skills.put(skill);
        }
        return skills;
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
