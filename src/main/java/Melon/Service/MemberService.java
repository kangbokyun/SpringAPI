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
        List<MemberEntity> memberEntities = memberRepository.findAll();
        MemberDTO memberDTO = new MemberDTO();
        for(MemberEntity memberEntity : memberEntities) {
            if(memberEntity.getMid().equals(mid) && memberEntity.getMpw().equals(mpw)) {
                memberDTO.setMid(memberEntity.getMid());
                memberDTO.setMname(memberEntity.getMname());
                memberDTO.setMemail(memberEntity.getMemail());
                memberDTO.setMpw(memberEntity.getMpw());
                memberDTO.setMphone(memberEntity.getMphone());
                memberDTO.setMaddress(memberEntity.getMaddress());
                memberDTO.setMno(memberEntity.getMno());
                memberDTO.setMreserv(memberEntity.getMreserv());
        	return memberDTO;
            } else {
		    return null;
	    }
        }
    return null;
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
