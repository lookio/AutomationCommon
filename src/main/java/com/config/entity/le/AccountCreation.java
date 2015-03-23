package com.config.entity.le;


import com.liveperson.automation.datamodel.liveengage.Account;
import com.liveperson.automation.datamodel.liveengage.user.User;
import com.liveperson.automation.e2e.e2e.topology.EnvironmentProperties;
import com.liveperson.automation.e2e.liveengage.account.accountcreation.CreateE2EAccountService;
import com.liveperson.automation.propsUtils.TestProps;
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

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: amiro
 * Date: 1/22/14
 * Time: 2:37 PM
 */
public class AccountCreation {

    private static final Logger logger = Logger.getLogger(AccountCreation.class);

    public static final String CONFIG_FILE = "config.properties";
    public static final String PROPERTIES_FILES_DIR = "propsDir";
    public static final String ACCOUNT_CREATION_SERVICE = TestProps.getProperty(CONFIG_FILE, "account_creation_service");
    public static final String APPSERVER = TestProps.getProperty(CONFIG_FILE, "app_server");
    public static final String USERNAME = TestProps.getProperty(CONFIG_FILE, "siteUsername");
    public static final String PASSWORD = TestProps.getProperty(CONFIG_FILE, "sitePassword");
    public static final String HC1 = TestProps.getProperty(CONFIG_FILE, "app_server_domain");
    public static final String EMAIL = TestProps.getProperty(CONFIG_FILE, "email");
    public static final String ENV_PROPS_LOCATION = System.getProperty(PROPERTIES_FILES_DIR);
    public static final String APPSERVER_DOMAIN = TestProps.getProperty(CONFIG_FILE, "app_server_domain");
    public static final String AppKey = TestProps.getProperty(CONFIG_FILE, "app_key");
    public static final String TokenKey = "rstgyeh4r5hg54y";
    public static final String AppSecret = TestProps.getProperty(CONFIG_FILE, "app_secret");
    public static final String TokenSecret = "gfdh54y45yh5uy";

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


    private static EnvironmentProperties getEnvironmentProps(String filename) throws Exception {
        FileInputStream inputStream = new FileInputStream(filename);
        EnvironmentProperties environmentProperties = EnvironmentProperties.create(inputStream);
        return environmentProperties;
    }

