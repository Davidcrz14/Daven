# DAVEN - Reproductor de Video

**DAVEN** está diseñado para ser la aplicación de video predeterminada en tu Redmi 11 Pro 5G, priorizando la velocidad y la experiencia sin interrupciones.

## 🚀 Características Principales

| Función | Descripción | Estado |
|---------|-------------|--------|
| **Gestor de Videos Local** | Escaneo rápido del almacenamiento para listar todos los archivos de video (.mp4, .mkv, etc.) con su título y duración | ✅ Implementado |
| **Reproducción de Alto Rendimiento** | Utiliza ExoPlayer (Media3) para una decodificación de video eficiente, soporte amplio de formatos y reproducción fluida, incluso en alta resolución | ✅ Implementado |
| **Control de Ciclo de Vida** | Pausa y reanuda automáticamente la reproducción al salir o volver a la aplicación, conservando batería y recursos | ✅ Implementado |
| **Navegación Intuitiva** | Permite pasar fácilmente de la lista de archivos al reproductor y viceversa con un botón de retroceso claro | ✅ Implementado |
| **Controles Modernos** | Interfaz de control superpuesta simple, basada en los componentes de ExoPlayer, sin publicidad | ✅ Implementado |
| **Rotación de Pantalla** | Botón para cambiar entre orientación vertical y horizontal | ✅ Implementado |
| **Interfaz Moderna Minimalista** | Diseño limpio con tonos azules pastel oscuros y Material Design 3 | ✅ Implementado |
| **Filtros por Chips** | Sistema de filtros modernos por categorías (Todos, Recientes, Carpetas) | ✅ Implementado |
| **Detección Automática de Orientación** | El reproductor detecta si el video es vertical u horizontal | ✅ Implementado |
| **Reproducción de Audio en Segundo Plano** | Escucha el audio del video con la pantalla apagada | ✅ Implementado |
| **Repetir Video/Bucle** | Reproduce el mismo video continuamente | ✅ Implementado |
| **Gestos Táctiles** | Deslizar para ajustar volumen, brillo, avanzar/retroceder | ✅ Implementado |
| **Zoom y Recorte** | Ajusta el aspecto del video (16:9, 4:3, llenar pantalla, ajustar) | ✅ Implementado |
| **Presentación Animada** | Splash screen con animación de entrada | ✅ Implementado |
| **Aplicación Predeterminada** | Configurable como reproductor de video predeterminado del sistema | ✅ Implementado |
| **Lista Vertical Fija** | Todos los videos se muestran en orientación vertical en la lista | ✅ Implementado |

## 🛠️ Tecnologías

- **Lenguaje:** Kotlin (Moderno y seguro)
- **Reproductor:** ExoPlayer (Media3)
- **Plataforma:** Android (Optimizado para Redmi 11 Pro 5G)
- **UI:** Material Design 3 con elementos personalizados
- **Arquitectura:** MVVM con StateFlow y Coroutines

## 📱 Características de la Interfaz

- **Diseño minimalista moderno** con tonos azules pastel oscuros
- **Material Design 3** con componentes nativos de Android
- **Chips de filtro** para navegación intuitiva
- **Cards elevadas** con bordes sutiles y sombras suaves
- **Thumbnails grandes** con overlay gradient y botón de play glassmorphism
- **Iconos con tint dinámico** que se adaptan al tema
- **Tipografía moderna** con sans-serif-medium
- **Animaciones fluidas** y transiciones suaves
- **Controles del reproductor minimalistas** con botones semi-transparentes
- **Sistema de favoritos** con indicador visual
- **Detección automática de orientación** del video
- **Interfaz responsive** optimizada para diferentes tamaños de pantalla
- **Gestos táctiles intuitivos** para control de volumen, brillo y navegación
- **Zoom y ajuste de aspecto** con múltiples opciones de visualización
- **Splash screen animado** con transición fluida
- **Reproducción en segundo plano** con controles de notificación
- **Modo bucle** para reproducción continua

## 🎯 Objetivos del Proyecto

- Proporcionar una experiencia de reproducción de video sin interrupciones
- Optimizar el rendimiento para dispositivos Redmi
- Mantener una interfaz simple y funcional
- Soporte completo para múltiples formatos de video

## 📋 Formatos Soportados

- MP4
- MKV
- AVI
- MOV
- Y otros formatos compatibles con ExoPlayer

