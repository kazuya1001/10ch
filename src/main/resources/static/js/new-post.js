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
            window.location.href = '/sign-in';
        }
    });
    
	// 投稿ボタン押下時
	$('#new-post-button').on('click', function() {
		const title = $('#title').val().trim();
		const content = $('#content').val().trim();

		$('#error-message').empty(); // エラーメッセージエリアをクリア

		// 入力チェック
        let errorMessage = '';
        if (!title) {
            errorMessage += 'タイトルを入力してください';
        }
        if (!content) {
			if(errorMessage){
				errorMessage = 'タイトルと';
			}
            errorMessage += '内容を入力してください';
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
			},
			error: function(error) {
				console.error('Error:', error);
				$('#error-message').text('サーバーエラーが発生しました。').show();
			}
		});
 	});
 });