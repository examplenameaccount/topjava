# REST API

The REST API to the app is described below.

## Get list of Meals

### Request

`GET /rest/meals/`

    curl -X GET http://localhost:8079/rest/meals

### Response

        Status = 200
        Error message = null
        Headers = [Content-Type:"application/json;charset=UTF-8"]
        Content type = application/json
        Body = [{"id":100008,"dateTime":"2015-05-31T20:00:00","description":"Ужин","calories":510,"excess":true},{"id":100007,"dateTime":"2015-05-31T13:00:00","description":"Обед","calories":1000,"excess":true},{"id":100006,"dateTime":"2015-05-31T10:00:00","description":"Завтрак","calories":500,"excess":true},{"id":100005,"dateTime":"2015-05-31T00:00:00","description":"Еда на граничное значение","calories":100,"excess":true},{"id":100004,"dateTime":"2015-05-30T20:00:00","description":"Ужин","calories":500,"excess":false},{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"excess":false},{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]
        Forwarded URL = null
        Redirected URL = null
        Cookies = []

## Get list of Meals with filter by date/time

### Request

`GET /rest/meals/filter?startDate=&endDate=&startTime=&endtime=`

    curl -X GET "http://localhost:8079/rest/meals/filter?startDate=2015-05-30&endDate=2015-05-31&startTime=00:00&endTime=20:00

### Response

        Status = 200
        Error message = null
        Headers = [Content-Type:"application/json;charset=UTF-8"]
        Content type = application/json
        Body = [{"id":100008,"dateTime":"2015-05-31T20:00:00","description":"Ужин","calories":510,"excess":true},{"id":100007,"dateTime":"2015-05-31T13:00:00","description":"Обед","calories":1000,"excess":true},{"id":100006,"dateTime":"2015-05-31T10:00:00","description":"Завтрак","calories":500,"excess":true},{"id":100005,"dateTime":"2015-05-31T00:00:00","description":"Еда на граничное значение","calories":100,"excess":true},{"id":100004,"dateTime":"2015-05-30T20:00:00","description":"Ужин","calories":500,"excess":false},{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"excess":false},{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]
        Forwarded URL = null
        Redirected URL = null
        Cookies = []

## Create a new Meal

### Request

`POST /rest/meals/`

    curl -X POST -H "Content-Type: application/json" -d "{\"dateTime\":\"2018-07-01T18:00:00\",\"description\":\"Description\",\"calories\":300}" http://localhost:8079/rest/meals

### Response

    Status = 201
    Error message = null
    Headers = [Location:"http://localhost/rest/meals/100011", Content-Type:"application/json;charset=UTF-8"]
    Content type = application/json
    Body = {"id":100011,"dateTime":"2015-06-01T18:00:00","description":"Созданный ужин","calories":300,"user":null}
    Forwarded URL = null
    Redirected URL = http://localhost/rest/meals/100011
    Cookies = []

## Get a specific Meal

### Request

`GET /meal/{id}`

    curl -X GET http://localhost:8079/rest/meals/100002

### Response

    Status = 200
    Error message = null
    Headers = [Content-Type:"application/json;charset=UTF-8"]
    Content type = application/json
    Body = {"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"user":null}
    Forwarded URL = null
    Redirected URL = null
    Cookies = []

## Update Meal

### Request

`PUT /rest/meals/{id}`

    curl -X PUT -H "Content-Type: application/json" -d "{ \"id\": 100002, \"dateTime\": \"2015-05-30T10:00:00\", \"description\": \"Update Meal\", \"calories\": 300 }" http://localhost:8079/rest/meals/100002

### Response

    Status = 204
    Error message = null
    Headers = []
    Content type = null
    Body = 
    Forwarded URL = null
    Redirected URL = null
    Cookies = []

## Delete Meal 

### Request

`DELETE /rest/meals/{id}`

    curl -X DELETE http://localhost:8079/rest/meals/100002

### Response

    Status = 204
    Error message = null
    Headers = []
    Content type = null
    Body = 
    Forwarded URL = null
    Redirected URL = null
    Cookies = []