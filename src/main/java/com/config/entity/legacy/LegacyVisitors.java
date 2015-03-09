package com.config.entity.legacy;

import com.config.base.BaseLegacyConfigItems;
import com.config.data.legacy.LegacyConfigData;
import com.config.data.legacy.LegacyConfigData.Site.VisitorsData;

import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LegacyVisitors extends BaseLegacyConfigItems<LegacyVisitors, VisitorsData> {

    private List<VisitorsData> visitorsData;


    public LegacyVisitors(){
        visitorsData = super.parseObjects(LegacyVisitors.class);
    }

    public void create() {
        create(visitorsData);
    }

    public void modify() {
        modify(visitorsData);
    }

    public void delete() {
        delete(visitorsData);
    }

    @Override
    public <E> void create(List<E> ConfigItem) {
        LegacyConfigData.Site.VisitorsData.CreateVisitors createVisitors;
        for(LegacyConfigData.Site.VisitorsData visitor : visitorsData){
            createVisitors = visitor.getCreateVisitors();
        }
    }

    @Override
    public <E> void modify(List<E> ConfigItem) {
        LegacyConfigData.Site.VisitorsData.ModifyVisitors modifyVisitors;
        for(LegacyConfigData.Site.VisitorsData visitor : visitorsData){
            modifyVisitors = visitor.getModifyVisitors();
        }
    }

    @Override
    public <E> void delete(List<E> ConfigItem) {
        LegacyConfigData.Site.VisitorsData.DeleteVisitors deleteVisitors;
        for(LegacyConfigData.Site.VisitorsData visitor : visitorsData){
            deleteVisitors = visitor.getDeleteVisitors();
        }
    }


}
