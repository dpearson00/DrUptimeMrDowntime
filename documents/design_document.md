# Dr Uptime and Mr Downtime Design Document

## *DUMD Server Monitor* Design

## 1. Problem Statement

Dr.Uptime & Mr.Downtime is a real-time server monitoring application. It allows system administrators to keep track of the status of their servers.  It is designed to monitor the client's application and will notify you of major issues via SMS/Email, provide list of logs on information on errors, and give the statistics on server uptime/downtime.


## 2. Top Questions to Resolve in Review

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.*

1.   We are have some blockers with the Swagger documentation.
2.   We are trying to decided what is the best method for displaying a graph with the history of the uptime/downtime of a clients server. We have talked about rendering a graph on the server and sending a .img to the frontend or sending the data and rendering the graph using js/html/css.
3.   We aren't exactly sure what kind of data we want to collect and store.

## 3. Use Cases

*This is where we work backwards from the customer and define what our customers
would like to do (and why). You may also include use cases for yourselves, or
for the organization providing the product to customers.*

U1. As a dumd customer, I want to be logged in and at my dashboard to my profile when I enter my credentials

U2. As a dumd customer, I want to view my server information when I click on the tiled cards
    
U3. As a dumd customer, I want to view my account information when I click on access my account

U4. As a dumd customer, I want to edit my account information when I click on access my account

U5. As a dumd customer, I want to log out when I click the log out button

## 4. Project Scope

*Clarify which parts of the problem you intend to solve. It helps reviewers know
what questions to ask to make sure you are solving for what you say and stops
discussions from getting sidetracked by aspects you do not intend to handle in
your design.*

The application is trying to collect relevant data and present this information efficiently. The relevant data in this case is the server health (uptime/downtime) which includes an abritrary amount of time of the server's health. 

### 4.1. In Scope

*Which parts of the problem defined in Sections 1 and 2 will you solve with this
design?*

- Creating a plug-and-play B2B solution for server monitoring.

### 4.2. Out of Scope

*Based on your problem description in Sections 1 and 2, are there any aspects
you are not planning to solve? Do potential expansions or related problems occur
to you that you want to explicitly say you are not worrying about now? Feel free
to put anything here that you think your team can't accomplish in the unit, but
would love to do with more time.*

- Fixing a client's server issues
- Multiple user's per account with different permissions
- Different tiers of accounts
- Dedicated mobile application

# 5. Proposed Architecture Overview

*Describe broadly how you are proposing to solve for the requirements you
described in Section 2.*

*This may include class diagram(s) showing what components you are planning to
build.*

*You should argue why this architecture (organization of components) is
reasonable. That is, why it represents a good data flow and a good separation of
concerns. Where applicable, argue why this architecture satisfies the stated
requirements.*

This initial iteration will provide the minimum lovable product (MLP) including creating, retrieving, and updating server error logs as well as adding to and retrieving a saved list of logs.

We will use API Gateway and Lambda to create six endpoints (CreateAccount, LoginUser, AddNewApp, DeleteApp, GetUserApps, and GetAppDetails) that will handle the creation, update, deletion and retrieval of user accounts and apps to be monitored.

We will store there server status history in a table in DynamoDB. Server logs themselves will also be stored in DynamoDB. For simpler log list retrieval, we will store the list of logs in a given playlist directly in the server history table.

DUMD Monitor Service will also provide a web interface for users to manage their monitored applications. A user's dashboard provides a list view of all of their applications.

# 6. API

## 6.1. Public Models

*Define the data models your service will expose in its responses via your
*`-Model`* package. These will be equivalent to the *`PlaylistModel`* and
*`SongModel`* from the Unit 3 project.*

// UserModel

String userId;  
String name;  
String email;  
String hashedPassword;  
String phoneNumber;  
List<String> appIds;  


// ApplicatonModel

String appId;  
String name;  
String description;  
String appUrl; // or IP address  
String userId;  
List<String> serverHistoryId  

