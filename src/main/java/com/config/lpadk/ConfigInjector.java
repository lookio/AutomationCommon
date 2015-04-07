package com.config.lpadk;


import com.config.data.le.LeConfigData;
import com.liveperson.automation.datamodel.liveengage.Account;
import com.liveperson.automation.datamodel.liveengage.user.User;
import com.liveperson.automation.e2e.e2e.topology.EnvironmentProperties;
import com.liveperson.automation.e2e.liveengage.account.accountcreation.CreateE2EAccountService;
import com.liveperson.automation.usermanagement.UserManagementTestHelper;
import com.liveperson.automation.usermanagement.entityoperations.CommonEntityOperations;
import com.liveperson.automation.usermanagement.enums.UserManagementServiceName;
import com.liveperson.http.requests.Enums;
import com.sun.istack.Nullable;
import com.util.genutil.GeneralUtils;
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
public class ConfigInjector {

    private static final Logger logger = Logger.getLogger(ConfigInjector.class);

    private static final ConfigInjector INSTANCE = new ConfigInjector();
    private CrossConfInitializer cross = new CrossConfInitializer();

    private ConfigInjector() {

    }

    public static ConfigInjector getInstance() {
        return INSTANCE;
    }

    private static final String CONFIG_FILE = "config.properties";
    private static final String ENV_PROPS_LOCATION = "C:\\Users\\asih\\IdeaProjects\\AutomationCommon\\src\\main\\resources\\conf\\";
    private static EnvironmentProperties envProps = null;

    static {
        PropInitializer.initProps();
    }

    private Initializer initializer = new Initializer();
    private Creator creator = new Creator();

    private static final String ACCOUNT_CREATION_SERVICE_KEY = envProps.getProperty("account_creation_service");
    private static final String APP_SERVER = envProps.getProperty("app_server");
    private static final String HC1 = envProps.getProperty("app_server_domain");
    private static final String APP_SERVER_DOMAIN = envProps.getProperty("app_server_domain");
    private static final String AppKey = envProps.getProperty("app_key");
    private static final String TokenKey = "rstgyeh4r5hg54y";
    private static final String AppSecret = envProps.getProperty("app_secret");
    private static final String TokenSecret = "gfdh54y45yh5uy";

    String cassandraHosts;
    Set<Integer> privileges;
    private String LOGIN_KEY;
    private CommonEntityOperations commonEntityOperations;

    private CreateE2EAccountService E2EAccService;
    private HttpHost accService;
    private HttpHost appServer;
    public Account testAccount;
    private User user;
    Set<String> acFeatures;
    Set<String> acPackages;

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

        void initSite() {
            E2EAccService = new CreateE2EAccountService();
            accService = new HttpHost(ACCOUNT_CREATION_SERVICE_KEY);
            appServer = new HttpHost(APP_SERVER);
            testAccount = creator.createAccount();
            acFeatures = cross.setAcFeatures();
            acPackages = cross.setAcPackages();
        }

