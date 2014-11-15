package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Packet;

public class FromContainsFilter
        implements PacketFilter
{
    private String from;

    public FromContainsFilter(String from)
    {
        if (from == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }
        this.from = from.toLowerCase();
    }

    public boolean accept(Packet packet)
    {
        if (packet.getFrom() == null) {
            return false;
        }
        return packet.getFrom().toLowerCase().indexOf(this.from) != -1;
    }
}
