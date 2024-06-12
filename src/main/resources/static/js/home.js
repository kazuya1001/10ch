/**
 * ホーム機能
 */

 // ホーム画面表示
 function homeDisp(){
	 
	 // ホームテーブル表示
	 getHomeTable();
	
 }
 
function getHomeTable(){
	$.ajax({
	// HTTP POST送信
	url: '/api/getHomePost',
	type: 'post',
	success: function(response) {
		
	},
	error: function(error) {
		console.error('Error:', error);
		$('#error-message').text('サーバーエラーが発生しました。').show();
	}
	 })
 }
 
 /* 初期化 */
 const init = function(){
	 homeDisp();
 }
 
 $(init);