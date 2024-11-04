/**
 * サインアップ機能
 */
// 入力チェック
$(document).ready(function(){
	// サインアップボタン使用可否
	function checkFormValidity(){
		const isUserNameValid = $('#user-name').val().length >= 1 && $('#user-name').val().length <= 10; 
		const userIdValue = $('#user-id').val();
		const isUserIdValid = /^[a-zA-Z0-9]*$/.test(userIdValue) && $('#user-id').val().length >= 3 && $('#user-id').val().length <= 15; 
		const passwordValue = $('#password').val();
		const isPasswordValid = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]*$/.test(passwordValue) && $('#password').val().length >= 8 ; 
		const isPasswordMatch = $('#password').val() === $('#con-password').val();
		const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		const isEmailValid = emailRegex.test($('#email').val());
		if(isUserNameValid && isUserIdValid && isPasswordValid && isPasswordMatch && isEmailValid){
			$('#sign-up-button').removeClass('disabled').prop('disabled',false);
		}else{
			$('#sign-up-button').addClass('disabled').prop('disabled',true);
		}
	}
	// 入力チェックOKの場合の挙動
	function addSuccess(element,hintElement){
		element.removeClass('error').addClass('success');
		hintElement.removeClass('hint').addClass('valid');
	}
	// 入力チェックNGの場合の挙動
	function addError(element,hintElement){
		element.removeClass('success').addClass('error');
		hintElement.removeClass('valid').addClass('hint');
	}
	// ユーザーネームチェック
	$('#user-name').on('input',function(){
		const value = $(this).val();
		if(value.length >= 1 && value.length <= 10){
            addSuccess($(this), $('#user-name-hint'));
        } else {
            addError($(this), $('#user-name-hint'));
        }
		checkFormValidity();
	});
	//　ユーザーIDチェック
	$('#user-id').on('input',function(){
		const value = $(this).val();
		if(/^[a-zA-Z0-9]*$/.test(value) && value.length >= 3 && value.length <= 15){
			$(this).removeClass('error').addClass('success');
			$('#user-id-hint').removeClass('hint').addClass('valid');			
		}else{
			$(this).removeClass('success').addClass('error');
			$('#user-id-hint').removeClass('valid').addClass('hint');			
		}
		checkFormValidity();
	});
	//　パスワードチェック
	$('#password').on('input',function(){
		const value = $(this).val();
        if (/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]*$/.test(value) && value.length >= 8) {
            addSuccess($(this), $('#password-hint'));
        } else {
            addError($(this), $('#password-hint'));
        }
		checkFormValidity();
	});
	//　確認用パスワードチェック
	$('#con-password').on('input',function(){
		const password = $('#password').val();
		const confirmPassword = $(this).val();
		if(password === confirmPassword){
            addSuccess($(this), $('#con-password-hint'));
        } else {
            addError($(this), $('#con-password-hint'));
        }
		checkFormValidity();
	});
	//　メールアドレスチェック
	$('#email').on('input',function(){
		const value = $(this).val();
		const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if(emailRegex.test(value)){
            addSuccess($(this), $('#email-hint'));
        } else {
            addError($(this), $('#email-hint'));
        }
		checkFormValidity();
	});
	
    // パスワード表示/非表示切り替え
    $('#toggle-password').on('click', function() {
        const passwordField = $('#password');
        const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        this.classList.toggle('fa-eye-slash');
    });

    $('#toggle-con-password').on('click', function() {
        const passwordField = $('#con-password');
        const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        this.classList.toggle('fa-eye-slash');
    });
	// サインアップボタン
	$('#sign-up-button').on('click',function() {
		if($(this).prop('disabled')){
			return;
		}
		// 入力された値を変数に格納する
		const data = {
			userName:$('#user-name').val(),
			userId:$('#user-id').val(),
			pass:$('#password').val(),
			email:$('#email').val(),
		};
		$.ajax({
		// HTTP POST送信
			url: '/api/signUp',
			type: 'post',
			dataType: 'json',
			data: data,
		    success: function(response) {
                if (response.processResult === 1){
					// エラーメッセージを表示
					const errMsg = response.errMessage.replace("{0}",messageSignUp.signUp);
					$('#error-message').text(errMsg + messageList.info.I0001);
					return;
				}else if (response.processResult === 2) {
                    // エラーメッセージを表示
                    $('#error-message').text(response.errMessage).show();
                    return;
                }
            $('body').children().hide(); 
            $('body').append('<div id="success-message" style="color: green; text-align: center; margin-top: 20px;">登録が完了しました。5秒後にサインイン画面に遷移します。</div>');
            
            setTimeout(function() {
            	window.location.href = '/sign-in'; // サインイン画面のURLに遷移
            }, 5000);
        	},
            error: function(error) {
                console.error('Error:', error);
                $('#error-message').text('サーバーエラーが発生しました。').show();
            }
        });
	});	
});