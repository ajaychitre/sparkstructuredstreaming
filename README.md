# Assessment Project

## Overview:
    A web conference is the live exchange and mass articulation of information among several persons and
    machines remote from one another but linked by a web communications system. Terms such as audio conferencing,
    video conferencing and teleconferencing

## Background:
    This is the dummy data of web conferences of a day. The data consist of record of all source/destination ip involved
     in the conferences in a given day.

    {
     "uid":"CFroyW1gkfoVUrbdEh", //user Id
     "source_ip":"10.61.156.51", // source ip of user
     "destination_ip":"10.35.138.15", // destination ip of web conferencing
     "protocol":"udp", // protocol
     "domain": "example.com",
     "sub_domain": "teams.example.com",
     "sub_domain_entropy": 3
     }

## ProblemStatement:
    Using any of the distributed framework (spark/flink), Query the data and output the result,
    so as to use it for further analysis by a Conference Analyst
    
    There are two problem Statements which are there in problemStatement/ directory. 
    
## Input and Output:
    Input sample data is under sample-data/input/web_conference.json. You can read the data using kafka as input source (sample code for reading/writing to kafka is already added) or else read/write from file as input source.
    
    Output the result json as kafka sink or in file under sample-data/output directory
    
## How to develop code

All contributions to this repo are done via pull requests.  The best developer flow is:

1. Fork the repo under your own github user
2. Create a branch in your fork for each development task
3. Code-Test-Repeat
4. Push to your fork's branch
5. Submit PR from this repo (not on your fork)

src/main/scala/com/cisco/assessment/app/Main.scala is the main initiation class for spark streaming job. Some other classes/objects/packages  are also created as per structure but Feel free to create/modify any as per requirement

## Language:
    Scala. A sample structure for scala is already created but feel free to modify/create any class/object and raise PR. 
    
## Test Cases:
       You can write unit test case in src/test/scala/com/cisco/assessment/StreamingAnalysisTest.scala. Feel free to modify/create any file
    
## Developer Setup

Things you will need to locally build this repo:

* java8 (1.8.0_202)
* maven (3.6.0)
Download and install Intellij IDEA Ultimate

## How to build and test

```mvn clean test``` will perform a clean build and run all tests.  ```mvn clean package``` will perform a clean build and create a .jar file under the ```target``` subdirectory.
