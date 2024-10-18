package com.example.ch.common;

import org.springframework.stereotype.Component;

@Component
public class ChUtil {

	/* 処理結果 */
	public final int SUCCESS   = 0;
	public final int FAILURE_1 = 1;
	public final int FAILURE_2 = 2;
	
	/* エラーメッセージ */
	public final String ERROR = "{0}に失敗しました。";
	public final String ERROR_DB = "データベース処理エラーにより{0}に失敗しました。";
	public final String ERROR_REGISTERED = "ユーザID又はemailは既に使用されています。";
	public final String ERROR_NOT_EXISTS = "入力されたユーザID又はemailは存在しません。";
	public final String ERROR_PASS_WRONG = "入力されたパスワードは間違っています。";
	
	/* NewPostServiceで使用 */
	public final String NO_REMAINDER = "余りがありません";
	public final String CAN_NOT_POST = "これ以上投稿できません";
	
	/* HomeControllerで使用 */
	public final String IS_LOGGEDOUT = "最新5投稿を表示\n他にも投稿を見たい場合はサインインしてください";
}
