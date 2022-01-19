function newSongUpdate() {
        $.ajax({
                url: "/NewSong/NewSongUpdate",
                success: function(result) {
                        if(result == 1) {
                                alert("최신곡이 업데이트 됐습니다.");
                                location.href = "/NewSong/NewSongList";
                        } else {
                                alert("업데이트에 실패했습니다.")
                        }
                }
        });
}
setInterval(newSongUpdate, 100000);