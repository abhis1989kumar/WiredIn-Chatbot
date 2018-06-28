@echo off
color 2a
title Frequently Asked Questions 
:top
echo ***************************************************************
echo.
echo Frequently Asked Questions 
echo.
echo ***************************************************************
echo.
echo Q. What is CYR3CON ?
echo A. CYR3CON specializes in identifying cyber-threats in the earliest stages. We leverage both human analysts and advanced machine learning  
echo capabilities to search portions of the Internet where malicious hackers organize, plan, purchase malware, sell exploits, and conduct other 
echo activities prior to conducting a cyber-attack
echo. 
echo Q. What is the mission of CYR3CON ?
echo A. Our mission is to provide machine learning driven proactive intelligence from multiple sources to a wide variety of global customers. We 
echo empower companies to mitigate risk and lower costs by discovering and anticipating cyber threats.
echo.
echo Q. Tell me about CYR3CON's Team.
echo A. Our CYR3CON team consists of seasoned individuals from a variety of backgrounds including not only cyber-security, but machine learning, big echo data systems, sociology, and intelligence analysis. Our team is uniquely capable of delivering early-breaking cyber threat intelligence through echo the use of cutting-edge technology.
echo.
echo Q. How does CYR3CON handles its media presence.
echo A. At CYR3CON™ we value spreading the word about how advanced artificial intelligence, machine learning, and big data techniques can be combined echo with methods from sociology and intelligence analysis to deliver the next generation of advanced cyber threat intelligence.
echo.
echo Q. Explain the solutions CYR3CON gives to its customers.
echo A. Whether you are a service provider, defending an enterprise network, assessing cyber-risk, or looking to develop new cyber-security tools echo that leverage CYR3CON threat intelligence, we offer a variety of solutions for your needs.
echo.
echo Q.Where can I find research work done by CYR3CON team.
echo A.In our blog you will find the latest research and analysis from our team as well as press releases describing the latest news about CYR3CON.
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