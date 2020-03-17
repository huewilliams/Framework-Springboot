package com.springboot.book.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // JUnit 에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_save_and_load() {
        //given
        String title = "test title";
        String content = "test content";
        String author = "test author";

        postsRepository.save(Posts.builder() // posts 테이블에 insert/update 쿼리 실행
                .title(title)
                .content(content)
                .author(author)
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // posts 테이블의 모든 데이터 조회

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }
}
