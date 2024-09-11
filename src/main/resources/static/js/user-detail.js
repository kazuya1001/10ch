/**
 * ユーザ詳細機能
 */

 // ユーザ詳細画面表示
 function userDetailDisp(){
	 
	 // ユーザ投稿一覧表示
	 getUserPosts();
	
 }

function getUserPosts() {
    // ユーザ詳細データを取得して表示する処理
    var userId = new URLSearchParams(window.location.search).get('userId');

    $.ajax({
        url: '/api/getUserPosts',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(userId),
        success: function(response) {
            var postsContainer = $('#user-posts');
            postsContainer.empty(); 
            response.forEach(function(post) {
                var postHtml = `
                    <div class="post">
                        <h2>${post.title}</h2>
                        <p>${post.content}</p>
                        <p>投稿日: ${post.updateAt}</p>
                    </div>`;
                postsContainer.append(postHtml);
            });
        },
        error: function(error) {
            console.error('エラー:', error);
            alert('エラーが発生しました。');
        }
    });
});


 /* 初期化 */
 const init = function(){
	 userDetailDisp();
 }
 
 $(init);