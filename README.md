# A Gamified Numerical Simulator for the Secant Method: Bridging Active Learning and Algorithmic Convergence in STEM Pedagogy

This repository hosts the source code for the **Soldado vs Zombie Simulator**, an interactive, gamified software artifact engineered to support STEM pedagogy in Numerical Analysis. Designed as an educational intervention tool, it bridges abstract mathematical convergence theories with visual, real-time reinforcement learning, specifically targeting the **Secant Method** for root-finding in non-linear equations.

---

## Abstract

Traditional numerical analysis teaching methods often struggle with low student engagement and high cognitive loads when introducing root-finding algorithms' convergence criteria. This software introduces a gamified simulation framework where the physical progress of two competitors—a Soldier and a Zombie—on a virtual track is mathematically driven by the iteration efficiency of the Secant Method. 

By mapping the convergence velocity directly to visual animations, the software promotes active learning, enabling students to immediately associate starting interval sensitivity, error thresholds, and function geometries with real-time physical telemetry. Additionally, it implements automated mathematical guards (Intermediate Value Theorem sweeps and singularity filters) to prevent UI failures and provide instant pedagogical feedback upon divergence or root absence.

---

## Core Pedagogical and Scientific Contributions

This artifact is designed to support academic research in engineering education and has been structured around three key research pillars:

### 1. Direct Mapping of Algorithmic Complexity to Visual Progress
* **The Speed-to-Iteration Vector**: The physical speed of each competitor on the track is calculated as an inverse function of the total iterations ($N_i$) required to reach convergence ($E_{rel} < \epsilon$).
* **Real-time Cognitive Reinforcement**: Students observe how changing initial guesses ($x_0, x_1$) impacts the slope of the secant line and translates into immediate acceleration or deceleration of the competitor, reinforcing the concept of interval sensitivity and local convergence behaviors.

### 2. Dual-Dimensional Telemetry Comparison Dashboard
* **Synchronized Numerical-Graphical Viewports**: The comparative telemetry dashboard presents JTables side-by-side with static root-convergence plots.
* **Precision Extraction (15 Decimals)**: To prevent cognitive overcrowding, JTables render data at 8 decimal places ($10^{-8}$). However, hovering over any numerical cell exposes the raw double-precision floating-point value to **15 decimal places** ($10^{-15}$) via dynamic tooltips. This design allows students to observe asymptotic convergence rates without UI clutter.
* **Adaptive Micro-Scaling**: If numerical roots are closely clustered, the plotting engine computes local bounds and triggers a dynamic zoom (up to a microscopic threshold of $0.05$ units) with automated grid coordinates displayed along the viewport margins.

### 3. Divergence Classification and Singularity Filters
To support self-paced student learning, the simulator features an intelligent pre-calculation engine that screens input parameters for two common numerical analysis singularities:
* **Root Inexistence Detection**: Prior to starting the simulation, the engine executes a dense 200-point sweep across the search space $[min(x_0, x_1) - 10, max(x_0, x_1) + 10]$. Using the **Intermediate Value Theorem (IVT)**, it checks for sign changes or proximity to zero. If no root is detected, it aborts the simulation and launches a dedicated educational warning screen explaining the mathematics of non-intersecting graphs.
* **Singularity / Division-by-Zero Handling**: If two successive iterations result in identical function evaluations ($f(x_j) = f(x_{j-1})$), the slope becomes horizontal (flat). The solver detects this singularity, avoids division-by-zero crashes, limits the loop to 100 safe iterations, and triggers a pedagogical alert window outlining the flat-slope phenomenon.

---

## Software Architecture and Technical Stack

* **Platform**: Cross-platform Java Desktop Application (Swing Framework).
* **Parser Engine**: Dynamic non-linear equation evaluator supporting polynomials, exponentials, logarithmic base changes, and trigonometric expressions.
* **Threading**: Multi-threaded concurrency model utilizing `SwingUtilities.invokeLater()` to isolate background numerical computations from the Event Dispatch Thread (EDT), ensuring fluid 60 FPS animation during simulation.
* **UI Design**: Undecorated custom-bordered frame layouts, optimized for high-contrast dark environments to reduce eye strain during prolonged classroom sessions.

---

##  Installation & Reproducibility Guide

To compile and execute the simulator locally for research or replication studies:

### Prerequisites
* **Java Development Kit (JDK)** version 8 or higher.
* Git client.

### Compiling and Running
1. Clone the repository:
   ```bash
   git clone https://github.com/VickiannJC/Soldado-Vs-Zonbie-Secand-Method.git
   ```
2. Navigate to the source folder:
   ```bash
   cd Soldado-Vs-Zonbie-Secand-Method/SoldadoVsZombie
   ```
3. Compile all packages:
   ```bash
   javac -d build/classes -sourcepath src src/soldadovszombie/*.java
   ```
4. Run the main class:
   ```bash
   java -cp build/classes soldadovszombie.Inicio
   ```