    /**
     * create/get site with defaults for LiveEngageV2 with administrator permission
     *
     * @return        site id
     * @throws Exception
     */
    public static String createSite() throws Exception {
        CreateE2EAccountService createE2EAccountService = new CreateE2EAccountService();
        HttpHost accountCreationService = new HttpHost(ACCOUNT_CREATION_SERVICE);
        HttpHost appServer = new HttpHost(APPSERVER);

        Account testAccount = createAccount();
        User user = createUser();
        testAccount.getUsers().addUser(user);
        Set<String> acFeatures = setAcFeatures();
        Set<String> acPackages = setAcPackages();

        EnvironmentProperties environmentProperties = getEnvironmentProps(ENV_PROPS_LOCATION + CONFIG_FILE);
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, environmentProperties,testAccount,acFeatures,acPackages,"1");
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return (testAccount.getId());
    }

    /**
     * create site with defaults for LiveEngageV2 with administrator permission
     *
     * @return       test account
     * @throws Exception
     */
    public static Account createNewSite(int id) throws Exception {
        CreateE2EAccountService createE2EAccountService = new CreateE2EAccountService();
        HttpHost accountCreationService = new HttpHost(ACCOUNT_CREATION_SERVICE);
        HttpHost appServer = new HttpHost(APPSERVER);

        Account testAccount = createAccount();
        User user = createUser();
        testAccount.getUsers().addUser(user);
        Set<String> acFeatures = setAcFeatures();
        Set<String> acPackages = setAcPackages();

        EnvironmentProperties environmentProperties = getEnvironmentProps(ENV_PROPS_LOCATION + CONFIG_FILE);
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, environmentProperties,testAccount,acFeatures,acPackages, String.valueOf(id));
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return testAccount;
    }

    /**
     *  create site with user permission type ,features and packages
     *
     * @param features            - features to set
     * @param packages            - packages to set
     * @return
     * @throws Exception
     */
    public static String createSite(String[] features , String[] packages) throws Exception {
        CreateE2EAccountService createE2EAccountService = new CreateE2EAccountService();
        HttpHost accountCreationService = new HttpHost(ACCOUNT_CREATION_SERVICE);
        HttpHost appServer = new HttpHost(APPSERVER);

        Account testAccount = createAccount();
        User user = createUser();
        testAccount.getUsers().addUser(user);
        Set<String> acFeatures = setAcFeatures(features);
        Set<String> acPackages = setAcPackages(packages);

        EnvironmentProperties environmentProperties = getEnvironmentProps(ENV_PROPS_LOCATION + CONFIG_FILE);
        testAccount = createE2EAccountService.getSiteForTest(accountCreationService,appServer, APPSERVER_DOMAIN, environmentProperties,testAccount,acFeatures,acPackages,"1");
        createE2EAccountService.extendSiteExpiration(testAccount.getId(),AppKey,AppSecret);
        return (testAccount.getId());
    }

    /**
     * create account
     *
     * @return
     */
    public static Account createAccount(){
        Account testAccount = new Account();
        testAccount.setTokenKey(TokenKey);
        testAccount.setTokenSecret(TokenSecret);
        return testAccount;
    }

    /**
     * create user with default permission (administrator)
     *
     * @return
     */
    public static User createUser(){
        User user = new User();
        user.setName(USERNAME);
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        return user;
    }

    /**
     *   create user with permission type (administrator, agent, campaign manager)
     *
     * @param testAccount     - test account
     * @param permissionType  - user permission
     * @param loginName       - login name (must be email address)  ** unique param
     * @param displayName     - display name                        ** unique param
     * @return                - http response ex: 201 for create success
     */
    public static Boolean createUser(Account testAccount ,String displayName ,String loginName ,PermissionType permissionType){
        try {
            String cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
            Set<Integer> privileges = new HashSet<Integer>();
            privileges.add(111);
            privileges.add(112);
            privileges.add(1501); // user management module privilege
            String LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
                    testAccount.getId(),              // account id
                    privileges, cassandraHosts);
            CommonEntityOperations commonEntityOperations;
            commonEntityOperations = new CommonEntityOperations(
                    "https",
                    HC1,
                    testAccount.getId(),           // user id
                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
                    UserManagementServiceName.OPERATORS,
                    LOGIN_KEY
            );
            HttpResponse operatorResponse = commonEntityOperations.createEntity("{displayName: "+ displayName +" ,emailAddress: qa@qa.com ,enabled: true ,loginName: "+ loginName +" ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: "+ permissionType.getPermissionType() +" ,skills: []}", Enums.BodyType.JSON);
            if (operatorResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_CREATED){
                logger.info("New operator created");
                return true;
            }
            else {
                HttpEntity entity = operatorResponse.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                logger.info(responseString);
                if (responseString.contains("unique")) {
                    logger.info("operator already exist");
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch(Exception e){
            logger.error("error create operator");
            return null;
        }
    }

    /**
     *
     * @param testAccount      - test account
     * @param displayName      - display name    ** unique param
     * @param loginName        - login name (must be email address)  ** unique param
     * @param permissionType   - user permission
     * @param skills           - list of skills to add to user
     * @return                 - http response ex: 201 for create success
     */
    public static Boolean createUser(Account testAccount ,String displayName ,String loginName ,PermissionType permissionType ,JSONArray skills){
        try {
            String cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
            Set<Integer> privileges = new HashSet<Integer>();
            privileges.add(111);
            privileges.add(112);
            privileges.add(1501); // user management module privilege
            String LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
                    testAccount.getId(),              // account id
                    privileges, cassandraHosts);
            CommonEntityOperations commonEntityOperations = new CommonEntityOperations(
                    "https",
                    HC1,
                    testAccount.getId(),           // user id
                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
                    UserManagementServiceName.OPERATORS,
                    LOGIN_KEY
            );
            HttpResponse operatorResponse = commonEntityOperations.createEntity("{displayName: " + displayName + " ,emailAddress: qa@qa.com ,enabled: true ,loginName: " + loginName + " ,maxNumberOfChats: Unlimited ,nickName: automation ,password: 12345678 ,permissionGroup: " + permissionType.getPermissionType() + " ,skills: " + skills + "}", Enums.BodyType.JSON);
            if (operatorResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_CREATED){
                logger.info("New operator created");
                return true;
            }
            else {
                HttpEntity entity = operatorResponse.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                logger.info(responseString);
                if (responseString.contains("unique")) {
                    logger.info("operator already exist");
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch(Exception e){
            logger.error("error create operator");
            return null;
        }
    }

    /**
     *
     * @param testAccount      - test account
     * @param skillName        - skill name to create
     * @return                 - http response ex: 201 for create success
     */
    public static Boolean createSkill(Account testAccount ,String skillName){
        try {
            String cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
            Set<Integer> privileges = new HashSet<Integer>();
            privileges.add(111);
            privileges.add(112);
            privileges.add(1501); // user management module privilege
            String LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
                    testAccount.getId(),              // account id
                    privileges, cassandraHosts);
            CommonEntityOperations commonEntityOperations = new CommonEntityOperations("https", HC1,
                    testAccount.getId(),           // user id
                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
                    UserManagementServiceName.SKILLS, LOGIN_KEY);
            HttpResponse skillResponse = commonEntityOperations.createEntity("{name:"+ skillName +", description:automation, maxWaitTime:120}", Enums.BodyType.JSON);
            if (skillResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_CREATED){
                logger.info("New skill created");
                return true;
            }
            else {
                HttpEntity entity = skillResponse.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                logger.info(responseString);
                if (responseString.contains("unique")) {
                    logger.info("skill already exist");
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch(Exception e){
            logger.error("error skill operator");
            return null;
        }
    }

    /**
     *
     * @param testAccount
     * @param loginName
     * @return
     */
    public static String getUserId(Account testAccount ,String loginName){
        try {
            String cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
            Set<Integer> privileges = new HashSet<Integer>();
            privileges.add(111);
            privileges.add(112);
            privileges.add(1501); // user management module privilege
            String LOGIN_KEY;
            LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
                    testAccount.getId(),              // account id
                    privileges, cassandraHosts);
            CommonEntityOperations commonEntityOperations = new CommonEntityOperations("https", HC1,
                    testAccount.getId(),           // user id
                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
                    UserManagementServiceName.OPERATORS, LOGIN_KEY);
            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
            JSONObject jsnobject = new JSONObject(skillResponse);
            JSONArray jsonArray = jsnobject.getJSONArray("operators");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                if (explrObject.get("loginName").equals(loginName))
                    return explrObject.get("id").toString();
            }
            return null;
        }
        catch(Exception e){
            logger.error("error get  operator id");
            return null;
        }
    }

    /**
     *
     * @param testAccount
     * @param skillName
     * @return
     */
    public static String getSkillId(Account testAccount ,String skillName){
        try {
            String cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
            Set<Integer> privileges = new HashSet<Integer>();
            privileges.add(111);
            privileges.add(112);
            privileges.add(1501); // user management module privilege
            String LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
                    testAccount.getId(),              // account id
                    privileges, cassandraHosts);
            CommonEntityOperations commonEntityOperations = new CommonEntityOperations("https", HC1,
                    testAccount.getId(),           // user id
                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
                    UserManagementServiceName.SKILLS, LOGIN_KEY);
            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
            JSONObject jsnobject = new JSONObject(skillResponse);
            JSONArray jsonArray = jsnobject.getJSONArray("Skill");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                if (explrObject.get("name").equals(skillName))
                    return explrObject.get("id").toString();
            }
            return null;
        }
        catch(Exception e){
            logger.error("error get  operator id");
            return null;
        }
    }

    /**
     * set default features for LiveEngage V2
     *
     * @return
     */
    public static Set<String> setAcFeatures(){
        Set<String> acFeatures = new HashSet<String>();
        acFeatures.add("LEUI.ConnectionBar_Display");
        acFeatures.add("LEUI.WebAnalytics");
        acFeatures.add("Common.Billing_CPI2");
        acFeatures.add("Common.LiveEngage_2");
        acFeatures.add("Common.LiveEngage_2_Unified_window");
        return acFeatures;
    }

    /**
     * set specific features for account
     *
     * @param features  - list of features to set
     * @return
     */
    public static Set<String> setAcFeatures(String[] features){
        Set<String> acFeatures = new HashSet<String>();
        for(String feature : features){
            acFeatures.add(feature);
        }
        return acFeatures;
    }

    /**
     * set default packages for LiveEngage V2
     * @return
     */
    public static Set<String> setAcPackages(){
        Set<String> acPackages = new HashSet<String>();
        acPackages.add("LE_Platform");
        acPackages.add("LP_Chat");
        acPackages.add("LIVE_ENGAGEv2");
        return acPackages;
    }

    /**
     * set specific packages for account
     *
     * @param packages
     * @return
     */
    public static Set<String> setAcPackages(String[] packages){
        Set<String> acPackages = new HashSet<String>();
        for(String acPackage : packages){
            acPackages.add(acPackage);
        }
        return acPackages;
    }
}
