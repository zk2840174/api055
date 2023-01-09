package org.zerock.api01.service;

import org.zerock.api01.dto.MemberDTO;

public interface MemberService {

    MemberDTO getMember(String mid, String mpw);
}
