INFO
----

Core functionality is in phases 1 & 2

Consider phase 3 as the MVP. Functionality exists after Phase 2, but it's not practical to use.

Phase 1 Capabilities
----
1. **BACKEND** Read services to monitor[^phase-1-input]
2. **BACKEND** Create scheduling function[^phase-1-schedule-func]
3. **BACKEND** Create and request to URL to get it's status on the scheduled interval.[^phase-1-req]
4. **BACKEND** Receive and process response.[^phase-1-res]
5. **FRONTEND** Basic web UI[^phase-1-webui]

Phase 2 Capabilities
----
6. **BACKEND** Read database to create history graphs[^phase-2-graphs]
7. **BACKEND** Handle responses better[^phase-2-res]
8. **FRONTEND** Graphs implemented into the web UI[^phase-2-webui]

Phase 3 Capabilities
----
9. **MIDDLEWARE** Implement an API that applications can POST to with their token[^phase-3-api]
10. **FRONTEND** Implement web interface for adding services and sysadmins.[^phase-3-webui]
11. **BACKEND** Send an SMS when an event occurs.[^phase-3-sms-ping]

INFO
----

Please view the footnotes below that correspond to each number for clarification on each task.

[^phase-1-input]: In Phase 1, the monitored apps may be entered from the back end to focus on core functionality

[^phase-1-schedule-func]: The scheduling system should support intervals of: **1h**, **6h**, **12h**, and **24h**. It should be applied to each service, but for resource sake, it shouldn't continue a running clock for every monitored application. Example implementation, each application's request is fired each time the master clock reaches an interval from the time since last updated.

[^phase-1-webui]: The website should be online, serving an outline of the page

[^phase-1-req]: The request should be sent to the URL of the monitored application to find it's status. This should require NO CHANGE in the monitored application itself.

[^phase-1-res]: Parse the response to find an HTTP response object and status code. Log the response in the database. If the status code is NOT 200, send it to a second function to print "NOT 200" this function will eventually be used to alert via SMS added phone numbers[^phase-3-sms-ping]

[^phase-2-res]: Process the response to find find details such as keywords in the response object. Check the code to print in console either **SUCCESS** or **ERR >> CODE - RELAVENT INFO**

[^phase-2-graphs]: Graphs will probably be generated via a third party library. The uptime will be read from the storage mechanism.

[^phase-2-webui]: The web UI should have graphs implemented in a logical way, formatted with the title of the service above or below it.

[^phase-3-sms-ping]: SMS can be sent to phone numbers via their carrier's email service.  
T-Mobile – phonenumber@tmomail.net  
Virgin Mobile – phonenumber@vmobl.com  
AT&T – phonenumber@txt.att.net  
Sprint – phonenumber@messaging.sprintpcs.com  
Verizon – phonenumber@vtext.com  
Tracfone – phonenumber@mmst5.tracfone.com  
Ting – phonenumber@message.ting.com  
Boost Mobile – phonenumber@myboostmobile.com  
U.S. Cellular – phonenumber@email.uscc.net   
Metro PCS – phonenumber@mymetropcs.com  

[^phase-3-api]: The original/most feasible idea is sending JSON formatted incident reports from an app to the API so the monitored application doesn't need to wait for the monitor to get a response from it before sysadmins are pinged and the graphs updated. This also allows for more specific errors from a backend when the frontend responds fine. The API would require that a token is present to both link it and ensure validity of sender.

[^phase-3-webui]: Sysadmins should be able to add services and alert numbers via the web UI, perhaps with an authentication method.