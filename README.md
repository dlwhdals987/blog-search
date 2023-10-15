
## 외부 라이브러리 목록

> io.github.resilience4j:resilience4j-all	2.0.2	서킷브레이커 기능을 제공하는 라이브러리  
com.h2database:h2	2.1.214	테스트용 인메모리 데이터베이스 라이브러리  
org.apache.httpcomponents:httpclient	4.5.14	HTTP 클라이언트 기능을 제공하는 라이브러리  


## API 명세

### 블로그 검색

```
GET /api/v1/blog/search
```

> Request: 
> 
> data class BlogSearchRequest(
val query: String?="",
val page: Int?,
val sort: String?
)

> Response : 
 {
"message": "success",
"data": {
"documents": [],
"meta": {}
}
}

>documents   
blogName :: string :: 블로그 이름  
contents :: string :: 블로그 내용  
datetime :: string :: 작성 일시  
thumbnail :: string :: 블로그 썸네일 이미지 URL  
title :: string :: 블로그 제목  
url :: string :: 블로그 URL

>meta  
isEnd ::	boolean ::	마지막 페이지 여부 (true/false)  
pageableCount ::	integer	total_count :: 중 노출 가능 문서 수  
totalCount ::	integer ::	검색 결과 문서 총 개수

### 인기검색어 조회

```
GET /api/v1/blog/popular/search-keyword
```

> Response : 
{
"message": "success",
"data": [
{
"keyword": "11122",
"count": 2
}
]
}

> data  
keyword ::	String ::	검색어  
count ::	int ::	조회수

## 다운로드 및 실행

https://github.com/dlwhdals987/blog-search/blob/main/application-1.0.0-SNAPSHOT.jar

```
java -jar application-1.0.0-SNAPSHOT.jar
```

### TO_DO
동시성 이슈로 임시로 낙관적 락 사용 -> 분산처리로 동시성 이슈 보완(redis 등)

