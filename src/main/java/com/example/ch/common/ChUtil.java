package com.example.ch.common;

import org.springframework.stereotype.Component;

@Component
public class ChUtil {

	/* 処理結果 */
	public final int SUCCESS   = 0;
	public final int FAILURE_1 = 1;
	public final int FAILURE_2 = 2;
	
	/* エラーメッセージ */
	public final String ERROR = "{0}に失敗しました";
	public final String ERROR_REGISTERED = "ユーザID又はemailは既に使用されています";
}
