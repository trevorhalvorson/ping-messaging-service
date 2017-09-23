# Ping-Messaging-Service

A web service for sending SMS messages using Twilio.

Built using the [Ktor](https://github.com/Kotlin/ktor) framework.

### Setup

Intellij:

1. *Edit configurations*
2. Set *Main class* to `org.jetbrains.ktor.netty.DevelopmentHost`
3. Set *Use classpath of module* to `ping-messaging-service_main`
4. Set *Environment variables*:
    - `PORT`: port this server will run on
    - `TWILIO_NUMBER`: a Twilio phone number messages will be sent **from**
    - `ACCOUNT_SID`: a Twilio account SID
    - `AUTH_TOKEN`: a Twilio auth token

### Testing

`PHONE_NUMBER`: a Twilio-verified phone number that can receive messages

`MESSAGE`: a message that will be sent to PHONE_NUMBER

`PORT`: the port where this server is running

Request:

`curl -H "Content-Type: application/json" -X POST -d '{"phoneNumber":"PHONE_NUMBER","message":"MESSAGE"}' http://localhost:PORT/ping`

Response:

```json
{
    "success": true,
    "message": "MESSAGE_ID"
}
```