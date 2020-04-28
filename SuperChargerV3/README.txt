This program was made for my COP 1500 class. The assignment was to update one of our older assignments to use file IO and
exceptions. I chose to update my SuperChargerV2.

This program expects 3 command line arguments in a specific order. If no commands are given it will attempt to use the
default files.

First Command:
    Output file name: ex. output.txt
    The program will give you how long and the amount of money charged for each vehicle.
    Then it will give you the totals by outlet.
    Then finally, the overall totals.

Second Command:
    Vehicle input file name: ex. vehicleInput.txt
    Each line represents a separate vehicle.
    Each category on the line must be separated by ';' and the category name and value must be separated by ':'
    They must be in this order:
        1. outlet #
        2. battery capacity
        3. battery efficiency
        4. starting charge
        5. ending charge
        6. paying customer boolean
    Example of properly formatted line:
        outlet #:1; battery capacity:50; battery efficiency:90; starting charge level:20; ending charge level:80; paying:true

Third Command:
    Station Configuration file name: ex stationConfig.txt
    First line shows how many outlets
    Following lines show the voltage and amperes for those outlets
    Last line holds the cost per KWH
    Example of properly formatted file:

        Number of outlets:3
        1. Voltage:120
        1. Amperes:15
        2. Voltage:240
        2. Amperes:32
        3. Voltage:120
        3. Amperes:15
        Cost per KWH:0.12
