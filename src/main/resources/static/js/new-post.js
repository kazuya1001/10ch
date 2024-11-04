/**
 * 新規投稿画面
 */
$(document).ready(function() {
	let userId;
	// 画面表示時にユーザ情報を取得
    $.ajax({
        url: '/api/getUserInfo',
        type: 'get',
        dataType: 'json',
        success: function(user) {
			userId = user.userId;
        },
        error: function(error) {
            console.error('Error fetching user info:', error);
            showPopup(messageNewPost.newPost + messageStatusSignOut.messageCommon);
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
    
	// 投稿ボタン押下時
	$('#new-post-button').on('click', function() {
		const title = $('#title').val().trim();
		const content = $('#content').val().trim();

        // エラーメッセージエリアをクリア
		$('#error-message').empty();

		// 入力チェック
        let errorMessage = '';
        if (!title) {
            errorMessage += messageList.error.E0001;
        }
        if (!content) {
			if(errorMessage){
				errorMessage = 'タイトルと';
			}
            errorMessage += messageList.error.E0002;
        }
        
        // エラーメッセージの表示と処理の中断
        if (errorMessage) {
            $('#error-message').html(errorMessage);
            return; // ここで処理を中断
        }
        
		// 入力フィールドのチェックがすべて通った場合の処理
		const data = {
			title: title,
			content: content,
			userId: userId,
		};
		// AJAXリクエストを送信する
		$.ajax({
			type: 'POST',
			url: '/api/newPost',
			data: data,
			dataType: 'json',
			success: function(response) {
				if (response.processResult === 1) {
					const errMsg = response.errMessage.replace("{0}", messageNewPost.newPost);
					$('#error-message').text(errMsg + messageList.info.I0001);
					return;
				} 
				alert('投稿されました！');
                location.reload();
			},
			error: function(error) {
				console.error('Error:', error);
				$('#error-message').text('サーバーエラーが発生しました。').show();
			}
		});
 	});
 });