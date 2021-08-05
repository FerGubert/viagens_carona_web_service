## Introduction

Distributed application with client server architecture simulating a hitchhiking application where interested passengers and drivers can register their trips and receive notifications in case there is a change in any event of their interest. 
To provide communication between processes, Web Services Rest is used with the Jersey implementation. For sending event notifications, the SSE (Server-Sent Events) is used.

## Folder Structure

The workspace contains two applications, where:

- `ClienteViagensCarona`: client application where passengers and drivers can register their interests.
- `ServidorViagensCarona`: server application that handles requests and sends notifications.

