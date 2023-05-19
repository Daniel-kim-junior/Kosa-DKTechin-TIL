package springrest.exam.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.RESET_CONTENT;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springrest.exam.entity.Friend;
import springrest.exam.repository.FriendRepository;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendRepository friendRepository;

    @GetMapping
    ResponseEntity<List<Friend>> getRequestFriends() {
        return ResponseEntity.ok(friendRepository.findAll());
    }

    @PostMapping
    @Transactional
    ResponseEntity<String> addFriend(@RequestBody Friend friend) {
        try{
            Friend f = Friend.builder().fname(friend.getFname()).fage(friend.getFage()).build();
            friendRepository.save(f);
        } catch(Exception e) {
            return new ResponseEntity<>("입력을 실패 했습니다.", null, 500);
        }

        return new ResponseEntity<>("입력을 성공했습니다", null, 201);
    }


    @GetMapping("{friendId}")
    ResponseEntity<Friend> getRequestById(@RequestParam int friendId) {
        Optional<Friend> friend = friendRepository.findById(friendId);
        if(friend.isEmpty()) {
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.set("BAD_ID", Integer.toString(friendId));
            return new ResponseEntity<>(null, headers, BAD_REQUEST);
        }
        return new ResponseEntity<>(friend.get(), null,  OK);
    }

    @GetMapping("{name}")
    ResponseEntity<Friend> getRequestByName(@RequestParam String name) {
        Optional<Friend> friend = friendRepository.findByFname(name);
        if(friend.isEmpty()) {
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.set("BAD_NAME", name);
            return new ResponseEntity<>(null, headers, BAD_REQUEST);
        }
        return new ResponseEntity<>(friend.get(), null,  OK);
    }


    @PutMapping
    @Transactional
    ResponseEntity<String> putFriend(@RequestBody Friend friend) {
        try{
            Friend getF = friendRepository.findById(friend.getId()).get();
            getF.update(friend.getFname(), friend.getFage());
            friendRepository.save(getF);
        } catch (Exception e) {
            return new ResponseEntity<>("수정 실패", null, INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("정상적 수정 완료", null, RESET_CONTENT);
    }

    @DeleteMapping
    @Transactional
    ResponseEntity<String> deleteFriend(@RequestParam int id) {
        Optional<Friend> getF = friendRepository.findById(id);
        if(getF.isEmpty()) {
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.set("BAD_ID", Integer.toString(id));
            return new ResponseEntity<>("BAD_ID", headers, BAD_REQUEST);
        }

        try{
            friendRepository.delete(getF.get());
        } catch (Exception e) {
            return new ResponseEntity<>("삭제 실패", null, INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("정상적 삭제 완료", null, RESET_CONTENT);
    }

}
