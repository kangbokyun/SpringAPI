function SingMenu() {
    var hiddenVal = $("#hiddenValue").val();
    if(hiddenVal == 1) {
        $("#menuRow").html("<div><a onclick = 'Test();'><button id = 'btn1' style = 'background-color: white; border: none;' value = '발라드'>발라드</button></a>&nbsp;&nbsp;&nbsp;<a>발라드</a></div><div></div>");
        $("#hiddenValue").val("2");
    } else if(hiddenVal == 2) {
        $("#menuRow").html("");
        $("#hiddenValue").val("1");
    }
}

function Test() {
    alert($("#btn1").val());
}