# Code Reviewer - AIコードレビューCLIツール

> [English](README.md)

# Code Reviewer - AIコードレビューCLIツール

JavaからClaude APIを使って、Javaコードを自動レビューするCLIツールです。

## 機能

- Javaファイルを指定してAIによるコードレビューを実行
- 4つのレビュー観点から選択可能（総合 / セキュリティ / パフォーマンス / 可読性・命名規則）
- レビュー結果をターミナルに表示

## 使い方
```
java Main.java <レビュー対象のファイル>
```

### 例
```
$ java Main.java Sample.java

📂 読み込んだファイル：Sample.java

レビュー観点を選んでください：
1. 総合レビュー
2. セキュリティ
3. パフォーマンス
4. 可読性・命名規則
番号を入力：1

🔍 レビュー中...

📝 レビュー結果：
（AIによるレビュー結果が表示されます）
```

## セットアップ

### 必要なもの

- Java 21以上
- Anthropic APIキー

### APIキーの設定
```bash
export ANTHROPIC_API_KEY="your-api-key"
```

## 技術スタック

- Java（HttpClient, Files API）
- Anthropic Claude API（claude-sonnet-4-20250514）

## 学んだこと

- JavaによるHTTP通信（HttpClient）
- REST APIの基本（リクエスト/レスポンス）
- JSONの解析と文字列操作
- CLIツールの設計（引数処理、エラーハンドリング）