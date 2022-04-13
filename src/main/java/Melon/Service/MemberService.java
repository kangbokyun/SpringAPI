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
        for(MemberEntity memberEntity : memberEntities) {
            if(memberEntity.getMid().equals(mid) && memberEntity.getMpw().equals(mpw)) {
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setMid(memberEntity.getMid());
                memberDTO.setMname(memberEntity.getMname());
                memberDTO.setMemail(memberEntity.getMemail());
                return memberDTO;
            }
        }
        return null;
    }
}
