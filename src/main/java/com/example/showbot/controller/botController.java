package com.example.showbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class botController {
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/kkoChat", method = {RequestMethod.POST, RequestMethod.GET}, headers = {"Accept=application/json"})
    public HashMap<String, Object> botController(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        //params => 유저가 보낸 발화 json

        HashMap<String, Object> resultJson = new HashMap<>();

        try {
            //ObjectMapper => json to javaObject || JavaObject to json? 그런건가
            //여기서는 HashMap을 String으로 변환
            ObjectMapper mapper = new ObjectMapper();
            //json을 String으로 형변환
            String jsonInString = mapper.writeValueAsString(params);


            //유저한테 받은 json에 있는 "userRequest"의 value값 => HashMap으로 다운캐스팅
            HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");

            //그 중에 발화내용 String으로 변환
            String utter = userRequest.get("utterance").toString().replace("\n", "");

            // "userRequest" 객체의 "user" 객체를 추출
            HashMap<String, Object> user = (HashMap<String, Object>) userRequest.get("user");

            // "user" 객체의 "properties" 객체를 추출
            HashMap<String, String> properties = (HashMap<String, String>) user.get("properties");

            // "properties" 객체의 "botUserKey" 값을 가져옴
            String botUserKey = properties.get("botUserKey");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultJson;
    }
}
