#### 日本語版
[日本語](README.ja.md)

# Code Reviewer - AI Code Review CLI Tool

A CLI tool that automatically reviews Java code using the Claude API.

## Features

- Review Java files with AI-powered analysis
- 4 review perspectives: General / Security / Performance / Readability & Naming
- Results displayed directly in terminal

## Usage
```
java Main.java <file-to-review>
```

### Example
```
$ java Main.java Sample.java

📂 File loaded: Sample.java

Select review perspective:
1. General Review
2. Security
3. Performance
4. Readability & Naming
Enter number: 1

🔍 Reviewing...

📝 Review Result:
(AI review results will be displayed)
```

## Setup

### Requirements

- Java 21 or higher
- Anthropic API Key

### API Key Configuration
```bash
export ANTHROPIC_API_KEY="your-api-key"
```

## Tech Stack

- Java (HttpClient, Files API)
- Anthropic Claude API (claude-sonnet-4-20250514)

## What I Learned

- HTTP communication in Java (HttpClient)
- REST API fundamentals (request/response)
- JSON parsing and string manipulation
- CLI tool design (argument handling, error handling)