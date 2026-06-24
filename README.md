# Meta Post Analytics

## Description

A Spring Boot application that integrates with the Instagram Graph API and analyzes Instagram post performance.

## Features

- Fetch Instagram posts from Graph API
- Calculate Top 3 posts by engagement
- Determine the best day for likes
- Generate analytics summary

## Technologies

- Java 22
- Spring Boot 3
- Maven
- Instagram Graph API
- REST API

## API Endpoints

### Get Instagram Posts

```http
GET /posts
```

### Get Analytics

```http
GET /api/analytics
```

## Environment Variables

The application requires:

```text
META_ACCESS_TOKEN=<your_token>
```

## Example Response

```json
{
  "topPosts": [
    {
      "postId": "17935229036199516",
      "message": "Environment",
      "engagement": 31
    }
  ],
  "bestDayForLikes": "FRIDAY",
  "summary": "Top 3 posts are calculated based on likes + comments."
}
```