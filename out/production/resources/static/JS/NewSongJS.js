function newSongUpdate() {
        $.ajax({
                url: "/NewSong/NewSongUpdate",
                success: function(result) {
                        if(result == 1) {
                                alert("최신곡이 업데이트 됐습니다.");
                                location.href = "/NewSong/NewSongList";
                        }
                }
        });
}
// 설정한 시간 지나면 자동 함수 실행
setInterval(newSongUpdate, 100000);