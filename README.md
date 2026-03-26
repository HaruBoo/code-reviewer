[日本語版はこちら](README.ja.md)

# code-reviewer

> Java CLI tool for AI-powered code review — the origin of the code-reviewer series

## Overview

The first project in the code-reviewer series. Built entirely in Java using only the standard library — no frameworks, no dependencies beyond the Anthropic SDK.

This project later evolved into [code-reviewer-py](https://github.com/HaruBoo/code-reviewer-py), a full-stack web app with Python + FastAPI + React.

## Features

- 4 review types: General / Security / Performance / Readability
- File reading via command-line arguments
- Error handling: missing args, file not found, API errors
- Zero dependencies beyond Anthropic SDK

## Usage
```bash
java Main <file-to-review> [review-type]

# Example
java Main Sample.java security
```

## Tech Stack

- Java 21
- Anthropic Claude API
- Standard library only (HttpClient, Files API)

## Why this matters

Building this in pure Java — handling HTTP, JSON parsing, and file I/O without frameworks — demonstrates understanding of fundamentals that frameworks abstract away.

## Related

- [code-reviewer-py](https://github.com/HaruBoo/code-reviewer-py) — Python + FastAPI + React evolution

## Author

[HaruBoo](https://github.com/HaruBoo) — Aspiring AI engineer based in Tokyo. Building developer tools with Claude API.