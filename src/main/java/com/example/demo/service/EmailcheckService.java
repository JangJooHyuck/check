package com.example.demo.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.springframework.stereotype.Service;

@Service
public class EmailcheckService {

    // email 정규식 정의
    final static String Emailpattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    // email이 정규식에 맞는지 체크, 맞으면 success, 틀리면 faild를 반환함
    public String isValidEmail(String userEmail) {

        Pattern pattern = Pattern.compile(Emailpattern);
        Matcher matcher = pattern.matcher(userEmail);

        return matcher.find()? "success" : "fail";
    }
}
