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

   
![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/180d671f-f9d6-4d61-b2bd-d211378490ac)




2. Finding missing values

   It looks like the first school in our database had no data in the percent_tested column!

   Let's identify how many schools  have missing data for this column,  indicating schools that 
   did not report the percentage of students tested.

   To understand  whether this  missing data  problem is  widespread  in New York, we will also 
   calculate the total number of schools in the database.

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/ffc6116a-5f03-4d40-a92a-5194194cbbec)




4. Schools by building code

   There  are 20 schools  with  missing data for percent_tested,  which only makes up 5% of all 
   rows in the database.

   Now let's turn our attention to how  many schools there are. When we displayed the first ten 
   rows 
   of the database,  several had the same value  in the building_code column,  suggesting there 
   are  multiple schools  based in the  same location.  Let's  find out how  many unique school 
   locations exist in our database.

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/e43e6e2a-d987-4e0a-8aed-ec210df4caa1)




4. Best schools for math

   Out of 375 schools, only 233 (62%) have a unique building_code!

   Now let's start our analysis of school performance.  As each school reports individually, we 
   will treat them this way rather than grouping them by building_code.

   First, let's find all schools with an average math score of at least 80% (out of 800).

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/b2861d3d-bbfd-4baa-a3ec-4395b491767e)




5. Lowest reading score

   Wow,  there are only ten public  schools in  New York City with  an average math score of at 
   least 640!

   Now let's look at the other end of the spectrum and find the single lowest score for reading.
   We will only select the score, not the school, to avoid naming and shaming!

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/0fc8d830-3a1c-4b2d-be99-02e23879a863)




6. Best writing school

   The lowest average score for reading across schools in New York City is less than 40% of the 
   total available points!

   Now let's find the school with the highest average writing score.

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/dcee632d-e537-4a18-af0e-a082a5f358be)




7. Top 10 schools

   An average writing score of 693 is pretty impressive!

   This top writing score was at the  same school that got the top math score,  Stuyvesant High 
   School. 
   Stuyvesant is widely known as a perennial top school in New York.

   What other schools are also excellent across the board? Let's look at scores across reading, 
   writing, and math to find out.

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/ee561dc6-c44c-4bdc-97f9-bf6dcafbcd09)




8. Ranking boroughs

   There are four schools  with average SAT scores of over 2000!  Now let's analyze performance 
   by New York City borough.

   We will build a  query that calculates the  number of schools  and the average SAT score per 
   borough!

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/f553207f-bae3-4bd0-9b1e-9fcfb2a25ec1)




9. Brooklyn numbers

   It appears that schools in Staten Island, on average, produce higher scores across all three 
   categories. However,  there are only 10 schools in Staten Island,  compared to an average of 
   91 schools in the other four boroughs!

   For our final query of the database, let's focus on Brooklyn, which has 109 schools. We wish 
   to find the top five schools for math performance.

![image](https://github.com/SlavikKhrapach/Capstone-SQL-Project/assets/120146359/52cda328-bed8-411c-ba64-97817d43419f)
