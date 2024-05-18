/**
 * サインアップ機能
 */

// サインアップボタン押下
function clickSignUpButton(){
	
	// sign-upで入力された値を変数に格納する
	const data = {
		userName:$('#user-name').val(),
		userId:$('#user-id').val(),
		pass:$('#password').val(),
		email:$('#email').val(),
	}
	// 入力チェック
	
	$.ajax({
		// HTTP POST送信
		url: '/api/signUp',
		type: 'post',
		dataType: 'json',
		data: data,
	})
	// 通信成功
	.done(function (result){
		window.alert(通信成功);
		
	})
	.fail(function(jqXHR,textStatus,errorThrown){
		window.alert(通信失敗);
	});
}
 
 
 