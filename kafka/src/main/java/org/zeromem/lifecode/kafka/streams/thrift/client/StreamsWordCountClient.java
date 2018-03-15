package org.zeromem.lifecode.kafka.streams.thrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.zeromem.lifecode.kafka.streams.thrift.StreamsWordCountService;

import java.util.Scanner;

/**
 * @author zeromem
 * @date 2018/3/15
 */
public class StreamsWordCountClient {
    public static void main(String[] args) {
        TTransport transport = null;
        try {
            transport = new TFramedTransport(new TSocket("localhost", 10422, 5000));
            // 协议要和服务端一致
            TProtocol protocol = new TCompactProtocol(transport);
            StreamsWordCountService.Client client = new StreamsWordCountService.Client(protocol);
            transport.open();
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equals("quit")) {
                    break;
                }
                long count = client.get(input);
                System.out.printf("%s -> %d\n", input, count);
            }

        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
