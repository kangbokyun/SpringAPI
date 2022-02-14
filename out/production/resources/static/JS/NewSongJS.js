// 설정한 시간 지나면 자동 함수 실행
setInterval(newSongUpdate, 100000);
function newSongUpdate() {
        $.ajax({
                url: "/NewSong/NewSongUpdate",
                success: function(result) {
                        if(result == 1) {
                                location.href = "/NewSong/NewSongList";
                        }
                }
        });
}
