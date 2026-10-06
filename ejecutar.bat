@echo off
chcp 65001 >nul
setlocal

REM ============================================================
REM  Sistema Saludador - compilar y ejecutar (Java + JavaFX)
REM  1) Instala JDK 17 o superior.
REM  2) Descarga el JavaFX SDK desde https://gluonhq.com/products/javafx/
REM  3) Cambia la ruta de abajo a la carpeta "lib" del SDK.
REM ============================================================
if "%PATH_TO_FX%"=="" set PATH_TO_FX=C:\JavaFx\javafx-sdk-17.0.20\lib

if not exist "%PATH_TO_FX%" (
    echo [ERROR] No se encontro JavaFX en: %PATH_TO_FX%
    echo Edita ejecutar.bat y ajusta la variable PATH_TO_FX.
    pause
    exit /b 1
)

if not exist bin mkdir bin

echo Compilando...
javac -encoding UTF-8 --module-path "%PATH_TO_FX%" --add-modules javafx.controls -d bin src\Saludador.java
if errorlevel 1 (
    echo [ERROR] Fallo la compilacion.
    pause
    exit /b 1
)

echo Ejecutando...
java --module-path "%PATH_TO_FX%" --add-modules javafx.controls -Dfile.encoding=UTF-8 -cp bin Saludador

endlocal
