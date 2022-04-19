
function SingMenu(i) {
    $("#cateNo").val($("#cateNo" + i).val());
}

// 중간카테고리 클릭으로 펼치기, 닫기
function MiddleCateClick(i) {
    var cateNo = $("#cateNo").val();
    var indexNo = $("#middleIndex" + i).val();
    var inputValue = $("#inputValue").val();
    if(inputValue == 0) {
        $.ajax({
            url: "/Board/MiddleCategory",
            data: {"indexNo": indexNo, "cateNo" : cateNo},
            success: function(result) {
                $("#tableView").html(result);
            }
        });
        $("#inputValue").val("1");
    } else {
        $("#tableView").html("");
        $("#inputValue").val("0");
    }
}

// 게시글 등록
function BoardWrite() {
    var title = $("#titleW").val();
    var contents = $("#contentsW").val();
    $.ajax({
        url: "/Board/BoardW",
        data: {"title" : title, "contents" : contents},
        success: function(result) {
            if(result == 1) {
                alert("글이 등록되었습니다.");
                location.href = "/Board/BoardMain";
            } else {
                alert("글 등록 실패 :: 관리자에게 문의");
            }
        }
    });
}

// 댓글 등록
function ReplyWrite(bno, mno) {
    var Reply = $("#ReplyTag").val();
    var bno = bno;
    var mno = mno;
    $.ajax({
        url: "/Board/RiplyWrite",
        data: {"reply" : Reply, "bno" : bno, "mno" : mno},
        success: function(result) {
            alert(result);
            if(result == 1) {
                alert("댓글이 등록 되었습니다.");
                $("#ReplyTable").load(location.href+ " #ReplyTable");
                $("#ReplyTag").val("");
            } else {
                alert("댓글 등록 실패 :: 관리자에게 문의");
            }
        }
    });
}