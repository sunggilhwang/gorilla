/*
package com.example.gorillacrawl.sms;

import com.example.gorillacrawl.entity.Receiver;
import com.example.gorillacrawl.repository.ReceiverRepository;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SendSms {

    @Autowired
    static
    ReceiverRepository receiverRepository;
    static public void sendSmsOneMessage(String item) {
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSLCTF5QRQDLB1Z", "GJOTDV2DZNV81PCC8IKM5EOLHT4MTRZ2", "https://api.coolsms.co.kr");

    */
/*    Message message = new Message();
    // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01089149389");
        message.setTo("01089149389");
        message.setText(item);

         SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
*//*


        ArrayList<Message> messageList = new ArrayList<>();

        List<Receiver> phoneList = receiverRepository.findAll();
        for (Receiver receiver : phoneList) {
            String phoneNumber = receiver.getPhoneNumber();
            Message message = new Message();
            // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
            message.setFrom("01089149389");
            message.setTo(phoneNumber);
            message.setText(item);

            messageList.add(message);
        }

        LocalDateTime localDateTime = LocalDateTime.parse("2022-11-26 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(localDateTime);
        Instant instant = localDateTime.toInstant(zoneOffset);

        try {
            MultipleDetailMessageSentResponse response = messageService.send(messageList, instant);
        } catch (NurigoMessageNotReceivedException e) {
            throw new RuntimeException(e);
        } catch (NurigoEmptyResponseException e) {
            throw new RuntimeException(e);
        } catch (NurigoUnknownException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
