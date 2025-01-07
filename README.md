# Demo Image Manage API

## Build and Run

### 1. Build with Tests
```sh
demo-image-manage-api> ./gradlew clean test
```
#### Test Report here:
```she
demo-image-manage-api\build\reports\tests\test\index.html
```

### 2. Build Application JAR File
```sh
demo-image-manage-api> ./gradlew bootJar
```
### 3. Run Application with Docker Infrastructure
```sh
demo-image-manage-api> docker-compose up --build 

```
## API Endpoints
### 4. Image Controller Endpoints
####   4.1 Add Image URL
```sh
curl --location 'localhost:8080/addImage' \
--header 'Content-Type: application/json' \
--data '{
    "url": "https://example.com/image18.jpg",
    "duration": 27
}'
```
#### Ok Response:
```sh
{
    "id": 34
}
```
#### Error Response:
```sh
[
    {
        "field": "url",
        "message": "URL does not contain a valid image format (JPEG, PNG, WEBP, etc.)"
    }
]
```
####   4.2 Delete Image
```sh
curl --location --request DELETE 'localhost:8080/deleteImage/47'
```
#### Ok Response:
```sh

```
#### Error Response:
```sh
{
    "message": "No image is found for id: 47"
}
````

####   4.3 Search Images
```sh
curl --location 'localhost:8080/images/search?keyword=example&duration=22'
```

```sh
[
    {"id":6,"url":"https://example.com/image12.jpg","duration":22,"additionDate":"2024-12-17 17:34:12.590"},
    {"id":7,"url":"https://example.com/image12.jpg","duration":22,"additionDate":"2024-12-17 18:05:56.336"},
    {"id":8,"url":"https://example.com/image12.jpg1232","duration":22,"additionDate":"2024-12-17 18:06:08.334"},
    {"id":9,"url":"https://example.com/image12.dfdf","duration":22,"additionDate":"2024-12-17 18:06:23.533"}
]
```

### 5. Slideshow Controller Endpoints
####   5.1 Add Slideshow
```sh
curl --location 'localhost:8080/addSlideshow' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Hay new list manually added na na na",
    "description": "Description of list la la la",
    "imageList": [
        {
            "id": 6,
            "url": "https://example.com/image12.jpg",
            "duration": 22,
            "additionDate": 1734449652590
        },
        {
            "id": 7,
            "url": "https://example.com/image12.jpg",
            "duration": 22,
            "additionDate": 1734451556336
        },
        {
            "id": 8,
            "url": "https://example.com/image12.jpg1232",
            "duration": 22,
            "additionDate": 1734451568334
        },
        {
            "id": 9,
            "url": "https://example.com/image12.dfdf",
            "duration": 22,
            "additionDate": 1734451583533
        }
    ]
}'
```
```sh
{
    "id": 12
}
```
####   5.2 Delete Slideshow
```sh
curl --location --request DELETE 'localhost:8080/deleteSlideshow/39'
```
####   5.3 Get Slideshow Order
```sh
curl --location 'http://localhost:8080/slideShow/11/slideshowOrder'
```
#### Ok Response:
```sh
[
    {
        "id": 6,
        "url": "https://example.com/image12.jpg",
        "duration": 22,
        "additionDate": "2024-12-17 17:34:12.590"
    },
    {
        "id": 7,
        "url": "https://example.com/image12.jpg",
        "duration": 22,
        "additionDate": "2024-12-17 18:05:56.336"
    },
    {
        "id": 8,
        "url": "https://example.com/image12.jpg1232",
        "duration": 22,
        "additionDate": "2024-12-17 18:06:08.334"
    },
    {
        "id": 9,
        "url": "https://example.com/image12.dfdf",
        "duration": 22,
        "additionDate": "2024-12-17 18:06:23.533"
    }
]
```
#### Error Response:
```sh
{
    "message": "No images is found by slideshow id: 111"
}
````
####   5.4 Record Proof of Play
```sh
curl --location 'localhost:8080/api/slideshows/11/proof-of-play/6'
```

#### 
Feel free to use this `README.md` file as a guide for building, running, and testing your application, as well as for interacting with the provided API endpoints. If you need any further assistance, let me know!
####
## Events Produced and Consumed throw Kafka 
### 6. Eliminated  events throw Kafka broker in logs:
```sh
demo-image-manage-api  | 2025-01-07 11:34:41 - initializing Kafka metrics collector                                                                                                                                                                                                                                                                                                 
demo-image-manage-api  | 2025-01-07 11:34:41 - Kafka version: 3.8.1                                                                                                                                                                                                                                                                                                                 
demo-image-manage-api  | 2025-01-07 11:34:41 - Kafka commitId: 70d6ff42debf7e17                                                                                                                                                                                                                                                                                                     
demo-image-manage-api  | 2025-01-07 11:34:41 - Kafka startTimeMs: 1736249681594                                                                                                                                                                                                                                                                                                     
demo-image-manage-api  | 2025-01-07 11:34:41 - [Producer clientId=producer-1] Cluster ID: a-Smz8s-SwC3piPDPqSrJQ                                                                                                                                                                                                                                                                    
demo-image-manage-api  | 2025-01-07 11:34:41 - Channel 'demo-image-manage-api.processImage-out-0' has 1 subscriber(s).
demo-image-manage-api  | 2025-01-07 11:34:41 - Sent image event -> processImage-out-0: ImageEventsDto(event=PROOF_OF_PLAY, id=6)                                                                                                                                                                                                                                                    
demo-image-manage-api  | 2025-01-07 11:34:41 - Received image event --> processImage-in-0: {"event":"PROOF_OF_PLAY","id":6}                                                                                                                                                                                                                                                         
demo-image-manage-api  | 2025-01-07 11:34:44 - Sent image event -> processImage-out-0: ImageEventsDto(event=ADD_SLIDESHOW, id=40)                                                                                                                                                                                                                                                   
demo-image-manage-api  | 2025-01-07 11:34:44 - Received image event --> processImage-in-0: {"event":"ADD_SLIDESHOW","id":40}
demo-image-manage-api  | 2025-01-07 11:34:49 - ResourceNotFoundException - No slideshow found: 39.                                                                                                                                                                                                                                                                                  
demo-image-manage-api  | 2025-01-07 11:34:52 - Sent image event -> processImage-out-0: ImageEventsDto(event=DELETE_SLIDESHOW, id=40)
demo-image-manage-api  | 2025-01-07 11:34:52 - Received image event --> processImage-in-0: {"event":"DELETE_SLIDESHOW","id":40}
demo-image-manage-api  | 2025-01-07 11:35:03 - Sent image event -> processImage-out-0: ImageEventsDto(event=ADD_IMAGE, id=49)
demo-image-manage-api  | 2025-01-07 11:35:03 - Received image event --> processImage-in-0: {"event":"ADD_IMAGE","id":49}
demo-image-manage-api  | 2025-01-07 11:35:08 - Sent image event -> processImage-out-0: ImageEventsDto(event=DELETE_IMAGE, id=49)                                                                                                                                                                                                                                                    
demo-image-manage-api  | 2025-01-07 11:35:08 - Received image event --> processImage-in-0: {"event":"DELETE_IMAGE","id":49}
