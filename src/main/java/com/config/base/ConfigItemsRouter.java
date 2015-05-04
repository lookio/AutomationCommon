package com.config.base;

import com.util.jaxb.JaxbHandler;
import com.config.data.le.LeConfigData;
import com.config.data.legacy.LegacyConfigData;
import com.config.entity.le.LESites;
import com.config.entity.legacy.LegacySites;
import org.apache.log4j.Logger;

//import config.entity.legacy.LegacySites;

/**
 * Created by asih on 11/02/2015.
 */
public class ConfigItemsRouter {

    private static final ConfigItemsRouter INSTANCE = new ConfigItemsRouter();
    private static final Logger logger = Logger.getLogger(ConfigItemsRouter.class);

    private Object confData;
    private ConfigType type;

    private ConfigItemsRouter(){

    }

    public static ConfigItemsRouter getInstance(){
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public void routeAction(ConfigType type, String folder){
        setConfData(initService(folder + type.docValue, type.confClass));
        Router.route(type.actionClass, type.actionMethodName);
    }

    @SuppressWarnings("unchecked")
    public <T> T initService(String doc, Class<T> confClass){
        return (T)JaxbHandler.unmarshal(doc, confClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfData() {
        return (T)confData;
    }

    private <T> void setConfData(T confData) {
        this.confData = (T)confData;
    }

    public enum ConfigType {

        LECreate(Constants.CREATE_lE_FILE_NAME, Constants.LE_CONF_CLASS, Constants.LE_SITE_CLASS, Constants.CREATE_METHOD_NAME),
        LEModify(Constants.MODIFY_lE_FILE_NAME, Constants.LE_CONF_CLASS, Constants.LE_SITE_CLASS, Constants.MODIFY_METHOD_NAME),
        LEDelete(Constants.DELETE_lE_FILE_NAME, Constants.LE_CONF_CLASS, Constants.LE_SITE_CLASS, Constants.DELETE_METHOD_NAME),
        LegacyCreate(Constants.CREATE_lEGACY_FILE_NAME, Constants.LEGACY_CONF_CLASS, Constants.LEGACY_SITES_CLASS, Constants.CREATE_METHOD_NAME),
        LegacyModify(Constants.MODIFY_lEGACY_FILE_NAME, Constants.LEGACY_CONF_CLASS, Constants.LEGACY_SITES_CLASS , Constants.MODIFY_METHOD_NAME),
        LegacyDelete(Constants.DELETE_lEGACY_FILE_NAME, Constants.LEGACY_CONF_CLASS, Constants.LEGACY_SITES_CLASS, Constants.DELETE_METHOD_NAME);

        String docValue;
        Class confClass;
        Class actionClass;
        String actionMethodName;

        ConfigType(String docValue, Class confClass, Class actionClass, String actionMethodName) {
            this.docValue = docValue;
            this.confClass = confClass;
            this.actionClass = actionClass;
            this.actionMethodName = actionMethodName;
        }

        private static class Constants{

            private static final String CREATE_METHOD_NAME = "create";
            private static final String MODIFY_METHOD_NAME = "modify";
            private static final String DELETE_METHOD_NAME = "delete";

            private static final String CREATE_lE_FILE_NAME = "LE_config_data.xml";
            private static final String MODIFY_lE_FILE_NAME = "";
            private static final String DELETE_lE_FILE_NAME = "";
            private static final String CREATE_lEGACY_FILE_NAME = "Legacy_config_data.xml";
            private static final String MODIFY_lEGACY_FILE_NAME = "";
            private static final String DELETE_lEGACY_FILE_NAME = "";

            private static final Class LE_SITE_CLASS = LESites.class;
            private static final Class LEGACY_SITES_CLASS = LegacySites.class;

            private static final Class LE_CONF_CLASS = LeConfigData.class;
            private static final Class LEGACY_CONF_CLASS = LegacyConfigData.class;

        }

    }


}
