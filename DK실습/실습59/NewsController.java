package com.example.springnews.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.springnews.model.News;
import com.example.springnews.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final NewsRepository newsRepository;

    @GetMapping("/newsmain")
    public String getNews(Model model) {
        Pageable pageable = PageRequest.of(0, 5);
        long count = newsRepository.count() % 5 == 0 ? newsRepository.count() / 5 - 1 : newsRepository.count() / 5;
        List<News> list = newsRepository.findByOrderByWriteDateDesc(pageable);
        model.addAttribute("newsList", list);
        model.addAttribute("news", new News());
        model.addAttribute("count", count);
        return "newsmain";
    }

    @PostMapping("/insert")
    public String insertNews(@ModelAttribute News news, Model model) {
        news.setCnt(0);
        newsRepository.save(news);
        return "redirect:/newsmain";
    }

    @GetMapping("/list")
    public String getPage(@RequestParam int page, Model model) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        long count = newsRepository.count() % 5 == 0 ? newsRepository.count() / 5 - 1 : newsRepository.count() / 5;
        List<News> list = newsRepository.findByOrderByWriteDateDesc(pageable);
        model.addAttribute("newsList", list);
        model.addAttribute("news", new News());
        model.addAttribute("count", count);
        return "newsmain";
    }


    @GetMapping("/search")
    public String getNewsContentLike(@RequestParam String inputContent, Model model) {
        List<News> byContentContaining = newsRepository.findByContentContainingOrderByWriteDateDesc(inputContent);
        model.addAttribute("newsList", byContentContaining);
        return "newsResultList";
    }


    @GetMapping("/listOne")
    @Transactional
    public String getNewsId(int newsId, Model model) {
        newsRepository.findByIdOrderByWriteDateDesc(newsId)
                .ifPresent(news -> {
                    model.addAttribute("news", news);
                    news.setCnt(news.getCnt() + 1);
                });

        return "newsDetail";
    }

    @GetMapping("/writer")
    public String getWriter(@RequestParam String newsWriter, Model model) {
        List<News> byWriterList = newsRepository.findByWriterOrderByWriteDateDesc(newsWriter);
        model.addAttribute("newsList", byWriterList);
        return "newsResultList";
    }

    @GetMapping("/delete")
    public String deleteNews(int newsId) {
        newsRepository.deleteById(newsId);
        return "redirect:/newsmain";
    }


    @PostMapping("/update")
    @Transactional
    public String updateNews(News news) {
        Optional<News> byId = newsRepository.findByIdOrderByWriteDateDesc(news.getId());
        News newNews = byId.get();
        newNews.setWriter(news.getWriter());
        newNews.setTitle(news.getTitle());
        newNews.setContent(news.getContent());
        return "redirect:/newsmain";
    }



}