// ServerHistoryModel

String serverHistoryId;  
String appId;  
Map<String, String> errorLogs; //<Timestamp, ErrorStatus>  

*Describe the behavior of the first endpoint you will build into your service
API. This should include what data it requires, what data it returns, and how it
will handle any known failure cases. You should also include a sequence diagram
showing how a user interaction goes from user to website to service to database,
and back. This first endpoint can serve as a template for subsequent endpoints.
(If there is a significant difference on a subsequent endpoint, review that with
your team before building it!)*

*(You should have a separate section for each of the endpoints you are expecting
to build...)*

*(repeat, but you can use shorthand here, indicating what is different, likely
primarily the data in/out and error conditions. If the sequence diagram is
nearly identical, you can say in a few words how it is the same/different from
the first endpoint)*

## 6.2. Create Account Endpoint

- Accepts `POST` requests to `/users/create`
- Accepts data to create a new user with provided name, email, and password. Returns 200 status and `{"1": "SUCCESS"}`.
- A userId is generated and stored in the users table
- Invalid create account request will return a 400 status and `{"1": "FAILED"}`

## 6.3 Login User Endpoint

- Accepts a `POST` request to `/users/auth`
- Accepts user's email and password and returns a 200 status and `{“1”: "SUCCESS”, “2”: “{name}”, "3": "{userId}"}`.
- An Invalid user request returns a 400 status and `{"1": "FAILED"}`

## 6.4 Get User Apps Endpoint

- Accepts a `GET` request to `/users/{userId}/apps`
- Accepts a userId and returns the list of apps `{1: ["{site1}", "{site2}", "{etc}"], 2: [{id1}, {id2}, {etc}]}`.
- An Invalid user request returns a 400 status and `{"1": "FAILED"}`

## 6.5 Get App Details Endpoint

- Accepts a `GET` request to `/apps/{userId}/view/{appId}`
- Accepts a userId and appId and returns the app details `{1: "{appName}", 2: "{appDescription}", 3: "{appOwner}"}`.
- An Invalid user request returns a 400 status and `{"1": "FAILED"}`

## 6.6 Add New App Endpoint

- Accepts `POST` request to `/apps/{userId}/create`
- Accepts data to create a new app with app name, description, userId, url. Returns 200 status and `{"1": "SUCCESS"}`
- An appId will be generated and stored in applications table
- An Invalid user request returns a 400 status and `{"1": "FAILED"}`

## 6.7 Delete App Endpoint

- Accepts `GET` request to `/apps/{userId}/delete/{appId}`
- Accepts the userId and appId and returns 200 status and `{"1": "SUCCESS"}`
- An Invalid user request returns a 400 status and `{"1": "FAILED"}`

# 7. Tables

*Define the DynamoDB tables you will need for the data your service will use. It
may be helpful to first think of what objects your service will need, then
translate that to a table structure, like with the *`Playlist` POJO* versus the
`playlists` table in the Unit 3 project.*

# 7.1 `users`

userId // partition key, string  
customerName // string  
email // string  
phoneNumber // string  
hashedPassword // string  
appIds // list  

# 7.2 `applications`

appId // partition key, string  
appName // string  
description // string  
appUrl // string  
userId // string  
serverHistoryIds // list  

# 7.3 `serverHistory`

serverHistoryId // string  
appId // string  
errorsLogs // map  

# 8. Pages

*Include mock-ups of the web pages you expect to build. These can be as
sophisticated as mockups/wireframes using drawing software, or as simple as
hand-drawn pictures that represent the key customer-facing components of the
pages. It should be clear what the interactions will be on the page, especially
where customers enter and submit data. You may want to accompany the mockups
with some description of behaviors of the page (e.g. “When customer submits the
submit-dog-photo button, the customer is sent to the doggie detail page”)*

![Homepage](/documents/assets/homepage.png)
![Login](/documents/assets/login.png)