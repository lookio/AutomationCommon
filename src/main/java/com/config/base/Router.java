package com.config.base;

import com.util.genutil.GeneralUtils;
import com.config.service.Configurable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by asih on 03/03/2015.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Router {

    @SuppressWarnings("NullArgumentToVariableArgMethod")
    public static void route(Class<? extends Configurable> actionClass, String methodName)  {
        Method m = null;
        try {
            m = actionClass.getDeclaredMethod(methodName, null);
        } catch (NoSuchMethodException e) {
            GeneralUtils.handleError("No such method " + m.getName(), e);
        }
        try {
            assert m != null;
            m.invoke(actionClass.newInstance(), null);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            GeneralUtils.handleError("Can't invoke method " + m.getName(), e);
        }
    }

    private Router(){

    }



}
