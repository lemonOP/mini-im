package com.james.im.server;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.james.im.log.Log;
import com.james.im.message.IMServerMessageProtocol.BaseMessage;
import com.james.im.message.IMServerMessageProtocol.Message;
import com.james.im.message.IMServerMessageProtocol.MessageType;
import com.james.im.packet.Packet;
import com.james.im.server.IPacketHandlerReceiver;


/**
 * 消息包 处理 监听
 * Javadoc
 *
 * @author james
 * @version 0.1, 2015-11-30
 */
public class PacketHandlerReceiver implements IPacketHandlerReceiver {

    private static final String TAG = PacketHandlerReceiver.class.getSimpleName();

    public PacketHandlerReceiver() {

    }


    @Override
    public void onReceive(Packet packet) {

        try {
            Log.d(TAG, "==PacketHandlerReceiver begin ==");
            
            if(packet.getMessageType() == MessageType.BASE_MSG_VALUE){
	            Message e = Message.parseFrom(packet.getMessageBody());
	            BaseMessage baseMessage  = e.getBasemessage();
            }
            
            Log.d(TAG, "==PacketHandlerReceiver end ==");
        } catch (InvalidProtocolBufferException var6) {
            var6.printStackTrace();
        }



    }
}
