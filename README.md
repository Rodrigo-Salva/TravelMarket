# 🌍 TravelMarket


![Versión](https://img.shields.io/badge/version-1.0-blue) ![Licencia](https://img.shields.io/badge/license-MIT-green)

Aplicación móvil desarrollada en **Kotlin** con **Jetpack Compose** que ofrece a los turistas una experiencia interactiva para explorar lugares, eventos, gastronomía y servicios locales durante los Juegos Panamericanos.  
El proyecto combina diseño atractivo, arquitectura modular y una interfaz fluida inspirada en **Material Design 3**.

---


## 1. Descripción General

**TravelMarket** nace con el propósito de facilitar la experiencia de los visitantes durante los Juegos Panamericanos, permitiendo descubrir actividades y sitios destacados en una sola plataforma.  
La aplicación integra pantallas principales de **Inicio**, **Detalle** y **Perfil**, ofreciendo navegación dinámica, datos organizados y una estructura adaptable a futuros servicios (favoritos, mapas, filtrado, entre otros).

---

## 2. Objetivo General

Desarrollar una aplicación móvil moderna y funcional que brinde información relevante a los turistas sobre lugares, eventos y servicios locales, siguiendo principios de **usabilidad, diseño y buenas prácticas** en desarrollo Android.

---

## 3. Objetivos Específicos

- Implementar una arquitectura clara basada en los principios de separación de capas (**UI**, **Data**, **Model**, **Navigation**).  
- Utilizar **Jetpack Compose** para construir una interfaz declarativa coherente con **Material Design 3**.  
- Gestionar la navegación de pantallas mediante **Navigation Compose**.  
- Presentar datos dinámicos desde fuentes locales (listas o JSON simulado).  
- Fomentar el trabajo colaborativo y el control de versiones mediante **GitHub**.  
- Entregar un producto documentado, funcional y alineado con el prototipo de diseño en **Figma**.

---

## 4. Público Objetivo

Turistas nacionales e internacionales asistentes a los Juegos Panamericanos que deseen conocer rápidamente los lugares más representativos, eventos y experiencias gastronómicas de la ciudad anfitriona.

---

## 5. Equipo de Desarrollo

| Rol                  | Integrante                        | Responsabilidades |
|----------------------|-----------------------------------|------------------|
| Líder Técnico        | Rodrigo Salva                     | Configuración del proyecto, estructura base, control de versiones y soporte técnico. |
| Diseñador UI/UX      | Rafael Chuco                      | Diseño del prototipo en Figma, estilos visuales y definición de componentes de interfaz. |
| Tester / Documentador| Miguel Carasas                    | Pruebas funcionales, control de calidad y documentación final del proyecto. |

---

## 6. Prototipo y Diseño

El diseño inicial fue creado en **Figma**, con una propuesta centrada en la simplicidad, legibilidad y fácil acceso a la información.

**Pantallas principales del prototipo:**
- **Inicio:** Acceso a las categorías principales (Lugares, Eventos, Gastronomía, Transporte).  
- **Detalle:** Muestra información completa de un elemento seleccionado (imagen, descripción, ubicación).  
- **Perfil / Favoritos:** Representación del usuario y elementos guardados.

**Enlace al prototipo:** [Agregar enlace Figma aquí]

---

## 7. Arquitectura del Proyecto

El proyecto se basa en una arquitectura modular, separando las responsabilidades en capas para facilitar la escalabilidad y el mantenimiento:

<img width="900" height="220" alt="image" src="https://github.com/user-attachments/assets/7ba7a17e-53b2-4422-a727-912081536b50" />


---

## 8. TECNOLOGÍAS Y HERRAMIENTAS

| Tecnología / Herramienta | Propósito |
|---------------------------|------------|
| Kotlin | Lenguaje principal de desarrollo. |
| Jetpack Compose | Creación de interfaces reactivas y modernas. |
| Navigation Compose | Gestión de rutas y navegación entre pantallas. |
| Material Design 3 | Base de diseño visual y componentes estilizados. |
| Android Studio | Entorno de desarrollo integrado. |
| Figma | Diseño del prototipo visual del proyecto. |
| Git / GitHub | Control de versiones y colaboración en equipo. |

---

## 9. PLAN DE TRABAJO

| Día | Objetivo | Actividades Clave | Entregable |
|------|-----------|------------------|-------------|
| 1 | Planificación y diseño | Reunión, definición de roles, creación de prototipo y README inicial | Repositorio + enlace Figma |
| 2 | Estructura base | Configuración del entorno, paquetes, navegación inicial | Proyecto base funcional |
| 3 | Interfaz de usuario | Implementación de pantallas principales (Home, Detalle, Perfil) | UI completa según Figma |
| 4 | Lógica y datos | Creación de modelos, listas locales y conexión de datos | App con datos simulados |
| 5 | Integración final | Búsqueda, filtrado, pruebas y merge de ramas | Versión candidata v1.0 |
| 6 | Presentación | Documentación completa, release y demostración final | Proyecto final en GitHub |

---

## 10. CONTROL DE VERSIONES

| Rama | Función |
|-------|----------|
| main | Contiene la versión estable del proyecto. |
| develop | Rama base para integración de nuevas características. |
| feature| Ramas individuales para el desarrollo de cada integrante. |

---

## 11. REQUERIMIENTOS FUNCIONALES PRINCIPALES

| Código | Descripción |
|--------|--------------|
| RF01 | Visualizar pantalla de inicio con accesos a categorías. |
| RF02 | Mostrar detalles de cada elemento (nombre, imagen, descripción, ubicación). |
| RF06 | Desarrollar el sistema en Android Studio con Kotlin y Jetpack Compose. |
| RF08 | Implementar navegación de pantallas con Navigation Compose. |
| RF11 | Mostrar lista de elementos con LazyColumn o Grid. |
| RF17 | Cargar información dinámica desde una fuente local (lista o JSON). |
| RF21 | Permitir búsqueda o filtrado de elementos. |
| RF27 | Ejecutar correctamente la aplicación en un emulador o dispositivo físico. |
| RF30 | Representar fielmente el prototipo Figma en la versión final. |

---

## 12. DATOS DEL CURSO

| Campo | Detalle |
|--------|----------|
| Curso | Programación en Móviles |
| Docente | Juan León |
| Duración | 6 días |
| Modalidad | Trabajo colaborativo (equipos de 2 o 3 estudiantes) |
| Versión actual | v1.0 – Proyecto Final |

---

## 13. CONCLUSIÓN

**TravelMarket** representa la aplicación práctica de los conocimientos adquiridos en el curso de desarrollo móvil con Android, integrando diseño, arquitectura, trabajo en equipo y control de versiones.

> “Explora los Juegos Panamericanos con una sola aplicación.  
> TravelMarket: tu guía digital para descubrir lo mejor de cada destino.”
