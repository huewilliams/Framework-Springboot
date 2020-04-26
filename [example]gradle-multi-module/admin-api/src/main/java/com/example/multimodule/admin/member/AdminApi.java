package com.example.multimodule.admin.member;

import com.example.multimodule.member.Member;
import com.example.multimodule.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApi {

    private final MemberRepository memberRepository;

    @PostMapping("/{name}")
    public Member create(@PathVariable String name) {
        final Member member = new Member(name);
        return memberRepository.save(member);
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
