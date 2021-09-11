package com.digital.util;

import infobip.api.client.SendSingleTextualSms;
import infobip.api.config.BasicAuthConfiguration;
import infobip.api.model.sms.mt.send.SMSResponse;
import infobip.api.model.sms.mt.send.textual.SMSTextualRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmsProvider {

    public static void SendSmsViaInfoBip(String sender, String content, String receiver) {

        SendSingleTextualSms client = new SendSingleTextualSms(new BasicAuthConfiguration(Constant.userInfoBip, Constant.passwordInfoBip));
        SMSTextualRequest request = new SMSTextualRequest();
        request.setFrom(sender);
        request.setTo(Arrays.asList(receiver));
        request.setText(content);
        SMSResponse response = client.execute(request);
        System.out.println("response:->" + response);
       //return  response;
    }

    public void SendSmsViaAroli(String sender, String content, String receiver) {

       /* Logger.info("Start send sms via Aroli");

        final Map<String, Object> params = new HashMap<String, Object>();

        params.put("user", Constant.userAroli);
        params.put("password", Constant.passwordAroli);
        params.put("sender", sender);
        params.put("content", content);
        params.put("receiver", receiver);
        Gson gson = new Gson();

        final WS.WSRequest request = WS
                .url(Constant.urlAroli)
                .setParameter("user", Constant.userAroli)
                .setParameter("password", Constant.passwordAroli)
                .setParameter("sender", sender)
                .setParameter("content", content)
                .setParameter("receiver", receiver);

        final WS.HttpResponse response = request.get();

//        Logger.info("request trace  is %s", request.trace());
        Logger.info("Status of Aroli sms response is %s", response.getStatus());
        Logger.info(" sms response is %s", response.getString());
        Logger.info("ok pour  is %s", receiver);
        Logger.info("Done  Sms Send");*/
    }
}
