package com.config.entity.le;

import com.config.base.BaseLEConfigItems;
import com.config.data.le.LeConfigData;
import com.config.data.le.LeConfigData.Site.VisitorsData;

import java.util.List;

/**
* Created by asih on 09/02/2015.
*/
public class LEVisitors extends BaseLEConfigItems<LEVisitors,VisitorsData> {

    private List<VisitorsData> visitorsData;

    public LEVisitors(){
        visitorsData = super.parseObjects(LEVisitors.class);
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
        LeConfigData.Site.VisitorsData.CreateVisitors createVisitors;
        for(LeConfigData.Site.VisitorsData visitor : visitorsData){
            createVisitors = visitor.getCreateVisitors();
        }
    }

    @Override
    public <E> void modify(List<E> ConfigItem) {
        LeConfigData.Site.VisitorsData.ModifyVisitors modifyVisitors;
        for(LeConfigData.Site.VisitorsData visitor : visitorsData){
            modifyVisitors = visitor.getModifyVisitors();
        }
    }

    @Override
    public <E> void delete(List<E> ConfigItem) {
        LeConfigData.Site.VisitorsData.DeleteVisitors deleteVisitors;
        for(LeConfigData.Site.VisitorsData visitor : visitorsData){
            deleteVisitors = visitor.getDeleteVisitors();
        }
    }



}
