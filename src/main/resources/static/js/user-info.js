/**
 * ユーザ更新機能
 */
// 入力チェック
$(document).ready(function(){
    // 初期表示時に入力済みフィールドの状態をチェック
    function initialCheck() {
        checkField($('#user-name'), $('#user-name-hint'), 1, 10);
        checkField($('#user-id'), $('#user-id-hint'), 3, 15, /^[a-zA-Z0-9]*$/);
        checkField($('#email'), $('#email-hint'), 0, Infinity, /^[^\s@]+@[^\s@]+\.[^\s@]+$/);
        if ($('#now-password').val() !== "") addSuccess($('#now-password'), $('#now-password-hint'));
        checkNewPassword(); 
        checkPasswordMatch();
        checkFormValidity();
    }

    // 汎用的な入力チェック
    function checkField(element, hintElement, minLength, maxLength, regex = null) {
        const value = element.val().trim();
        const isValid = value.length >= minLength && value.length <= maxLength && (regex ? regex.test(value) : true);
        if (isValid) {
            addSuccess(element, hintElement);
        } else {
            addError(element, hintElement);
        }
    }

    // ボタンの使用可否を確認
    function checkFormValidity(){
        const isUserNameValid = $('#user-name').hasClass('success');
        const isUserIdValid = $('#user-id').hasClass('success');
        const isNowPasswordEntered = $('#now-password').hasClass('success');
        const isNewPasswordValid = $('#new-password').hasClass('success');
        const isPasswordMatch = $('#con-password').hasClass('success');
        const isEmailValid = $('#email').hasClass('success');
        
        if(isUserNameValid && isUserIdValid && isNewPasswordValid　&& isPasswordMatch && isEmailValid && isNowPasswordEntered){
            $('#update-button').removeClass('disabled').prop('disabled',false);
        } else {
            $('#update-button').addClass('disabled').prop('disabled',true);
        }
    }

    // OKの場合の挙動
    function addSuccess(element, hintElement){
        element.removeClass('error').addClass('success');
        hintElement.removeClass('hint').addClass('valid');
    }

    // NGの場合の挙動
    function addError(element, hintElement){
        element.removeClass('success').addClass('error');
        hintElement.removeClass('valid').addClass('hint');
    }

    // パスワード一致チェック
    function checkPasswordMatch() {
        const password = $('#new-password').val();
        const confirmPassword = $('#con-password').val();
        if (password === confirmPassword && password.length >= 8) {
            addSuccess($('#con-password'), $('#con-password-hint'));
        } else {
            addError($('#con-password'), $('#con-password-hint'));
        }
    }

    // イベントリスナー
    $('#user-name, #user-id, #email, #now-password, #new-password, #con-password').on('input', function() {
        initialCheck();
    });
    
    // 新しいパスワードのチェック
    function checkNewPassword() {
        const value = $('#new-password').val();
        if (/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]*$/.test(value) && value.length >= 8) {
            addSuccess($('#new-password'), $('#new-password-hint'));
        } else {
            addError($('#new-password'), $('#new-password-hint'));
        }
    }
    
    $('#user-name, #user-id, #email, #now-password').on('input', function() {
        initialCheck();
    });
    $('#new-password').on('input', checkNewPassword);
    $('#con-password').on('input', checkPasswordMatch); 

    // 初期チェック
    initialCheck();
    
    $('#update-button').on('click',function() {
		if($(this).prop('disabled')){
			return;
		}
		// 入力された値を変数に格納する
		const data = {
			userName:$('#user-name').val(),
			userId:$('#user-id').val(),
			nowPass:$('#now-password').val(),
			newPass:$('#new-password').val(),
			email:$('#email').val(),
		};
		$.ajax({
		    // HTTP POST送信
			url: '/api/updateAcount',
			type: 'post',
			dataType: 'json',
			data: data,
		    success: function(response) {
				if (response.processResult === 1){
					// エラーメッセージを表示
					const errMsg = response.errMessage.replace("{0}",messageUserInfo.updateAcount);
					$('#error-message').text(errMsg + messageList.info.I0001);
					return;
				}else if (response.processResult === 2) {
                    // エラーメッセージを表示
                    $('#error-message').text(response.errMessage).show();
                    return;
                }
                alert('更新されました！');
                location.reload();
        	},
        	error: function(xhr, status, error) {
				location.reload();
				console.log("Error:", error);
				console.log("Status:", status);
				console.log("Response:", xhr.responseText);
				$('#error-message').text('エラー内容: ' + xhr.responseText).show();
				}
        });
	});
});
