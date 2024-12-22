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
demo-image-manage-api> docker-compose up
```
## API Endpoints
### 4. Image Controller Endpoints
####   4.1 Add Image URL
```sh
curl --location 'localhost:8080/api/images/addImage' \
--header 'Content-Type: application/json' \
--data '{
    "url": "https://example.com/image18.jpg",
    "duration": 27
}'
```
#### Good Response:
```sh
{
    "id": 34
}
```
#### Bad Response:
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
curl --location --request DELETE 'localhost:8080/api/images/deleteImage/19'
```

####   4.3 Search Images
```sh
curl --location 'http://localhost:8080/api/images/search?keyword=image&duration=22'
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
curl --location 'localhost:8080/api/slideshows/addSlideshow' \
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
curl --location --request DELETE 'localhost:8080/api/slideshows/deleteSlideshow/12'
```
####   5.3 Get Slideshow Order
```sh
curl --location 'http://localhost:8080/api/slideshows/11/slideshowOrder'
```
####   5.4 Record Proof of Play
```sh
curl --location 'localhost:8080/api/slideshows/11/proof-of-play/6'
```

#### 
Feel free to use this `README.md` file as a guide for building, running, and testing your application, as well as for interacting with the provided API endpoints. If you need any further assistance, let me know!
