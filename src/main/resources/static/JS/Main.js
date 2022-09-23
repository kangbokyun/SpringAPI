var Graph = setInterval(function test() {
        $.ajax({
                url : "/CountSong",
                success : function(result) {
                        <!--// 차트를 그럴 영역을 dom요소로 가져온다.-->
                           var chartArea = document.getElementById('myChart').getContext('2d');
                           <!--// 차트를 생성한다. -->
                           var myChart = new Chart(chartArea, {
                               <!--// ①차트의 종류(String)-->
                               type: 'bar',
                               <!--// ②차트의 데이터(Object)-->
                               data: {
                                   <!--// ③x축에 들어갈 이름들(Array)-->
                                   labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                                   <!--// ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.-->
                                   datasets: [{
                                       <!--// ⑤dataset의 이름(String)-->
                                       label: 'Song',
                                       <!--// ⑥dataset값(Array)-->
                                       data: result,
                                       <!--// ⑦dataset의 배경색(rgba값을 String으로 표현)-->
                                       backgroundColor: 'rgb(255, 99, 132)',
                                       <!--// ⑧dataset의 선 색(rgba값을 String으로 표현)-->
                                       borderColor: 'rgb(255, 99, 132)',
                                       <!--// ⑨dataset의 선 두께(Number)-->
                                       borderWidth: 1
                                   }]
                               },
                              options: {
                                  scales: {
                                      yAxes: [
                                          {
                                              ticks: {
                                                  beginAtZero: true
                                              }
                                          }
                                      ]
                                  }
                              }
                           });
                }
        });
    clearInterval(Graph)
}, 100);

function OverFooter(no) {
    if(no == 1) {
        $("#divOver").html("<div style = 'border : solid 1px black; text-align : center;' id = 'divOver'><h4>개인정보 처리방침</h4><p style = 'margin-bottom : 5px;'>회원가입 시 등록한 개인정보는 어떠한 곳에도 이용하지 않습니다.<p style = 'margin-bottom : 5px; margin-top : 5px;'>회원가입 시 유효성검사 하지 않습니다.</div>");
    } else if(no == 2) {
        $("#divOver").html("<div style = 'border : solid 1px black; text-align : center;' id = 'divOver'><h4>운영정책</h4><p style = 'margin-bottom : 5px;'>로컬 서버를 이용하거나 AWS를 이용해 배포합니다.<p>크롤링 및 스크래핑된 정보를 통한 어떠한 수익도 발생하지 않습니다.</div>");
    } else {
        $("#divOver").html("<div style = 'border : solid 1px black; text-align : center;' id = 'divOver'><h4>문의 및 건의</h4><p style = 'margin-bottom : 5px;'>문제가 되는 부분이 있거나 문의하실 내용이 <p style = 'margin-bottom : 5px; margin-top : 5px;'>있다면 아래 메일로 연락 바랍니다.<p style = 'margin-bottom : 5px; margin-top : 5px;'>bk940204@naver.com</div>");
    }
}
function LeaveFooter() {
    $("#divOver").html("<div style = 'display : none;' id = 'divOver'></div>");
}