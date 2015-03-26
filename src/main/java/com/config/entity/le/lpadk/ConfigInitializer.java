package com.config.entity.le.lpadk;


import com.liveperson.automation.datamodel.liveengage.Account;
import com.liveperson.automation.datamodel.liveengage.user.User;
import com.liveperson.automation.e2e.e2e.topology.EnvironmentProperties;
import com.liveperson.automation.e2e.liveengage.account.accountcreation.CreateE2EAccountService;
import com.liveperson.automation.usermanagement.UserManagementTestHelper;
import com.liveperson.automation.usermanagement.entityoperations.CommonEntityOperations;
import com.liveperson.automation.usermanagement.enums.UserManagementServiceName;
import com.liveperson.http.requests.Enums;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: amiro
 * Date: 1/22/14
 * Time: 2:37 PM
 */
@SuppressWarnings("FieldCanBeLocal")
public class ConfigInitializer {

    private static final Logger logger = Logger.getLogger(ConfigInitializer.class);

    private static final ConfigInitializer INSTANCE = new ConfigInitializer();

    private ConfigInitializer(){

    }

    public static ConfigInitializer getInstance(){
        return INSTANCE;
    }

    private static final String CONFIG_FILE = "config.properties";
    private static final String ENV_PROPS_LOCATION = "C:\\Users\\asih\\IdeaProjects\\AutomationCommon\\src\\main\\java\\com\\config\\entity\\le\\";
    private static EnvironmentProperties envProps = null;

    static {
        PropInitializer.initProps();
    }

    private Initializer initializer;

    private static final String ACCOUNT_CREATION_SERVICE = envProps.getProperty("account_creation_service");
    private static final String APPSERVER = envProps.getProperty("app_server");
    private static final String USERNAME = envProps.getProperty("siteUsername");
    private static final String PASSWORD = envProps.getProperty("sitePassword");
    private static final String HC1 = envProps.getProperty("app_server_domain");
    private static final String EMAIL = envProps.getProperty("email");
    private static final String APPSERVER_DOMAIN = envProps.getProperty("app_server_domain");
    private static final String AppKey = envProps.getProperty("app_key");
    private static final String TokenKey = "rstgyeh4r5hg54y";
    private static final String AppSecret = envProps.getProperty("app_secret");
    private static final String TokenSecret = "gfdh54y45yh5uy";

    private String skillResponse;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private JSONObject exprObject;

    private String cassandraHosts;
    private Set<Integer> privileges;
    private String LOGIN_KEY;
    private CommonEntityOperations commonEntityOperations;

    private CreateE2EAccountService createE2EAccountService;
    private HttpHost accountCreationService;
    private HttpHost appServer;
    private Account testAccount;
    private User user;
    private Set<String> acFeatures;
    private Set<String> acPackages;

    public enum PermissionType {

        ADMINISTRATOR("0"),
        AGENT("1"),
        AGENT_MANAGER("2"),
        CAMPAIGN_MANAGER("3");

        private String permissionType;

        PermissionType(String permissionType){
            this.permissionType = permissionType;
        }

        public String getPermissionType() {
            return permissionType;
        }
    }

    public static class PropInitializer {

        private static void initProps() {
            try {
                envProps = EnvironmentProperties.create(new FileInputStream(new File(ENV_PROPS_LOCATION + CONFIG_FILE)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public class Initializer {

        private void initSite() {
            createE2EAccountService = new CreateE2EAccountService();
            accountCreationService = new HttpHost(ACCOUNT_CREATION_SERVICE);
            appServer = new HttpHost(APPSERVER);
            testAccount = createAccount();
            user = createUser();
            testAccount.getUsers().addUser(user);
            acFeatures = setAcFeatures();
            acPackages = setAcPackages();
        }

        private void initUserSkill(UserManagementServiceName userManagementServiceName) throws Exception {
            cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
            privileges = new HashSet<Integer>();
            privileges.add(111);
            privileges.add(112);
            privileges.add(1501); // user management module privilege
            LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
                    testAccount.getId(),              // account id
                    privileges, cassandraHosts);
            commonEntityOperations = new CommonEntityOperations(
                    "https",
                    HC1,
                    testAccount.getId(),           // user id
                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
                    userManagementServiceName,
                    LOGIN_KEY
            );
        }

        private boolean handleResponse(HttpResponse response, String successMsg, String failMsg) throws IOException {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                logger.info(successMsg);
                return true;
            } else {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                logger.info(responseString);
                if (responseString.contains("unique")) {
                    logger.warn(failMsg);
                    return true;
                } else {
                    return false;
                }
            }
        }

        private String getId(UserManagementServiceName userManagementServiceName, String objKey, String expKey, String confType) throws Exception {
            initUserSkill(userManagementServiceName);
            skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0, commonEntityOperations.getResourceUrl().indexOf('?')));
            jsonObject = new JSONObject(skillResponse);
            jsonArray = jsonObject.getJSONArray(confType);
            for (int i = 0; i < jsonArray.length(); i++) {
                exprObject = jsonArray.getJSONObject(i);
                if (exprObject.get(objKey).equals(expKey))
                    return exprObject.get("id").toString();
            }
            return null;
        }

    }


