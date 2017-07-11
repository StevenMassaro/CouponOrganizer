# CouponOrganizer
This is an extrememly lightweight program which runs on a simple webserver and allows for a user to upload coupons and sort them by expiration date, merchant, etc. Instead of keeping all of your coupons in your email account, now you can organize them online!

This system is unique because it does not use any database systems to keep track of the coupons and their relevant information. Rather, when you upload a coupon through the web interface, the file name is changed to reflect the information that you input about the merchant, expiration date, etc. This allows for an extremely lightweight interface that only requires a PHP capable webserver.

View a simple, non interactive version of the <a target="_blank" href="https://stevenmassaro.github.io/CouponOrganizer/SampleSite/index.html">coupon organizer here</a>.
  (Note that uploading the site to a PHP capable webserver is crucial to allow the site to function as designed, this is merely a sample site to show the basic functionality).

## Advantages
- No SQL or any type of database needed
- Connects to Cronofy, which allows events to be automatically created in your calendar (Outlook, Google, etc) that correspond to the expiration dates of the coupons (basic functionality working now, still a work in progress)
- Extremely lightweight, unless the website is being accessed there is nothing running on the server
  - Entire codebase is less than 20 KB
  - Expired coupons are only erased when the coupon listing page is loaded, there is no background service that runs constantly on the server
  - No external JavaScript is loaded (no JQuery)
  
## Disadvantages
- Currently, the percent symbol (%) is not preserved in the filenames and thus a coupon that offers `5% off` will be displayed as `5 off`.


## Installation process
Download the repo. Move the contents to your webserver's wwwroot location. Create a folder called "uploads" in the root directory (the same directory as all of the PHP files). Navigate to the index.php file through your favorite web browser. Enjoy!

*Cronofy setup:*
Create Cronofy account and generate a bearerToken (API key). Create a file in the root directory called "\_creds.php". Store your bearerToken in that file as shown below:
```
<?php
$bearerToken = "paste token here";
?>
```

## Compatibility
I've successfully used this program on the following webservers:
- Microsoft IIS 7.5
- Apache (unsure of version, will update later)
---
#### To do
- ~~Reenable the sorting functionality~~
- ~~Create garbageCollection function that erases all coupons which have expired~~
- ~~Change the listing page so that links are displayed rather than static text (this will allow coupons to be downloaded from the interface)~~
- Allow percent symbol (%) to be displayed and stored in file name
- ~~Create sample site and load to Github~~
- ~~Open Coupons in iframe on coupon listing page~~
- ~~Implement Google Calendar API so events are automatically created in calendar to correspond with expiration dates~~
- ~~Deleted coupons delete corresponding google calendar event~~
- ~~Update calendar description to correspond to the description of the coupon~~
