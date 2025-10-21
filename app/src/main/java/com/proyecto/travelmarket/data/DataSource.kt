package com.proyecto.travelmarket.data

import com.proyecto.travelmarket.model.*

object DataSource {
    
    // ========== LUGARES TURÍSTICOS ==========
    val lugares = listOf(
        Lugar(
            id = 1,
            nombre = "Machu Picchu",
            descripcion = "La ciudadela Inca más famosa del mundo, Patrimonio de la Humanidad y una de las Siete Maravillas del Mundo",
            descripcionDetallada = "Machu Picchu es una ciudadela inca situada en lo alto de las montañas de los Andes peruanos, construida en el siglo XV. Este impresionante sitio arqueológico es considerado una obra maestra de la arquitectura inca y ofrece vistas espectaculares del Valle Sagrado. La combinación perfecta de ingeniería, naturaleza y misticismo hace de Machu Picchu un destino imperdible para cualquier visitante de Perú.",
            ubicacion = "Aguas Calientes, Cusco",
            precio = 152,
            rating = 5.0,
            imagen = "machu_picchu",
            categoria = "Sitio Arqueológico",
            horario = "6:00 AM - 5:30 PM",
            destacado = true
        ),
        Lugar(
            id = 2,
            nombre = "Malecón de Miraflores",
            descripcion = "Impresionante malecón con vistas al Océano Pacífico. Ideal para pasear, hacer parapente y disfrutar de la gastronomía limeña",
            descripcionDetallada = "El Malecón de Miraflores ofrece una vista panorámica espectacular del Océano Pacífico. Es el lugar perfecto para caminatas románticas, ciclismo, y actividades extremas como parapente. Con sus parques bien cuidados, zonas de descanso y opciones gastronómicas, el malecón es un destino favorito tanto para locales como turistas. Los atardeceres aquí son absolutamente inolvidables.",
            ubicacion = "Circuito Costa Verde - Lima",
            precio = 54,
            rating = 4.8,
            imagen = "malecon_miraflores",
            categoria = "Espacio Público",
            horario = "24 horas",
            destacado = true
        ),
        Lugar(
            id = 3,
            nombre = "Plaza Mayor de Lima",
            descripcion = "Corazón del Centro Histórico de Lima, Patrimonio de la Humanidad. Rodeada del Palacio de Gobierno",
            descripcionDetallada = "La Plaza Mayor, también conocida como Plaza de Armas, es el punto de fundación de Lima y el corazón de su centro histórico. Rodeada por importantes edificios coloniales como el Palacio de Gobierno, la Catedral de Lima y el Palacio Arzobispal, esta plaza es testigo de más de 400 años de historia peruana. El cambio de guardia del Palacio de Gobierno es un espectáculo que no te puedes perder.",
            ubicacion = "Jr. de la Unión, Cercado de Lima",
            precio = 0,
            rating = 4.8,
            imagen = "plaza_mayor",
            categoria = "Centro Histórico",
            horario = "24 horas",
            destacado = true
        )
    )
    
