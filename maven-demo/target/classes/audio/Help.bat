@echo off
color 0a
title HELP screen
:top
echo ***************************************************************
echo.
echo Use the Below Commands
echo (The commands are nor case-sensitive.)
echo ***************************************************************
echo.
echo 1] HELP          - Opens the Help screen displaying the supported commands.
echo 2] FAQ           - Displays the Freqently asked Questions.
echo 3] Clear         - Clears the message console.
echo 4] Restart/reset - Restarts the WiredIn.
echo 5] Calculator    - Opens the calculator
echo 6] Date          - Prints the date.
echo 7] Time          - Prints the Time.
echo 8] Open google   - Opens Google Browser.
echo.
echo [e] Type e for Exit
echo.
echo ***************************************************************
echo.
echo.
set /p udefine= 
echo.
echo ***************************************************************
if %udefine%==e goto exit
cls
echo ***************************************************************
echo.
echo Thank You for using 
echo.
echo ***************************************************************
echo Type [e] to exit 
echo.
set /p udefine=
echo.
echo ***************************************************************

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