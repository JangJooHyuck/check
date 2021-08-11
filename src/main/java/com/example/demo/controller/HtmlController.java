package com.example.demo.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.DAO.Word;
import com.example.demo.service.Findword;
import com.example.demo.service.JPAService;
import com.example.demo.service.Paging;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HtmlController {

    @Autowired
    Paging paging;

    @Autowired
    JPAService jpaService;

    @Autowired
    Findword findword;

    Word userWord = new Word();

    @RequestMapping("/")
    public String email_check() throws Exception {
        // index.html 불러옴
        return "index";
    }

    // main.html
    @ModelAttribute
    @RequestMapping("/main")
    public String main(Model model) throws Exception {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String mailString = req.getHeader("X-FORWARDED-FOR");
        if (mailString == null) {
            mailString = req.getRemoteAddr();
        }
        model.addAttribute("value", mailString);
        return "main";
    }

    // printLog.html
    @RequestMapping(value = "/printlog", method = RequestMethod.GET)
    public String printLog(@RequestParam(value = "value", defaultValue = "1") int pageNumber, Model model)
            throws Exception {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());

        paging.setCurrentPage(pageNumber);
        paging.setTotalPage(jpaService.findAll(pageable).getTotalPages());
        // Thymeleaf 로 받을 List<User> , paging 2가지 데이터를 추가
        model.addAttribute("userlogs", jpaService.findAll(pageable).getContent());
        model.addAttribute("pagingData", paging);
        return "printlog";
    }

    // dictionary.html
    @ModelAttribute
    @RequestMapping(value = "/dictionary", method = RequestMethod.GET)
    public String dictionary(@RequestParam(value = "word", required = false) String word, Model model)
            throws Exception {
        // thymeleaf로 값 html에 배치시키기
        if (word == null) {
            userWord = new Word();
            model.addAttribute("userWord", userWord);
            return "dictionary";
        } else {
            if (findword.findByWord(word) == null) {
                findword.save(new Word(word, findword.getContent(word)));
                userWord = findword.findByWord(word);
                model.addAttribute("userWord", userWord);
            } else {
                userWord = findword.findByWord(word);
                model.addAttribute("userWord", userWord);
            }
        }
        return "dictionary";
    }

    @RequestMapping(value = "/wordRank")
    public String wordRank() throws Exception {
        return "wordRank";
    }

}