    // ========== EVENTOS DEPORTIVOS ==========
    val eventos = listOf(
        Evento(
            id = 1,
            nombre = "Atletismo",
            descripcion = "Pruebas de velocidad, salto y lanzamiento que ponen a prueba fuerza y resistencia",
            descripcionDetallada = "El atletismo es la disciplina reina de los Juegos Panamericanos. Disfruta de emocionantes competencias de velocidad en los 100m, 200m y 400m, pruebas de medio fondo y fondo, saltos de altura y longitud, lanzamiento de jabalina, disco y martillo. Los mejores atletas del continente americano se dan cita en el Estadio Nacional para demostrar su poderío físico y técnico.",
            deporte = "Atletismo",
            ubicacion = "Lima",
            estadio = "Estadio Nacional del Perú",
            precio = 50,
            rating = 4.8,
            imagen = "atletismo",
            fecha = "23-30 Julio 2025",
            hora = "9:00 AM - 8:00 PM",
            destacado = true
        ),
        Evento(
            id = 2,
            nombre = "Ciclismo de ruta",
            descripcion = "Recorrido en carretera que combina velocidad, resistencia y estrategia",
            descripcionDetallada = "La prueba de ciclismo de ruta recorre las mejores vistas de Lima, pasando por el Malecón de Miraflores, Costa Verde y rutas estratégicas de la ciudad. Los ciclistas profesionales más destacados del continente competirán en diferentes categorías: contrarreloj individual, prueba en ruta masculina y femenina. Una disciplina que combina estrategia de equipo, resistencia física y velocidad pura.",
            deporte = "Ciclismo",
            ubicacion = "Lima",
            estadio = "Circuito Costa Verde",
            precio = 54,
            rating = 4.8,
            imagen = "ciclismo",
            fecha = "26-28 Julio 2025",
            hora = "7:00 AM - 2:00 PM",
            destacado = true
        ),
        Evento(
            id = 3,
            nombre = "Vóley",
            descripcion = "Partidos de alta competencia entre los mejores equipos del continente",
            descripcionDetallada = "El voleibol es una de las disciplinas más populares en Perú. Los Juegos Panamericanos traen los mejores equipos de América para disputar el oro continental. Tanto en rama masculina como femenina, podrás disfrutar de partidos llenos de emoción, remates espectaculares y jugadas defensivas increíbles. El Polideportivo de Villa El Salvador será el escenario de estas intensas batallas deportivas.",
            deporte = "Voleibol",
            ubicacion = "Lima",
            estadio = "Jr. de la Unión - Cercado de Lima",
            precio = 50,
            rating = 4.8,
            imagen = "voley",
            fecha = "22-29 Julio 2025",
            hora = "10:00 AM - 9:00 PM",
            destacado = true
        )
    )
    
    // ========== RESTAURANTES ==========
    val restaurantes = listOf(
        Restaurante(
            id = 1,
            nombre = "La Granja Azul",
            descripcion = "Es una de las pollerías más emblemáticas del Perú. Se le reconoce por la receta clásica del pollo a la brasa",
            descripcionDetallada = "La Granja Azul es sinónimo de tradición y sabor en el Perú. Desde 1969, esta pollería familiar ha perfeccionado la receta del pollo a la brasa, convirtiéndose en un referente gastronómico nacional. Su pollo jugoso, dorado a la perfección y sazonado con especias secretas, se acompaña de papas fritas crujientes y ensaladas frescas. Un clásico que no puedes dejar de probar durante tu visita a Lima.",
            tipoCocina = "Pollería",
            ubicacion = "Santa Clara - Ate",
            precio = 45,
            rating = 5.0,
            imagen = "la_granja_azul",
            especialidad = "Pollo a la brasa con papas fritas",
            horario = "11:00 AM - 11:00 PM",
            destacado = true
        ),
        Restaurante(
            id = 2,
            nombre = "Maido",
            descripcion = "Maido, del chef Mitsuharu \"Micha\" Tsumura, fue elegido \"mejor restaurante del mundo\" en 2025",
            descripcionDetallada = "Maido representa la fusión perfecta entre la cocina peruana y japonesa, conocida como Nikkei. El chef Mitsuharu Tsumura ha llevado esta gastronomía a niveles internacionales, siendo reconocido como uno de los mejores restaurantes del mundo. Cada plato es una obra de arte que combina técnicas japonesas con ingredientes peruanos de alta calidad. Una experiencia culinaria única que desafía los sentidos.",
            tipoCocina = "Peruano-Japonesa",
            ubicacion = "Lima",
            precio = 320,
            rating = 4.8,
            imagen = "maido",
            especialidad = "Cocina Nikkei - Sushi y fusión",
            horario = "12:30 PM - 3:00 PM, 7:00 PM - 11:00 PM",
            destacado = true
        ),
        Restaurante(
            id = 3,
            nombre = "Central",
            descripcion = "Central es del chef Virgilio Martínez. Se caracteriza por explorar la biodiversidad del Perú",
            descripcionDetallada = "Central es más que un restaurante, es un viaje gastronómico a través de los diversos ecosistemas del Perú. El chef Virgilio Martínez y su equipo exploran ingredientes desde el nivel del mar hasta los 4,500 metros de altura. Cada plato representa un ecosistema diferente, combinando investigación, innovación y técnicas ancestrales. Una experiencia que va más allá del sabor, es una conexión profunda con la biodiversidad peruana.",
            tipoCocina = "Alta Cocina Peruana",
            ubicacion = "Lima",
            precio = 350,
            rating = 4.8,
            imagen = "central",
            especialidad = "Alta cocina con ingredientes endémicos",
            horario = "12:45 PM - 3:30 PM, 7:45 PM - 11:00 PM",
            destacado = true
        )
    )
    
