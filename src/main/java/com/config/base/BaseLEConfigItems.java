package com.config.base;

import com.config.data.le.LeConfigData;
import com.config.entity.le.LECampaigns;
import com.config.entity.le.LEUsers;
import com.config.entity.le.LEVisitors;
import com.config.service.Parsable;
import com.config.service.Configurable;

import java.util.List;

/**
 * Created by asih on 05/02/2015.
 */
public abstract class BaseLEConfigItems<T,E> implements Configurable<BaseLEConfigItems>, Parsable {

    public static LeConfigData confData;

    public static LeConfigData getConfData() {
        if(confData == null) {
            confData = ConfigItemsRouter.getInstance().getConfData();
        }
        return confData;
    }

    @Override
    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T, E> List<E> parseObjects(Class<? extends T> entityClass){
        confData = getConfData();
       if(entityClass == LEUsers.class){
           return (List<E>) confData.getSite().getUsersData();
       }
        else if(entityClass == LECampaigns.class){
           return (List<E>) confData.getSite().getCampaignData();
       }
       else if(entityClass == LEVisitors.class){
           return (List<E>) confData.getSite().getVisitorsData();
       }
        return null;
    }

    public abstract <E> void create(List<E> ConfigItem);


    public abstract <E> void modify(List<E> ConfigItem);


    public abstract <E> void delete(List<E> ConfigItem);


    public abstract boolean isAllreadyContains();


}
