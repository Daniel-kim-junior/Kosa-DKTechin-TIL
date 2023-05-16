package com.example.springedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springedu.dao.BookMyBatisDAO;
import com.example.springedu.domain.BookDTO;

@Controller
public class BookController {
    @Autowired
    private BookMyBatisDAO bookMyBatisDAO;

    @GetMapping("/book")
    public String book() {
        return "bookPage";
    }

    @GetMapping("/bookCreate")
    public String bookCreate() {
        return "bookCreatePage";
    }


    @PostMapping("/bookCreate")
    public String bookCreate(BookDTO bookDTO, Model model) {
        bookMyBatisDAO.insert(bookDTO);
        model.addAttribute("BookDTO",bookDTO);
        return "bookCreatePage";
    }






    @GetMapping("/bookinfo/b{num}")
    public String bookInfo(@PathVariable int num , Model model) {
        List<BookDTO> bookObj;
        String msg = "<h3>추출된 데이터가 없네용</h3>";
        if(num == 1) {
             bookObj = bookMyBatisDAO.exam1();
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }
        } else if(num == 2) {
            bookObj = bookMyBatisDAO.exam2();
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }
        } else if(num == 3) {
            bookObj = bookMyBatisDAO.exam3();
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }
        } else if(num == 4) {
            bookObj = bookMyBatisDAO.exam4();
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }
        } else if(num == 5) {
            bookObj = bookMyBatisDAO.exam5();
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }

        } else if(num == 6) {
            bookObj = bookMyBatisDAO.exam6("자바");
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }
        } else if(num == 7) {
            bookObj = bookMyBatisDAO.exam6("스프링");
            if (bookObj.size() == 0) {
                model.addAttribute("msg", msg);
            } else {
                model.addAttribute("books", bookObj);
            }
        } else if(num == 8) {
            bookObj = bookMyBatisDAO.exam7();
            if(bookObj.size() == 0) {
                model.addAttribute("msg",msg);
            } else {
                model.addAttribute("books",bookObj);
            }
        }

        return "bookPage";
    }
}
