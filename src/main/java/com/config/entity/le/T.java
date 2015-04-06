package com.config.entity.le;

import com.config.base.ConfigItemsRouter;
import com.config.lpadk.ConfigInitializer;
import org.json.JSONArray;

/**
 * Created by asih on 22/03/2015.
 */
public class T {

    static ConfigInitializer initializer = ConfigInitializer.getInstance();

    public static final void main(String[] args) throws Exception {

        ConfigItemsRouter.getInstance().routeAction(ConfigItemsRouter.ConfigType.LECreate, "C:\\Users\\asih\\IdeaProjects\\Sdk2WhiteBoxAndroid\\test\\src\\main\\resources\\first_test_demo\\");

    }
}
