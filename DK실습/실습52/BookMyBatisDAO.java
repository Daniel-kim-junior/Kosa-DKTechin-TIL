package com.example.springedu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springedu.domain.BookDTO;

@Repository
public class BookMyBatisDAO {
    @Autowired
    SqlSession session = null;

    public List<BookDTO> exam1() {
        String statement = "lab1.exam1";
        List<BookDTO> list = session.selectList(statement);
        return list;
    }

    public List<BookDTO> exam2() {
        String statement = "lab1.exam2";
        List<BookDTO> list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> exam3() {
        String statement = "lab1.exam3";
        List<BookDTO> list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> exam4() {
        String statement = "lab1.exam4";
        List<BookDTO> list = session.selectList(statement);
        return list;
    }

    public List<BookDTO> exam5() {
        String statement = "lab1.exam5";
        List<BookDTO> list = session.selectList(statement);
        return list;
    }
    public List<BookDTO> exam6(String param) {
        String statement = "lab1.exam6";
        List<BookDTO> list = session.selectList(statement, param);
        return list;
    }

    public List<BookDTO> exam7() {
        String statement = "lab1.exam7";
        List<BookDTO> list = session.selectList(statement);
        return list;
    }
}
