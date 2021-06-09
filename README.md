# OneStep


Welcome to OneStep interview take-home project. You will be given a coding task to complete that mimics (copies) our day to day coding process. 

You will receive:
Zeplin designs of the task
Clear instructions of what’s expected of the task
Slack communication with the developers you’re most likely to work with
All access to servers/api needed
A couple phone calls throughout the task to ask questions and communicated your thought process

What we’d like to see:
Communication - how you communicate when given a task, coding etc.
General level of coding, engineering, architecture, and mechanic
UI
Look & feel
Zeplin & integration with OneStep designer
Backend API & Entities
Thinking, creativity, resourcefulness!

You are encouraged to ask as many questions as needed and behave as you would in a normal coding work day. 

Below please find your task.

OneStep provides resources for users’ to gain insights about their gait and walk. One extremely important walk that the user records is their introductory walk, known as the Calibration Walk. For this walk we provide a summary shown only once in the app, that displays values of specific items we measure in their walk. 

We want you to create the UI for this summary. Attached is a link to the Zeplin design with all the specifications of how the summary screen should look. Due to the time constraints, we want you to ping a specific end point and use the values in the response json to create the UI.

However, other variables/metrics can affect how the UI looks. What if this is a user from the USA, they may want their result in feet or inches? What if it’s a user from Europe who wants their results in the metric system? 

So:
Create a new application in Android Studio 
Set up a way for a user to toggle/switch between Inches/Cm  - this can be a screen with two buttons or some other way for the user to signal between inches/cm
Ping the api and receive the json of the summary
Render the Calibration summary UI taking into account the user’s metric preference

Note: This project should include at least two screens, (whether that’s Activity to Activity, Activity to Fragment, Fragment to Fragment - that’s up to you, and we want to hear why you chose what you did) but there should be a way to navigate between the two screens!

Note: Ignore the info button
Note: Ignore the bubble color
Note: For simplicity, you can assume you will always get three cards in the same order, however you may receive an empty card with no value

Resources:
Zeplin
https://app.zeplin.io/project/5fb12279b6187c73e01cde9b
API
Walk with only step rate:
https://dev.takeonestep.com/api/v3/take_home_project/my_walks/c3b64b7c-caa3-444a-acda-caf9141c3b68
Walk with all params ( + insight):
https://dev.takeonestep.com/api/v3/take_home_project/my_walks/1c8d138e-e59b-4c31-a446-031896f6de31
If not value -> Not rainbow -> null
 
Contact for ongoing questions during the project:
<TBD>


Guidance:
Submit through Git 
It should take 4 -5 hours.
Please contact us with any questions during the project
You are encouraged to make your own decisions
Please use Android Studio, Kotlin and Gradle to build your project
Use any framework and libraries that you are comfortable within the project


Bonus:
Suggest or implement approach for “share”
Suggest or implement approach for caching / local DB

Explanation of API entities:

Walk metadata:
UUID
Title
Steps

Walk Summary:
UUID
Title
Timestamp
Steps
Duration
List of parameter cards
Optional Insight card

Summary Card:

	
Stat ID
Title
Subtitle
Rainbow - or null


Rainbow widget:
Image Asset [str]
Start [int]
End [int]
Value [int or null]
Units [str or null]

Optional Insight Card:

Title
Body



Questions (Discussion in the end):
Did you add or use any libraries? If so, why and what did you use them for?
Which parts did you spend the most time with? What did you find most difficult?
Which parts are you most proud of? And why?
How would you improve the OneStep APIs that you just used?
How would you improve the OneStep design that you just used?
How would you improve performance or loading time or experience?

  
