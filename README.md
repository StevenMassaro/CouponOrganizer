# Coupon Organizer

This application serves to organize your coupons and gift cards. It integrates with Cronofy, so that expiration dates
show up automatically on your Google Calendar.

I first wrote this application when I was learning about software development in my first internship. In its first
iteration, it was written in php. I had it deployed on a cheap webserver for years, before I wanted to make changes (and
had forgotten enough php that it didn't make sense to learn it) and rewrote it in Java using Spring Boot. At the time, I
didn't really know what I was doing, and so many choices were made in the development of this program that are not in
line with standards or best practices. There are likely many security holes.

I've been using this in some form since 2017. It has flaws, it's not pretty, and it desperately needs to be updated and
rewritten. I use it seldomly, and it works well enough for my purposes that I've never gotten around to improving it,
and I probably never will. If anyone opens up any PRs, I will happily review and merge them.

**Use this at your own risk!**

Docker images available [here](https://hub.docker.com/r/stevenmassaro/coupon-organizer), for amd64 and arm64 platforms.