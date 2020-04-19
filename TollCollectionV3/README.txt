I created this program to meet the following criteria for my COP 2006 class:
    Redo Assignment 03 with File I/O and Exceptions.

    1.  Read the configuration of the toll collection station from one or more files.  The configuration parameters include,
    but not limited to, the number of booths (lanes), the acceptable payment modes, and toll charges.

    2. Read the parameters of vehicles passing though the toll collection station from a file.  This information includes,
    but not limited to, the type of vehicle, the number of axles, and provided payment mode.

    3.  Write the report  of calculations from Assignment 03 to a file:
        The toll rate for each vehicle passing through.
        The total toll collected for each booth.
        The total toll collected for all booths.
        The toll collected by each payment mode.

    4.  The files in #1, 2, and 3 should be user configurable and not hardcoded.

    5.  Make appropriate use of command-line arguments.

This program is designed to take input from two specified .txt files.
    1. Example name: tollConfig.txt
        * user can change this file to change the configuration of the toll station, accepted payment types, and amount
          charged.
        * Proper configuration of tollConfig.txt example:

                Number of Booths:3
                Base Rate per axle:1
                Rate for 3 axles and over:5
                Price change for electric vehicle:0.5
                Price change for hybrid vehicle:0.75
                Price change for gas vehicle:1
                Price change for Card payment:1.15
                Price change for Cash payment:1
                Price change for electronic service:0.8

    2. Example  name: vehicleInput.txt
        * User can change this file to change the vehicles being processed.
        * each line represents a new vehicle
            - Booth number: vehicle type: number of axles: payment type
        * Proper configuration of vehicleInput.txt example

                3:gas:2:cash
                1:ev:1:es
                2:hybrid:5:card

This Program outputs into a specified .txt file.
    1. Example name: output.txt
        * Output format:

            - First line shows local date and time the program is ran.
                example: Ran on[2020-04-19, 15:16:37.200]
            - The next lines show how much each vehicle is charged
                example: Vehicle #1 was charged $1.20
            - After all the vehicles are processed it shows the total for all booths
                example: Booth #1 total: $1.15
                         Booth #2 total: $6.50
                         Booth #3 total: $1.20
            - Then totals for each payment type
                example: Total collected from card payments: $1.15
                         Total collected from cash payments: $6.50
                         Total collected from es payments: $1.20
            - And finally overall total
                example: Total collected for all booths: $8.85

When running this program through the cmd line the order of the arguments should be as follows
    1. output file (.txt)
    2. vehicle input file (.txt)
    3. toll station configuration(.txt)