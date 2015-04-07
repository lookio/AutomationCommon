package com.config.entity.le;

import com.config.base.BaseLEConfigItems;
import com.config.data.le.LeConfigData;
import com.config.data.le.LeConfigData.Site.UsersData;
import com.config.lpadk.ConfigInitializer;
import com.sun.istack.Nullable;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.junit.Assert;


import java.io.IOException;
import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LEUsers extends BaseLEConfigItems<LEUsers,UsersData> {

    private List<UsersData> usersData;
    private LeConfigData.Site.UsersData.CreateUser createUsers;
    private ConfigInitializer initializer = ConfigInitializer.getInstance();
    private final String ADMIN = "Administrator";

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

    @Nullable
    @Override
    public <E> void create(List<E> ConfigItem) {
        for(LeConfigData.Site.UsersData user : usersData){
            createUsers = user.getCreateUser();
            if(createUsers.getSkill().size() == 0){
                if(createUsers.getUserType().equalsIgnoreCase(ADMIN)){
                    initializer.createAdminUser(createUsers);
                }else{
                    Assert.assertTrue("Failed to create agent ",
                            initializer.createAgent(createUsers, null));
                }
            }else{
                Assert.assertTrue("Failed to create agent ",
                        initializer.createAgent(createUsers, getSkills()));
            }
        }
    }

    private JSONArray getSkills(){
        JSONArray skills = new JSONArray();
        for(String skill : createUsers.getSkill()){
            Assert.assertTrue("Failed to create skill ",
                    initializer.createSkill(skill));
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
