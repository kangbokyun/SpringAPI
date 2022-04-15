function SingMenu() {
    var hiddenVal = $("#hiddenValue").val();
    if(hiddenVal == 1) {
        $("#menuRow").html(
            "<div th:each = 'MCategory : ${MCategory}'><div th:text = '${MCategory.getMcname()}'></div>&nbsp;&nbsp;&nbsp;</div><div></div>"
        );
        $("#hiddenValue").val("2");
    } else if(hiddenVal == 2) {
        $("#menuRow").html("");
        $("#hiddenValue").val("1");
    }
}

function Test() {
    alert($("#btn1").val());
}