/**
 * サインイン機能
 */

$(document).ready(function() {
	// サインインボタン押下時
	$('#sign-in-button').on('click', function() {
		const inputValue = $('#user-id').val().trim();
		const password = $('#password').val().trim();
		let isValid = true;

		$('#error-message').empty(); // エラーメッセージエリアをクリア

		// 入力チェック
       let errorMessage = '';
        if (!inputValue) {
            errorMessage += 'ユーザIDまたはEmailを入力してください。<br>';
        }
        if (!password) {
            errorMessage += 'パスワードを入力してください。<br>';
        }

        // エラーメッセージの表示と処理の中断
        if (errorMessage) {
            $('#error-message').html(errorMessage);
            return; // ここで処理を中断
        }

		if (isValid) {
			let userId = null;
			let email =null;
			if(inputValue.includes('@')){
				email = inputValue;
			}else{
				userId = inputValue;
			}
			// 入力フィールドのチェックがすべて通った場合の処理
			const data = {
				userId: userId,
				pass: password,
				email: email
			};

			console.log(data); // デバッグ用にデータを表示

			// AJAXリクエストを送信する
			$.ajax({
				type: 'POST',
				url: '/api/signIn',
				data: data,
				dataType: 'json',
				success: function(response) {
					if (response.processResult === 1){
						const errMsg = response.errMessage.replace("{0}",messageSignIn.signIn);
						$('#error-message').text(errMsg + messageList.info.I0001);
						return;
					}else if (response.processResult === 2) {
						$('#error-message').text(response.errMessage).show();
						return;
					}
					window.location.href = '/home'; 
				},
				error: function(error) {
					console.error('Error:', error);
					$('#error-message').text('サーバーエラーが発生しました。').show();
				}
			});
		}
	});

	// パスワード表示/非表示切り替え
	$('#toggle-password').on('click', function() {
		const passwordField = $('#password');
		const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
		passwordField.attr('type', type);
		$(this).toggleClass('fa-eye-slash');
	});
});
