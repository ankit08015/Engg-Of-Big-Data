@echo off

FOR %%G IN (A B C D E F G H I J K L M N O P Q R S T U V W X Y Z) DO mongoimport --db assignment_4 --type csv --collection stocks --headerline --file C:\temp\unzip\NYSE\NYSE_daily_prices_%%G.csv

pause