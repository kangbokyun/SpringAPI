function Login() {
    var mid = $("#modalid").val();
    var mpw = $("#modalpw").val();
    $.ajax({
        url: "/Member/Login",
        data: {"mid" : mid, "mpw" : mpw},
        success: function(result) {
            if(result == 1) {
                alert("로그인 되었습니다.")
                location.href = "/";
            } else {
                alert("로그인 실패 :: 아이디와 비밀번호 재확인 요망")
            }
        }
    });
}