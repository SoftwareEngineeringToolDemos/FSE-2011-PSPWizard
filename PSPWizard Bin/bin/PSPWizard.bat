@echo off

@echo launching PSPWizard ...

set BASEDIR=%~dp0
set LIBS=%BASEDIR%lib\AbsolutLayout.jar:%BASEDIR%lib\swing-layout-1.0.4.jar

java -classpath "%LIBS%" -jar "%BASEDIR%PSPWizard.jar"
