package com.example.springedu2;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

@SpringBootTest
public class FriendTest {
    @Autowired
    private  FriendRepository friendRepository;


    @Test
    @Transactional
    void 친구_입력_조회() {
        Friend 김민성 = Friend.builder().fage(20).fname("김민성").build();
        Friend save = friendRepository.save(김민성);

        assertThat(김민성).isEqualTo(save);

    }

    @Test
    @Transactional
    void 친구_이름으로_찾아서_수정() {
        Friend 김민성 = Friend.builder().fage(20).fname("김민성").build();
        friendRepository.save(김민성);

        Optional<Friend> 옵셔널김민성 = friendRepository.findByFname("김민성");

        assertThat(김민성).isEqualTo(옵셔널김민성.get());

        Friend new김민성 = 옵셔널김민성.get();

        new김민성.update("김민성", 21);

        assertThat(new김민성.getFage()).isEqualTo(21);
    }

    @Test
    @Transactional
    void 친구_ID로_찾아서_친구_삭제() {
        Friend 김민성 = Friend.builder().fage(20).fname("김민성").build();
        Friend save = friendRepository.save(김민성);

        Optional<Friend> 옵셔널김민성 = friendRepository.findById(save.getId());
        assertThat(옵셔널김민성.get()).isEqualTo(김민성);

        int id = 옵셔널김민성.get().getId();
        friendRepository.deleteById(id);

        Optional<Friend> deleteF = friendRepository.findById(id);

        assertThrows(NoSuchElementException.class, () -> {
            deleteF.get();
        });
    }



}
