# COP 2006: Intro to Programing
#### FGCU, Spring 2020
## Course Description:
Introductory computer programming concepts and problem-solving skills are learned using a modern programming language. Principles of good programming style are emphasized.
## Learning Objectives:
The student will learn fundamental-to-intermediate programming concepts, including data types, expressions, classes, arrays, methods, conditional statements, loops, and input/output, with application development using the Java object-oriented programming language. Essential skills to decompose, design, and code basic control structures and program modules will also be taught. Specifically, the student will acquire the following outcomes: 
 1. Demonstrated an understanding of the domain and principles of high-level programming languages. 
 2. Demonstrated an understanding of the programming life cycle, including program development and maintenance/evolution. 
 3. Demonstrated the ability to compose programs from requirements expressed in natural language, mathematical notation, or algorithmic description. 
 4. Demonstrated an understanding of fundamental programming principles, problem solving, and critical thinking. 
 5. Demonstrated an understanding of the syntax and semantics of the basic language constructs, including variables, data types, conditional statements, and iterative constructs. 
 6. Demonstrate an understanding of the procedural abstraction and parameter-passing mechanisms. 
 7. Demonstrated the ability to develop programs in multiple modules using sound principles, e.g., multiple functions or procedures, and classes or files, as pertaining to development of scalable programs, and to promote reusability and maintainability. 1 A class hour at FGCU is typically 50 minutes to 1 hour and 15 minutes. 
 8. Demonstrated the ability to develop programs that use persistent storage (e.g., file I/O), linear data structures (e.g., arrays), and error checking (e.g., exceptional flow). 
 9. Demonstrated an understanding of the use of programming styles and documentation, as pertaining to software quality. 
 10. Demonstrated the usage of software tool support in the programming process, including composing source code, compilation, testing, debugging, and execution.
## Assignments
 ### EVCharging
 Each vehicle has a battery  _capacity_  specified in For example, Tesla’s Model 3-Standard Range has a battery capacity of 50  _kWh_.
-   Each vehicle has an  _average_  _mileage efficiency_  specified in kWh/100 miles. For example, the specified Tesla model’s average mileage efficiency is about  _7 kWh/100 miles_.
-   Charging depends on the  _socket type_ and  _charger_  (intake) specifications. The socket rating is specified in  _Volts_  and  _Amperes_. Assume a single-phase socket. For examples, the NEMA 5-15 socket has a rating of 120V, 15 A, 1-phase; and NEMA 14-50 has a rating of 240 V, 32 A.
-   We need to account for energy loss during charging due to several factors. This loss is captured into a parameter called  _charging efficiency._ For example, the charging efficiency of 0.9, which means 0.1 is lost.
-   The electric service provider typically provides  _price per kWh_.
-   We will not take into account other factors such as the actual full capacity and software locks put in by the vendor.

Your program should obtain the needed user parameters from the standard input. It should also compute and display the following information:

1.  _Charging time_: The total time needed in hours and minutes to charge the battery to the desired level from its current state. Note that the battery may not always be empty (0%) and may not always need to be charged fully (100%).
2.  _Charging Speed_: The number of miles per hour (_mph_) gained during the charging scenario.
3.  _Charging Cost_: The total cost of charging in $.

