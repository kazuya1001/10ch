/**
 * ホーム機能
 */

 // ホーム画面表示
 function homeDisp(){
	 
	 // ホームテーブル表示
	 getHomeTable();
	
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

function getHomeTable(){
	$.ajax({
	// HTTP POST送信
	url: '/api/getHomePost',
	type: 'post',
	success: function(response) {
		var userName = /*[[${session.user}]]*/ 'defaultUser';
		$('#posts-container').empty();
		response.postRecord.forEach(function(post, index) {
			const formattedDate = formatDate(post.updateAt);
			var postHTML = `
                <div class="post">
                    <div class="post-header">
                        <span class="post-user" onclick="redirectToUserDetail('${post.userId}')">${response.userNameRecord[index]}</span>
                        <span class="post-date">${formattedDate}</span>
                    </div>
                    <h2 class="post-title" onclick="redirectToPostDetail('${post.postId}')">タイトル：${post.title} </h2>
                    <p class="post-content">内容：${post.content}</p>
                </div>`;
			$('#posts-container').append(postHTML);
		});
	},
	error: function(error) {
		console.error('Error:', error);
		$('#error-message').text('サーバーエラーが発生しました。').show();
	}
	 });
 }
 
// ユーザ詳細画面に遷移
function redirectToUserDetail(userId) {
    window.location.href = `/user-detail?userId=${userId}`;
}

// 投稿詳細画面に遷移
function redirectToPostDetail(postId) {
	window.location.href = `/posts/post-detail?postId=${encodeURIComponent(postId)}`;
}
 
 /* 初期化 */
 const init = function(){
	 homeDisp();
 }
 
 $(init);