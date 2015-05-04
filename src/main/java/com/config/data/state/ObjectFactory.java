
package com.config.data.state;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfigState }
     * 
     */
    public ConfigState createConfigState() {
        return new ConfigState();
    }

    /**
     * Create an instance of {@link ConfigState.Skills }
     * 
     */
    public ConfigState.Skills createConfigStateSkills() {
        return new ConfigState.Skills();
    }

    /**
     * Create an instance of {@link ConfigState.Agents }
     * 
     */
    public ConfigState.Agents createConfigStateAgents() {
        return new ConfigState.Agents();
    }

    /**
     * Create an instance of {@link ConfigState.Sites }
     * 
     */
    public ConfigState.Sites createConfigStateSites() {
        return new ConfigState.Sites();
    }

    /**
     * Create an instance of {@link ConfigState.Skills.Skill }
     * 
     */
    public ConfigState.Skills.Skill createConfigStateSkillsSkill() {
        return new ConfigState.Skills.Skill();
    }

    /**
     * Create an instance of {@link ConfigState.Agents.Agent }
     * 
     */
    public ConfigState.Agents.Agent createConfigStateAgentsAgent() {
        return new ConfigState.Agents.Agent();
    }

    /**
     * Create an instance of {@link ConfigState.Sites.Site }
     * 
     */
    public ConfigState.Sites.Site createConfigStateSitesSite() {
        return new ConfigState.Sites.Site();
    }

}
