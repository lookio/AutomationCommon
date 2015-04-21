package com.config.entity.legacy;

import com.config.base.BaseLegacyConfigItems;
import com.config.data.legacy.LegacyConfigData;
import com.config.data.legacy.LegacyConfigData.Site.RulesData;

import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LegacyRules extends BaseLegacyConfigItems<LegacyRules, RulesData> {

    private List<RulesData> rulesData;

    public LegacyRules(){
        rulesData = super.parseObjects(LegacyRules.class);
    }

    public void create() {
        create(rulesData);
    }

    public void modify() {
        modify(rulesData);
    }

    public void delete() {
        delete(rulesData);
    }

    @Override
    public <E> void create(List<E> ConfigItem) {
        LegacyConfigData.Site.RulesData.CreateRule createRule;
        for(LegacyConfigData.Site.RulesData rule : rulesData){
            createRule = rule.getCreateRule();

        }
    }

    @Override
    public <E> void modify(List<E> ConfigItem) {
        LegacyConfigData.Site.RulesData.ModifyRule modifyVisitors;
        for(LegacyConfigData.Site.RulesData rule : rulesData){
            modifyVisitors = rule.getModifyRule();
        }
    }

    @Override
    public <E> void delete(List<E> ConfigItem) {
        LegacyConfigData.Site.RulesData.DeleteRule deleteVisitors;
        for(LegacyConfigData.Site.RulesData rule : rulesData){
            deleteVisitors = rule.getDeleteRule();
        }
    }

    @Override
    public boolean isAllreadyContains() {
        return false;
    }


}
