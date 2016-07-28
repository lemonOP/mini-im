package com.james.im.server;

import com.james.im.packet.Packet;

/**
 * 包回调监听
 * @author james
 *
 */
public interface IPacketHandlerReceiver {

    void onReceive(Packet packet);
    
}
