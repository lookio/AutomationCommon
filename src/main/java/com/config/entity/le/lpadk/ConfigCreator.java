package com.config.entity.le.lpadk;

import com.liveperson.automation.datamodel.liveengage.Account;
import com.liveperson.automation.datamodel.liveengage.user.User;
import com.liveperson.automation.usermanagement.enums.UserManagementServiceName;
import com.liveperson.http.requests.Enums;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;

/**
 * Created by asih on 26/03/2015.
 */
public class ConfigCreator {

    private static final Logger logger = Logger.getLogger(ConfigCreator.class);

    private static final ConfigCreator INSTANCE = new ConfigCreator();

    private SiteEntity siteEntity = new SiteEntity();
    private UserSkillEntity userSkillEntity = new UserSkillEntity();
    private GetIdEntity getIdEntity = new GetIdEntity();


    private ConfigCreator(){

    }

    public static ConfigCreator getInstance(){
        return INSTANCE;
    }

    public String createSite() throws Exception {
        ConfigInitializer.getInstance().initSite();
        siteEntity.setTestAccount(siteEntity.getCreateE2EAccountService().getSiteForTest(accountCreationService, appServer, APPSERVER_DOMAIN, envProps, testAccount, acFeatures, acPackages, "1"));
        siteEntity.getCreateE2EAccountService().extendSiteExpiration(siteEntity.getTestAccount().getId(), ConfigInitializer.getAppKey(), ConfigInitializer.getAppSecret());
        return (siteEntity.getTestAccount().getId());
    }

    public Account createNewSite(int id) throws Exception {
        ConfigInitializer.getInstance().initSite();
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, envProps,testAccount,acFeatures,acPackages, String.valueOf(id));
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return testAccount;
    }

    public String createSite(String[] features , String[] packages) throws Exception {
        ConfigInitializer.getInstance().initSite();
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, envProps,testAccount,acFeatures,acPackages,"1");
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return (testAccount.getId());
    }

    public Account createAccount(){
        Account testAccount = new Account();
        testAccount.setTokenKey(TokenKey);
        testAccount.setTokenSecret(TokenSecret);
        return testAccount;
    }

    public User createUser(){
        User user = new User();
        user.setName(USERNAME);
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        return user;
    }

    public Boolean createUser(Account testAccount ,String displayName ,String loginName ,PermissionType permissionType){
        try {
            ConfigInitializer.getInstance().initUserSkill(UserManagementServiceName.OPERATORS);
            HttpResponse operatorResponse = commonEntityOperations.createEntity("{displayName: " + displayName + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " + loginName + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: " + permissionType.getPermissionType() + " ,skills: []}", Enums.BodyType.JSON);
            handleResponse(operatorResponse, "New operator created", "operator already exist");
        } catch(Exception e){
            logger.error("error create operator");
            return null;
        }
        return null;
    }

    public Boolean createUser(Account testAccount ,String displayName ,String loginName ,PermissionType permissionType ,JSONArray skills){
        try {
            ConfigInitializer.getInstance().initUserSkill(UserManagementServiceName.OPERATORS);
            HttpResponse operatorResponse = commonEntityOperations.createEntity("{displayName: " + displayName + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " + loginName + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: " + permissionType.getPermissionType() + " ,skills: " + skills + "}", Enums.BodyType.JSON);
            handleResponse(operatorResponse, "New operator created", "operator already exist");
            return true;
        } catch(Exception e){
            logger.error("error create operator");
            return false;
        }
    }

    public Boolean createSkill(Account testAccount ,String skillName){
        try {
            ConfigInitializer.getInstance().initUserSkill(UserManagementServiceName.SKILLS);
            HttpResponse skillResponse = commonEntityOperations.createEntity("{name:"+ skillName +", description:automation, maxWaitTime:120}", Enums.BodyType.JSON);
            ConfigInitializer.getInstance().handleResponse(skillResponse, "New skill created", "skill already exist");
            return true;
        }
        catch(Exception e){
            logger.error("error skill operator");
            return false;
        }
    }

    public String getUserId(Account testAccount ,String loginName){
        try {
            ConfigInitializer.getInstance().initUserSkill(UserManagementServiceName.OPERATORS);
            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
            return ConfigInitializer.getInstance().getId(UserManagementServiceName.OPERATORS, "loginName", "loginName", "operators");
        }
        catch(Exception e){
            logger.error("error get  operator id");
            return null;
        }
    }

    public String getSkillId(Account testAccount ,String skillName){
        try {
            ConfigInitializer.getInstance().initUserSkill(UserManagementServiceName.SKILLS);
            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
            return ConfigInitializer.getInstance().getId(UserManagementServiceName.OPERATORS, "name", "skillName", "Skill");
        }
        catch(Exception e){
            logger.error("error get  operator id");
            return null;
        }
    }

}
