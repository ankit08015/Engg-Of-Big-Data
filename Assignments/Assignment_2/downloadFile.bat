@echo off
setlocal

certutil.exe -urlcache -split -f "http://msis.neu.edu/nyse/nyse.zip" C:\temp\nyse.zip

exit /b