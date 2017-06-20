# CouponOrganizer
This is an extrememly lightweight program which runs on a simple webserver and allows for a user to upload coupons and sort them by expiration date, merchant, etc. Instead of keeping all of your coupons in your email account, now you can organize them online!

This system is unique because it does not use any database systems to keep track of the coupons and their relevant information. Rather, when you upload a coupon through the web interface, the file name is changed to reflect the information that you input about the merchant, expiration date, etc. This allows for an extremely lightweight interface that only requires a PHP capable webserver.

## Installation process
Download the repo. Move the contents to your webserver's wwwroot location. Create a folder called "uploads" in the root directory (the same directory as all of the PHP files). Navigate to the index.php file through your favorite web browser. Enjoy!

## Compatibility
I've successfully used this program on the following webservers:
- Microsoft IIS 7.5
- Apache (unsure of version, will update later)

#### To do
- ~~Reenable the sorting functionality~~
- Create garbageCollection function that erases all coupons which have expired
- ~~Change the listing page so that links are displayed rather than static text (this will allow coupons to be downloaded from the interface)~~
