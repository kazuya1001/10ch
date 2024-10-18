/**
 * 投稿詳細機能
 */

 // ユーザ詳細画面表示
 function postDetailDisp(){
	// セッション情報取得
	let userInfo;
	// 画面表示時にユーザ情報を取得
    $.ajax({
        url: '/api/getUserInfo',
        type: 'get',
        dataType: 'json',
        success: function(user) {
			userInfo = user.userId;	 
			// ユーザ投稿一覧表示
	        getTargetPost();
        },
        error: function(error) {
            showPopup(messagePostDetail.postDetail + messageStatusSignOut.messageCommon); 
        }
    });
    // ポップアップ表示
    function showPopup(message) {
        $('#popupMessage').text(message);
        $('#popupOverlay').fadeIn();
        $('#popup').fadeIn();
    }
	
    // サインインボタンのクリックイベント
    $('#signInButton').on('click',function() {
	    window.location.href = '/sign-in';
    });
	
    // キャンセルボタンのクリックイベント
    $('#cancelButton').on('click',function() {
	    window.location.href = '/home';
    });
	
}

// 日付フォーマット関数
function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

function getTargetPost(){
	// URLからpostIdを取得
    var postId = new URLSearchParams(window.location.search).get('postId');
	
	    $.ajax({
        url: '/api/getTargetPost',
        type: 'POST',
        contentType: 'application/json',
        data: postId,
        success: function(response) {
			$('#post-container').empty();
			const formattedDate = formatDate(response.post.updateAt);
			var postHTML = `
                <div class="post">
                    <div class="post-header">
                        <span class="post-user">投稿者：${response.userName}</span>
                        <span class="post-date">投稿日：${formattedDate}</span>
                    </div>
                    <h2 class="post-title">タイトル：${response.post.title}</h2>
                    <p class="post-content">内容：${response.post.content}</p>
                </div>`;
			$('#post-container').append(postHTML);
		},
        error: function(error) {
            console.error('エラー:', error);
            alert('エラーが発生しました。');
        }
    });
}

  /* 初期化 */
 const init = function(){
	postDetailDisp();
 }
 
 $(init);