# JUKEBOX
------------------------------------------

Description

This project exposes a GET API that provides list of jukeboxes according to 
provided setting id and other filter options.
This Project makes use of mocked api of jukebox data and settings data.

------------------------------------------

API Spec
Request Params -> 
1. settingId
2. model(Optional)
3. offset(Optional)
4. limit(Optional)

Endpoint to get all supported jukeboxes is :
"jukeBoxes/getSupportedJukeBoxes"

Curl of the API ->

curl --location --request GET 'localhost:8080/jukeBoxes/getSupportedJukeBoxes?settingId=2321763c-8e06-4a31-873d-0b5dac2436da' \
--header 'ACCESS_TOKEN: apiToken'

-------------------------------------------

#Authentication Headers

{
    ACCESS_TOKEN:"apiToken"
                                }
                                
------------------------------------------------

# Dockerization

The project has been dockerized and image is available on docker hub
The image can be accessed by the below given command

docker pull singhmehtab57/jukebox-image:0.1
