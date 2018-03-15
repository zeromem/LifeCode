package org.zeromem.lifecode.kafka.streams.thrift;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @author zeromem
 * @date 2018/3/15
 */
public class StreamsWordCountRPCServer {
    private static StreamsWordCountServiceHandler handler;
    private static StreamsWordCountService.Processor<StreamsWordCountService.Iface> processor;

    /**
     * start rpc server in non-blocking mode.
     */
    public static void start(KafkaStreams streams, int port) {
        try {
            handler = new StreamsWordCountServiceHandler(streams);
            processor = new StreamsWordCountService.Processor<>(handler);
            TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(port);
            TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
            tnbArgs.processor(processor);
            tnbArgs.transportFactory(new TFramedTransport.Factory());
            tnbArgs.protocolFactory(new TCompactProtocol.Factory());
            TServer server = new TNonblockingServer(tnbArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start(null, 0);
    }
}
