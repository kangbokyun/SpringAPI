function MiddleCateClick(i) {
    var indexNo = $("#middleIndex" + i).val();
    var inputValue = $("#inputValue").val();
    if(inputValue == 0) {
        $.ajax({
            url: "/Board/MiddleCategory",
            data: {"indexNo": indexNo},
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