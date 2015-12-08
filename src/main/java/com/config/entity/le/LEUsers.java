//package com.config.entity.le;
//
//import com.config.base.BaseLEConfigItems;
//import com.config.data.le.LeConfigData;
//import com.config.data.le.LeConfigData.Site.UsersData;
//import com.config.lpadk.ConfigInjector;
//import com.sun.istack.Nullable;
//import com.util.genutil.GeneralUtils;
//import org.apache.log4j.Logger;
//import org.json.JSONArray;
//import org.junit.Assert;
//
//
//import java.util.List;
//
///**
// * Created by asih on 09/02/2015.
// */
//public class LEUsers extends BaseLEConfigItems<LEUsers,UsersData> {
//
//    private List<UsersData> usersData;
//    private LeConfigData.Site.UsersData.CreateUser createUsers;
//    private ConfigInjector.Creator confCreator = ConfigInjector.getInstance().getCreator();
//    private final String ADMIN = "Administrator";
//
//    private static final Logger logger = Logger.getLogger(LEUsers.class);
//
//
//    public LEUsers(){
//        usersData = super.parseObjects(LEUsers.class);
//    }
//
//
//    public void create() {
//        create(usersData);
//    }
//
//    public void modify() {
//        modify(usersData);
//    }
//
//    public void delete() {
//        delete(usersData);
//    }
//
//    @Nullable
//    @Override
//    public <E> void create(List<E> ConfigItem) {
//        int index = 0;
//        for(LeConfigData.Site.UsersData user : usersData){
//            index++;
//            if(index == 1){ // admin user
//                continue;
//            }
//            createUsers = user.getCreateUser();
//            if(createUsers.getSkill().size() == 0){
//                Assert.assertTrue("Failed to create agent ",
//                        confCreator.createAgent(createUsers, null));
//            }else{
//                Assert.assertTrue("Failed to create agent ",
//                        confCreator.createAgent(createUsers, getSkills()));
//            }
//        }
//    }
//
//    @Nullable
//    private JSONArray getSkills(){
//        JSONArray skills = new JSONArray();
//        for(String skill : createUsers.getSkill()){
//            Assert.assertTrue(
//                    "Failed to create skill ",
//                    confCreator.createSkill(skill)
//            );
//            try {
//                skills.put(confCreator.getSkillId(skill));
//            }catch (NullPointerException npe){
//                GeneralUtils.handleError("Skill id is null", npe);
//            }
//        }
//
//        return skills;
//    }
//
//    @Override
//    public <E> void modify(List<E> ConfigItem) {
//        LeConfigData.Site.UsersData.ModifyUser modifyUsers;
//        for(LeConfigData.Site.UsersData user : usersData){
//            modifyUsers = user.getModifyUser();
////            modifyUsers.getSkill();
//        }
//    }
//
//    @Override
//    public <E> void delete(List<E> ConfigItem) {
//        LeConfigData.Site.UsersData.DeleteUser deleteUsers;
//        for(LeConfigData.Site.UsersData user : usersData){
//            deleteUsers = user.getDeleteUser();
////            deleteUsers.getSkill();
//        }
//    }
//
//    @Override
//    public boolean isAllreadyContains() {
//        return false;
//    }
//
//
//}
