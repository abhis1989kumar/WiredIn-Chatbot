@echo off
color 1f
title Site Selector 
:top
echo ***************************************************************
echo.
echo Site Selector
echo.
echo ***************************************************************
echo.
echo Key: [1] CySIS home page
echo [2] Cysis Dashboard
echo [3] Paulo Shakarian
echo [4] CYR3CON home page
echo [5] Paulo Shakarian-New America
echo.
echo [e] Exit
echo.
echo ***************************************************************
echo Enter the number of the website which you would like to go to:
echo.
set /p udefine= 
echo.
echo ***************************************************************
if %udefine%==1 start http://cysis.engineering.asu.edu/
if %udefine%==2 start https://cydashmon.us/
if %udefine%==3 start https://cidse.engineering.asu.edu/directory/shakarian-paulo/
if %udefine%==4 start https://www.cyr3con.com/
if %udefine%==5 start https://www.newamerica.org/our-people/paulo-shakarian/
if %udefine%==e goto exit

cls
echo ***************************************************************
echo.
echo Thank You for using 
echo.
echo ***************************************************************
echo Type [e] to exit or [b] to go back and select another site.
echo.
set /p udefine=
echo.
echo ***************************************************************
if %udefine%==b goto top
if %udefine%==e goto exit
:exit
cls
echo ***************************************************************
echo.
echo Thank You for using 
echo.
echo ***************************************************************
pause
exit