Spring Boot Starter
========================================

This project reads files in github repository  [Spring Boot] .

Java 8 is needed to run this sample.
Spring boot 2.3.9.RELEASE

Clone
--------

```sh
git clone https://github.com/ramonrpb/repo.git
```

```
Access
--------

### Read repository - Method PUT

```
URLs
--------
http://localhost:8080/repo/git

Swagger
--------
http://localhost:8080/repo/swagger-ui.html
https://repo-rpb.herokuapp.com/repo/swagger-ui.html

Heroku
--------
https://repo-rpb.herokuapp.com/repo/git

```
### Body 

```json
{
    "url" : "https://github.com/ramonrpb/healthcare"
}
```
JSON Response:

```json
[   
    {
        "name": "MavenWrapperDownloader.java",
        "quantityLines": 117,
        "size": 4942,
        "extension": "java"
    },
    {
        "name": "HealthcareInstitutionController.java",
        "quantityLines": 60,
        "size": 2385,
        "extension": "java"
    },
    {
        "name": "ExamController.java",
        "quantityLines": 48,
        "size": 1631,
        "extension": "java"
    }
]
```

Edit
--------

Use Spring Tool Suite 4 or above.