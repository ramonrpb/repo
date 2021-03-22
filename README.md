Spring Boot Starter
========================================

This project reads files in github repository  [Spring Boot][spring-boot] .

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
http://localhost:8080/repo/git
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