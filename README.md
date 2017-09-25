# Ping-Messaging-Service

A web service for sending SMS messages using Twilio.

Built using the [Ktor](https://github.com/Kotlin/ktor) framework.

### Setup

**Intellij**:

1. *Edit configurations*
2. Set *Main class* to `org.jetbrains.ktor.netty.DevelopmentHost`
3. Set *Use classpath of module* to `ping-messaging-service_main`
4. Set *Environment variables*:
    - `PORT`: port this server will run on
    - `TWILIO_NUMBER`: a Twilio phone number messages will be sent **from**
    - `ACCOUNT_SID`: a Twilio account SID
    - `AUTH_TOKEN`: a Twilio auth token
    
**Heroku**:

Set the `GRADLE_TASK` Config Variable to build the `.jar` specified in the `Procfile`:

`heroku config:set GRADLE_TASK="shadowJar"`

Add a `.env` file to the root of the project containing your environment variables (see Intellij setup for details):

```
PORT=5000
TWILIO_NUMBER="+10000000000"
ACCOUNT_SID="0"
AUTH_TOKEN="0"
```

*NOTE*: if you are deploying to heroku you must either remove `.env` from the `.gitignore` file and commit it or
add your variables to your project's Config Variables in the Heroku platform

### Testing

`PHONE_NUMBER`: a Twilio-verified phone number that can receive messages

`MESSAGE`: a message that will be sent to PHONE_NUMBER

`PORT`: the port where this server is running

**Request**:

`curl -H "Content-Type: application/json" -X POST -d '{"phoneNumber":"PHONE_NUMBER","message":"MESSAGE"}' http://localhost:PORT/ping`

**Response**:

```json
{
    "success": true,
    "message": "MESSAGE_ID"
}
```

### Clients

- [Android](https://github.com/trevorhalvorson/ping-android)