package com.spring.jpaapp.api;

import com.spring.jpaapp.model.Member;
import com.spring.jpaapp.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    //문제점: Entity를 직접 받으면 안됨
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    //별도의 Dto를 통해서 확장성, 직관성 증가
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @Data
    static class CreateMemberRequest{
        private String name;
    }
    @Data
    static class CreateMemberResponse{
        public CreateMemberResponse(Long id) {
            this.id = id;
        }

        private Long id;
    }
}
