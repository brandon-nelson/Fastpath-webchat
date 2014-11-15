package org.jivesoftware.smackx.workgroup.user;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.*;
import org.jivesoftware.smackx.workgroup.ext.email.EmailIQ;


public class WorkgroupExt extends Workgroup {

    private XMPPConnection connection;

    /**
     * @param workgroupJID
     * @param connection
     */
    public WorkgroupExt(String workgroupJID, XMPPConnection connection) {
        super(workgroupJID, connection);
        this.connection = connection;
    }
    
    public boolean sendMail(String to, String from, String subject, String message, boolean useHTML) throws XMPPException,SmackException {
        EmailIQ localEmailIQ = new EmailIQ();
        localEmailIQ.setToAddress(to);
        localEmailIQ.setFromAddress(from);
        localEmailIQ.setSubject(subject);
        localEmailIQ.setMessage(message);
        localEmailIQ.setHtml(useHTML);
        localEmailIQ.setType(IQ.Type.SET);
        localEmailIQ.setTo(getWorkgroupJID());
        PacketCollector localPacketCollector = connection.createPacketCollector(new PacketIDFilter(localEmailIQ.getPacketID()));
        this.connection.sendPacket(localEmailIQ);
        Packet localPacket = localPacketCollector.nextResult(SmackConfiguration.getDefaultPacketReplyTimeout());
        localPacketCollector.cancel();
        if (localPacket == null)
            throw new SmackException.NoResponseException();
        if (localPacket.getError() != null)
            throw new XMPPException.XMPPErrorException(localPacket.getError());
        return true;
    }
    
    public boolean sendTranscript(String to, String from) throws XMPPException,SmackException {
        EmailIQ localEmailIQ = new EmailIQ();
        localEmailIQ.setToAddress(to);
        localEmailIQ.setSessionID(from);
        localEmailIQ.setType(IQ.Type.SET);
        localEmailIQ.setTo(getWorkgroupJID());
        PacketCollector localPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(localEmailIQ.getPacketID()));
        this.connection.sendPacket(localEmailIQ);
        Packet localPacket = localPacketCollector.nextResult(SmackConfiguration.getDefaultPacketReplyTimeout());
        localPacketCollector.cancel();
        if (localPacket == null)
            throw new SmackException.NoResponseException();
        if (localPacket.getError() != null)
            throw new XMPPException.XMPPErrorException(localPacket.getError());
        return true;
    }
    
}
