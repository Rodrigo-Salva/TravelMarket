# 🌍 TravelMarket - Lima Juegos Panamericanos 2025


<p align="center">
	<img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
	<img src="https://img.shields.io/badge/Language-Kotlin-blue.svg" alt="Language">
	<img src="https://img.shields.io/badge/UI-Jetpack%20Compose-brightgreen.svg" alt="UI">
	<img src="https://img.shields.io/badge/Architecture-MVVM-orange.svg" alt="Architecture">
	<img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License">
</p>

## 📱 Descripción

**TravelMarket** es una aplicación móvil Android diseñada para turistas y visitantes que asisten a los Juegos Panamericanos en Lima, Perú. La aplicación proporciona información completa sobre lugares turísticos, eventos deportivos, gastronomía local y opciones de transporte, facilitando la experiencia de los usuarios durante su estancia en la capital peruana.

### 🎯 Propósito

Conectar a turistas con los mejores servicios y experiencias de Lima durante los Juegos Panamericanos 2025, ofreciendo una plataforma centralizada y fácil de usar para explorar la ciudad.

---

## ✨ Características Principales
## 🎉 Pantalla Inicial (Welcome)
- Logo de TravelMarket
- Botones: Iniciar sesión y Registrarse
![alt text](image-7.png)

### 🔐 Sistema de Autenticación
- Pantalla de Login con validación
- Opción "Recordarme"
- Integración futura con redes sociales

![alt text](image-8.png)
### 🏠 Pantalla de Inicio
- Banner de bienvenida personalizado
- Sección de elementos destacados con mejor valoración
- Navegación rápida a todas las categorías
![alt text](image-1.png)
### 🗺️ Lugares Turísticos
- Machu Picchu, Malecón de Miraflores, Plaza Mayor de Lima
- Información detallada: ubicación, categoría, horario, precio
- Sistema de calificación con estrellas
![alt text](image-2.png)
### 🏆 Eventos Deportivos
- Calendario de eventos Panamericanos
- Atletismo, Ciclismo de ruta, Vóley
- Información de estadios, fechas, horarios y precios de entradas
![alt text](image-3.png)
### 🍽️ Gastronomía Peruana
- Restaurantes destacados: La Granja Azul, Maido, Central
- Tipos de cocina, especialidades y horarios
- Precios promedios y ubicaciones
![alt text](image-4.png)
### 🚌 Transporte
- LATAM Airlines Perú, Metropolitano, Mirabus
- Rutas, horarios y tarifas
- Información de acceso y ubicaciones
![alt text](image-5.png)
### 📄 Pantalla de Detalle
- Información completa de cada elemento seleccionado
- Descripciones extendidas y datos específicos
- Navegación intuitiva con botón de retorno
![alt text](image-6.png)

---

## 🛠️ Tecnologías y Herramientas

### Lenguaje y Framework
- Kotlin 2.0+ (proyecto configurado con 2.0.21)
- Jetpack Compose (UI moderna y declarativa)
- Material Design 3

### Arquitectura
- MVVM (Model-View-ViewModel)
- Repository Pattern
- StateFlow para gestión de estado reactiva
- Coroutines para asincronía

### Bibliotecas Principales

- AndroidX Core KTX: `androidx.core:core-ktx:1.17.0`
- Lifecycle Runtime KTX: `androidx.lifecycle:lifecycle-runtime-ktx:2.9.4`
- Activity Compose: `androidx.activity:activity-compose:1.11.0`
- Compose BOM: `androidx.compose:compose-bom:2024.09.00`
	- UI, UI Graphics, UI Tooling Preview, Material3
- Navigation Compose: `androidx.navigation:navigation-compose:2.8.2`
- Carga de imágenes: `io.coil-kt:coil-compose:2.4.0`
- Testing:
	- JUnit 4: `junit:junit:4.13.2`
	- AndroidX Test JUnit: `androidx.test.ext:junit:1.3.0`
	- Espresso: `androidx.test.espresso:espresso-core:3.7.0`
	- Compose UI Test JUnit4 y Manifest

> Nota: Configuración basada en `gradle/libs.versions.toml` y `app/build.gradle.kts` del proyecto.

---

## ⚙️ Requisitos, Instalación y Ejecución

### Requisitos
- Android Studio (versión reciente)
- JDK 11
- Android SDK 24+ (minSdk 24), compileSdk 36, targetSdk 36

### Pasos
1) Clona el repositorio y ábrelo en Android Studio.
2) Sincroniza Gradle y descarga dependencias.
3) Conecta un dispositivo o inicia un emulador.
4) Ejecuta la app desde el botón "Run" de Android Studio.

Opcional por terminal (Linux/macOS):

```bash
./gradlew assembleDebug
./gradlew installDebug
```

---

## 🧭 Estructura del Proyecto (MVVM)

```
app/src/main/java/com/proyecto/travelmarket/
├── data/
├── model/
│   ├── Evento.kt
│   ├── Lugar.kt
│   ├── Restaurante.kt
│   ├── Transporte.kt
│   └── User.kt
├── navigation/
│   ├── Screen.kt
│   ├── BottomNavItem.kt
│   └── NavGraph.kt
├── ui/
│   ├── MainScreen.kt
│   ├── components/
│   │   ├── LoginScreen.kt
│   │   ├── BottomNavigationBar.kt
│   │   ├── FavoritosScreen.kt
│   │   ├── PerfilScreen.kt
│   │   ├── RegisterScreen.kt
│   │   └── WelcomeScreen.kt
│   └── screens/
│       ├── HomeScreen.kt
│       ├── LugaresScreen.kt
│       ├── EventosScreen.kt
│       ├── GastronomiaScreen.kt
│       ├── TransporteScreen.kt
│       └── DetalleScreen.kt
├── viewmodel/
│   ├── AuthViewModel.kt
│   ├── AuthViewModelFactory.kt
│   ├── DetalleViewModel.kt
│   ├── EventosViewModel.kt
│   ├── FavoritosViewModel.kt
│   ├── GastronomiaViewModel.kt
│   ├── HomeViewModel.kt
│   ├── LugaresViewModel.kt
│   └── TransporteViewModel.kt
└── MainActivity.kt

```

> La app utiliza Navigation Compose para la gestión de rutas y Jetpack Compose para la UI.

---

## 🎨 Prototipo (Figma)

- Enlace: https://www.figma.com/design/iXDlfKNys1IFMhit5DE8yc/TravelMarket?node-id=0-1&p=f

---
## Enlace del Video

-Enlace: https://drive.google.com/drive/folders/1pIltqhOZbnY02HQ9REAhI0c8WdYQcFPO?usp=sharing

## 🧩 Roadmap

- Favoritos y listas personalizadas
- Integración con Google Maps (mapa y rutas)
- Búsqueda avanzada y filtros por categoría
- Modo offline (caché local)
- Inicio de sesión con Google/Redes sociales
- Notificaciones locales para recordatorios de eventos
- Internacionalización (ES/EN)

---

## 🤝 Cómo Contribuir

1) Haz un fork del repositorio
2) Crea una rama feature: `git checkout -b feature/nombre`
3) Realiza tus cambios con buenas prácticas (Kotlin/Compose)
4) Abre un Pull Request hacia la rama correspondiente

Sugerencias: incluye descripción clara, capturas de pantalla y, si aplica, tests.

---

## 📝 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE si está disponible en el repositorio.

---

## 👥 Créditos

- Rodrigo Salva — Liderazgo técnico y configuración
- Rafael Chuco — Diseño UI/UX (Figma)
- Miguel Carasas — QA y documentación


