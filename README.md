# Capstone-Project-2

##Price Cutz

###Description

This app allows consumers and business owners connect through the available offers, coupons, discounts and deals.

This application provides a platform where business owners can create and manage the presentation of their deals to potential customers.

The app presents these coupons/deals to customers based on any combination of the following:
* User interests
* Locations based alerts
* Custom search

The webserver that supports the application will be a Google App Engine instance.

Features such as barcode scanning provide secure coupon redemption.

###Intended User

This application is for anyone that is interested in maximizing his/her savings opportunity by optimally accessing available cost-saving deals.
Also, business owners who are interested in getting more patronage by offering potential customers deals.
Features

List the main features of your app. For example:
* Deals by location
* Deals by filter
* Custom deal search
* Barcode scanning verification
* Coupon management for Business Owners

###Key Considerations

####Data Persistence

An SQLite library will be used to persist user preference data
However, standing data (coupons created) and transaction history (coupon usage) will be backed up on Google cloud (datastore) as well as on individual user devices.

####Corner Cases

There are 2 main corner cases;

* No location data: The application will be set to use GPS and well as Network location providers, however, if none of these are available, location feature will be disabled in the application without errors.
* No network access: If there are no network access during image requests, placeholder images will be used, however, Glide library also provide cache images when image is being reused

####Libraries in use

* Glide for image loading
* Crashlytics to monitor runtime errors on user devices
* Gson for easy serialization of objects to and from JSON
* Play Services for maps, location, ads and mobile vision (Barcode)
* Design Support Library
* Facebook SDK for Facebook login

####Implementation of Google Play Services.

* Location & Maps - this will be used to visually represent discounts in the area
* Ads - the app will have a free and a paid version
* Mobile Vision - (some) discounts can be redeemed using QR codes generated for users