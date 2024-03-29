// 카카오주소
function sample6_execDaumPostcode() {
        new daum.Postcode({
                oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if(data.buildingName !== '' && data.apartment === 'Y'){
                                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if(extraAddr !== ''){
                                extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("sample6_extraAddress").value = extraAddr;

                        } else {
                                document.getElementById("sample6_extraAddress").value = '';
                        }

                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('sample6_postcode').value = data.zonecode;
                        document.getElementById("sample6_address").value = addr;
                        // 커서를 상세주소 필드로 이동한다.
                        document.getElementById("sample6_detailAddress").focus();
                }
        }).open();
}

// 회원가입
function SignUpCheck() {
    var mid = $("#mid").val();
    var mpw1 = $("#mpw1").val();
    var mpw2 = $("#mpw2").val();
    var mname = $("#mname").val();
    var memail = $("#memail").val();
    var mphone = $("#mphone").val();
    var maddress = $("#sample6_address").val() + " " + $("#sample6_detailAddress").val();
    var checkid = true; var checkpw = true; checkname = true;

    if(mid.length < 2 || mid.length > 15) {
            alert("2글자 이상, 15글자 이하를 입력하세요.");
            checkid = false;
            return;
        } else {
            checkid = true;
        }
        if(mpw1 != mpw2) {
            alert("비밀번호를 확인해주세요.");
            checkpw = false;
            return;
        } else {
            checkpw = true;
        }
        if(mname.length < 2) {
            alert("이름은 두글자 이상 입력해야 합니다.");
            checkname = false;
            return;
        } else {
            checkname = true;
        }

        if(checkid && checkpw && checkname) {
            $.ajax({
                url: "/Member/SignUpController",
                data: {"mid":mid, "mpw":mpw1, "mname":mname, "memail":memail, "mphone":mphone, "maddress":maddress},
                success: function(result) {
                    if(result == 1) {
                        alert("회원가입이 되었습니다.");
                        location.href = "/";
                    } else {
                        alert("회원가입 실패 :: 관리자에게 문의");
                    }
                }
            });
        }

}


// 가수 예약하기
function Reservation(mno) {
    var reserv = $("#reservbtn").val();
    $.ajax({
        url: "/Member/Reservation",
        data: {"reserv" : reserv, "mno" : mno},
        success: function(result) {
            if(result == 1) {
                alert("예약되었습니다.");
                location.href = "/Member/MyInfo";
//                $("#reservbtn").val("");
//                $("#ReservDIV").load(location.href+ " #ReservDIV");
            } else {
                alert("시스템에러 :: 관리자에게 문의");
            }
        }
    });
}

// 로그아웃
function Logout(mno) {
    $.ajax({
        url: "/Member/Logout",
        data: {"mno" : mno},
        success: function(result) {
            if(result == 1) {
                alert("로그아웃 되었습니다.");
                location.href = "/";
            } else {
                alert("로그아웃 실패 :: 관리자에게 문의");
            }
        }
    });
}

// 회원가입 유효성검사
function checkSignUp() {
    var id = $("#mid").val();
    var pw1 = $("#mpw1").val();
    var pw2 = $("#mpw2").val();
    var name = $("#mname").val();


}