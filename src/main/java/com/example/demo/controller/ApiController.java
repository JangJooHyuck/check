package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DAO.Userlog;
import com.example.demo.DAO.Word;

import com.example.demo.model.User;
import com.example.demo.service.EmailcheckService;
import com.example.demo.service.Findword;
import com.example.demo.service.JPAService;
import com.example.demo.service.WordLogService;

@Controller // /api로 들어오는것들은 밑에 클래스에서 받아올수있다.
@RequestMapping("/api")
public class ApiController {

    // 객체 자동생성 Autowired
    @Autowired
    EmailcheckService emailcheckservice;

    @Autowired
    JPAService jpaService;

    Page<Userlog> userlogList;

    Userlog userlog2 = new Userlog();

    @Autowired
    Findword findwordservice;

    @Autowired
    WordLogService wordLogService;

    // get방식
    @GetMapping("/get")
    @ResponseBody
    // 값이 check 인것을 파라미터로 받아온다.
    public Userlog getResult(@RequestParam(value = "check") String userEmail,
            @RequestParam(value = "userIP") String UserIP) throws Exception {

        // 3초 지연 (spinner 를 보여주기위해 일부러 지연시킴)
        Thread.sleep(3000);

        jpaService.save(new Userlog(UserIP, userEmail, emailcheckservice.isValidEmail(userEmail)));
        return jpaService.findFirstByOrderByIdDesc();
    }

    // post 방식
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public Userlog POSTresult(@RequestBody User userlog) throws Exception {

        // 3초 지연
        Thread.sleep(3000);
        jpaService.save(
                new Userlog(userlog.getUserip(), userlog.getMail(), emailcheckservice.isValidEmail(userlog.getMail())));
        return jpaService.findFirstByOrderByIdDesc();
    }

    @GetMapping("/ajax")
    @ResponseBody
    public Page<Userlog> pageList(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber)
            throws Exception {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return jpaService.findAll(pageable);
    }

    @RequestMapping(value = "/updateword", method = RequestMethod.POST)
    @ResponseBody
    public Word updateWord(@RequestBody Word word) throws Exception {
        findwordservice.updateWord(word.getWord(), word.getContent());
        return findwordservice.findByWord(word.getWord());
    }

    @RequestMapping(value = "/deleteword", method = RequestMethod.POST)
    @ResponseBody
    public void deleteWord(@RequestBody Word word) throws Exception {
        findwordservice.deleteByWord(word.getWord());
        wordLogService.deleteByWord(word.getWord());
    }

}
