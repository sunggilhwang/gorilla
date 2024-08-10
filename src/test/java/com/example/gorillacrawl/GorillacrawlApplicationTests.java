package com.example.gorillacrawl;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
class GorillacrawlApplicationTests {

	@Autowired
	ReceiverRepository receiverRepository;
    @Test
    void contextLoads() {
    }

	@Test
	void test01() throws IOException {
		/*DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("");

		ArrayList<Message> messageList = new ArrayList<>();

		List<Receiver> phoneList = receiverRepository.findAll();
		for (Receiver receiver : phoneList) {
			String phoneNumber = receiver.getPhoneNumber();
			Message message = new Message();
			// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
			message.setFrom("01089149389");
			message.setTo(phoneNumber);
			message.setText("test");

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
		}*/

	/*	DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("");

		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		message.setFrom("01089149389");
		message.setTo("01089149389");
		message.setText("test");

		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));*/
	}
    @Test
    void test3() {
        String test = """      
				 <input type="hidden" name="brand_id" id="brand_id_0" value="Gorilla220">					
									<input type="hidden" name="brand_name" id="brand_name_0" value="페크론파워뱅크">					
									<input type="hidden" name="ct_ea" id="ct_ea_0" value="1">					
									<input type="hidden" name="ct_stand" id="ct_stand_0" value="">					
									<input type="hidden" name="sold_out" id="it_sold_out_0" value="0">					
									<input type="hidden" name="ct_code" id="ct_code_0" value="">					
									<tr>						
									<td>							
									<div class="img_wrap">								
									<!-- 20231205 제품목록이미지클릭시 모달창으로 확대이미지 보이기(2/3) : data 속성 추가 (시작) -->																	
									<span><img src="/shop/data/item/1707103709/1707103709_m1.jpg" data_img="/shop/data/item/1707103709/1707103709_l1.jpg" class="shop_img" /></span>	
									<!-- 20231205 제품목록이미지클릭시 모달창으로 확대이미지 보이기(2/3)(끝) : data 속성 추가  -->							</div><!-- // img_wrap -->	
									</td>						<td>페크론파워뱅크</td>						
									<td>페크론 E1500 LFP 인산철 파워뱅크</td>						
									<td>1</td>						<td></td>						
									<td style="color: red;">￦ 1,599,000원</td>						
									<td>							<!-- 							
									￦ 1,263,200원							 -->							￦ 1,263,200원						</td>						
									<td class="button_wrap">							
									<button type="button" onclick="change_qty(1, 'ct_qty_0')">+</button>							
									<input type="text" id="ct_qty_0" value="1" onkeyup="input_qty('ct_qty_0')" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\\..*)\\./g, '$1');"/>	
									<button type="button" onclick="change_qty(-1, 'ct_qty_0')">-</button>						
									</td>						<td class="link">							
									<a href="javascript:void(0);" onclick="javascript:direct_buy('1707103709', '0');">구매하기</a>							
									<a href="javascript:void(0);" onclick="javascript:cart_update('1707103709', '0');">장바구니</a>						
									</td>					</tr>			
									
									<input type="hidden" name="brand_id" id="brand_id_1" value="Gorilla126">					
									<input type="hidden" name="brand_name" id="brand_name_1" value="레토">					
									<input type="hidden" name="ct_ea" id="ct_ea_1" value="6">					
									<input type="hidden" name="ct_stand" id="ct_stand_1" value="">					
									<input type="hidden" name="sold_out" id="it_sold_out_1" value="0">					
									<input type="hidden" name="ct_code" id="ct_code_1" value="">					
									<tr>						
									<td>							
									<div class="img_wrap">								
									<!-- 20231205 제품목록이미지클릭시 모달창으로 확대이미지 보이기(2/3) : data 속성 추가 (시작) -->																	
									<span><img src="/shop/data/item/1707095651/1707095651_m1.png" data_img="/shop/data/item/1707095651/1707095651_l1.png" class="shop_img" /></span>	
									<!-- 20231205 제품목록이미지클릭시 모달창으로 확대이미지 보이기(2/3)(끝) : data 속성 추가  -->							
									</div><!-- // img_wrap -->													
									</td>						
									<td>레토</td>						
									<td>레토 캠핑 전골 그리들 40cm </td>						
									<td>6</td>						<td></td>						<td style="color: red;">￦ 38,900원</td>						<td>							
									<!-- 							￦ 26,800원							 -->							
									￦ 26,800원						</td>						
									<td class="button_wrap">							
									<button type="button" onclick="change_qty(1, 'ct_qty_1')">+</button>							
									<input type="text" id="ct_qty_1" value="1" onkeyup="input_qty('ct_qty_1')" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\\..*)\\./g, '$1');"/>	
									<button type="button" onclick="change_qty(-1, 'ct_qty_1')">-</button>						</td>						<td class="link">
									<a href="javascript:void(0);" onclick="javascript:direct_buy('1707095651', '1');">구매하기</a>							
									<a href="javascript:void(0);" onclick="javascript:cart_update('1707095651', '1');">장바구니</a>						
									</td>					</tr>	
				""";



		String [] test2 = test.split("<input type=\"hidden\" name=\"brand_id\"");


		String soldIdString = "it_sold_out_";
		for(int i=0;i<test2.length; i++){
			String soldFlagRegex = "<input type=\"hidden\" name=\"sold_out\" id=\"it_sold_out_([0-9]+)\" value=\"([0-9]+)\">";
			Pattern soldFlagPattern = Pattern.compile(soldFlagRegex);
			Matcher soldFlagMatcher = soldFlagPattern.matcher(test2[i]);
			while (soldFlagMatcher.find()) {
				String sequence = soldFlagMatcher.group(1);
				String value = soldFlagMatcher.group(2);
				System.out.println("sequence: " + sequence + ", value: " + value);
			}

			String regex = "<td>(.*?)</td>";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(test2[i]);
			matcher.find();
			if (matcher.find()) {
				String result = matcher.group(1);
				System.out.println(result); // "페크론 E1500 LFP 인산철 파워뱅크"
			}

			String regex2 = "direct_buy\\('(.*?)\\'";
			Pattern pattern2 = Pattern.compile(regex2);
			Matcher matcher2 = pattern2.matcher(test2[i]);
			if (matcher2.find()) {
				String result = matcher2.group(1);
				System.out.println(result); // "1707103709"
			}

		}

//		String regex = "<td>페크론 E1500 LFP 인산철 파워뱅크</td>";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(test);
//		if (matcher.find()) {
//			String result = matcher.group();
//			System.out.println(result);
//		}

	}

	@Test
	void test4() {
		String str = "abcdefghijk";

		int maxByte = 2;
		int strLength = str.length();
		int startIndex = 0;
		int endIndex = maxByte;
		while (startIndex < strLength) {
			if (endIndex > strLength) {
				endIndex = strLength;
			}
			String subString = str.substring(startIndex, endIndex);
			startIndex = endIndex;
			endIndex += maxByte;
		}
	}


}
