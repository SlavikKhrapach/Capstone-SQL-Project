# Capstone-SQL-Project
Capstone Project: Completed the DataCamp project Analyzing NYC Public School Test Result Scores. 

This project required SQL knowledge and using of summary statistics and filters to analyze test scores across New York City's public schools.
As an added feature I implemented the project in Java and connected it to SQLite database I set up on my local machine. I constructed a method to line up query outputs in tables so it would look like a SQL query result.

Here is an output of my java implementation:

1. inspectingTheData

   Every year,  American high school students take SATs,  which are standardized tests intended 
   to measure literacy,  numeracy, and writing skills. There are three sections - reading, math,
   and writing,  each with a maximum  score of 800 points.  These tests are extremely important 
   for students and colleges, as they play a pivotal role in the admissions process.

   Analyzing the performance  of schools is important for a variety of stakeholders,  including 
   policy and education professionals,  researchers,  government,  and even parents considering 
   which school their children should attend.

   In this notebook, we will take a look at data on SATs across public schools in New York City.
   Our database contains a single table:

   Let's familiarize ourselves with the data by taking a looking at the first few schools!

#================================================================#===========#===============#==============#=================#=================#================#
|                                                    school_name |   borough | building_code | average_math | average_reading | average_writing | percent_tested |
#================================================================#===========#===============#==============#=================#=================#================#
| New Explorations into Science, Technology and Math High School | Manhattan |          M022 |          657 |             601 |             601 |                |
|                                           Essex Street Academy | Manhattan |          M445 |          395 |             411 |             387 |           78.9 |
|                                   Lower Manhattan Arts Academy | Manhattan |          M445 |          418 |             428 |             415 |           65.1 |
|                High School for Dual Language and Asian Studies | Manhattan |          M445 |          613 |             453 |             463 |           95.9 |
|                  Henry Street School for International Studies | Manhattan |          M056 |          410 |             406 |             381 |           59.7 |
|                                 Bard High School Early College | Manhattan |          M097 |          634 |             641 |             639 |           70.8 |
|                   Urban Assembly Academy of Government and Law | Manhattan |          M445 |          389 |             395 |             381 |           80.8 |
|                                        Marta Valle High School | Manhattan |          M025 |          438 |             413 |             394 |           35.6 |
|                            University Neighborhood High School | Manhattan |          M446 |          437 |             355 |             352 |           69.9 |
|                                         New Design High School | Manhattan |          M445 |          381 |             396 |             372 |           73.7 |
#================================================================#===========#===============#==============#=================#=================#================#



2. Finding missing values

   It looks like the first school in our database had no data in the percent_tested column!

   Let's identify how many schools  have missing data for this column,  indicating schools that 
   did not report the percentage of students tested.

   To understand  whether this  missing data  problem is  widespread  in New York, we will also 
   calculate the total number of schools in the database.

#====================#=============#
| num_tested_missing | num_schools |
#====================#=============#
|                 20 |         375 |
#====================#=============#



3. Schools by building code

   There  are 20 schools  with  missing data for percent_tested,  which only makes up 5% of all 
   rows in the database.

   Now let's turn our attention to how  many schools there are. When we displayed the first ten 
   rows 
   of the database,  several had the same value  in the building_code column,  suggesting there 
   are  multiple schools  based in the  same location.  Let's  find out how  many unique school 
   locations exist in our database.

#======================#
| num_school_buildings |
#======================#
|                  233 |
#======================#



4. Best schools for math

   Out of 375 schools, only 233 (62%) have a unique building_code!

   Now let's start our analysis of school performance.  As each school reports individually, we 
   will treat them this way rather than grouping them by building_code.

   First, let's find all schools with an average math score of at least 80% (out of 800).

#=======================================================================#==============#
|                                                           school_name | average_math |
#=======================================================================#==============#
|                                                Stuyvesant High School |          754 |
|                                          Bronx High School of Science |          714 |
|                                   Staten Island Technical High School |          711 |
|                   Queens High School for the Sciences at York College |          701 |
| High School for Mathematics, Science, and Engineering at City College |          683 |
|                                        Brooklyn Technical High School |          682 |
|                                           Townsend Harris High School |          680 |
|                     High School of American Studies at Lehman College |          669 |
|        New Explorations into Science, Technology and Math High School |          657 |
|                                         Eleanor Roosevelt High School |          641 |
#=======================================================================#==============#



5. Lowest reading score

   Wow,  there are only ten public  schools in  New York City with  an average math score of at 
   least 640!

   Now let's look at the other end of the spectrum and find the single lowest score for reading.
   We will only select the score, not the school, to avoid naming and shaming!

#================#
| lowest_reading |
#================#
|            302 |
#================#



6. Best writing school

   The lowest average score for reading across schools in New York City is less than 40% of the 
   total available points!

   Now let's find the school with the highest average writing score.

#========================#=============#
|            school_name | max_writing |
#========================#=============#
| Stuyvesant High School |         693 |
#========================#=============#



7. Top 10 schools

   An average writing score of 693 is pretty impressive!

   This top writing score was at the  same school that got the top math score,  Stuyvesant High 
   School. 
   Stuyvesant is widely known as a perennial top school in New York.

   What other schools are also excellent across the board? Let's look at scores across reading, 
   writing, and math to find out.

#=======================================================================#=============#
|                                                           school_name | average_sat |
#=======================================================================#=============#
|                                                Stuyvesant High School |        2144 |
|                                          Bronx High School of Science |        2041 |
|                                   Staten Island Technical High School |        2041 |
|                     High School of American Studies at Lehman College |        2013 |
|                                           Townsend Harris High School |        1981 |
|                   Queens High School for the Sciences at York College |        1947 |
|                                        Bard High School Early College |        1914 |
|                                        Brooklyn Technical High School |        1896 |
|                                         Eleanor Roosevelt High School |        1889 |
| High School for Mathematics, Science, and Engineering at City College |        1889 |
#=======================================================================#=============#



8. Ranking boroughs

   There are four schools  with average SAT scores of over 2000!  Now let's analyze performance 
   by New York City borough.

   We will build a  query that calculates the  number of schools  and the average SAT score per 
   borough!

#===============#=============#=====================#
|       borough | num_schools | average_borough_sat |
#===============#=============#=====================#
| Staten Island |          10 |                1439 |
|        Queens |          69 |                1345 |
|     Manhattan |          89 |                1340 |
|      Brooklyn |         109 |                1230 |
|         Bronx |          98 |                1202 |
#===============#=============#=====================#



9. Brooklyn numbers

   It appears that schools in Staten Island, on average, produce higher scores across all three 
   categories. However,  there are only 10 schools in Staten Island,  compared to an average of 
   91 schools in the other four boroughs!

   For our final query of the database, let's focus on Brooklyn, which has 109 schools. We wish 
   to find the top five schools for math performance.

#================================================#==============#
|                                    school_name | average_math |
#================================================#==============#
|                 Brooklyn Technical High School |          682 |
|                          Brooklyn Latin School |          625 |
| Leon M. Goldstein High School for the Sciences |          563 |
|                Millennium Brooklyn High School |          553 |
|                            Midwood High School |          550 |
#================================================#==============#
