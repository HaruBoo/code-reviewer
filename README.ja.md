[English](README.md)

# code-reviewer

> AIコードレビューCLIツール — code-reviewerシリーズの原点

## 概要

code-reviewerシリーズの第一作。Javaの標準ライブラリのみで実装 — Anthropic SDK以外のフレームワーク・依存関係ゼロ。

このプロジェクトはその後、[code-reviewer-py](https://github.com/HaruBoo/code-reviewer-py)（Python + FastAPI + React）へと発展した。

## 機能

- 4つのレビュー観点：総合 / セキュリティ / パフォーマンス / 可読性
- コマンドライン引数によるファイル読み込み
- エラーハンドリング：引数なし・ファイル未存在・APIエラー
- Anthropic SDK以外の依存関係ゼロ

## 使い方
```bash
java Main <レビュー対象ファイル> [レビュー観点]

# 例
java Main Sample.java security
```

## 技術スタック

- Java 21
- Anthropic Claude API
- 標準ライブラリのみ（HttpClient、Files API）

## なぜこれが重要か

フレームワークなしで純粋なJavaでHTTP通信・JSONパース・ファイルI/Oを実装することで、フレームワークが抽象化している基礎を理解していることを示している。

## 関連プロジェクト

- [code-reviewer-py](https://github.com/HaruBoo/code-reviewer-py) — Python + FastAPI + React への発展版

## 作者

[HaruBoo](https://github.com/HaruBoo) — 東京在住、AIエンジニア志望。Claude APIを使った開発ツールを作っています。