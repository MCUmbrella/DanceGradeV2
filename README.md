DanceGradeV2 is the back end of an academic research project on dance grading based on motion sensors and artificial intelligence.

# Data objects

## DanceData
Represents the information of a student and their dance rating.

### Fields
- `id: Long` - The ID of the data in the database.
- `studentId: Long` - The student ID of the student.
- `name: String` - The name of the student.
- `actions: Integer[]` - The order of actions (the index of the first dance action is 0).
- `scores: Double[]`: - The score of each dance action.
- `scoreAvg: Double` - The average score of all acions.

### Example
```json
{
  "id": 1,
  "studentId": 19532801,
  "name": "Student Name 1",
  "actions": [0, 1, 2, 1, 2, 4, 3],
  "scores": [98.285001, 92.104919, 94.184573, 91.501711, 96.171609],
  "scoreAvg": 94.449563
}
```

## CommonMappedResult
The data returned by the server after receiving an HTTP request.

### Fields
- `code: Integer` - The status code. Used to indicate success or failure of the operation.
- `message: String` - Additional message of the operation.
- `data: Object (nullable)` - The data requested by the client, or the details of an error. Can be any type.

### Example
```json
{
  "code": 200,
  "message": "OK",
  "data": 38101472
}
```
```json
{
  "code": 201,
  "message": "Data created",
  "data": null
}
```
```json
{
  "code": 404,
  "message": "The resource you requested was not found on this server",
  "data": null
}
```
```json
{
  "code": 40003,
  "message": "Content-Type 'application/octet-stream' is not supported",
  "data": null
}
```
```json
{
  "code": 40005,
  "message": "'name' cannot be empty",
  "data": "org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<?> vip.floatationdevice.dancegrade.controller.DataWriteController.insertData(vip.floatationdevice.dancegrade.data.DanceData) with 6 errors: ..."
}
```

## SearchResult
Stores the result in a search.
Returned as CommonMappedResult's 'data' field during search operations.

### Fields
- `result: List<DanceData>` - The paged list of DanceData objects (max 10 objects per page)
- `total: Integer` - The total number of the data that matches the search criteria 

### Example
```json
{
  "result": [
    {
      "id": 2,
      "studentId": 19532802,
      "name": "Rain Silves",
      "actions": [0, 1, 2, 3, 4],
      "scores": [100.0 ,100.0, 100.0, 100.0, 100.0],
      "scoreAvg": 100.0
    }
  ],
  "total": 1
}
```

# HTTP API
Default bind address: `0.0.0.0:60080`

## Read related functions

### Get data count

**GET** `/api/dataCount`

#### Example response
```json
{
  "code": 200,
  "message": "OK",
  "data": 3
}
```

### Get data list (max 10 per page)

**GET** `/api/data?page={}`

Query parameters:
- `page`: Integer, required

#### Example
Request: `GET /api/data?page=0`<br>
Response:
```json
{
  "code": 200,
  "message": "OK",
  "data": [
    {
      "id": 0,
      "studentId": 19532801,
      "name": "Student Name 1",
      "actions": [0, 1, 2, 1, 2, 4, 3],
      "scores": [98.285001, 92.104919, 94.184573, 91.501711, 96.171609],
      "scoreAvg": 94.449563
    },
    {
      "id": 2,
      "studentId": 19532802,
      "name": "Rain Silves",
      "actions": [0, 1, 2, 3, 4],
      "scores": [100.0 ,100.0, 100.0, 100.0, 100.0],
      "scoreAvg": 100.0
    },
    {
      "id": 5,
      "studentId": 19532805,
      "name": "Nixuelle",
      "actions": [4, 1, 4, 2, 3, 1, 2, 3, 2],
      "scores": [82.180572, 80.471065, 78.587753, 88.67142, 83.160093],
      "scoreAvg": 82.6141806
    }
  ]
}
```

### Search data (max 10 per page)

**GET** `/api/data?page={}&studentId={}&name={}`

Query parameters:
- `page`: Integer, required
- `studentId`: Integer, optional*
- `name`: String, optional*

*Provide at least 1 search criteria

#### Example
Request: `GET /api/data?page=0&name=den`<br>
Response:
```json
{
  "code": 200,
  "message": "OK",
  "data": {
    "result": [
      {
        "id": 0,
        "studentId": 19532801,
        "name": "Student Name 1",
        "actions": [0, 1, 2, 1, 2, 4, 3],
        "scores": [98.285001, 92.104919, 94.184573, 91.501711, 96.171609],
        "scoreAvg": 94.449563
      }
    ],
    "total": 1
  }
}
```

### Get a single data

**GET** `/api/data/{id}`

URL parameters:
- `id`: Integer, required

#### Example
Request: `GET /api/data/5`<br>
Response:
```json
{
  "code": 200,
  "message": "OK",
  "data": {
    "id": 5,
    "studentId": 19532805,
    "name": "Nixuelle",
    "actions": [4, 1, 4, 2, 3, 1, 2, 3, 2],
    "scores": [82.180572, 80.471065, 78.587753, 88.67142, 83.160093],
    "scoreAvg": 82.6141806
  }
}
```

## Write related functions

### Insert a data into the database

**POST** `/api/data`

JSON parameters:
- `studentId`: Integer, required
- `name`: String, required
- `scores`: Double[], required
- `scoreAvg`: Double, required
- `actions`: Integer[], required

#### Example response
```json
{
  "code": 201,
  "message": "Data created",
  "data": null
}
```

### Delete a data from the database

**DELETE** `/api/data/{id}`

URL parameters:
- `id`: Integer, required

#### Example
Request: `DELETE /api/data/1`<br>
Response:
```json
{
  "code": 200,
  "message": "Data deleted",
  "data": null
}
```

# Override the default config
The default configuration doesn't meet everyone's needs. This example shows how to change the binding port to 8080, change the URL, username & password of the MySQL connection, and turn on verbose logging of MyBatis.
1. Create a new file named `application.yml` under the working directory (usually the same location as the runnable jar file).
2. Write these lines:
```yml
server:
  port: 8080
spring:
  datasource:
    url: "jdbc:mysql://node0.example.org:3306/myDatabase?user=admin&password=111111"
mybatis:
  configuration:
    log-impl: "org.apache.ibatis.logging.stdout.StdOutImpl"
```
3. Start the program and see the results.
