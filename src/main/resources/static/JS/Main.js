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