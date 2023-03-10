# Automatic Irrigation System


As a irrigation system which helps the automatic irrigation of agricultural lands without human intervention, system has to
be designed to fulfil the requirement of maintaining and configuring the plots of land by the irrigation time slots and the
amount of water required for each irrigation period.
The irrigation system should have integration interface with a sensor device to direct letting the sensor to irrigate based on
the configured time slots/amount of water.


## Instructions
To compile

```
mvn clean compile
```

To compile (also runs unit tests)

```
mvn package
```

## Database
```
This system uses MongoDB as the primary database. For MongoDB we have used MongoDB Atlas free tier with public access.
```

## Alert
```
The mail server configuration needs to be updated to send alert to system admin when irrigation controller service is not available
```

## To build
```
mvn clean compile
```

## To run it in local

```
mvn spring-boot:run
```

....and navigate your browser to  http://localhost:8080/

To  add plot post request to http://localhost:8080/api/v1/plot

```JSON
{
    "code": "73732823y723899",
    "area": 2000,
    "plotType": "Sandy"
}
```

To  edit plot put request to http://localhost:8080/api/v1/plot/{plotId}

```JSON
{
    "code": "73732823y723899",
    "area": 2000,
    "plotType": "Clay Soil"
}
```

To  edit configure plot post request to http://localhost:8080/api/v1/plot/{plotId}/configure

```JSON
{
    "deviceName": "Sensie153",
    "timeSlot":"2022-08-10T22:09:32.000+00:00",
    "durationInMinutes": 3600,
    "intervalInDays": 4,
    "amountOfWater": 2000
}
```

To  get plot details navigate to http://localhost:8080/api/v1/plot/
```JSON
[
    {
        "id": 1,
        "code": "29388933",
        "plotType": "Clay Soil",
        "area": 2000.0,
        "plotConfigurations": [
            {
                "id": 1,
                "deviceName": "Sensie123",
                "timeSlot": "2022-08-10T23:45:32.000+00:00",
                "intervalInDays": 4,
                "durationInMinutes": 3600,
                "amountOfWater": 2000,
                "nextTimeSlot": "2022-12-14T23:45:32.000+00:00",
                "createdOn": "2022-08-10T20:46:32.000+00:00",
                "modifiedOn": null
            }
        ]
    },
    {
        "id": 2,
        "code": "7373282",
        "plotType": "Loamy",
        "area": 2000.0,
        "plotConfigurations": [
            {
                "id": 2,
                "deviceName": "Sensie153",
                "timeSlot": "2022-08-10T23:45:32.000+00:00",
                "intervalInDays": 4,
                "durationInMinutes": 3600,
                "amountOfWater": 2000,
                "nextTimeSlot": "2022-09-26T11:45:32.000+00:00",
                "createdOn": "2022-08-10T20:47:36.000+00:00",
                "modifiedOn": null
            },
            {
                "id": 3,
                "deviceName": "Sensie153",
                "timeSlot": "2022-08-10T22:09:32.000+00:00",
                "intervalInDays": 4,
                "durationInMinutes": 3600,
                "amountOfWater": 2000,
                "nextTimeSlot": "2022-08-10T22:09:32.000+00:00",
                "createdOn": "2022-08-10T21:08:48.000+00:00",
                "modifiedOn": null
            }
        ]
    },
    {
        "id": 3,
        "code": "2722782392889",
        "plotType": "sandy soil",
        "area": 23348.0,
        "plotConfigurations": []
    }
]
```

## Run all the test.
```
$ mvn test
```
