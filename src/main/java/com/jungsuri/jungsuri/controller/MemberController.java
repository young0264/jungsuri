package com.jungsuri.jungsuri.controller;

import com.jungsuri.jungsuri.dto.member.SignUpMemberDto;
import com.jungsuri.jungsuri.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //===로그인===//
    @GetMapping("/member/sign-in")
    public String signin(Model model) {
//        model.addAttribute("member", member);
        return "/member/signIn";
    }


    //===회원가입===//
    @GetMapping("/member/sign-up")
    public String signup(@ModelAttribute("signUpMemberDto") SignUpMemberDto signUpMemberDto) {

        return "/member/signUp";
    }

    @PostMapping("/member/sign-up")
    public String signup(@Valid @ModelAttribute("signUpMemberDto") SignUpMemberDto signUpMemberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/signUp";
        }
        if (!signUpMemberDto.getPassword1().equals(signUpMemberDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "두개의 패스워드가 일치하지 않습니다");
            return "member/signUp";
        }
        try {
            memberService.createMember(signUpMemberDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "member/signUp";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("singupFailed", e.getMessage());
            return "member/signUp";
        }

        return "redirect:/";
    }

}
