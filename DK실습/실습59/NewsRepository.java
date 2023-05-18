package com.example.springnews.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springnews.model.News;
public interface NewsRepository extends JpaRepository<News, Integer> {

    // /newsmain
    List<News> findByOrderByWriteDateDesc(Pageable pageable);

    // /listOne
    Optional<News> findByIdOrderByWriteDateDesc(int newsId);

    // /update(save)


    // /delete
    void deleteById(int newsId);

    // /search

    List<News> findByContentContainingOrderByWriteDateDesc(String newsContent);

    // /writer
    List<News> findByWriterOrderByWriteDateDesc(String writer);


}
