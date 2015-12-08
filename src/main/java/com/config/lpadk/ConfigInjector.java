//package com.config.lpadk;
//
//
//import com.config.data.le.LeConfigData;
//import com.liveperson.automation.datamodel.liveengage.Account;
//import com.liveperson.automation.datamodel.liveengage.user.User;
//import com.liveperson.automation.e2e.e2e.topology.EnvironmentProperties;
//import com.liveperson.automation.e2e.liveengage.account.accountcreation.CreateE2EAccountService;
//import com.liveperson.automation.usermanagement.UserManagementTestHelper;
//import com.liveperson.automation.usermanagement.entityoperations.CommonEntityOperations;
//import com.liveperson.automation.usermanagement.enums.UserManagementServiceName;
//import com.liveperson.http.requests.Enums;
//import com.sun.istack.Nullable;
//import com.util.genutil.GeneralUtils;
//import com.util.properties.PropertiesHandlerImpl;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.log4j.Logger;
//import org.json.JSONArray;
//
//import java.io.*;
//
///**
// * Created with IntelliJ IDEA.
// * User: amiro
// * Date: 1/22/14
// * Time: 2:37 PM
// */
//public class class {
//
//    private static final Logger logger = Logger.getLogger(ConfigInjector.class);
//
//    private static final ConfigInjector INSTANCE = new ConfigInjector();
//
//    private static final String CONFIG_FILE = "config.properties";
//    //        private static final String ENV_PROPS_LOCATION = "./src/main/resources/conf/lpadk/";
//    private static final String ENV_PROPS_LOCATION = "/conf/lpadk/";
//    private static EnvironmentProperties envProps = null;
//
//    static {
//        PropInitializer.initProps();
//    }
//
//    private Initializer initializer = new Initializer();
//    private Creator creator = new Creator();
//
//    private static final String ACCOUNT_CREATION_SERVICE_KEY = envProps.getProperty("account_creation_service");
//    private static final String APP_SERVER = envProps.getProperty("app_server");
//    private static final String HC1 = envProps.getProperty("app_server_domain");
//    public static final String APP_SERVER_DOMAIN = envProps.getProperty("app_server_domain");
//    public static final String AppKey = envProps.getProperty("app_key");
//    public static final String TokenKey = "rstgyeh4r5hg54y";
//    public static final String AppSecret = envProps.getProperty("app_secret");
//    public static final String TokenSecret = "gfdh54y45yh5uy";
//
//    private String LOGIN_KEY;
//
//    private CommonEntityOperations commonEntityOperations;
//    private CreateE2EAccountService E2EAccService = new CreateE2EAccountService();
//    private CrossConfInitializer crossInitializer = new CrossConfInitializer();
//    private JsonService jsonService = JsonService.getInstance();
//
//    private HttpHost accService = new HttpHost(ACCOUNT_CREATION_SERVICE_KEY);
//    private HttpHost appServer = new HttpHost(APP_SERVER);
//    public Account testAccount;
//
//
//    private ConfigInjector() {
//
//    }
//
//    public static ConfigInjector getInstance() {
//        return INSTANCE;
//    }
//
//    private static class PropInitializer {
//
//        private static void initProps() {
//            String fileName = ENV_PROPS_LOCATION + CONFIG_FILE;
//            try {
//                envProps = EnvironmentProperties.create(
//                        PropertiesHandlerImpl.getInstance().parseStreamFromJar(fileName));
//            } catch (IOException e) {
//                GeneralUtils.handleError("Error parsing prop file " + fileName, e);
//            }
//        }
//
//    }
//
//    public class Initializer {
//
//        private void initSite() {
//            crossInitializer.initCrossConf();
//            creator.createAccount();
//        }
//
//        private void initUserSkill(UserManagementServiceName userManagementServiceName) throws Exception {
//            LOGIN_KEY = UserManagementTestHelper.getLoginSessionKey(
//                    testAccount.getUsers().getUsers().get(0).toString(),      // user id
//                    testAccount.getUsers().getUsers().get(0).getName(),       // user name
//                    testAccount.getId(),              // account id
//                    crossInitializer.getPrivileges(), crossInitializer.getCassandraHosts());
//            commonEntityOperations = new CommonEntityOperations(
//                    "https",
//                    HC1,
//                    testAccount.getId(),           // user id
//                    testAccount.getUsers().getUsers().get(0).getName(),            // user name
//                    testAccount.getUsers().getUsers().get(0).getPassword(),        // login password
//                    userManagementServiceName,
//                    LOGIN_KEY
//            );
//        }
//
//    }
//
//    public class Creator {
//
//        private String siteId;
//        private boolean isExtentExpiration;
//
//        public void createNewSite(String siteId, boolean isExtentExpiration, LeConfigData.Site.UsersData.CreateUser leUser) throws Exception {
//            this.siteId = siteId;
//            this.isExtentExpiration = isExtentExpiration;
//            initializer.initSite();
//            createAdminUser(leUser);
//        }
//
//        public void updateConfigurationInSite() throws IOException {
//            String id = getSiteIdByType();
//            testAccount = E2EAccService.getSiteForTest(
//                    accService,
//                    appServer,
//                    APP_SERVER_DOMAIN,
//                    envProps,
//                    testAccount,
//                    crossInitializer.getAcFeatures(),
//                    crossInitializer.getAcPackages(),
//                    id
//            );
//            if (isExtentExpiration) {
//                E2EAccService.extendSiteExpiration(
//                        testAccount.getId(),
//                        AppKey,
//                        AppSecret
//                );
//            }
//        }
//
//        private String getSiteIdByType() {
//            String id;
//            if (siteId != null) {
//                return siteId;
//            } else {
//                return "1";
//            }
//        }
//
//        public void createAccount() {
//            testAccount = new Account();
//            testAccount.setTokenKey(TokenKey);
//            testAccount.setTokenSecret(TokenSecret);
//        }
//
//        private void createAdminUser(LeConfigData.Site.UsersData.CreateUser leUser) {
//            User user = new User();
//            user.setName(leUser.getUser());
//            user.setPassword(leUser.getPassword());
//            user.setEmail(leUser.getEmail());
//            try {
//                addUserToSite(user);
//                updateConfigurationInSite();
//            } catch (IOException e) {
//                GeneralUtils.handleError("Failed to update configuration", e);
//            }
//        }
//
//        public void addUserToSite(User user) throws IOException {
//            testAccount.getUsers().addUser(user);
//        }
//
//        @Nullable
//        public Boolean createAgent(LeConfigData.Site.UsersData.CreateUser user, JSONArray skills) {
//            try {
//                initializer.initUserSkill(UserManagementServiceName.OPERATORS);
//                HttpResponse operatorResponse = commonEntityOperations.createEntity(
//                        jsonService.getAgentRequest(
//                                user,
//                                skills),
//                        Enums.BodyType.JSON
//                );
//                jsonService.handleResponse(
//                        operatorResponse,
//                        "New operator created",
//                        "operator already exist"
//                );
//                updateConfigurationInSite();
//                return true;
//            } catch (Exception e) {
//                logger.error("error create operator");
//                return false;
//            }
//        }
//
//        public Boolean createSkill(String skillName) {
//            try {
//                initializer.initUserSkill(UserManagementServiceName.SKILLS);
//                HttpResponse skillResponse = commonEntityOperations.createEntity(
//                        "{name:" + skillName + ", " + "description:automation, " + "maxWaitTime:120}",
//                        Enums.BodyType.JSON
//                );
//                jsonService.handleResponse(
//                        skillResponse,
//                        "New skill created",
//                        "skill already exist"
//                );
//                updateConfigurationInSite();
//                return true;
//            } catch (Exception e) {
//                logger.error("error skill operator");
//                return false;
//            }
//        }
//
//        private final String objKey = "name";
//        private final String confType = "Skill";
//
//        public String getSkillId(String skillName) {
//            try {
//                initializer.initUserSkill(UserManagementServiceName.SKILLS);
//                return jsonService.getId(
//                        objKey,
//                        skillName,
//                        confType,
//                        commonEntityOperations
//                );
//            } catch (Exception e) {
//                logger.error("error get  operator id");
//                return null;
//            }
//        }
//
//
//    }
//
//    public Creator getCreator() {
//        return creator;
//    }
//
//    public Initializer getInitializer() {
//        return initializer;
//    }
//
//}