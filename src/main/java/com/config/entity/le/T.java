package com.config.entity.le;

import com.config.base.ConfigItemsRouter;
import com.config.lpadk.ConfigInjector;

/**
 * Created by asih on 22/03/2015.
 */
public class T {

    static ConfigInjector initializer = ConfigInjector.getInstance();

    public static final void main(String[] args) throws Exception {

        ConfigItemsRouter.getInstance().routeAction(ConfigItemsRouter.ConfigType.LECreate, "C:\\Users\\asih\\IdeaProjects\\Sdk2WhiteBoxAndroid\\test\\src\\main\\resources\\first_test_demo\\");


    }
}
