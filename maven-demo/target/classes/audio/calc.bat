@echo off
title Calculator
color 1f
:top
echo --------------------------------------------------------------
echo Calculator
echo --------------------------------------------------------------
echo.

set /p sum=
set /a ans=%sum%
echo.
echo = %ans%
echo --------------------------------------------------------------
pause
cls
echo Previous Answer: %ans%
if %udefine%==e goto exit

goto top
pause
exit