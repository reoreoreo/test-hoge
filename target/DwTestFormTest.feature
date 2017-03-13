@tag
Feature: 開発ワーキング向けにDWサンプルフォームの簡単なテストデモを行います。

  Scenario: DWサンプルフォームの登録、確認、完了の正常系テストを実施します。
    When DWサンプルフォームにアクセスします。
    Then メッセージラベルを確認する。"以下の項目を入力してください。"
    When フォームの項目を入力します。
      | 氏名   | 生年月日       | 性別 | 希望日        |
      | 山田花子 | 1985年1月23日 | 女  | 2017/03/22 |
    When 確認画面に遷移する。
    Then 確認画面の表示を確認します。
      | 氏名   | 生年月日       | 性別 | 希望日        |
      | 山田花子 | 1985年1月23日 | 女  | 2017/03/22 |
    Then メッセージラベルを確認する。"以下の内容でよろしいですか。"
    When 完了画面に遷移する。
    Then メッセージラベルを確認する。"ご登録ありがとうございました。"
    Then 登録履歴の表示を確認します。
      | 氏名   | 生年月日       | 性別 | 希望日        |
      | 山田花子 | 1985年1月23日 | 女  | 2017/03/22 |
      | 山田太郎 | 1980年12月6日 | 男  | 2017/01/22 |
      | 田中二郎 | 1986年5月11日 | 男  | 2017/08/22 |
