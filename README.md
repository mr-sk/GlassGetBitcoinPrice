GlassGetBitcoinPrice
====================

GlassGetBicoinPrice (GGBP) is a simple Glass application to get the current Bitcoin trading price in dollars. 

![image](https://www.glass-community.com/t5/image/serverpage/image-id/2561i3F098FC35FF82AB9/image-size/large?v=mpbl-1&px=-1)

Introduction
------------

GGBP is a very simple Glass application that responds to a single voice trigger. It will fetch the
exchange rates from Coinbase.com using a GET request and extract the "btc_to_usd" value. 

The request is executed in an asynchronous thread and when complete executes a preset callback
which updates the prices and utilizes the Text-To-Speech (TTS) capabilies of Glass to read
the price to the user. 

Installation
------------

To build and install GlassGetBitcoinPrice, you will need the [Android SDK
tools](http://developer.android.com/sdk/index.html) and the [Glass Development
Kit](http://developers.google.com/glass/develop/gdk/index).

If you are getting an unknown error on com.google.* check that the Project->Properties
are set to use the "Glass Development Kit".

Open Eclipse/ADT and build the project to your Glass. 

Usage
-----

GGBP uses GDK voice triggers. Supported commands are:

* `get bitcoin price`

Thanks
------

I didn't get this far on my own and I want to give thanks to the following for help and code samples:
* Glass Community
* StackOverflow
* https://github.com/azharb/
* https://github.com/ayshen
* https://github.com/DasCody/
