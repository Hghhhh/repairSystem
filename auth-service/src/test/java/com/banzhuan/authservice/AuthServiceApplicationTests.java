package com.banzhuan.authservice;

import com.banzhuan.authservice.dto.Result;
import com.banzhuan.authservice.util.HmaCUtil;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Random;


import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceApplicationTests {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void applyToken(){
		Long current = System.currentTimeMillis() ;
		String url = "http://localhost:8081/auth/apply-token";
		MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<String, Object>();
		String clientKey = "123";// 客户端标识（用户名）
		String mix = String.valueOf(new Random().nextFloat());// 随机数，进行混淆
		String timeStamp = current.toString();// 时间戳
		dataMap.add("clientKey", clientKey);
		dataMap.add("mix", mix);
		dataMap.add("timeStamp", timeStamp);
		String baseString = clientKey+mix+timeStamp;
		String digest = HmaCUtil.hmacDigest(baseString,"HmacMD5","banzhuan");// 生成HMAC摘要
		dataMap.add("digest", digest);
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.POST;
		// 以表单的方式提交
		//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//将请求头部和参数合成一个请求
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(dataMap, headers);
		//执行HTTP请求，将返回的结构使用ResultVO类格式化
		ResponseEntity<Result> response = client.exchange(url, method, (HttpEntity<?>) requestEntity, Result.class);

		System.out.println(response.getBody().getData());
	}
}
