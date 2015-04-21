package com.config.base;

import com.config.data.legacy.LegacyConfigData;
import com.config.entity.legacy.LegacyRules;
import com.config.entity.legacy.LegacyUsers;
import com.config.entity.legacy.LegacyVisitors;
import com.config.service.Configurable;
import com.config.service.Parsable;

import java.util.List;

/**
 * Created by asih on 05/02/2015.
 */
public abstract class BaseLegacyConfigItems<T,E> implements Configurable<BaseLEConfigItems> , Parsable {


    public static LegacyConfigData confData;

    public static LegacyConfigData getConfData() {
        if(confData == null) {
            confData =  ConfigItemsRouter.getInstance().getConfData();
        }
        return confData;
    }

    @Override
    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T, E> List<E> parseObjects(Class<? extends T> entityClass){
        confData = getConfData();
        if(entityClass == LegacyUsers.class){
            return (List<E>) confData.getSite().getUsersData();
        }
        else if(entityClass == LegacyRules.class){
            return (List<E>) confData.getSite().getRulesData();
        }
        else if(entityClass == LegacyVisitors.class){
            return (List<E>) confData.getSite().getVisitorsData();
        }
        return null;
    }

    public abstract <E> void create(List<E> ConfigItem);

    public abstract <E> void modify(List<E> ConfigItem);

    public abstract <E> void delete(List<E> ConfigItem);

    public abstract boolean isAllreadyContains();

}
