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
<img width="379" height="840" alt="image" src="https://github.com/user-attachments/assets/64a0279b-6057-4533-8615-5f8d75de5894" />


### 🔐 Sistema de Autenticación
- Pantalla de Login con validación
- Opción "Recordarme"
- Integración futura con redes sociales


<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/3e34d67e-d439-4de3-b5ab-931426866939" />


### 🏠 Pantalla de Inicio
- Banner de bienvenida personalizado
- Sección de elementos destacados con mejor valoración
- Navegación rápida a todas las categorías
  <img width="399" height="833" alt="image" src="https://github.com/user-attachments/assets/4356d4a5-342e-4c5a-8afc-4f3bd3711991" />

  <img width="373" height="826" alt="image" src="https://github.com/user-attachments/assets/78298aa2-efb6-4f8d-a6a3-3c316b7edad2" />

### 🗺️ Lugares Turísticos
- Machu Picchu, Malecón de Miraflores, Plaza Mayor de Lima
- Información detallada: ubicación, categoría, horario, precio
- Sistema de calificación con estrellas
  <img width="373" height="826" alt="image" src="https://github.com/user-attachments/assets/6ed4e784-1b7a-4ebe-b38b-56ed59ba4ea0" />

### 🏆 Eventos Deportivos
- Calendario de eventos Panamericanos
- Atletismo, Ciclismo de ruta, Vóley
- Información de estadios, fechas, horarios y precios de entradas
<img width="385" height="823" alt="image" src="https://github.com/user-attachments/assets/e5ac8e71-cc3a-4483-9b6e-03ac354ec34e" />

### 🍽️ Gastronomía Peruana
- Restaurantes destacados: La Granja Azul, Maido, Central
- Tipos de cocina, especialidades y horarios
- Precios promedios y ubicaciones
<img width="386" height="821" alt="image" src="https://github.com/user-attachments/assets/da896d27-7cb2-4ec0-99cc-2249d0a3ab5a" />

### 🚌 Transporte
- LATAM Airlines Perú, Metropolitano, Mirabus
- Rutas, horarios y tarifas
- Información de acceso y ubicaciones
<img width="383" height="831" alt="image" src="https://github.com/user-attachments/assets/89971193-cf95-4107-8f6d-f26ae9a89f22" />

### 📄 Pantalla de Detalle
- Información completa de cada elemento seleccionado
- Descripciones extendidas y datos específicos
- Navegación intuitiva con botón de retorno
<img width="348" height="720" alt="image" src="https://github.com/user-attachments/assets/be57372b-5606-4d6b-ba4a-1db848cdff1e" />


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



