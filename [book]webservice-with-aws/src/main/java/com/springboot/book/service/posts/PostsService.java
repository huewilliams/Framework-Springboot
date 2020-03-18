package com.springboot.book.service.posts;

import com.springboot.book.domain.posts.Posts;
import com.springboot.book.domain.posts.PostsRepository;
import com.springboot.book.web.dto.PostsResponseDto;
import com.springboot.book.web.dto.PostsSaveRequestDto;
import com.springboot.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository; // 생성자로 Bean을 주입받는다.

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        // update 기능에서 쿼리를 날리는 부분이 없다. => JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경)
        // 더티 체킹 : JPA 엔티티 매니저가 Entity 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경을 반영함.

        return  id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}