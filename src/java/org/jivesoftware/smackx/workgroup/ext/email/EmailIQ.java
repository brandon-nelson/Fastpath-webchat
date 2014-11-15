package org.jivesoftware.smackx.workgroup.ext.email;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class EmailIQ extends IQ {
    public static final String ELEMENT_NAME = "send-email";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String message;
    private boolean html;
    private String sessionID;

    public String getChildElementXML() {
        XmlStringBuilder builder = new XmlStringBuilder();
        builder.halfOpenElement(ELEMENT_NAME);
        builder.xmlnsAttribute(NAMESPACE);
        builder.rightAngelBracket();
        builder.element("fromAddress", getFromAddress());
        builder.element("toAddress", getToAddress());
        builder.element("subject", getSubject());
        builder.element("message", getMessage());
        builder.element("useHTML", Boolean.toString(isHtml()));
        if(getSessionID() != null) {
            builder.element("sessionID", getSessionID());
        }
        builder.closeElement(ELEMENT_NAME);
        return builder.toString();
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHtml() {
        return this.html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}