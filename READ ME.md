# SILVA'S BOOKSTORE BACKEND SYSTEM
##### This project is a backend system for "Silva's bookstore" designed to enhance the functionality of his book publication business. The system allows for the registration of authors and books, supports book search, likes, and periodically reporting of like counts for the authors.

## FEATURES
- Register authors with first name, last name, email, and contact number.
- Register books with ISBN, category, title, and author.
- Search for books using ISBN.
- Like books to maintain a like count.
- Periodic log entry containing the like count for each author every 5 minutes.
- Event logging for each action in the system.

## API Endpoints
### Register a new author
```http://localhost:8080/api/v1/author/registerAuthor```

#### BODY

```
{
    "firstName":"kumarathunga",
    "lastName":"munidasa",
    "email":"munidasa2@gmail.com",
    "contactNo":"0712345678"
}
```

#### RESPONSE

```registration successful```

### Get Author Details

```http://localhost:8080/api/v1/author/get/{authorEmail}```

#### RESPONSE

```
{
    "id": 3,
    "firstName": "kumarathunga",
    "lastName": "munidasa",
    "email": "munidasa2@gmail.com",
    "contactNo": "0712345678"
}
```

### Get Author Details

```http://localhost:8080/api/v1/author/getAll```

#### RESPONSE

```
[
    {
        "id": 1,
        "firstName": "thushara",
        "lastName": "munidasa",
        "email": "munidasa5@gmail.com",
        "contactNo": "0712345678"
    },
    {
        "id": 3,
        "firstName": "kumarathunga",
        "lastName": "munidasa",
        "email": "munidasa2@gmail.com",
        "contactNo": "0712345678"
    }
]
```


### Update Author

```http://localhost:8080/api/v1/author/update/{authorId}```

#### BODY

```
{
    "firstName":"kumarathunga",
    "lastName":"munidasa",
    "email":"munidasa2@gmail.com",
    "contactNo":"0712345678"
}
```

#### RESPONSE

```update successful```

### Delete Author
```http://localhost:8080/api/v1/author/delete/{authorEmail}```

#### RESPONSE

```deletion successful```


### Register A New Book
```http://localhost:8080/api/v1/book/register```

#### BODY

```
{
    "isbn":"12",
    "title":"helloworld",
    "category":"programming",
    "author" : {
        "id":3,
        "firstName":"kumarathunga",
        "lastName":"munidasa",
        "email":"munidasa2@gmail.com",
        "contactNo":"0712345678"
    }
}
```

#### RESPONSE

```book registered```


### Get a Book

```http://localhost:8080/api/v1/book/get/{bookId}```

#### RESPONSE

```
{
    "isbn": "12",
    "category": "programming",
    "title": "helloworld",
    "likeCount": 5,
    "author": {
        "id": 3,
        "firstName": "kumarathunga",
        "lastName": "munidasa",
        "email": "munidasa2@gmail.com",
        "contactNo": "0712345678"
    }
}
```


### Search Books

```http://localhost:8080/api/v1/book/search/{bookId}```


#### RESPONSE

```
[
    {
        "isbn": "12",
        "category": "programming",
        "title": "helloworld",
        "likeCount": 5,
        "author": {
            "id": 3,
            "firstName": "kumarathunga",
            "lastName": "munidasa",
            "email": "munidasa2@gmail.com",
            "contactNo": "0712345678"
        }
    },
    {
        "isbn": "125",
        "category": "programming",
        "title": "helloworld",
        "likeCount": 0,
        "author": {
            "id": 3,
            "firstName": "kumarathunga",
            "lastName": "munidasa",
            "email": "munidasa2@gmail.com",
            "contactNo": "0712345678"
        }
    }
]
```

### Update a Book
```http://localhost:8080/api/v1/book/update```

#### BODY

```
{
    "isbn":"125",
    "title":"helloworld",
    "category":"programming",
    "author" : {
        "id":3,
        "firstName":"kumarathunga",
        "lastName":"munidasa",
        "email":"munidasa2@gmail.com",
        "contactNo":"0712345678"
    }
}
```

#### RESPONSE

```book updated```


### Delete a Book
```http://localhost:8080/api/v1/book/delete/{bookId}```

#### RESPONSE

```book deleted```

### Like a Book
```http://localhost:8080/api/v1/book/like/{bookId}```

#### RESPONSE

```book liked```