    public String createSite() throws Exception {
        initializer.initSite();
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, envProps,testAccount,acFeatures,acPackages,"1");
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return (testAccount.getId());
    }

    public Account createNewSite(int id) throws Exception {
        initializer.initSite();
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, envProps,testAccount,acFeatures,acPackages, String.valueOf(id));
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return testAccount;
    }

    public String createSite(String[] features , String[] packages) throws Exception {
        initializer.initSite();
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
            initializer.initUserSkill(UserManagementServiceName.OPERATORS);
            HttpResponse operatorResponse = commonEntityOperations.createEntity("{displayName: "+ displayName +" ,emailAddress: qa@qa.com ,enabled: true ,loginName: "+ loginName +" ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: "+ permissionType.getPermissionType() +" ,skills: []}", Enums.BodyType.JSON);
            initializer.handleResponse(operatorResponse, "New operator created", "operator already exist");
        } catch(Exception e){
            logger.error("error create operator");
            return null;
        }
        return null;
    }

    public Boolean createUser(Account testAccount ,String displayName ,String loginName ,PermissionType permissionType ,JSONArray skills){
        try {
            initializer.initUserSkill(UserManagementServiceName.OPERATORS);
            HttpResponse operatorResponse = commonEntityOperations.createEntity("{displayName: " + displayName + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " + loginName + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: " + permissionType.getPermissionType() + " ,skills: " + skills + "}", Enums.BodyType.JSON);
            initializer.handleResponse(operatorResponse, "New operator created", "operator already exist");
            return true;
        } catch(Exception e){
            logger.error("error create operator");
            return false;
        }
    }

    public Boolean createSkill(Account testAccount ,String skillName){
        try {
            initializer.initUserSkill(UserManagementServiceName.SKILLS);
            HttpResponse skillResponse = commonEntityOperations.createEntity("{name:"+ skillName +", description:automation, maxWaitTime:120}", Enums.BodyType.JSON);
            initializer.handleResponse(skillResponse, "New skill created", "skill already exist");
            return true;
        }
        catch(Exception e){
            logger.error("error skill operator");
            return false;
        }
    }

    public String getUserId(Account testAccount ,String loginName){
        try {
            initializer.initUserSkill(UserManagementServiceName.OPERATORS);
            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
            return initializer.getId(UserManagementServiceName.OPERATORS, "loginName", "loginName", "operators");
        }
        catch(Exception e){
            logger.error("error get  operator id");
            return null;
        }
    }

    public String getSkillId(Account testAccount ,String skillName){
        try {
            initializer.initUserSkill(UserManagementServiceName.SKILLS);
            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
            return initializer.getId(UserManagementServiceName.OPERATORS, "name", "skillName", "Skill");
        }
        catch(Exception e){
            logger.error("error get  operator id");
            return null;
        }
    }

    private Set<String> setAcFeatures(){
        Set<String> acFeatures = new HashSet<String>();
        acFeatures.add("LEUI.ConnectionBar_Display");
        acFeatures.add("LEUI.WebAnalytics");
        acFeatures.add("Common.Billing_CPI2");
        acFeatures.add("Common.LiveEngage_2");
        acFeatures.add("Common.LiveEngage_2_Unified_window");
        return acFeatures;
    }

    private Set<String> setAcFeatures(String[] features){
        Set<String> acFeatures = new HashSet<String>();
        for(String feature : features){
            acFeatures.add(feature);
        }
        return acFeatures;
    }

    private Set<String> setAcPackages(){
        Set<String> acPackages = new HashSet<String>();
        acPackages.add("LE_Platform");
        acPackages.add("LP_Chat");
        acPackages.add("LIVE_ENGAGEv2");
        return acPackages;
    }

    private Set<String> setAcPackages(String[] packages){
        Set<String> acPackages = new HashSet<String>();
        for(String acPackage : packages){
            acPackages.add(acPackage);
        }
        return acPackages;
    }
}