## 🔧 Instalación y Configuración

1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza las dependencias de Gradle
4. Ejecuta la aplicación en tu dispositivo Android

## 📁 Estructura del Proyecto

```
app/
├── src/main/java/com/daven/videoplayer/
│   ├── model/          # Modelos de datos
│   ├── repository/     # Repositorios para acceso a datos
│   ├── ui/            # Activities y UI
│   ├── viewmodel/     # ViewModels
│   └── adapter/       # Adaptadores de RecyclerView
├── src/main/res/
│   ├── layout/        # Layouts XML
│   ├── drawable/      # Recursos gráficos
│   ├── values/        # Colores, strings, temas
│   └── xml/           # Configuraciones XML
```

## 🔮 Próximas Funcionalidades

### 🎬 Reproducción Avanzada
- **Controles de velocidad de reproducción** - 0.25x, 0.5x, 0.75x, 1x, 1.25x, 1.5x, 1.75x, 2x
- **Modo PiP (Picture in Picture)** - Mira videos mientras usas otras apps
- **Saltar intro/outro** - Botones para saltar segmentos predefinidos
- **Marcadores de tiempo** - Guarda puntos específicos del video

### 📝 Subtítulos y Audio
- **Soporte de subtítulos** - SRT, ASS, VTT, SUB
- **Búsqueda automática de subtítulos** - Descarga desde OpenSubtitles
- **Ajuste de sincronización** - Adelantar/atrasar subtítulos
- **Personalización de subtítulos** - Tamaño, color, fuente, posición
- **Múltiples pistas de audio** - Cambiar entre idiomas disponibles
- **Ecualizador de audio** - Ajusta graves, medios y agudos

### 🎨 Personalización Visual
- **Temas personalizables** - Claro, oscuro, AMOLED, colores personalizados
- **Filtros de video** - Brillo, contraste, saturación, temperatura de color
- **Modo nocturno** - Reduce el brillo automáticamente

### 📊 Gestión y Organización
- **Historial de reproducción** - Ve qué videos has visto recientemente
- **Reanudar reproducción** - Continúa desde donde lo dejaste
- **Listas de reproducción personalizadas** - Crea y organiza tus colecciones
- **Favoritos con categorías** - Organiza por géneros, series, etc.
- **Búsqueda avanzada** - Por nombre, duración, tamaño, fecha, carpeta
- **Ordenar videos** - Por nombre, fecha, tamaño, duración, más vistos
- **Estadísticas** - Tiempo total visto, videos más reproducidos

### 🔒 Privacidad y Seguridad
- **Carpeta oculta/privada** - Protege videos con contraseña o huella dactilar
- **Modo incógnito** - No guardar en historial
- **Bloqueo de aplicación** - PIN, patrón o biométrico

### 🌐 Funciones de Red
- **Streaming desde URL** - Reproduce videos de internet
- **Chromecast/DLNA** - Transmite a TV o dispositivos compatibles
- **Compartir con código QR** - Comparte videos localmente
- **Servidor local** - Accede a tus videos desde otros dispositivos

### 🎯 Funciones Inteligentes
- **Detección automática de episodios** - Agrupa series automáticamente
- **Reproducción automática** - Siguiente episodio sin intervención
- **Recomendaciones** - Basadas en tu historial
- **Miniaturas animadas** - Preview al deslizar la barra de progreso
- **Captura de pantalla** - Guarda fotogramas del video
- **Recortar video** - Crea clips cortos desde la app

### ⚡ Optimización y Rendimiento
- **Decodificación por hardware** - Usa GPU para mejor rendimiento
- **Caché inteligente** - Precarga siguiente video en playlist
- **Modo ahorro de batería** - Reduce calidad para extender duración
- **Reproducción sin conexión** - Descarga para ver después
- **Compresión de video** - Reduce tamaño sin perder mucha calidad

### 🎵 Funciones Multimedia
- **Extractor de audio** - Convierte video a MP3
- **Editor básico** - Recortar, rotar, ajustar velocidad
- **Fusionar videos** - Combina múltiples archivos
- **Convertidor de formato** - MP4, MKV, AVI, etc.

### 📱 Integración del Sistema
- **Widget de pantalla de inicio** - Acceso rápido a videos recientes
- **Accesos directos** - Atajos a carpetas o playlists
- **Compartir desde otras apps** - Abre videos compartidos directamente
