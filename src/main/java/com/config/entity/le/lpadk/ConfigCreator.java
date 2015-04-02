//package com.config.entity.le.lpadk;
//
//import com.liveperson.automation.datamodel.liveengage.Account;
//import com.liveperson.automation.datamodel.liveengage.user.User;
//import com.liveperson.automation.usermanagement.enums.UserManagementServiceName;
//import com.liveperson.http.requests.Enums;
//import org.apache.http.HttpResponse;
//import org.apache.log4j.Logger;
//import org.json.JSONArray;
//
///**
// * Created by asih on 26/03/2015.
// */
//public class ConfigCreator {
//
//    private static final Logger logger = Logger.getLogger(ConfigCreator.class);
//
//    private static final ConfigCreator INSTANCE = new ConfigCreator();
//
//    private ConfigInitializer baseInitializer = ConfigInitializer.getInstance();
//
//
//    private ConfigCreator(){
//
//    }
//
//    public static ConfigCreator getInstance(){
//        return INSTANCE;
//    }
//
//    public String createSite() throws Exception {
//        baseInitializer.getInitializer().initSite();
//        baseInitializer.testAccount = baseInitializer.getCreateE2EAccountService().getSiteForTest(
//                baseInitializer.getAccService(), baseInitializer.getAppServer(), baseInitializer.getAPPSERVER_DOMAIN(),
//                        ConfigInitializer.envProps, baseInitializer.getTestAccount(),
//                                baseInitializer.getAcFeatures(), baseInitializer.getAcPackages(), "1");
//        baseInitializer.getCreateE2EAccountService().extendSiteExpiration(
//                baseInitializer.getTestAccount().getId(), baseInitializer.getAppKey(), baseInitializer.getAppSecret());
//        return (baseInitializer.testAccount.getId());
//    }
//
////    public Account createNewSite(int id) throws Exception {
////        baseInitializer.getInitializer().initSite();
////        baseInitializer.testAccount = baseInitializer.getCreateE2EAccountService().getSiteForTest(
////                accountCreationService, appServer, APPSERVER_DOMAIN, envProps, testAccount, acFeatures, acPackages, String.valueOf(id));
////        baseInitializer.getCreateE2EAccountService().extendSiteExpiration(baseInitializer.testAccount.getId(),baseInitializer.getAppKey(), baseInitializer.getAppSecret());
////        return baseInitializer.testAccount;
////    }
////
////    public String createSite(String[] features , String[] packages) throws Exception {
////        baseInitializer.getInitializer().initSite();
////        baseInitializer.testAccount = baseInitializer.getCreateE2EAccountService().getSiteForTest(accountCreationService, appServer, APPSERVER_DOMAIN, envProps, baseInitializer.testAccount, acFeatures, acPackages, "1");
////        baseInitializer.getCreateE2EAccountService().extendSiteExpiration(baseInitializer.testAccount.getId(),baseInitializer.getAppKey(),baseInitializer.getAppSecret());
////        return (baseInitializer.testAccount.getId());
////    }
//
//    public Account createAccount(){
//        Account testAccount = new Account();
//        testAccount.setTokenKey(baseInitializer.getTokenKey());
//        testAccount.setTokenSecret(baseInitializer.getTokenSecret());
//        return testAccount;
//    }
//
//    public User createUser(){
//        User user = new User();
//        user.setName(baseInitializer.getUSERNAME());
//        user.setPassword(baseInitializer.getPASSWORD());
//        user.setEmail(baseInitializer.getEMAIL());
//        return user;
//    }
//
//    public Boolean createUser(Account testAccount ,String displayName ,String loginName ,ConfigInitializer.PermissionType permissionType){
//        try {
//            baseInitializer.getInitializer().initUserSkill(UserManagementServiceName.OPERATORS);
//            HttpResponse operatorResponse = baseInitializer.getCommonEntityOperations().createEntity("{displayName: " + displayName + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " + loginName + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: " + permissionType.getPermissionType() + " ,skills: []}", Enums.BodyType.JSON);
//            baseInitializer.getInitializer().handleResponse(operatorResponse, "New operator created", "operator already exist");
//        } catch(Exception e){
//            logger.error("error create operator");
//            return null;
//        }
//        return null;
//    }
//
//    public Boolean createUser(Account testAccount ,String displayName ,String loginName ,ConfigInitializer.PermissionType permissionType ,JSONArray skills){
//        try {
//            baseInitializer.getInitializer().initUserSkill(UserManagementServiceName.OPERATORS);
//            HttpResponse operatorResponse = baseInitializer.getCommonEntityOperations().createEntity(
//                    "{displayName: " + displayName + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " +
//                            loginName + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678" +
//                            " ,permissionGroup: " + permissionType.getPermissionType() + " ,skills: " + skills + "}", Enums.BodyType.JSON);
//            baseInitializer.getInitializer().handleResponse(operatorResponse, "New operator created", "operator already exist");
//            return true;
//        } catch(Exception e){
//            logger.error("error create operator");
//            return false;
//        }
//    }
//
//    public Boolean createSkill(Account testAccount ,String skillName){
//        try {
//            baseInitializer.getInitializer().initUserSkill(UserManagementServiceName.SKILLS);
//            HttpResponse skillResponse = baseInitializer.getCommonEntityOperations().createEntity("{name:"+ skillName +", description:automation, maxWaitTime:120}", Enums.BodyType.JSON);
//            baseInitializer.getInitializer().handleResponse(skillResponse, "New skill created", "skill already exist");
//            return true;
//        }
//        catch(Exception e){
//            logger.error("error skill operator");
//            return false;
//        }
//    }
//
//    public String getUserId(Account testAccount ,String loginName){
//        try {
//            baseInitializer.getInitializer().initUserSkill(UserManagementServiceName.OPERATORS);
//            String skillResponse = baseInitializer.getCommonEntityOperations().getEntity(
//                    baseInitializer.getCommonEntityOperations().getResourceUrl().substring(
//                            0, baseInitializer.getCommonEntityOperations().getResourceUrl().indexOf('?')));
//            return baseInitializer.getInitializer().getId(UserManagementServiceName.OPERATORS, "loginName", "loginName", "operators");
//        }
//        catch(Exception e){
//            logger.error("error get  operator id");
//            return null;
//        }
//    }
//
//    public String getSkillId(Account testAccount ,String skillName){
//        try {
//            baseInitializer.getInitializer().initUserSkill(UserManagementServiceName.SKILLS);
//            String skillResponse = baseInitializer.getCommonEntityOperations().getEntity(
//                    baseInitializer.getCommonEntityOperations().getResourceUrl().substring(
//                            0, baseInitializer.getCommonEntityOperations().getResourceUrl().indexOf('?')));
//            return baseInitializer.getInitializer().getId(UserManagementServiceName.OPERATORS, "name", "skillName", "Skill");
//        }
//        catch(Exception e){
//            logger.error("error get  operator id");
//            return null;
//        }
//    }
//
//}
