package com.example.gorillacrawl.service;

import com.example.gorillacrawl.constant.Const;
import com.example.gorillacrawl.entity.Brand;
import com.example.gorillacrawl.entity.GorillaItem;
import com.example.gorillacrawl.entity.Receiver;
import com.example.gorillacrawl.repository.BrandRepository;
import com.example.gorillacrawl.repository.GorillaItemRepository;
import com.example.gorillacrawl.repository.ReceiverRepository;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

// import net.nurigo.sdk.NurigoApp.initialize;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;

@Service
public class GorillaService {

    @Autowired
    GorillaItemRepository gorillaItemRepository;

    @Autowired
    ReceiverRepository receiverRepository;

    @Autowired
    BrandRepository brandRepository;

    @Value("${gojumun.uri}")
    private String goJumunUri;
    @Value("${gojumun.id}")
    private String goJumunId;
    @Value("${gojumun.password}")
    private String goJumunPass;

    @Value("${coolsms.uri}")
    private String coolSmsApiKey;
    @Value("${coolsms.NCSLCTF5QRQDLB1Z}")
    private String coolSmsSecretKey;
    @Value("${coolsms.GJOTDV2DZNV81PCC8IKM5EOLHT4MTRZ2}")
    private String coolSmsUri;

    DefaultMessageService messageService = new DefaultMessageService(coolSmsApiKey, coolSmsSecretKey, coolSmsUri);

    public void goJumunLogin() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(goJumunUri);

        // Form 데이터 추가

        StringEntity entity = new StringEntity(
                "mode=Login&mb_id=" + goJumunId + "&mb_pass=" + goJumunPass + "&i_name=&i_email1=&i_email2=&p_id=&p_name=&p_email1=&p_email2=",
                ContentType.create("application/x-www-form-urlencoded"));
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity responseEntity = response.getEntity();
            System.out.println("Response status: " + response.getStatusLine());
            if (responseEntity!= null) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

                String line;
                while ((line = bufferedReader.readLine())!= null) {
                    System.out.println(line);
                }
                bufferedReader.close();
            }
        } finally {
            response.close();
        }

        String sessionId = response.getHeaders("Set-Cookie")[0].toString().split(" ")[1];

        sessionId = sessionId.substring(0, sessionId.length() - 1);

        Const.sessionName = sessionId;
    }



    public ArrayList<GorillaItem> getGorillaItem(String test){
        long beforeTime = System.currentTimeMillis();
        String [] test2 = test.split("<input type=\"hidden\" name=\"brand_id\"");

        ArrayList<GorillaItem> items = new ArrayList<GorillaItem>();
        String soldFlag = null;
        String itemName = null;
        String itemNumber = null;
        String brandId = null;

        for(int i=0;i<test2.length; i++){
            String soldFlagRegex = "<input type=\"hidden\" name=\"sold_out\" id=\"it_sold_out_([0-9]+)\" value=\"([0-9]+)\">";
            Pattern soldFlagPattern = Pattern.compile(soldFlagRegex);
            Matcher soldFlagMatcher = soldFlagPattern.matcher(test2[i]);
            while (soldFlagMatcher.find()) {
                String sequence = soldFlagMatcher.group(1);
                soldFlag = soldFlagMatcher.group(2);
            }

            String brandRegex = "value=\"(\\w+)\"";
            Pattern brandPattern = Pattern.compile(brandRegex);
            Matcher brandMatcher = brandPattern.matcher(test2[i]);

            if (brandMatcher.find()) {
                brandId = brandMatcher.group(1);
            }

            String regex = "<td>(.*?)</td>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(test2[i]);
            matcher.find();
            matcher.find();
            if (matcher.find()) {
                itemName = matcher.group(1);

            }

            String regex2 = "direct_buy\\('(.*?)\\'";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(test2[i]);
            if (matcher2.find()) {
                itemNumber = matcher2.group(1);

            }

            GorillaItem g1 = new GorillaItem();

            if(itemName!=null) {
                g1.setItemName(itemName);
                g1.setSoldFlag(soldFlag);
                g1.setItemNumber(itemNumber);
                g1.setBrandId(brandId);
                items.add(g1);
            }
       }
        return items;
    }

    public void getCompareSoldList() throws IOException {

        StringBuffer response = new StringBuffer();

        for(int i=1; i<8; i++) {
            String url = "http://www.gojumun.co.kr/shop/list.php?ca_id=10&sort1=&sort2=&brand=&search=&search_2st=&page_rows=1000&page=" + Integer.toString(i);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set request method to GET
            con.setRequestMethod("GET");

            // Send request
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Cookie", Const.sessionName);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }

        ArrayList<GorillaItem> soldList = getGorillaItem(response.toString());

        List<Brand> brandList = brandRepository.findAll();

        String newItems = "";
        String items= "";

        for(int i=0; i< soldList.size();i++){
            GorillaItem gorillaItem = gorillaItemRepository.findAllByItemNumber(soldList.get(i).getItemNumber());

            if(gorillaItem==null){
                GorillaItem newItem = new GorillaItem();
                newItem.setItemName(soldList.get(i).getItemName());
                newItem.setSoldFlag(soldList.get(i).getSoldFlag());
                newItem.setItemNumber(soldList.get(i).getItemNumber());
                newItem.setBrandId(soldList.get(i).getBrandId());

                for (Brand element : brandList) {
                    if (element.getBrandId().equals(soldList.get(i).getBrandId())) {
                        newItems += soldList.get(i).getItemName() + "\n";
                    }
                }
                gorillaItemRepository.save(newItem);
                continue;
            }


            if(gorillaItem.getSoldFlag().equals("1") && soldList.get(i).getSoldFlag().equals("0")) {
                gorillaItemRepository.save(soldList.get(i));

                for (Brand element : brandList) {
                    if (element.getBrandId().equals(soldList.get(i).getBrandId())) {
                        items += soldList.get(i).getItemName() + "\n";
                    }
                }
            } else if(gorillaItem.getSoldFlag().equals("0") && soldList.get(i).getSoldFlag().equals("1")){
                gorillaItemRepository.save(soldList.get(i));
            }
        }

        if(!items.equals("") || !newItems.equals("")) {
           sendSmsOneMessage(newItems + "-\n" +items);
        }

        System.out.println(newItems);
        System.out.println(items);
    }
    public void sendSmsOneMessage(String item) {
        ArrayList<Message> messageList = new ArrayList<>();

        List<Receiver> phoneList = receiverRepository.findAll();

        int maxByte = 1000;
        int strLength = item.length();
        int startIndex = 0;
        int endIndex = maxByte;
        while (startIndex < strLength) {
            if (endIndex > strLength) {
                endIndex = strLength;
            }
            String subString = item.substring(startIndex, endIndex);
            startIndex = endIndex;
            endIndex += maxByte;

            for (Receiver receiver : phoneList) {
                String phoneNumber = receiver.getPhoneNumber();
                Message message = new Message();
                // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
                message.setFrom("01089149389");
                message.setTo(phoneNumber);
                message.setText(subString);

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
}
