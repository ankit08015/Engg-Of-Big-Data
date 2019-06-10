@echo off

CALL downloadFile.bat

CALL unzip.bat

FOR %%G IN (A B C D E F G H I J K L M N O P Q R S T U V W X Y Z) DO mongoimport --db nysedb --type csv --collection stocks --headerline --file C:\temp\unzip\NYSE\NYSE_daily_prices_%%G.csv

pause