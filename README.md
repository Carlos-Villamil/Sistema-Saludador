# Sistema Saludador

Aplicación de escritorio en **Java + JavaFX** que saluda al estudiante por su nombre y menciona su edad.

## Modelo de requerimientos

- **Actor:** Estudiante
- **Sistema:** Sistema Saludador
- **Caso de uso:** *Solicitar saludo* (`<<include>>` *Pedir datos*)

Flujo de "Solicitar saludo":

1. El estudiante hace clic en **Solicitar saludo**.
2. El sistema solicita nombre, edad y hora (AM/PM).
3. El estudiante ingresa lo solicitado.
4. El sistema saluda al estudiante por su nombre y menciona su edad.

**Restricciones:** tecnología Java / JavaFX, aplicación de escritorio.

## Estructura

```
SaludadorApp/
├── src/Saludador.java   # código fuente
├── ejecutar.bat         # compila y ejecuta en Windows
├── prompts/             # secuencia de prompts (.txt)
├── docs/                # documento de reflexión (.md)
└── README.md
```

## Cómo ejecutar

1. Instalar **JDK 17+** (`java -version` y `javac -version` deben funcionar).
2. Descargar el **JavaFX SDK** (https://gluonhq.com/products/javafx/) y descomprimirlo, por ejemplo en `C:\javafx-sdk-21`.
3. Abrir `ejecutar.bat` y ajustar `PATH_TO_FX` a la carpeta `lib` del SDK (o definir esa variable de entorno).
4. Hacer doble clic en `ejecutar.bat`.

---

## Prompt que usaría para pedir esta app

> Actúa como un desarrollador Java senior. Necesito una **aplicación de escritorio con interfaz de ventanas usando Java y JavaFX** (sin Maven ni Gradle, un solo archivo `.java` dentro de `src/`) llamada **"Sistema Saludador"**.
>
> **Modelo de requerimientos**
> - Actor: Estudiante.
> - Caso de uso principal: "Solicitar saludo", que incluye (`<<include>>`) el caso de uso "Pedir datos".
>
> **Flujo**
> 1. La ventana principal muestra un título y un botón **"Solicitar saludo"**.
> 2. Al hacer clic, se abre una ventana modal "Pedir datos" que solicita: **nombre**, **edad** y **hora (1–12) con selector AM/PM**.
> 3. El estudiante ingresa los datos y pulsa **Aceptar** (o **Cancelar** para cerrar).
> 4. El sistema valida: nombre no vacío, edad entre 0 y 120, hora entre 1 y 12. Si hay un error, muestra una alerta clara y no cierra la ventana.
> 5. Si todo es válido, la ventana principal muestra un saludo personalizado por su **nombre** que **mencione su edad** y use la hora (AM → "Buenos días", PM de 12 a 5 → "Buenas tardes", PM de 6 a 11 → "Buenas noches").
>
> **Restricciones técnicas**
> - Java 17+ y JavaFX (`javafx.controls`), aplicación de escritorio.
> - Código limpio, comentado en español, con métodos separados para la interfaz, la validación y la construcción del saludo.
> - Textos de la interfaz en español con tildes correctas (UTF-8).
>
> **Entregables**
> 1. `src/Saludador.java` con el código completo.
> 2. `ejecutar.bat` para Windows que compile con `javac` y ejecute con `java`, usando `--module-path` con una variable `PATH_TO_FX` configurable y mostrando un mensaje de error útil si no encuentra JavaFX.
> 3. Un `README.md` con el modelo de requerimientos, la estructura del proyecto y las instrucciones paso a paso para ejecutar.
>
> Al final, explica brevemente la estructura del código y qué librerías o comandos usaste, para poder escribir mi documento de reflexión.
