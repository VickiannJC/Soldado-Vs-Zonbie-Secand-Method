# 🧟 Soldado vs Zombie: Secant Method Simulation Game 🪖

[![Java Version](https://img.shields.io/badge/Java-1.8%2B-orange.svg)](https://www.oracle.com/java/technologies/)
[![Educational Tool](https://img.shields.io/badge/Purpose-Educational%20Gamification-green.svg)]()
[![Repository](https://img.shields.io/badge/GitHub-Soldado--Vs--Zombie-blue.svg)](https://github.com/VickiannJC/Soldado-Vs-Zonbie-Secand-Method)

> **¿Quién dijo que el Análisis Numérico tenía que ser aburrido?** 🚀
> 
> **Soldado vs Zombie** es un videojuego educativo e interactivo que transforma el estudio del **Método de la Secante** en una emocionante carrera por la supervivencia. Ingresa tus funciones, define tus intervalos y mira cómo el Soldado y el Zombie compiten en tiempo real: ¡el competidor con el cálculo matemático más eficiente y veloz cruzará la meta primero!

---

## 🎯 ¿Por qué elegir este software? (Propuesta de Valor)

Para los estudiantes y docentes de ingeniería, comprender la convergencia de algoritmos numéricos suele ser abstracto y puramente numérico. **Soldado vs Zombie** soluciona esto gamificando la teoría:
* **Matemática Visual**: Observa de forma directa cómo la recta secante se traza iteración por iteración en gráficas dinámicas.
* **Competencia de Eficiencia**: La velocidad física de los corredores en la pista superior está directamente vinculada al número de iteraciones requeridas para converger. ¡Menos iteraciones significan un corredor más rápido!
* **Aesthetic Terminal Retro**: Una interfaz inmersiva inspirada en consolas retro, con temas oscuros de alto contraste, bordes de neón reactivos, efectos de sonido arcade y tipografía monoespaciada de estilo militar/computacional.

---

## 🛠️ Características Principales

### 📈 1. Graficador Inteligente y Zoom Dinámico
* Soporta múltiples familias de funciones: **Polinomiales (cúbicas, cuadráticas), Exponenciales, Trigonométricas y Logarítmicas**.
* **Zoom Adaptativo**: Si los puntos de aproximación numérica se encuentran muy cercanos entre sí, la gráfica ajusta la escala automáticamente en tiempo real (con un zoom profundo de hasta 0.05 unidades) permitiendo analizar la convergencia a nivel microscópico.
* **Cuadrícula con Ejes Flotantes**: Muestra las coordenadas exactas de las cuadrículas en los márgenes de la pantalla, manteniendo la orientación matemática aun en acercamientos extremos.

### 💻 2. Telemetría Gráfica Comparativa Lado a Lado
* Al finalizar la carrera, se presenta un panel completo de control y comparación donde podrás contrastar la telemetría del Soldado (azul) contra la del Zombie (verde).
* **Precisión Científica Flotante**: Las celdas de las tablas muestran hasta 8 decimales. 
* **Tooltips de 15 Decimales**: ¿Necesitas precisión total? Coloca el cursor del ratón sobre cualquier número de la tabla y se desplegará instantáneamente un tooltip flotante con el valor double completo de **15 decimales** (`Valor completo: x.xxxxxxxxxxxxxxx`).
* **Comparador de Gráficas Finales**: Al lado de cada tabla numérica se renderiza la gráfica final completa con el camino geométrico que realizó cada personaje para converger a la raíz.

### 🛡️ 3. Filtros y Alertas de Divergencia Pedagógicas
El juego no se romperá ni se congelará si introduces datos erróneos. Cuenta con un sistema interno de detección matemática que analiza el comportamiento antes de correr:
* **Filtro de Inexistencia de Raíces**: Evalúa la región de búsqueda mediante un barrido inteligente de 200 puntos. Si la función no cruza el eje X en el intervalo de búsqueda, el juego frena la carrera y despliega la advertencia **`¡FUNCIÓN SIN RAÍCES REALES!`** explicando por qué no se puede resolver.
* **Filtro de Divergencia y Pendientes Planas**: Si se detecta una indeterminación por división para cero (secantes paralelas al eje X) o si el cálculo supera las 100 iteraciones de límite seguro, se despliega la ventana **`¡DIVERGENCIA EN SECANTE!`** con recomendaciones claras para reajustar los valores iniciales.

---

## 🎮 ¿Cómo se Juega?

1. **Selecciona o Construye tu Ecuación**: Escoge una de las funciones predeterminadas o introduce tus propias constantes y coeficientes personalizados.
2. **Ingresa los Parámetros**: Define los valores iniciales $x_0$ y $x_1$ de partida, tanto para el Soldado como para el Zombie, además del margen de tolerancia al error.
3. **¡Inicia la Carrera!**: Presiona **Run** y observa a los personajes avanzar en la pista mientras los contadores numéricos y los gráficos en miniatura se actualizan a su paso.
4. **Compara e Investiga**: Revisa la telemetría lado a lado para entender cuál intervalo o aproximación fue óptima para el método de la secante.

---

## 🚀 Requisitos e Instalación

### Requisitos Previos
* **Java Development Kit (JDK)** versión 8 o superior instalado en el sistema.
* Consola de comandos de tu sistema operativo.

### Instrucciones de Compilación y Ejecución
1. Descarga o clona este repositorio en tu máquina:
   ```bash
   git clone https://github.com/VickiannJC/Soldado-Vs-Zonbie-Secand-Method.git
   ```
2. Accede al directorio raíz del proyecto:
   ```bash
   cd Soldado-Vs-Zonbie-Secand-Method/SoldadoVsZombie
   ```
3. Compila los archivos fuentes de Java:
   ```bash
   javac -d build/classes -sourcepath src src/soldadovszombie/*.java
   ```
4. Ejecuta el juego:
   ```bash
   java -cp build/classes soldadovszombie.Inicio
   ```

---

## 🎓 Créditos e Institución
Este software fue desarrollado como un prototipo educativo interactivo y riguroso para la materia de **Análisis Numérico** de la **Escuela Politécnica Nacional (EPN)**, sirviendo de apoyo visual e interactivo a estudiantes de ingeniería en el estudio de métodos numéricos para búsqueda de raíces en ecuaciones no lineales.

---
*Desarrollado con pasión por la programación y las matemáticas.* 💻✨
