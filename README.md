# Logfile Parser

This program parses a logfile and displays information about the resources with the highest average request duration as well as a histogram of the hourly requests.

## Installation

Run "gradle build" from the command line in the project root folder. This will create a subfolder "dist" which includes the executable jar file.

## How to use this program

* Copy a log file into the dist folder
* Start the program from the command line in the dist folder: "java -jar assignment.jar [logfile] [number]"
* The logfile argument needs to specify the whole name of the logfile, e.g. timing.log
* The number provided limits the output of the top resources with the highest average request duration
* Alternatively, run "java -jar assignment.jar -h" for instructions in the command line

## Limitations

This program parses a logfile with entries that adhere to one of the following two formats:
* 7 parts (date, timestamp, thread-id, user context, URI + query string, string "in", request duration in ms), separated by space
* 8 parts (date, timestamp, thread-id, user context, resource name, data payload, string "in", request duration in ms), separated by space