        void initUserSkill(UserManagementServiceName userManagementServiceName) throws Exception {
            cross.setCassandraHosts();
            cross.setPrivilages();
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

//        private void setPrivilages() {
//            privileges = new HashSet<Integer>();
//            privileges.add(111);
//            privileges.add(112);
//            privileges.add(1501); // user management module privilege
//        }
//
//        private void setCassandraHosts() {
//            cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
//        }

        boolean handleResponse(HttpResponse response, String successMsg, String failMsg) throws IOException {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                logger.info(successMsg);
                creator.updateConfigurationInSite();
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

        private String skillResponse;
        private JSONArray jsonArray;
        private JSONObject jsonObject;
        private JSONObject exprObject;

        String getId(String objKey, String expKey, String confType) throws Exception {
            skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0, commonEntityOperations.getResourceUrl().indexOf('?')));
            if(expKey.equalsIgnoreCase("mobile")){
                return skillResponse.substring(skillResponse.indexOf("id") + 5, skillResponse.indexOf("\",\"name\":\"mobile"));
            }
            jsonObject = new JSONObject(skillResponse);
            jsonArray = jsonObject.getJSONArray(confType);
            for (int i = 0; i < jsonArray.length(); i++) {
                exprObject = jsonArray.getJSONObject(i);
                if (exprObject.get(objKey).equals(expKey))
                    return exprObject.get("id").toString();
            }
            return null;
        }

//        private Set<String> setAcFeatures() {
//            acFeatures = new HashSet<String>();
//            acFeatures.add("LEUI.ConnectionBar_Display");
//            acFeatures.add("LEUI.WebAnalytics");
//            acFeatures.add("Common.Billing_CPI2");
//            acFeatures.add("Common.LiveEngage_2");
//            acFeatures.add("Common.LiveEngage_2_Unified_window");
//            return acFeatures;
//        }
//
//        private Set<String> setAcFeatures(String[] features) {
//            acFeatures = new HashSet<String>();
//            for (String feature : features) {
//                acFeatures.add(feature);
//            }
//            return acFeatures;
//        }
//
//        private Set<String> setAcPackages() {
//            acPackages = new HashSet<String>();
//            acPackages.add("LE_Platform");
//            acPackages.add("LP_Chat");
//            acPackages.add("LIVE_ENGAGEv2");
//            return acPackages;
//        }
//
//        private Set<String> setAcPackages(String[] packages) {
//            acPackages = new HashSet<String>();
//            for (String acPackage : packages) {
//                acPackages.add(acPackage);
//            }
//            return acPackages;
//        }

    }

    public class Creator {

        private Integer siteId;
        private boolean isExtentExpiration;

        public void createNewSite(Integer siteId, boolean isExtentExpiration, LeConfigData.Site.UsersData.CreateUser leUser) throws Exception {
            initializer.initSite();
            createAdminUser(leUser);
            this.siteId = siteId;
            this.isExtentExpiration = isExtentExpiration;
            updateConfigurationInSite();
        }

        public void updateConfigurationInSite() throws IOException {
            if (siteId != null) {
                testAccount = E2EAccService.getSiteForTest(accService, appServer, APP_SERVER_DOMAIN, envProps, testAccount, acFeatures, acPackages, String.valueOf(siteId));
            } else {
                testAccount = E2EAccService.getSiteForTest(accService, appServer, APP_SERVER_DOMAIN, envProps, testAccount, acFeatures, acPackages, "1");
            }
            if (isExtentExpiration) {
                E2EAccService.extendSiteExpiration(testAccount.getId(), AppKey, AppSecret);
            }
        }

        public Account createAccount() {
            testAccount = new Account();
            testAccount.setTokenKey(TokenKey);
            testAccount.setTokenSecret(TokenSecret);
            return testAccount;
        }

        private void createAdminUser(LeConfigData.Site.UsersData.CreateUser leUser) {
            User user = new User();
            user.setName(leUser.getUser());
            user.setPassword(leUser.getPassword());
            user.setEmail(leUser.getEmail());
            try {
                addUserToSite(user);
            } catch (IOException e) {
                GeneralUtils.handleError("Failed create admin user" , e);
            }
        }

        public void addUserToSite(User user) throws IOException {
            testAccount.getUsers().addUser(user);
            updateConfigurationInSite();
        }

        @Nullable
        public Boolean createAgent(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills) {
            try {
                initializer.initUserSkill(UserManagementServiceName.OPERATORS);
                HttpResponse operatorResponse = commonEntityOperations.createEntity(
                        RequestBuilder.getAgentRequest(user, skills), Enums.BodyType.JSON);
                initializer.handleResponse(operatorResponse, "New operator created", "operator already exist");
                updateConfigurationInSite();
                return true;
            } catch (Exception e) {
                logger.error("error create operator");
                return false;
            }
        }

        public Boolean createSkill(String skillName) {
            try {
                initializer.initUserSkill(UserManagementServiceName.SKILLS);
                HttpResponse skillResponse = commonEntityOperations.createEntity(
                        "{name:" + skillName + ", " +
                                "description:automation, " +
                                "maxWaitTime:120}", Enums.BodyType.JSON);
                initializer.handleResponse(skillResponse, "New skill created", "skill already exist");
                updateConfigurationInSite();
                return true;
            } catch (Exception e) {
                logger.error("error skill operator");
                return false;
            }
        }

        private final String objKey = "name";
        private final String confType = "Skill";

        public String getSkillId(String skillName){
            try {
                initializer.initUserSkill(UserManagementServiceName.SKILLS);
                return initializer.getId(objKey, skillName, confType);
            }
            catch(Exception e){
                logger.error("error get  operator id");
                return null;
            }
        }

    }

    public Creator getCreator() {
        return creator;
    }

    public Initializer getInitializer() {
        return initializer;
    }



}
























































