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

## Output

The program display three things in the command line:
* The resources with the highest average request duration found in the log file
* The number of hourly requests
* The total runtime of the program

In case the log file contains entries that could not be processed (e.g. incomplete), it attempts to create a faultyLogEntries.txt file in the same folder, containing each unprocessed log entry along with a short description of the problem. In this case, information about the number of unprocessed log entries will also be displayed in the command line.

## Limitations

This program parses a logfile with entries that adhere to one of the following two formats:
* 7 parts (date, timestamp, thread-id, user context, URI + query string, string "in", request duration in ms), separated by space
* 8 parts (date, timestamp, thread-id, user context, resource name, data payload, string "in", request duration in ms), separated by space