### TollCollection
-   A toll collection point may have multiple toll booths. Assume one booth for each toll lane.
-   Multiple vehicles may pass through each booth. Each booth has a label/number (e.g., Booth# 1).
-   The charged toll depends on the vehicle type (e.g., EV vs. Gasoline). This information must be provided when the car passes through.
-   The charged toll also depends on the number of axels in the vehicle. This information must be provided when the car passes through.
-   The charged toll also depends on the payment mode: cash, credit card, and/or electronic system (e.g.,  _SunPass_). This information must be provided when the car passes through.

You must compute the following:

1.  The toll rate for each vehicle passing through.
2.  The total toll collected for each booth.
3.  The total toll collected for all booths.
4.  The toll collected by each payment mode.

### SuperCharger
You are tasked to develop a software prototype to manage a particular supercharging station for Electric Vehicles (EVs).

-   Each supercharging station has multiple stalls.
-   At any given time, only one EV can be charged at a stall.
-   Once an EV is done charging, another EV can use the same stall.
-   The charging time of each EV depends on factors you used in Assignment 01.
-   The charging cost of each EV depends on factors you used in Assignment 01, except that the supercharging station decides the cost per kWh.
-   The supercharging station provides complimentary (free) charging to certain models of EV.

The supercharging station would like a report with the following information computed between specified start and end periods:

1.  The charging time and cost of each EV. You can reuse this part from Assignment 01.
2.  The total charging time of all vehicles that used a particular stall. You may step through your program one stall at a time.
3.  The total charging time used at the supercharging station, i.e., of all EVs at all the stalls.
4.  The total charging cost, i.e., revenue generated from, from all vehicles that the stalls at the supercharging station.
5.  The total charging cost and time that were provided complimentary at the supercharging station.

In our scenario, you may consider the start and end periods to align with one execution of your program.

### TollCollectionV2
Main Task:

**Redo Assignment 02 with collections and multiple classes.**

Since Assignment 02, the toll collection has changed the way they charge toll. It is now based on the following rule:

1.  The base rate is $2.00 per vehicle with a single axel.
2.  Each additional axel is charged $1.00 for up to 3 axels.
3.  Each additional axel is charged $5.00 for the number of axels greater than 3. For example, a vehicle with 5 axels would b.00e charged $14 = $2.00 + $1.00 for the second axel + $1.00 for the third axel + $10.00 for the fourth and fifth axels.
4.  An electric vehicle is discounted by 50%. If the vehicle in #3 was electric, the toll would be $7.00.
5.  A hybrid vehicle is discounted by 25%.
6.  There is a 15% convenience fee if paying with a credit or debit card.
7.  There is a 20% discount if paying with Electronic System (ES).
8.  The charged toll cannot be negative.
9.  The toll collection agency anticipates the base rate, fees, and discounts to change in the foreseeable future.
10.  Other requirements are the same from Assignment 02.

You must compute the following:

1.  The toll rate for each vehicle passing through.
2.  The total toll collected for each booth.
3.  The total toll collected for all booths.
4.  The toll collected by each payment mode.

### SuperChargerV2
You are tasked to develop a software prototype V2.0 to manage a particular supercharging station for Electric Vehicles (EVs)

**You must use multiple (several) classes and collections such as arrays. The classes should follow the single responsibility principle like quality attributes we discussed.**

-   Each supercharging station has multiple stalls, which should be user configurable.
-   A stall may have multiple EVs charged during a specific period. Assume that that the number of EVs charged at a particular stall is available after the fact, which should be configurable, i.e., user provided. That is, you are not doing a live count or monitoring of EV charging. There is no requirement to use a real timer.
-   The charging time of each EV depends on factors you used in Assignment 01.
-   The charging cost of each EV depends on factors you used in Assignment 01, except that the supercharging station decides the cost per kWh, which should be configurable.
-   The supercharging station provides complimentary (free) charging to certain models of EV. You have the flexibility to design the complimentary scheme. Make it configurable.

The supercharging station would like a report with the following information computed between specified start and end periods:

1.  The charging time and cost of each EV. You can reuse this part from Assignment 01 or Exam 01.
2.  The total charging time of all vehicles that used a particular stall.
3.  The total charging time used at the supercharging station, i.e., of all EVs at all the stalls.
4.  The total charging cost, i.e., revenue generated from, from all vehicles that the stalls at the supercharging station.
5.  The total charging cost and time that were provided complimentary at the supercharging station.

In our scenario, you may consider the start and end periods to align with one execution of your program.

### TollCollectionV3
Main Task:

**Redo Assignment 03 with File I/O and Exceptions.**

1. Read the configuration of the toll collection station from one or more files. The configuration parameters include, but not limited to, the number of booths (lanes), the acceptable payment modes, and toll charges.

2. Read the parameters of vehicles passing though the toll collection station from a file. This information includes, but not limited to, the type of vehicle, the number of axels, and provided payment mode.

3. Write the report of calculations from Assignment 03 to a file:

4.  The toll rate for each vehicle passing through.
5.  The total toll collected for each booth.
6.  The total toll collected for all booths.
7.  The toll collected by each payment mode.

8. The files in #1, 2, and 3 should be user configurable and not hardcoded.

9. Make appropriate use of command-line arguments.

### SuperChargerV3
Take a program or two that you did in this class and improve them. 