//    private String getUserId(){
//        try {
//            initializer.initUserSkill(UserManagementServiceName.OPERATORS);
//            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
//            return initializer.getId(UserManagementServiceName.OPERATORS, "loginName", "loginName", "operators");
//        }
//        catch(Exception e){
//            logger.error("error get  operator id");
//            return null;
//        }
//    }

//    public String getSkillId(String skillName){
//        try {
//            initializer.initUserSkill(UserManagementServiceName.SKILLS);
//            String skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0,commonEntityOperations.getResourceUrl().indexOf('?')));
//            return initializer.getId(UserManagementServiceName.SKILLS, "name", skillName, "Skill");
//        }
//        catch(Exception e){
//            logger.error("error get  operator id");
//            return null;
//        }
//    }

//    public void deleteSite(){
//
//        new OperatorOperations("https", APPSERVER, testAccount.getId(), "asihhh", PASSWORD).setMultipleSkillsToOperatorTransaction("asihhh", new String[] {"sales"});
////        HibernateUtils.deleteSite(testAccount.getId//());
//    }

//            String getId(UserManagementServiceName userManagementServiceName, String objKey, String expKey, String confType) throws Exception {
//            initUserSkill(userManagementServiceName);
//            skillResponse = commonEntityOperations.getEntity(commonEntityOperations.getResourceUrl().substring(0, commonEntityOperations.getResourceUrl().indexOf('?')));
//            jsonObject = new JSONObject(skillResponse);
//            jsonArray = jsonObject.getJSONArray(confType);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                exprObject = jsonArray.getJSONObject(i);
//                if (exprObject.get(objKey).equals(expKey))
//                    return exprObject.get("id").toString();
//            }
//            return null;
//        }



//    public void createAgent(String userNameProp, String passwordNameProp){
////        AgentAPIFactory.createAgent(ENV_PROPS_LOCATION + CONFIG_FILE, userNameProp, passwordNameProp);
//
//
//
//
//
//
//
//        AgentAPIFactory.createAgent(testAccount.getId(), AppKey,  AppSecret,
//                 TokenKey,  TokenSecret,  userNameProp,  passwordNameProp,
//                 "6",  "https",  "qtvr-wap08.dev.lprnd.net",
//                 null);
//
//
//
//
//
//
//
//    }




//    public void createAgent(String displayName, String loginName, JSONArray skills) {
//        try {
//            initializer.initUserSkill(UserManagementServiceName.OPERATORS);
//            HttpResponse operatorResponse = commonEntityOperations.createEntity(
//                            "{displayName: " + displayName + " ," +
//                                    "emailAddress: " + loginName + " ," +
//                                    "enabled: true ," +
//                                    "loginName: " + loginName + " " +
//                                    ",maxNumberOfChats: Unlimited ," +
//                                    "nickName: automation ," +
//                                    "password: 12345678 ," +
//                                    "permissionGroup: " + PermissionType.AGENT.getPermissionType() + " ," +
//                                    "skills: " + skills + "}", Enums.BodyType.JSON);
//            initializer.handleResponse(operatorResponse, "New operator created", "operator already exist");
//            updateConfigurationInSite();
//        } catch (Exception e) {
//            logger.error("error create operator");
//        }
//    }