    // ========== TRANSPORTES ==========
    val transportes = listOf(
        Transporte(
            id = 1,
            nombre = "LATAM Airlines Perú",
            descripcion = "Es una de las aerolíneas más importantes del país, con muchas rutas nacionales e internacionales",
            descripcionDetallada = "LATAM Airlines Perú es la principal aerolínea del país, ofreciendo conexiones a más de 145 destinos en América, Europa y Oceanía. Con una flota moderna y servicios de alta calidad, LATAM garantiza un viaje cómodo y seguro. Desde Lima, puedes volar a Cusco para visitar Machu Picchu, a Arequipa para conocer el Cañón del Colca, o conectar con vuelos internacionales a cualquier parte del mundo.",
            tipo = "Aerolínea",
            ubicacion = "Aeropuerto Jorge Chávez",
            ruta = "Nacional e Internacional desde Lima",
            precio = null,
            rating = 5.0,
            imagen = "latam",
            horario = "Vuelos 24/7 - Consultar horarios",
            destacado = true
        ),
        Transporte(
            id = 2,
            nombre = "Metropolitano",
            descripcion = "Sistema de buses de tránsito rápido (BRT) que recorre Lima de norte a sur",
            descripcionDetallada = "El Metropolitano es el sistema de transporte público más eficiente de Lima. Con carriles exclusivos y estaciones modernas, conecta los principales distritos de la ciudad desde el norte en Independencia hasta el sur en Chorrillos. Es la forma más rápida y económica de moverse por Lima, evitando el tráfico característico de la capital. Las tarjetas recargables facilitan el pago y puedes hacer transbordos sin costo adicional.",
            tipo = "Transporte Público",
            ubicacion = "Lima Metropolitana",
            ruta = "Norte a Sur por Vía Expresa",
            precio = 2,
            rating = 4.8,
            imagen = "metropolitano",
            horario = "5:00 AM - 10:00 PM",
            destacado = true
        ),
        Transporte(
            id = 3,
            nombre = "Mirabus - Bus Turístico",
            descripcion = "Autobús turístico de dos pisos que recorre los principales atractivos de Lima",
            descripcionDetallada = "Mirabus es la forma ideal de conocer Lima como turista. Este bus de dos pisos descapotable recorre los principales atractivos de la capital: Centro Histórico, Malecón de Miraflores, Parque del Amor, Larcomar, Huaca Pucllana y más. Con audioguías en múltiples idiomas y la posibilidad de subir y bajar en cualquier parada, Mirabus ofrece flexibilidad y comodidad. El piso superior descubierto te permite tomar las mejores fotos de Lima.",
            tipo = "Turístico",
            ubicacion = "Lima",
            ruta = "Circuito turístico - Centro a Miraflores",
            precio = 30,
            rating = 4.8,
            imagen = "mirabus",
            horario = "9:00 AM - 7:00 PM (Salidas cada hora)",
            destacado = true
        )
    )
    
    // ========== FUNCIÓN PARA OBTENER DESTACADOS ==========
    fun getDestacados(): List<Any> {
        val destacados = mutableListOf<Any>()
        destacados.addAll(lugares.filter { it.destacado })
        destacados.addAll(eventos.filter { it.destacado })
        destacados.addAll(restaurantes.filter { it.destacado })
        destacados.addAll(transportes.filter { it.destacado })
        return destacados.shuffled().take(6)
    }
}
