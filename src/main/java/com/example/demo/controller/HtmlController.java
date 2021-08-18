package com.example.demo.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.DAO.Word;
import com.example.demo.DAO.WordRank;
import com.example.demo.service.DateCalService;
import com.example.demo.service.Findword;
import com.example.demo.service.JPAService;
import com.example.demo.service.Paging;
import com.example.demo.service.WordLogService;

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

    @Autowired
    WordLogService wordLogService;

    @Autowired
    Word userWord;

    @Autowired
    DateCalService dateCal;

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

        // Thymeleaf 로 받을 List<User> , paging 2가지 데이터를 추가ß
        model.addAttribute("userlogs", jpaService.findAll(pageable).getContent());
        model.addAttribute("pagingData", Paging.builder().currentPage(pageNumber)
                .totalPage(jpaService.findAll(pageable).getTotalPages()).build());
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

    @RequestMapping(value = "/dictionaryfav", method = RequestMethod.GET)
    public String dicRank(@RequestParam(value = "period", defaultValue = "Daily") String period,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, Model model) throws Exception {
        List<WordRank> wordRank = wordLogService.findWordRank(dateCal.findStartDate(period), dateCal.findEndDate(),
                (currentPage - 1) * 10);
        int totalPage = wordLogService.findCountByWordRank(dateCal.findStartDate(period), dateCal.findEndDate());
        totalPage = totalPage % 10 > 0 ? totalPage / 10 + 1 : totalPage / 10;
        model.addAttribute("wordRank", wordRank);
        model.addAttribute("period", period);
        model.addAttribute("pagingData", Paging.builder().currentPage(currentPage).totalPage(totalPage).build());

        return "dictionaryfav";
    }
}
