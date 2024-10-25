/**
 * 投稿詳細機能
 */

let userId;
 // ユーザ詳細画面表示
 function postDetailDisp(){

	// 画面表示時にユーザ情報を取得
    $.ajax({
        url: '/api/getUserInfo',
        type: 'get',
        dataType: 'json',
        success: function(user) {
			userId = user.userId;	 
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
    const daysOfWeek = ["日", "月", "火", "水", "木", "金", "土"];
    const dayOfWeek = daysOfWeek[date.getDay()];
    return `${year}/${month}/${day}(${dayOfWeek}) ${hours}:${minutes}:${seconds}`;
}

function getTargetPost() {
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
                        <div>投稿者：<span  class="post-user" onclick="redirectToUserDetail('${response.post.userId}')">${response.userName}</span>  さん</div>
                        <span class="post-date">投稿日：${formattedDate}</span>
                    </div>
                    <h2 class="post-title">タイトル：${response.post.title}</h2>
                    <p class="post-content">内容：${response.post.content}</p>
                </div>`;
			$('#post-container').append(postHTML);
			
			// コメント件数取得
			const commentCount = response.commentList.length;
			$('#comment-count').text(commentCount);
		    response.commentList.forEach(function(post, index) {
				const formattedDate = formatDate(post.createdAt);
                var commentsHTML = `
                    <div class = "comments">
                        <div class="comment-header">
                            <div class="comment-header-top">Res.${index+1}  <span class="user-name-comment" onclick="redirectToUserDetail('${post.userId}')">${response.userNameListByPostComment[index]}</span> さん</div>
                            <div class="comment-header-bottom">${formattedDate}</div>
                        </div>
                        <div class="content">
                            <p class="comment-content">${post.content}</p>
                        </div>
                    </div>`;
                $('#comments-container').append(commentsHTML);
			});
		},
		error: function(error) {
			console.error('エラー:', error);
			alert('エラーが発生しました。');
		}
	});
}

// ユーザ詳細画面に遷移
function redirectToUserDetail(userId) {
    window.location.href = `/user-detail?userId=${userId}`;
}

$(document).ready(function() {

	$('#submitComment').on('click', function() {
		// フォーム内のデータを取得
		const content = document.getElementById('content').value.trim();
		// エラーメッセージエリアをクリア
		$('#error-message').empty();

		// 入力チェック
		let errorMessage = '';
		if (!content) {
			errorMessage += messageList.error.E0003;
		}

		// エラーメッセージの表示と処理の中断
		if (errorMessage) {
			$('#error-message').html(errorMessage);
			return; // ここで処理を中断
		}
		
        // URLからpostIdを取得
	    var postId = new URLSearchParams(window.location.search).get('postId');

        const data = {
			content: content,
			postId: postId,
			userId: userId,
		};
		// AJAXリクエストを送信する
		$.ajax({
			type: 'POST',
			url: '/api/postComment',
			data: data,
			dataType: 'json',
			success: function(response) {
                alert('コメントが投稿されました！');
                location.reload(); // ページをリロードしてコメントを表示
			},
			error: function() {
				$('#error-message').text('サーバーエラーが発生しました。').show();
			}
		});
	});
});

$(document).ready(function() {
    // スクロールイベントを監視
    $(window).on('scroll', function() {
        // 線の位置を再取得（線の下端）
        var linePosition = $('.comments-divider').offset().top + $('.comments-divider').height();

        // 各コメントの位置を取得し、それぞれ個別にチェック
        $('.comments').each(function() {
            var commentPosition = $(this).offset().top;

            // コメントが線の上に来たら非表示、線の下に来たら再表示
            if (commentPosition < linePosition) {
                $(this).css('visibility', 'hidden'); // このコメントを非表示
            } else {
                $(this).css('visibility', 'visible'); // このコメントを表示
            }
        });
    });
});


  /* 初期化 */
 const init = function(){
	postDetailDisp();
 }
 
 $(init);