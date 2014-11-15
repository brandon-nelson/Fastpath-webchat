package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.XmlStringBuilder;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Brandon Nelson on 11/15/2014.
 */
public class PropertyPacketExtension implements PacketExtension {
    private Map<String,Object> properties;
    public PropertyPacketExtension(Map<String,Object> properties) {
        this.properties = properties;
    }
    public String getElementName() {
        return "properties";
    }

    public String getNamespace() {
        return "http://www.jivesoftware.com/xmlns/xmpp/properties";
    }

    public Map<String,Object> getProperties() {
        return properties;
    }

    public CharSequence toXML() {
        XmlStringBuilder buf = new XmlStringBuilder();
        buf.halfOpenElement(getElementName());
        buf.xmlnsAttribute(getNamespace());
        Iterator key = properties.keySet().iterator();
        while(key.hasNext()) {
            Object curKey = key.next();
            buf.openElement("property");
            buf.element("name", curKey.toString());
            buf.halfOpenElement("value");
            if(properties.get(curKey) instanceof Integer) {
                buf.attribute("type", "integer");
            }
            else if(properties.get(curKey) instanceof Long) {
                buf.attribute("type", "long");
            }
            else if(properties.get(curKey) instanceof Float) {
                buf.attribute("type", "float");
            }
            else if(properties.get(curKey) instanceof Double) {
                buf.attribute("type", "double");
            }
            else if(properties.get(curKey) instanceof Boolean) {
                buf.attribute("type", "boolean");
            }
            else {
                buf.attribute("type", "string");
            }
            buf.rightAngelBracket();
            buf.escape(properties.get(curKey).toString());
            buf.closeElement("property");
        }
        return (CharSequence)buf.toString();
    }
}
