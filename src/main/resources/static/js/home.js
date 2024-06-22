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
		$('posts-container').empty();
		response.postRecord.forEach(function(post) {
			var cardHTML = `
				<div class="col-md-4">
					<div class="card mb-4">
						<div class="card-body">
							<h5 class="card-title">${post.title}</h5>
							<h6 class="card-subtitle mb-2 text-muted">${post.updateAt}</h6>
							<p class="card-text">By ${post.userId}</p>
						</div>
					</div>
				</div>`;
			$('#posts-container').append(cardHTML);
		});
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