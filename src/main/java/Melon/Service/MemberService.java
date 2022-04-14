package Melon.Service;

import Melon.Domain.DTO.MemberDTO;
import Melon.Domain.Entity.MemberEntity;
import Melon.Domain.Entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    // 회원가입
    public boolean MemberSignUp(String mid, String mpw, String mname, String memail, String mphone, String maddress) {
        MemberEntity memberEntity = MemberEntity.builder()
                .mid(mid)
                .mpw(mpw)
                .mname(mname)
                .memail(memail)
                .mphone(mphone)
                .maddress(maddress)
                .build();
        memberRepository.save(memberEntity);
        return true;
    }

    // 로그인
    public MemberDTO MemberLogin(String mid, String mpw) {
        System.out.println("2");
        List<MemberEntity> memberEntities = memberRepository.findAll();
        System.out.println("3");
        MemberDTO memberDTO = new MemberDTO();
        for(MemberEntity memberEntity : memberEntities) {
            System.out.println("4");
            if(memberEntity.getMid().equals(mid) && memberEntity.getMpw().equals(mpw)) {
                System.out.println("5");
                memberDTO.setMid(memberEntity.getMid());
                System.out.println("6");
                memberDTO.setMname(memberEntity.getMname());
                System.out.println("7");
                memberDTO.setMemail(memberEntity.getMemail());
                System.out.println("8");
                memberDTO.setMpw(memberEntity.getMpw());
                System.out.println("9");
                memberDTO.setMphone(memberEntity.getMphone());
                System.out.println("10");
                memberDTO.setMaddress(memberEntity.getMaddress());
                System.out.println("11");
                memberDTO.setMno(memberEntity.getMno());
                System.out.println("12");
                memberDTO.setMreserv(memberEntity.getMreserv());
                System.out.println("13");
            }
        }
        System.out.println("14");
        return memberDTO;
    }

    // 로그아웃
    public boolean Logout(int mno) {
        MemberEntity memberEntity = memberRepository.getById(mno);
        if(memberEntity.getMid() != null) {
            return true;
        } else {
            return false;
        }
    }

    // 가수 예약
    public boolean Reservation(String reserv, int mno) {
        MemberEntity memberEntity = memberRepository.getById(mno);
        if(memberEntity.getMreserv() == null) {
            memberEntity.setMreserv(reserv);
            memberRepository.save(memberEntity);
        } else {
            memberEntity.setMreserv(memberEntity.getMreserv() + "," + reserv);
            memberRepository.save(memberEntity);
        }
        return true;
    }
}
