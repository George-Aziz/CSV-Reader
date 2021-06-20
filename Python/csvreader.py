# Author: George Aziz
# Date Created: 14/04/2021
# Date Last Modified: 14/01/2021
# Purpose: Program to read and retrieve data from CSVs that are in same directory as program 

import sys
import os
import csv

# process all lines in a CSV
def process(filename, colNum):
    with open(filename) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
    
        for row in csv_reader:

            idx = 0
            for x in row:
                if x == "Total":
                    print(row[idx + int(colNum)]) 
                    with open("result.txt", "a") as myfile:
                        myfile.write(filename + " : " + row[idx + int(colNum)] + "\n")
                idx = idx + 1 

#Opens all CSV files in the same directory
for filename in os.listdir(os.getcwd()):
    if filename.endswith(".csv"):
        process(filename, sys.argv[1])
    