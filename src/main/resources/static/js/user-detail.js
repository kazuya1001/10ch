/**
 * ユーザ詳細機能
 */

 // ユーザ詳細画面表示
 function userDetailDisp(){
	 
	 // ユーザ投稿一覧表示
	 getUserPosts();
	
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

function getUserPosts() {
    // URLからuserIdを取得
    var userId = new URLSearchParams(window.location.search).get('userId');

    $.ajax({
        url: '/api/getUserPosts',
        type: 'POST',
        contentType: 'application/json',
        data: userId,
        success: function(response) {
			var poster = $('#poster');
			var userName = response.userName + "さんの投稿一覧";
			poster.append(userName);      
            var postsContainer = $('#post-container');
            postsContainer.empty();
            response.postRecord.forEach(function(post) {
				const formattedDate = formatDate(post.updateAt);
                var postHtml = `
                    <div class="post">
                        <div class="post-header">
                            <h2>タイトル：${post.title}</h2>
                            <span class="post-date">投稿日：${formattedDate}</span>
                        </div>
                            <p class="post-content">内容：<br>${post.content}</p>
                        </div>
                    </div>`;
                postsContainer.append(postHtml);
            });
        },
        error: function(error) {
            console.error('エラー:', error);
            alert('エラーが発生しました。');
        }
    });
}


 /* 初期化 */
 const init = function(){
	 userDetailDisp();
 }
 
 $(init);